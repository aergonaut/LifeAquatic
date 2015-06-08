package com.aergonaut.lifeaquatic.item

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenChest, LinenHelmet}
import com.aergonaut.lifeaquatic.item.tool.ToolManual
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

object ModItems extends TInitializer {
  final val Pearl: ItemBase = new Pearl
  final val Kelp: ItemBase = new Kelp
  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ToolManual

  final val CopperIngot: ItemBase = new CopperIngot
  final val TinIngot: ItemBase = new TinIngot
  final val NickelIngot: ItemBase = new NickelIngot
  final val BronzeIngot: ItemBase = new BronzeIngot
  final val BrassIngot: ItemBase = new BrassIngot

  override def preInit(): Boolean = {
    GameRegistry.registerItem(ToolManual, Names.Items.Tools.Manual)

    GameRegistry.registerItem(Pearl, Names.Items.Pearl)
    GameRegistry.registerItem(Kelp, Names.Items.Kelp)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
    GameRegistry.registerItem(LinenChest, Names.Items.Armor.LinenChest)

    GameRegistry.registerItem(CopperIngot, Names.Items.Ingots.Copper)
    GameRegistry.registerItem(TinIngot, Names.Items.Ingots.Tin)
    GameRegistry.registerItem(NickelIngot, Names.Items.Ingots.Nickel)
    GameRegistry.registerItem(BronzeIngot, Names.Items.Ingots.Bronze)
    GameRegistry.registerItem(BrassIngot, Names.Items.Ingots.Brass)

    OreDictionary.registerOre("ingotCopper", ItemHelper.stack(CopperIngot))
    OreDictionary.registerOre("ingotTin", ItemHelper.stack(TinIngot))
    OreDictionary.registerOre("ingotNickel", ItemHelper.stack(NickelIngot))
    OreDictionary.registerOre("ingotBronze", ItemHelper.stack(BronzeIngot))
    OreDictionary.registerOre("ingotBrass", ItemHelper.stack(BrassIngot))

    true
  }
}
