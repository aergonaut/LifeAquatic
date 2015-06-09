package com.aergonaut.lifeaquatic.item

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.armor.{ItemArmorBase, LinenChest, LinenHelmet, SwimTrunkFaceMask, SwimTrunkFins, SwimTrunkShorts}
import com.aergonaut.lifeaquatic.item.manual.ItemAlmanac
import com.aergonaut.lifeaquatic.item.material._
import com.aergonaut.lifeaquatic.item.metal._
import com.aergonaut.lifeaquatic.item.tool.{LavaPearl, Lens, Wrench}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.{Items, Blocks}
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object ModItems extends TInitializer {
  final val Pearl: ItemBase = new Pearl

  final val SwimTrunkFaceMask: ItemArmorBase = new SwimTrunkFaceMask
  final val SwimTrunkFins: ItemArmorBase = new SwimTrunkFins
  final val SwimTrunkShorts: ItemArmorBase = new SwimTrunkShorts

  final val LavaPearl: ItemBase = new LavaPearl

  final val LinenHelmet: ItemArmorBase = new LinenHelmet
  final val LinenChest: ItemArmorBase = new LinenChest

  final val ToolManual: ItemBase = new ItemAlmanac

  final val Wrench: ItemBase = new Wrench
  final val Lens: ItemBase = new Lens

  final val IronCasing: ItemBase = new IronCasing
  final val BronzeCasing: ItemBase = new BronzeCasing
  final val BrassCasing: ItemBase = new BrassCasing

  final val Ingot: ItemBase = new ItemIngot
  final val Calx: ItemBase = new ItemCalx

  // Convenience constants for referencing the ingots
  final val CopperIngot: ItemStack = ItemHelper.stack(Ingot, 1, 0)
  final val TinIngot: ItemStack = ItemHelper.stack(Ingot, 1, 1)
  final val NickelIngot: ItemStack = ItemHelper.stack(Ingot, 1, 2)
  final val BronzeIngot: ItemStack = ItemHelper.stack(Ingot, 1, 3)
  final val BrassIngot: ItemStack = ItemHelper.stack(Ingot, 1, 4)

  // Convenience constants for referencing the dusts
  final val IronAsh: ItemStack = ItemHelper.stack(Calx, 1, 0)
  final val GoldAsh: ItemStack = ItemHelper.stack(Calx, 1, 1)
  final val CopperAsh: ItemStack = ItemHelper.stack(Calx, 1, 2)
  final val TinAsh: ItemStack = ItemHelper.stack(Calx, 1, 3)
  final val NickelAsh: ItemStack = ItemHelper.stack(Calx, 1, 4)

  override def preInit(): Boolean = {
    GameRegistry.registerItem(ToolManual, Names.Items.Tool.Almanac)

    GameRegistry.registerItem(Wrench, Names.Items.Tool.Wrench)
    GameRegistry.registerItem(Lens, Names.Items.Tool.Lens)

    GameRegistry.registerItem(Pearl, Names.Items.Material.Pearl)

    GameRegistry.registerItem(SwimTrunkFaceMask, Names.Items.Armor.SwimTrunkFaceMask)
    GameRegistry.registerItem(SwimTrunkFins, Names.Items.Armor.SwimTrunkFins)
    GameRegistry.registerItem(SwimTrunkShorts, Names.Items.Armor.SwimTrunkShorts)

    GameRegistry.registerItem(LavaPearl, Names.Items.Tool.LavaPearl)

    GameRegistry.registerItem(LinenHelmet, Names.Items.Armor.LinenHelmet)
    GameRegistry.registerItem(LinenChest, Names.Items.Armor.LinenChest)

    GameRegistry.registerItem(IronCasing, Names.Items.IronCasing)
    GameRegistry.registerItem(BronzeCasing, Names.Items.BronzeCasing)
    GameRegistry.registerItem(BrassCasing, Names.Items.BrassCasing)

    GameRegistry.registerItem(Ingot, Names.Items.Ingot)
    GameRegistry.registerItem(Calx, Names.Items.Calx)

    OreDictionary.registerOre("ingotCopper", ItemHelper.cloneStack(CopperIngot))
    OreDictionary.registerOre("ingotTin", ItemHelper.cloneStack(TinIngot))
    OreDictionary.registerOre("ingotNickel", ItemHelper.cloneStack(NickelIngot))
    OreDictionary.registerOre("ingotBronze", ItemHelper.cloneStack(BronzeIngot))
    OreDictionary.registerOre("ingotBrass", ItemHelper.cloneStack(BrassIngot))

    OreDictionary.registerOre("dustIron", ItemHelper.cloneStack(IronAsh))
    OreDictionary.registerOre("dustGold", ItemHelper.cloneStack(GoldAsh))
    OreDictionary.registerOre("dustCopper", ItemHelper.cloneStack(CopperAsh))
    OreDictionary.registerOre("dustTin", ItemHelper.cloneStack(TinAsh))
    OreDictionary.registerOre("dustNickel", ItemHelper.cloneStack(NickelAsh))

    true
  }

  override def initialize(): Boolean = {
    ItemHelper.addSmelting(ItemHelper.getOre("ingotIron"), ItemHelper.cloneStack(IronAsh), 0.3F)
    ItemHelper.addSmelting(ItemHelper.getOre("ingotGold"), ItemHelper.cloneStack(GoldAsh), 0.3F)
    ItemHelper.addSmelting(ItemHelper.cloneStack(CopperIngot), ItemHelper.cloneStack(CopperAsh), 0.3F)
    ItemHelper.addSmelting(ItemHelper.cloneStack(TinIngot), ItemHelper.cloneStack(TinAsh), 0.3F)
    ItemHelper.addSmelting(ItemHelper.cloneStack(NickelIngot), ItemHelper.cloneStack(NickelAsh), 0.3F)

    true
  }
}
