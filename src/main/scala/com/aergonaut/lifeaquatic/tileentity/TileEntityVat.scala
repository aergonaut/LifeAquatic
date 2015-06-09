package com.aergonaut.lifeaquatic.tileentity

import java.util

import cofh.api.fluid.ITankContainerBucketable
import cofh.api.tileentity.ITileInfo
import cofh.lib.util.helpers.{FluidHelper, ItemHelper}
import com.aergonaut.lifeaquatic.recipe.VatRecipe
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}
import net.minecraft.util.{ChatComponentText, IChatComponent}
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.fluids._

class TileEntityVat extends TileEntityBase with ISidedInventory with ITankContainerBucketable with ITileInfo {
  var formed: Boolean = false

  // x, y, z
  var offset: Array[Int] = Array(0, 0, 0)

  var input: Option[ItemStack] = None
  var catalyst: Option[ItemStack] = None

  val waterTank: FluidTank = new FluidTank(12000)
  val outputTank: FluidTank = new FluidTank(12000)

  var maxProcessTime: Int = 1
  var processTime: Int = 0
  var currentRecipe: Option[VatRecipe] = None
  var activeCatalystAmount = 0

  def master: Option[TileEntityVat] = {
    if (!formed) return None

    if (offset(0) == 0 && offset(1) == 0 && offset(2) == 0) {
      return Some(this)
    }
    val xx = xCoord - offset(0)
    val yy = yCoord - offset(1)
    val zz = zCoord - offset(2)
    val tile = worldObj.getTileEntity(xx, yy, zz)
    if (tile.isInstanceOf[TileEntityVat]) Some(tile.asInstanceOf[TileEntityVat]) else None
  }

  override protected def readFromNBTCustom(nBTTagCompound: NBTTagCompound): Unit = {
    formed = nBTTagCompound.getBoolean("formed")
    offset = nBTTagCompound.getIntArray("offset")

    waterTank.readFromNBT(nBTTagCompound.getCompoundTag("waterTank"))
    outputTank.readFromNBT(nBTTagCompound.getCompoundTag("outputTank"))

    val currentRecipeTag = nBTTagCompound.getCompoundTag("currentRecipe")
    if (currentRecipeTag.hasKey("Empty")) {
      currentRecipe = None
    } else {
      currentRecipe = VatRecipe.loadRecipeFromNBT(currentRecipeTag)
    }

    processTime = nBTTagCompound.getInteger("processTime")
    maxProcessTime = nBTTagCompound.getInteger("maxProcessTime")
    activeCatalystAmount = nBTTagCompound.getInteger("activeCatalystAmount")
  }

  override def readFromNBT(nBTTagCompound: NBTTagCompound): Unit = {
    super.readFromNBT(nBTTagCompound)
    val invTagList = nBTTagCompound.getTagList("inventory", 10)
    for (i <- 0 until invTagList.tagCount()) {
      val tag = invTagList.getCompoundTagAt(i)
      val slot = tag.getByte("Slot").toInt
      slot match {
        case TileEntityVat.InputSlot => input = Some(ItemStack.loadItemStackFromNBT(tag))
        case TileEntityVat.CatalystSlot => catalyst = Some(ItemStack.loadItemStackFromNBT(tag))
        case _ => {}
      }
    }
  }

  override protected def writeToNBTCustom(nBTTagCompound: NBTTagCompound): Unit = {
    // multiblock formation and master positioning
    nBTTagCompound.setBoolean("formed", formed)
    nBTTagCompound.setIntArray("offset", offset)

    // tanks
    val waterTankTAg = waterTank.writeToNBT(new NBTTagCompound())
    nBTTagCompound.setTag("waterTank", waterTankTAg)
    val outputTankTag = outputTank.writeToNBT(new NBTTagCompound())
    nBTTagCompound.setTag("outputTank", outputTankTag)

    // current recipe
    val currentRecipeTag = new NBTTagCompound()
    if (currentRecipe.isEmpty) {
      currentRecipeTag.setString("None", "")
    } else {
      currentRecipe.get.writeToNBT(currentRecipeTag)
    }
    nBTTagCompound.setTag("currentRecipe", currentRecipeTag)

    // sync progress
    nBTTagCompound.setInteger("processTime", processTime)
    nBTTagCompound.setInteger("maxProcessTime", maxProcessTime)
    nBTTagCompound.setInteger("activeCatalystAmount", activeCatalystAmount)
  }

