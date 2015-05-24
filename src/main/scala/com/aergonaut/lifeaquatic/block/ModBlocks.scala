package com.aergonaut.lifeaquatic.block

import com.aergonaut.lifeaquatic.util.constants.Names
import cpw.mods.fml.common.registry.GameRegistry

object ModBlocks {
  final val PearlBlock: BlockBase = new PearlBlock
  final val Oyster: BlockBase = new Oyster

  def init(): Unit = {
    GameRegistry.registerBlock(PearlBlock, Names.Blocks.PearlBlock)
    GameRegistry.registerBlock(Oyster, Names.Blocks.Oyster)
  }
}
