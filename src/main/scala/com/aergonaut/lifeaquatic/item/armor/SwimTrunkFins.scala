package com.aergonaut.lifeaquatic.item.armor

import com.aergonaut.lifeaquatic.constants.Names
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class SwimTrunkFins extends SwimTrunkArmor(Names.Items.Armor.SwimTrunkFins, 3) {
  override def onArmorTick(world: World, player: EntityPlayer, itemStack: ItemStack): Unit = {
    if(player.isInWater()) {
        player.moveEntityWithHeading(player.moveStrafing, player.moveForward * 4);
    }
  }
}