  override def writeToNBT(nBTTagCompound: NBTTagCompound): Unit = {
    super.writeToNBT(nBTTagCompound)
    val invTagList = new NBTTagList()

    if (input.nonEmpty) {
      val inputTag = new NBTTagCompound()
      inputTag.setByte("Slot", TileEntityVat.InputSlot.toByte)
      input.get.writeToNBT(inputTag)
      invTagList.appendTag(inputTag)
    }

    if (catalyst.nonEmpty) {
      val catalystTag = new NBTTagCompound()
      catalystTag.setByte("Slot", TileEntityVat.CatalystSlot.toByte)
      catalyst.get.writeToNBT(catalystTag)
      invTagList.appendTag(catalystTag)
    }

    nBTTagCompound.setTag("inventory", invTagList)
  }

  override def updateEntity(): Unit = {
    if (!worldObj.isRemote && master.nonEmpty && master.get == this) {
      var update = false
      if (processTime > 0) {
        if (input.isEmpty) {
          processTime = 0
          currentRecipe = None
        } else {
          processTime -= 1
        }
        update = true
      } else {
        if (currentRecipe.nonEmpty) {
          val recipe = currentRecipe.get

          // consume the input
          decrStackSize(TileEntityVat.InputSlot, 1)

          // consume the fuels
          waterTank.drain(recipe.waterCost, true)
          activeCatalystAmount -= recipe.catalystCost

          // produce the output
          outputTank.fill(recipe.output.copy(), true)

          // clear the recipe
          currentRecipe = None
          update = true
        } else {
          // search for a recipe with the current conditions
          currentRecipe = VatRecipe.findRecipeForInput(input, waterTank.getFluidAmount, activeCatalystAmount)
          if (currentRecipe.nonEmpty) {
            processTime = currentRecipe.get.processTime
            maxProcessTime = processTime
            update = true
          } else {
            // maybe we didn't have enough catalyst
            // pretend to consume one and try the search again
            if (catalyst.nonEmpty) {
              val catalystValue = TileEntityVat.CatalystMapping(catalyst.get.getItem)
              currentRecipe = VatRecipe.findRecipeForInput(input, waterTank.getFluidAmount, activeCatalystAmount + catalystValue)
              if (currentRecipe.nonEmpty) {
                // adding more catalyst did it, so actually consume one
                decrStackSize(TileEntityVat.CatalystSlot, 1)
                activeCatalystAmount += catalystValue
                processTime = currentRecipe.get.processTime
                maxProcessTime = processTime
                update = true
              }
            }
          }
        }
      }
      if (update) {
        markDirty()
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord)
      }
    }
  }

  def progress: Int = ((maxProcessTime - processTime) / maxProcessTime.toFloat).round

  // ISidedInventory

  override def canExtractItem(p_102008_1_ : Int, p_102008_2_ : ItemStack, p_102008_3_ : Int): Boolean = false

  override def canInsertItem(slot: Int, stack: ItemStack, side: Int): Boolean = {
    master.exists(_._canInsertItem(slot, stack, side))
  }

  def _canInsertItem(slot: Int, stack: ItemStack, side: Int): Boolean = {
    isItemValidForSlot(slot, stack)
  }

  override def getAccessibleSlotsFromSide(p_94128_1_ : Int): Array[Int] = Array(TileEntityVat.InputSlot, TileEntityVat.CatalystSlot)

  override def decrStackSize(p_70298_1_ : Int, p_70298_2_ : Int): ItemStack = {
    master.flatMap(_._decrStackSize(p_70298_1_, p_70298_2_)).orNull
  }

  def _decrStackSize(slot: Int, amount: Int): Option[ItemStack] = {
    val stack = Option(getStackInSlot(slot))
    stack.map(s => {
      if (s.stackSize <= amount) {
        setInventorySlotContents(slot, null)
        s
      } else {
        val newStack = s.splitStack(amount)
        if (newStack.stackSize == 0) setInventorySlotContents(slot, null)
        newStack
      }
    })
  }

  override def closeInventory(): Unit = {}

  override def getSizeInventory: Int = if (formed) 2 else 0

  override def getInventoryStackLimit: Int = 64

  override def isItemValidForSlot(p_94041_1_ : Int, p_94041_2_ : ItemStack): Boolean = p_94041_1_ match {
    case TileEntityVat.InputSlot => ItemHelper.isOre(p_94041_2_)
    case TileEntityVat.CatalystSlot => TileEntityVat.Catalysts(p_94041_2_.getItem)
  }

  override def getStackInSlotOnClosing(p_70304_1_ : Int): ItemStack = {
    master.flatMap(_._getStackInSlotOnClosing(p_70304_1_)).orNull
  }

  def _getStackInSlotOnClosing(slot: Int): Option[ItemStack] = {
    val stack = Option(getStackInSlot(slot))
    if (stack.nonEmpty) setInventorySlotContents(slot, null)
    stack
  }

  override def openInventory(): Unit = {}

  override def setInventorySlotContents(p_70299_1_ : Int, p_70299_2_ : ItemStack): Unit = {
    master.foreach(_._setInventorySlotContents(p_70299_1_, Option(p_70299_2_)))
  }

  def _setInventorySlotContents(slot: Int, stack: Option[ItemStack]): Unit = slot match {
    case TileEntityVat.InputSlot => input = _processStackAndInsert(stack)
    case TileEntityVat.CatalystSlot => catalyst = _processStackAndInsert(stack)
  }

  def _processStackAndInsert(stack: Option[ItemStack]): Option[ItemStack] = stack.map(s => {
    if (s.stackSize > getInventoryStackLimit) s.stackSize = getInventoryStackLimit
    s
  })

  override def isUseableByPlayer(p_70300_1_ : EntityPlayer): Boolean = true

  override def getStackInSlot(p_70301_1_ : Int): ItemStack = master.flatMap(_._getStackInSlot(p_70301_1_)).orNull

  private def _getStackInSlot(slot: Int): Option[ItemStack] = slot match {
    case TileEntityVat.InputSlot => input
    case TileEntityVat.CatalystSlot => catalyst
  }

  override def hasCustomInventoryName: Boolean = false

  override def getInventoryName: String = "LifeAquaticVat"

  // ITankContainerBucketable

  override def allowBucketFill(stack: ItemStack): Boolean = {
    if (!FluidContainerRegistry.isBucket(stack)) return false
    val fluid = Option(FluidHelper.getFluidForFilledItem(stack))
    fluid.exists(fl => FluidHelper.isFluidEqual(fl, FluidHelper.WATER))
  }

  override def allowBucketDrain(stack: ItemStack): Boolean = false

  override def drain(from: ForgeDirection, resource: FluidStack, doDrain: Boolean): FluidStack = drain(from, resource.amount, doDrain)

  override def drain(from: ForgeDirection, maxDrain: Int, doDrain: Boolean): FluidStack = master.flatMap(_._drain(from, maxDrain, doDrain)).orNull

  def _drain(from: ForgeDirection, maxDrain: Int, doDrain: Boolean): Option[FluidStack] = Option(outputTank.drain(maxDrain, doDrain))

  override def canFill(from: ForgeDirection, fluid: Fluid): Boolean = FluidHelper.isFluidEqualOrNull(fluid, FluidHelper.WATER)

  override def canDrain(from: ForgeDirection, fluid: Fluid): Boolean = true

  override def fill(from: ForgeDirection, resource: FluidStack, doFill: Boolean): Int = master.map(_._fill(from, resource, doFill)).getOrElse(0)

  def _fill(from: ForgeDirection, resource: FluidStack, doFill: Boolean): Int = waterTank.fill(resource, doFill)

  override def getTankInfo(from: ForgeDirection): Array[FluidTankInfo] = master.map(_._getTankInfo(from)).getOrElse(Array())

  def _getTankInfo(from: ForgeDirection): Array[FluidTankInfo] = Array(waterTank.getInfo, outputTank.getInfo)

  override def getTileInfo(info: util.List[IChatComponent], side: ForgeDirection, player: EntityPlayer, debug: Boolean): Unit = {
    if (!formed) return
    info.add(new ChatComponentText("Available recipes:"))
  }
}

object TileEntityVat {
  final val InputSlot = 0
  final val CatalystSlot = 1

  final val Catalysts = Set(Items.gunpowder)

  final val CatalystMapping: Map[Item, Int] = Map((Items.gunpowder -> 2000))
}
