package com.aergonaut.lifeaquatic.item.armor

import com.aergonaut.lifeaquatic.constants.Names
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.potion.{Potion, PotionEffect}
import net.minecraft.world.World

class SwimTrunkFaceMask extends SwimTrunkArmor(Names.Items.Armor.SwimTrunkFaceMask, 0) {
  override def onArmorTick(world: World, player: EntityPlayer, itemStack: ItemStack): Unit = {
    if (player.isInWater()) {
        player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 10,1))
    }
  }
}
