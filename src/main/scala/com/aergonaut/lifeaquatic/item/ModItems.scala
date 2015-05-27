package com.aergonaut.lifeaquatic.item

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenChest, LinenHelmet, SwimTrunkFaceMask}
import com.aergonaut.lifeaquatic.item.manual.ItemAlmanac
import com.aergonaut.lifeaquatic.item.material._
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary

object ModItems extends TInitializer {
  final val Pearl: ItemBase = new Pearl

  final val SwimTrunkFaceMask: ItemArmorBase = new SwimTrunkFaceMask
  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ItemAlmanac

  final val CopperIngot: ItemBase = new CopperIngot
  final val TinIngot: ItemBase = new TinIngot
  final val NickelIngot: ItemBase = new NickelIngot
  final val BronzeIngot: ItemBase = new BronzeIngot
  final val BrassIngot: ItemBase = new BrassIngot

  override def preInit(): Boolean = {
    GameRegistry.registerItem(ToolManual, Names.Items.Tool.Almanac)

    GameRegistry.registerItem(Pearl, Names.Items.Material.Pearl)

    GameRegistry.registerItem(SwimTrunkFaceMask, Names.Items.Armor.SwimTrunkFaceMask)
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
}
