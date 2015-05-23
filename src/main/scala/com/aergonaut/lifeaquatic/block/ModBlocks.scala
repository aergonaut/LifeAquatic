package com.aergonaut.lifeaquatic.block

import cpw.mods.fml.common.registry.GameRegistry

object ModBlocks {
  final val pearlBlock: BlockBase = new PearlBlock
  final val oyster: BlockBase = new Oyster

  def init(): Unit = {
    GameRegistry.registerBlock(pearlBlock, "PearlBlock")
    GameRegistry.registerBlock(oyster, "Oyster")
  }
}
