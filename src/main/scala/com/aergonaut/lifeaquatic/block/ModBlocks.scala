package com.aergonaut.lifeaquatic.block

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.block.ore.{CopperOre, NickelOre, TinOre}
import com.aergonaut.lifeaquatic.block.storage._
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.ModItems
import cpw.mods.fml.common.registry.GameRegistry

object ModBlocks extends TInitializer {
  final val PearlBlock: BlockBase = new PearlBlock
  final val Oyster: BlockBase = new Oyster

  final val CopperOre: BlockBase = new CopperOre
  final val TinOre: BlockBase = new TinOre
  final val NickelOre: BlockBase = new NickelOre

  final val CopperBlock: BlockBase = new CopperBlock
  final val TinBlock: BlockBase = new TinBlock
  final val NickelBlock: BlockBase = new NickelBlock
  final val BronzeBlock: BlockBase = new BronzeBlock
  final val BrassBlock: BlockBase = new BrassBlock

  override def preInit(): Boolean = {
    GameRegistry.registerBlock(PearlBlock, Names.Blocks.Storage.Pearl)
    GameRegistry.registerBlock(Oyster, Names.Blocks.Oyster)

    GameRegistry.registerBlock(CopperOre, Names.Blocks.Ore.Copper)
    GameRegistry.registerBlock(TinOre, Names.Blocks.Ore.Tin)
    GameRegistry.registerBlock(NickelOre, Names.Blocks.Ore.Nickel)

    GameRegistry.registerBlock(CopperBlock, Names.Blocks.Storage.Copper)
    GameRegistry.registerBlock(TinBlock, Names.Blocks.Storage.Tin)
    GameRegistry.registerBlock(NickelBlock, Names.Blocks.Storage.Nickel)
    GameRegistry.registerBlock(BronzeBlock, Names.Blocks.Storage.Bronze)
    GameRegistry.registerBlock(BrassBlock, Names.Blocks.Storage.Brass)

    ItemHelper.registerWithHandlers("oreCopper", ItemHelper.stack(CopperOre))
    ItemHelper.registerWithHandlers("oreTin", ItemHelper.stack(TinOre))
    ItemHelper.registerWithHandlers("oreNickel", ItemHelper.stack(NickelOre))

    true
  }

  override def initialize(): Boolean = {
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(CopperBlock), ItemHelper.stack(ModItems.CopperIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(TinBlock), ItemHelper.stack(ModItems.TinIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(NickelBlock), ItemHelper.stack(ModItems.NickelIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(BronzeBlock), ItemHelper.stack(ModItems.BronzeIngot))
    ItemHelper.addTwoWayStorageRecipe(ItemHelper.stack(BrassBlock), ItemHelper.stack(ModItems.BrassIngot))

    true
  }

  override def postInit(): Boolean = {
    ItemHelper.addSmelting(ItemHelper.stack(ModItems.CopperIngot), ItemHelper.stack(CopperOre), 0.6F)
    ItemHelper.addSmelting(ItemHelper.stack(ModItems.TinIngot), ItemHelper.stack(TinOre), 0.7F)
    ItemHelper.addSmelting(ItemHelper.stack(ModItems.NickelIngot), ItemHelper.stack(NickelOre), 1.0F)

    true
  }
}
