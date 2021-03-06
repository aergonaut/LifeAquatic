package com.aergonaut.lifeaquatic.constants

import net.minecraftforge.common.util.EnumHelper

object Materials {
  object Armor {
    final val Linen = EnumHelper.addArmorMaterial(Names.Items.Material.Armor.Linen, 2, Array(1, 1, 1, 1), 25)
    final val SwimTrunk = EnumHelper.addArmorMaterial(Names.Items.Material.Armor.SwimTrunk, 2, Array(1, 1, 1, 1), 25)
  }
}
