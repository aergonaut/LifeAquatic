package com.aergonaut.lifeaquatic.item

import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenChest, LinenHelmet}
import com.aergonaut.lifeaquatic.item.tool.ToolManual
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object ModItems extends TInitializer {
  final val Pearl: ItemBase = new Pearl

  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ToolManual

  final val CopperIngot: ItemBase = new CopperIngot
  final val TinIngot: ItemBase = new TinIngot
  final val NickelIngot: ItemBase = new NickelIngot

  override def preInit(): Boolean = {
    GameRegistry.registerItem(Pearl, Names.Items.Pearl)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
    GameRegistry.registerItem(LinenChest, Names.Items.Armor.LinenChest)

    GameRegistry.registerItem(ToolManual, Names.Items.Tools.Manual)

    GameRegistry.registerItem(CopperIngot, Names.Items.Ingots.Copper)
    GameRegistry.registerItem(TinIngot, Names.Items.Ingots.Tin)
    GameRegistry.registerItem(NickelIngot, Names.Items.Ingots.Nickel)

    OreDictionary.registerOre("ingotCopper", new ItemStack(CopperIngot))
    OreDictionary.registerOre("ingotTin", new ItemStack(TinIngot))
    OreDictionary.registerOre("ingotNickel", new ItemStack(NickelIngot))

    true
  }
}
