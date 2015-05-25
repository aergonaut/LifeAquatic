package com.aergonaut.lifeaquatic.item

import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenHelmet}
import com.aergonaut.lifeaquatic.constants.Names
import cpw.mods.fml.common.registry.GameRegistry

object ModItems {
  final val Pearl: ItemBase = new Pearl

  final val LinenHelmet: ItemArmorBase = new LinenHelmet

  def init(): Unit = {
    GameRegistry.registerItem(Pearl, Names.Items.Pearl)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
  }
}
