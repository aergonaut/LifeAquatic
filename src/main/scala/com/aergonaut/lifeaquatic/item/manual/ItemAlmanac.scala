package com.aergonaut.lifeaquatic.item.manual

import com.aergonaut.lifeaquatic.LifeAquatic
import com.aergonaut.lifeaquatic.constants.{Guis, Names}
import com.aergonaut.lifeaquatic.item.ItemBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class ItemAlmanac extends ItemBase(Names.Items.Tool.Almanac) {
  override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
    if (world.isRemote) {
      player.openGui(LifeAquatic, Guis.Almanac, world, player.posX.toInt, player.posY.toInt, player.posZ.toInt)
    }

    stack
  }
}
