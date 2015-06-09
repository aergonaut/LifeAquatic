package com.aergonaut.lib.multiblock

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lifeaquatic.item.tool.Wrench
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

trait TMultiBlock {
  val structure: Array[Array[Array[ItemStack]]]

  val width: Int
  val height: Int
  val length: Int

  def isBlockTrigger(block: Block, meta: Int): Boolean

  def createStructure_Impl(world: World, x: Int, y: Int, z: Int, side: Int, player: EntityPlayer): Boolean = ???

  final def createStructure(world: World, x: Int, y: Int, z: Int, side: Int, player: EntityPlayer): Boolean = {
    val ret = createStructure_Impl(world, x, y, z, side, player)
    if (ret) {
      val heldItem = Option(player.getHeldItem).map(stack => stack.getItem)
      if (heldItem.nonEmpty) {
        val theItem = heldItem.get
        if (theItem.isInstanceOf[Wrench]) {
          theItem.asInstanceOf[Wrench].toolUsed(ItemHelper.stack(theItem), player, x, y, z)
        }
      }
    }
    ret
  }
}
