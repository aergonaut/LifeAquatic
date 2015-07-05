package com.aergonaut.lifeaquatic.item

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenChest, LinenHelmet, SwimTrunkFaceMask, SwimTrunkFins, SwimTrunkShorts}
import com.aergonaut.lifeaquatic.item.manual.ItemAlmanac
import com.aergonaut.lifeaquatic.item.material._
import com.aergonaut.lifeaquatic.item.tool.Wrench
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

object ModItems extends TInitializer {
  final val Pearl: ItemBase = new Pearl

  final val SwimTrunkFaceMask: ItemArmorBase = new SwimTrunkFaceMask
  final val SwimTrunkFins: ItemArmorBase = new SwimTrunkFins
  final val SwimTrunkShorts: ItemArmorBase = new SwimTrunkShorts


  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ItemAlmanac

  final val CopperIngot: ItemBase = new CopperIngot
  final val TinIngot: ItemBase = new TinIngot
  final val NickelIngot: ItemBase = new NickelIngot
  final val BronzeIngot: ItemBase = new BronzeIngot
  final val BrassIngot: ItemBase = new BrassIngot

  final val Wrench: ItemBase = new Wrench

  override def preInit(): Boolean = {
    GameRegistry.registerItem(ToolManual, Names.Items.Tool.Almanac)

    GameRegistry.registerItem(Wrench, Names.Items.Tool.Wrench)

    GameRegistry.registerItem(Pearl, Names.Items.Material.Pearl)

    GameRegistry.registerItem(SwimTrunkFaceMask, Names.Items.Armor.SwimTrunkFaceMask)
    GameRegistry.registerItem(SwimTrunkFins, Names.Items.Armor.SwimTrunkFins)
    GameRegistry.registerItem(SwimTrunkShorts, Names.Items.Armor.SwimTrunkShorts)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
    GameRegistry.registerItem(LinenChest, Names.Items.Armor.LinenChest)

    GameRegistry.registerItem(CopperIngot, Names.Items.Material.IngotCopper)
    GameRegistry.registerItem(TinIngot, Names.Items.Material.IngotTin)
    GameRegistry.registerItem(NickelIngot, Names.Items.Material.IngotNickel)
    GameRegistry.registerItem(BronzeIngot, Names.Items.Material.IngotBronze)
    GameRegistry.registerItem(BrassIngot, Names.Items.Material.IngotBrass)

    OreDictionary.registerOre("ingotCopper", ItemHelper.stack(CopperIngot))
    OreDictionary.registerOre("ingotTin", ItemHelper.stack(TinIngot))
    OreDictionary.registerOre("ingotNickel", ItemHelper.stack(NickelIngot))
    OreDictionary.registerOre("ingotBronze", ItemHelper.stack(BronzeIngot))
    OreDictionary.registerOre("ingotBrass", ItemHelper.stack(BrassIngot))

    true
  }

  override def initialize(): Boolean = {
    ItemHelper.addRecipe(ItemHelper.ShapedRecipe(ItemHelper.stack(Wrench),
      " i ",
      " ti",
      "p  ",
      char2Character('i'), "ingotIron",
      char2Character('t'), "ingotTin",
      char2Character('p'), ItemHelper.stack(Pearl)
    ))

    true
  }
}
