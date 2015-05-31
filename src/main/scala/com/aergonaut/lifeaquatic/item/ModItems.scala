package com.aergonaut.lifeaquatic.item

import com.aergonaut.lifeaquatic.item.armor.{LinenChest, ItemArmorBase, LinenHelmet}
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.tool.ToolManual
import cpw.mods.fml.common.registry.GameRegistry

object ModItems {
  final val Pearl: ItemBase = new Pearl

  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ToolManual

  def init(): Unit = {
    GameRegistry.registerItem(Pearl, Names.Items.Pearl)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
    GameRegistry.registerItem(LinenChest, Names.Items.Armor.LinenChest)

    GameRegistry.registerItem(ToolManual, Names.Items.Tools.Manual)
  }
}
