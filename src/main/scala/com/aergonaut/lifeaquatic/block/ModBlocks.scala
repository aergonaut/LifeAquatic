package com.aergonaut.lifeaquatic.block

import cpw.mods.fml.common.registry.GameRegistry

object ModBlocks {
  final val pearlBlock: BlockBase = new PearlBlock

  def init(): Unit = {
    GameRegistry.registerBlock(pearlBlock, "PearlBlock")
  }
}
