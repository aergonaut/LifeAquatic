package com.aergonaut.lifeaquatic.block.ore

import com.aergonaut.lifeaquatic.block.BlockBase
import com.aergonaut.lifeaquatic.constants.Names

class NickelOre extends BlockBase(Names.Blocks.Ore.Nickel) {
  setHardness(3.0F)
  setResistance(5.0F)
  setHarvestLevel("pickaxe", 2)
}