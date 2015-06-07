package com.aergonaut.lifeaquatic.block

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack

object ModBlocks extends TInitializer {
  final val PearlBlock: BlockBase = new PearlBlock
  final val Oyster: BlockBase = new Oyster
  final val CopperOre: BlockBase = new CopperOre
  final val TinOre: BlockBase = new TinOre

  override def preInit(): Boolean = {
    GameRegistry.registerBlock(PearlBlock, Names.Blocks.PearlBlock)
    GameRegistry.registerBlock(Oyster, Names.Blocks.Oyster)
    GameRegistry.registerBlock(CopperOre, Names.Ore.Copper)
    GameRegistry.registerBlock(TinOre, Names.Ore.Tin)

    ItemHelper.registerWithHandlers("oreCopper", new ItemStack(CopperOre))
    ItemHelper.registerWithHandlers("oreTin", new ItemStack(TinOre))

    true
  }
}
