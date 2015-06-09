package com.aergonaut.lifeaquatic.item.tool

import cofh.api.block.IBlockDebug
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.ItemBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

class Lens extends ItemBase(Names.Items.Tool.Lens) {
  setMaxStackSize(1)

  override def onItemUseFirst(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    Option(world.getBlock(x, y, z)) match {
      case Some(block: IBlockDebug) => {
        block.debugBlock(world, x, y, z, ForgeDirection.UNKNOWN, player)
        true
      }
      case _ => false
    }
  }
}
