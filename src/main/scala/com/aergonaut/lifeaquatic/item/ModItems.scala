package com.aergonaut.lifeaquatic.item

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenChest, LinenHelmet}
import com.aergonaut.lifeaquatic.item.manual.ItemAlmanac
import com.aergonaut.lifeaquatic.item.material._
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

object ModItems extends TInitializer {
  final val Pearl: ItemBase = new Pearl

  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ItemAlmanac

  final val CopperIngot: ItemBase = new CopperIngot
  final val TinIngot: ItemBase = new TinIngot
  final val NickelIngot: ItemBase = new NickelIngot
  final val BronzeIngot: ItemBase = new BronzeIngot
  final val BrassIngot: ItemBase = new BrassIngot

  override def preInit(): Boolean = {
    GameRegistry.registerItem(ToolManual, Names.Items.Tools.Almanac)

    GameRegistry.registerItem(Pearl, Names.Material.Pearl)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
    GameRegistry.registerItem(LinenChest, Names.Items.Armor.LinenChest)

    GameRegistry.registerItem(CopperIngot, Names.Material.IngotCopper)
    GameRegistry.registerItem(TinIngot, Names.Material.IngotTin)
    GameRegistry.registerItem(NickelIngot, Names.Material.IngotNickel)
    GameRegistry.registerItem(BronzeIngot, Names.Material.IngotBronze)
    GameRegistry.registerItem(BrassIngot, Names.Material.IngotBrass)

    OreDictionary.registerOre("ingotCopper", ItemHelper.stack(CopperIngot))
    OreDictionary.registerOre("ingotTin", ItemHelper.stack(TinIngot))
    OreDictionary.registerOre("ingotNickel", ItemHelper.stack(NickelIngot))
    OreDictionary.registerOre("ingotBronze", ItemHelper.stack(BronzeIngot))
    OreDictionary.registerOre("ingotBrass", ItemHelper.stack(BrassIngot))

    true
  }
}
