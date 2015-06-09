package com.aergonaut.lifeaquatic.item.tool

import cofh.lib.util.helpers.{BlockHelper, ItemHelper}
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.ItemBase
import com.aergonaut.lifeaquatic.util.ItemNBTHelper
import java.util
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.{ItemStack, Item}
import net.minecraft.util.EnumChatFormatting
import net.minecraft.world.World

class LavaPearl extends ItemBase(Names.Items.Tool.LavaPearl) {
  final val MaxEnergy = 10000

  final val SmeltingCost = 250
  final val SpawnLavaCost = 2000

  setMaxStackSize(1)
  setNoRepair()
  setMaxDamage(1000)

  override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]): Unit = {
    val l2 = list.asInstanceOf[util.List[ItemStack]]

    // empty
    val emptyPearl = ItemHelper.stack(item, 1, 1000)
    setEnergy(emptyPearl, 0)
    l2.add(emptyPearl)

    // full power
    val fullPower = ItemHelper.stack(item, 1, 1)
    setEnergy(fullPower, MaxEnergy)
    l2.add(fullPower)
  }

  override def getDamage(stack: ItemStack): Int = {
    val energy = getEnergy(stack)
    1000 - (energy / MaxEnergy.toFloat * 1000).round
  }

  override def getDisplayDamage(stack: ItemStack): Int = getDamage(stack)

  def setEnergy(stack: ItemStack, energy: Int): Unit = {
    stack.setItemDamage(getDamage(stack))
    ItemNBTHelper.setInt(stack, "energy", energy)
  }

  def getEnergy(stack: ItemStack): Int = ItemNBTHelper.getInt(stack, "energy")

  override def getContainerItem(stack: ItemStack): ItemStack = {
    val dup = stack.copy()
    dup.stackSize = 1
    val prevEnergy = getEnergy(stack)
    setEnergy(dup, 0.max(prevEnergy - SmeltingCost))
    dup
  }

  override def addInformation(stack: ItemStack, player: EntityPlayer, list: util.List[_], par4: Boolean): Unit ={
    val energy = getEnergy(stack)
    list.asInstanceOf[util.List[String]].add(s"${energy}/${MaxEnergy}")
  }

  override def getItemStackDisplayName(stack: ItemStack): String = {
    s"${EnumChatFormatting.RED}${EnumChatFormatting.ITALIC}${super.getItemStackDisplayName(stack)}"
  }

  override def onItemUseFirst(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    if (!world.isRemote) {
      val energy = getEnergy(stack)
      if (energy >= SpawnLavaCost) {
        val mop = BlockHelper.getCurrentMovingObjectPosition(player)
        val Array(xx, yy, zz) = BlockHelper.getAdjacentCoordinatesForSide(mop)

        if (world.getBlock(xx, yy, zz).isAir(world, xx, yy, zz)) {
          world.setBlock(xx, yy, zz, Blocks.flowing_lava, 0, 3)
          setEnergy(stack, energy - SpawnLavaCost)
          return true
        }
      }
    }
    false
  }
}
