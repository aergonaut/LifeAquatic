package com.aergonaut.lifeaquatic.tileentity

import java.util

import cofh.api.tileentity.ITileInfo
import cofh.lib.util.helpers.{ItemHelper, ServerHelper}
import com.aergonaut.lib.block.BlockSide
import com.aergonaut.lifeaquatic.constants.Constants
import com.aergonaut.lifeaquatic.recipe.{CalcinatorRecipe, CalcinatorRecipes}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.{NBTTagList, NBTTagCompound}
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.{ChatComponentText, IChatComponent}
import net.minecraftforge.common.util.ForgeDirection

import scala.collection.mutable

class TileEntityCalcinator extends TileEntityBase with ITileInfo with ISidedInventory {
  var formed: Boolean = false

  var masterCoords: Option[(Int, Int, Int)] = None

  val slots: mutable.ArrayBuffer[Option[ItemStack]] = mutable.ArrayBuffer(
    None, // 0: input slot
    None, // 1: fuel slot
    None  // 2: output slot
  )

  var burnTime = 0
  var lastBurnTime = 0
  var cookTime = 0

  final val CookTimeRequired = 150

  override def updateEntity(): Unit = {
    if (ServerHelper.isServerWorld(worldObj) && formed && isMaster()) {
      var doUpdate = false

      if (canSmelt()) {
        if (burnTime == 0) {
          slots(TileEntityCalcinator.FuelSlot).foreach(fuelStack => {
            burnTime += getItemBurnTime(fuelStack)
            lastBurnTime = burnTime
            fuelStack.stackSize -= 1
            slots(TileEntityCalcinator.FuelSlot) = Option(fuelStack.getItem.getContainerItem(fuelStack))
            doUpdate = true
          })
        }

        if (burnTime > 0) {
          slots(TileEntityCalcinator.InputSlot) match {
            case None => {
              cookTime = 0
            }

            case Some(inputStack: ItemStack) => {
              if (cookTime < CookTimeRequired) {
                burnTime -= 1
                cookTime += 1
              } else {
                currentRecipe().foreach(recipe => {
                  val output = recipe.output.copy()
                  decrStackSize(TileEntityCalcinator.InputSlot, 1)
                  // we have already validated that the output slot is capable of accepting the recipe's output,
                  // either because it is empty or because it contains a stack of the same item < 63 big
                  // so this code proceeds under the assumption that we can just shove stuff into the slot
                  slots(TileEntityCalcinator.OutputSlot) = slots(TileEntityCalcinator.OutputSlot).map(outputStack => {
                    outputStack.stackSize += output.stackSize
                    outputStack
                  }).orElse(Some(output))
                })
                cookTime = 0
              }
            }
          }
          doUpdate = true
        }
      } else {
        cookTime = 0
        doUpdate = true
      }

      if (doUpdate) {
        markDirty()
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord)
      }
    }
  }

  private def canSmelt(): Boolean = {
    if (currentRecipe().isEmpty) return false

    val noFuel = (for {
      fuelStack <- fuel
      if getItemBurnTime(fuelStack) > 0
    } yield fuelStack).isEmpty
    if (noFuel && burnTime == 0) return false

    val outputIncompatible = (for {
      recipe <- currentRecipe()
      outputStack <- output
      if outputStack.isItemEqual(recipe.output)
    } yield recipe).isEmpty
    if (output.nonEmpty && outputIncompatible) return false

    true
  }

  private def currentRecipe(): Option[CalcinatorRecipe] = for {
    input <- slots(TileEntityCalcinator.InputSlot)
    recipe <- CalcinatorRecipes.findRecipe(input)
  } yield recipe

  def input: Option[ItemStack] = slots(TileEntityCalcinator.InputSlot)

  def fuel: Option[ItemStack] = slots(TileEntityCalcinator.FuelSlot)

  def output: Option[ItemStack] = slots(TileEntityCalcinator.OutputSlot)

  def master: Option[TileEntityCalcinator] = for {
    (x, y, z) <- masterCoords
    tile <- Option(worldObj.getTileEntity(x, y, z))
    if tile.isInstanceOf[TileEntityCalcinator]
  } yield tile.asInstanceOf[TileEntityCalcinator]

  def isMaster(): Boolean = masterCoords.exists(_ == (xCoord, yCoord, zCoord))


  override protected def readFromNBTCustom(nBTTagCompound: NBTTagCompound): Unit = {
    formed = nBTTagCompound.getBoolean("formed")

    burnTime = nBTTagCompound.getInteger("burnTime")
    cookTime = nBTTagCompound.getInteger("cookTime")
    lastBurnTime = nBTTagCompound.getInteger("lastBurnTime")

    val masterPresent = nBTTagCompound.getBoolean("masterPresent")
    if (masterPresent) {
      val Array(x, y, z) = nBTTagCompound.getIntArray("masterCoords")
      masterCoords = Some(x, y, z)
    } else {
      masterCoords = None
    }
  }

  // NBT methods

  override def readFromNBT(nBTTagCompound: NBTTagCompound): Unit = {
    super.readFromNBT(nBTTagCompound)

    // drop whatever's in the slots currently since we're gonna replace it with the NBT data anyway
    slots.clear()
    slots ++= Array(None, None, None)

    val slotsTag = nBTTagCompound.getTagList("inventory", 10)
    for (i <- 0 until slotsTag.tagCount()) {
      val slotTag = slotsTag.getCompoundTagAt(i)
      val index = slotTag.getByte("Slot").toInt
      if (index >= 0 && index < slots.size) {
        slots(index) = Some(ItemStack.loadItemStackFromNBT(slotTag))
      }
    }
  }

  override protected def writeToNBTCustom(nBTTagCompound: NBTTagCompound): Unit = {
    nBTTagCompound.setBoolean("formed", formed)

    nBTTagCompound.setInteger("burnTime", burnTime)
    nBTTagCompound.setInteger("cookTime", cookTime)
    nBTTagCompound.setInteger("lastBurnTime", lastBurnTime)

    val masterPresent = masterCoords.nonEmpty
    nBTTagCompound.setBoolean("masterPresent", masterPresent)
    val master = masterCoords match {
      case Some((x, y, z)) => Array(x, y, z)
      case None => Array(0, 0, 0)
    }
    nBTTagCompound.setIntArray("masterCoords", master)
  }

  override def writeToNBT(nBTTagCompound: NBTTagCompound): Unit = {
    super.writeToNBT(nBTTagCompound)

    val slotsTag = new NBTTagList()
    for (i <- slots.indices) {
      slots(i).foreach(stack => {
        val slotTag = new NBTTagCompound()
        slotTag.setByte("Slot", i.toByte)
        stack.writeToNBT(slotTag)
        slotsTag.appendTag(slotTag)
      })
    }
    nBTTagCompound.setTag("inventory", slotsTag)
  }

  override def getTileInfo(info: util.List[IChatComponent], side: ForgeDirection, player: EntityPlayer, debug: Boolean): Unit = {
    info.add(new ChatComponentText(s"Tile at ${xCoord}, ${yCoord}, ${zCoord} reporting formed: ${formed}"))
    if (formed) {
      info.add(new ChatComponentText("Multiblock formed!"))
      info.add(new ChatComponentText(s"Master block at ${masterCoords}"))
    } else {
      info.add(new ChatComponentText("Multiblock not formed"))
    }
  }

  def getItemBurnTime(item: ItemStack): Int = TileEntityFurnace.getItemBurnTime(item)

  // ISidedInventory

  // the _underscore methods are meant to only be called on the master block

  override def canExtractItem(slot: Int, stack: ItemStack, side: Int): Boolean = slot == TileEntityCalcinator.OutputSlot

  override def canInsertItem(slot: Int, stack: ItemStack, side: Int): Boolean = false

  override def getAccessibleSlotsFromSide(slot: Int): Array[Int] = slot match {
    case BlockSide.Bottom => Array(TileEntityCalcinator.FuelSlot, TileEntityCalcinator.OutputSlot)
    case BlockSide.Top => Array(TileEntityCalcinator.InputSlot, TileEntityCalcinator.OutputSlot)
    case _ => Array(TileEntityCalcinator.OutputSlot)
  }

  override def decrStackSize(p_70298_1_ : Int, p_70298_2_ : Int): ItemStack = master.flatMap(_._decrStackSize(p_70298_1_, p_70298_2_)).orNull

  private def _decrStackSize(slot: Int, amount: Int): Option[ItemStack] = {
    _getStackInSlot(slot).map(stack => {
      if (stack.stackSize <= amount) {
        setInventorySlotContents(slot, null)
        stack
      } else {
        val newStack = stack.splitStack(amount)
        if (newStack.stackSize == 0) setInventorySlotContents(slot, null)
        newStack
      }
    })
  }

  override def closeInventory(): Unit = {}

  override def getSizeInventory: Int = if (formed) 3 else 0

  override def getInventoryStackLimit: Int = 64

  override def isItemValidForSlot(slot: Int, stack: ItemStack): Boolean = slot match {
    case TileEntityCalcinator.FuelSlot => getItemBurnTime(stack) > 0
    case TileEntityCalcinator.InputSlot => CalcinatorRecipes.findRecipe(stack).nonEmpty
    case _ => false
  }

  override def getStackInSlotOnClosing(p_70304_1_ : Int): ItemStack = master.flatMap(_._getStackInSlotOnClosing(p_70304_1_)).orNull

  private def _getStackInSlotOnClosing(slot: Int): Option[ItemStack] = {
    val stack = slots(slot)
    if (stack.nonEmpty) setInventorySlotContents(slot, null)
    stack
  }

  override def openInventory(): Unit = {}

  override def setInventorySlotContents(p_70299_1_ : Int, p_70299_2_ : ItemStack): Unit = {
    master.foreach(_._setInventorySlotContents(p_70299_1_, Option(p_70299_2_)))
  }

  private def _setInventorySlotContents(slot: Int, stack: Option[ItemStack]): Unit ={
    slots(slot) = stack.map(stack => {
      if (stack.stackSize > getInventoryStackLimit) stack.stackSize = getInventoryStackLimit
      stack
    })
  }

  override def isUseableByPlayer(p_70300_1_ : EntityPlayer): Boolean = true

  override def getStackInSlot(p_70301_1_ : Int): ItemStack = master.flatMap(_._getStackInSlot(p_70301_1_)).orNull

  private def _getStackInSlot(slot: Int): Option[ItemStack] = slots(slot)

  override def hasCustomInventoryName: Boolean = false

  override def getInventoryName: String = s"${Constants.ModID}:calcinator"
}

object TileEntityCalcinator {
  final val InputSlot: Int = 0
  final val FuelSlot: Int = 1
  final val OutputSlot: Int = 2
}
