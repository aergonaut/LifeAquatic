package com.aergonaut.lifeaquatic.block

import com.aergonaut.lifeaquatic.constants.Names
import cpw.mods.fml.common.registry.GameRegistry

object ModBlocks {
  final val PearlBlock: BlockBase = new PearlBlock
  final val Oyster: BlockBase = new Oyster
  final val CopperOre: BlockBase = new CopperOre
  final val TinOre: BlockBase = new TinOre

  def init(): Unit = {
    GameRegistry.registerBlock(PearlBlock, Names.Blocks.PearlBlock)
    GameRegistry.registerBlock(Oyster, Names.Blocks.Oyster)
    GameRegistry.registerBlock(CopperOre, Names.Ore.Copper)
    GameRegistry.registerBlock(TinOre, Names.Ore.Tin)
  }
}
