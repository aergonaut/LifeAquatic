package com.aergonaut.lifeaquatic.item

import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenChest, LinenHelmet}
import com.aergonaut.lifeaquatic.item.tool.ToolManual
import cpw.mods.fml.common.registry.GameRegistry

object ModItems extends TInitializer {
  final val Pearl: ItemBase = new Pearl

  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ToolManual

  override def preInit(): Boolean = {
    GameRegistry.registerItem(Pearl, Names.Items.Pearl)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
    GameRegistry.registerItem(LinenChest, Names.Items.Armor.LinenChest)

    GameRegistry.registerItem(ToolManual, Names.Items.Tools.Manual)

    true
  }
}
