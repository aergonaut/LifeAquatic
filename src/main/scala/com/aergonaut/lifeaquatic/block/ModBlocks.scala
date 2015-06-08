package com.aergonaut.lifeaquatic.block

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.ModItems
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack

object ModBlocks extends TInitializer {
  final val PearlBlock: BlockBase = new PearlBlock
  final val Oyster: BlockBase = new Oyster

  final val CopperOre: BlockBase = new CopperOre
  final val TinOre: BlockBase = new TinOre
  final val NickelOre: BlockBase = new NickelOre

  final val CopperBlock: BlockBase = new CopperBlock
  final val TinBlock: BlockBase = new TinBlock
  final val NickelBlock: BlockBase = new NickelBlock

  override def preInit(): Boolean = {
    GameRegistry.registerBlock(PearlBlock, Names.Blocks.PearlBlock)
    GameRegistry.registerBlock(Oyster, Names.Blocks.Oyster)

    GameRegistry.registerBlock(CopperOre, Names.Ore.Copper)
    GameRegistry.registerBlock(TinOre, Names.Ore.Tin)
    GameRegistry.registerBlock(NickelOre, Names.Ore.Nickel)

    GameRegistry.registerBlock(CopperBlock, Names.Blocks.CopperBlock)
    GameRegistry.registerBlock(TinBlock, Names.Blocks.TinBlock)
    GameRegistry.registerBlock(NickelBlock, Names.Blocks.NickelBlock)

    ItemHelper.registerWithHandlers("oreCopper", new ItemStack(CopperOre))
    ItemHelper.registerWithHandlers("oreTin", new ItemStack(TinOre))
    ItemHelper.registerWithHandlers("oreNickel", new ItemStack(NickelOre))

    true
  }

  override def postInit(): Boolean = {
    ItemHelper.addSmelting(ItemHelper.stack(ModItems.CopperIngot), ItemHelper.stack(CopperOre), 0.6F)
    ItemHelper.addSmelting(ItemHelper.stack(ModItems.TinIngot), ItemHelper.stack(TinOre), 0.7F)
    ItemHelper.addSmelting(ItemHelper.stack(ModItems.NickelIngot), ItemHelper.stack(NickelOre), 1.0F)

    true
  }
}
