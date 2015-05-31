package com.aergonaut.lifeaquatic.item.tool

import com.aergonaut.lifeaquatic.LifeAquatic
import com.aergonaut.lifeaquatic.constants.{Guis, Names}
import com.aergonaut.lifeaquatic.item.ItemBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ToolManual extends ItemBase(Names.Items.Tools.Manual) {
  override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
    if (world.isRemote) {
      player.openGui(LifeAquatic, Guis.Manual, world, player.posX.toInt, player.posY.toInt, player.posZ.toInt)
    }

    stack
  }
}
