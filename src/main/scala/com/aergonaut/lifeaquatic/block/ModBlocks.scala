package com.aergonaut.lifeaquatic.block

import com.aergonaut.lifeaquatic.util.constants.Names
import cpw.mods.fml.common.registry.GameRegistry

object ModBlocks {
  final val pearlBlock: BlockBase = new PearlBlock
  final val oyster: BlockBase = new Oyster

  def init(): Unit = {
    GameRegistry.registerBlock(pearlBlock, Names.Blocks.PearlBlock)
    GameRegistry.registerBlock(oyster, Names.Blocks.Oyster)
  }
}
