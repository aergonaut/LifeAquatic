package com.aergonaut.lifeaquatic.block

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.block.furnace.CalcinatorBrick
import com.aergonaut.lifeaquatic.block.storage._
import com.aergonaut.lifeaquatic.block.vat.{VatCasing, VatSiding}
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.{ItemBlockBase, ModItems}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.material.Material
import net.minecraft.init.{Items, Blocks}
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.{FluidRegistry, Fluid}
import net.minecraftforge.oredict.OreDictionary

object ModBlocks extends TInitializer {
  final val PearlBlock: BlockBase = new PearlBlock
  final val Oyster: BlockBase = new Oyster

  final val Ore: BlockBase = new BlockBase("ore", Material.rock, Vector("copper", "tin", "nickel"))

  final val CopperBlock: BlockBase = new CopperBlock
  final val TinBlock: BlockBase = new TinBlock
  final val NickelBlock: BlockBase = new NickelBlock
  final val BronzeBlock: BlockBase = new BronzeBlock
  final val BrassBlock: BlockBase = new BrassBlock

  final val VatCasing: BlockBase = new VatCasing
  final val VatSiding: BlockBase = new VatSiding

  final val FurnaceBlock: BlockBase = new CalcinatorBrick

  final val AqueousCopper: Fluid = new Fluid("aqueous_copper")
  final val AqueousTin: Fluid = new Fluid("aqueous_tin")
  final val AqueousIron: Fluid = new Fluid("aqueous_iron")
  final val AqueousGold: Fluid = new Fluid("aqueous_gold")

  var CopperOre: ItemStack = _
  var TinOre: ItemStack = _
  var NickelOre: ItemStack = _

  override def preInit(): Boolean = {
    GameRegistry.registerBlock(PearlBlock, Names.Blocks.Storage.Pearl)
    GameRegistry.registerBlock(Oyster, Names.Blocks.Oyster)

    GameRegistry.registerBlock(Ore, classOf[ItemBlockBase], "ore")

    CopperOre = ItemHelper.stack(Ore, 1, 0)
    TinOre = ItemHelper.stack(Ore, 1, 1)
    NickelOre = ItemHelper.stack(Ore, 1, 2)

    GameRegistry.registerBlock(CopperBlock, Names.Blocks.Storage.Copper)
    GameRegistry.registerBlock(TinBlock, Names.Blocks.Storage.Tin)
    GameRegistry.registerBlock(NickelBlock, Names.Blocks.Storage.Nickel)
    GameRegistry.registerBlock(BronzeBlock, Names.Blocks.Storage.Bronze)
    GameRegistry.registerBlock(BrassBlock, Names.Blocks.Storage.Brass)

    GameRegistry.registerBlock(VatCasing, Names.Blocks.Machine.VatCasing)
    GameRegistry.registerBlock(VatSiding, Names.Blocks.Machine.VatSiding)

    GameRegistry.registerBlock(FurnaceBlock, Names.Blocks.Machine.CalcinatorBrick)

    OreDictionary.registerOre("oreCopper", ItemHelper.stack(Ore, 1, 0))
    OreDictionary.registerOre("oreTin", ItemHelper.stack(Ore, 1, 1))
    OreDictionary.registerOre("oreNickel", ItemHelper.stack(Ore, 1, 2))

    registerFluids()

    true
  }

  override def initialize(): Boolean = {
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(CopperBlock), ItemHelper.cloneStack(ModItems.CopperIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(TinBlock), ItemHelper.cloneStack(ModItems.TinIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(NickelBlock), ItemHelper.cloneStack(ModItems.NickelIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(BronzeBlock), ItemHelper.cloneStack(ModItems.BronzeIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(BrassBlock), ItemHelper.cloneStack(ModItems.BrassIngot))

    ItemHelper.addSmelting(ItemHelper.cloneStack(ModItems.CopperIngot), ItemHelper.stack(Ore, 1, 0), 0.3F)
    ItemHelper.addSmelting(ItemHelper.cloneStack(ModItems.TinIngot), ItemHelper.stack(Ore, 1, 1), 0.3F)
    ItemHelper.addSmelting(ItemHelper.cloneStack(ModItems.NickelIngot), ItemHelper.stack(Ore, 1, 2), 0.3F)

    true
  }

  override def postInit(): Boolean = {

    true
  }

  def registerFluids(): Unit = {
    AqueousCopper.setDensity(3000).setViscosity(6000)
    FluidRegistry.registerFluid(AqueousCopper)

    AqueousTin.setDensity(3000).setViscosity(6000)
    FluidRegistry.registerFluid(AqueousTin)

    AqueousIron.setDensity(3000).setViscosity(6000)
    FluidRegistry.registerFluid(AqueousIron)

    AqueousGold.setDensity(3000).setViscosity(6000)
    FluidRegistry.registerFluid(AqueousGold)
  }
}
