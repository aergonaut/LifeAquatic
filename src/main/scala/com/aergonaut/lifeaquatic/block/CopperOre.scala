package com.aergonaut.lifeaquatic.block

import com.aergonaut.lifeaquatic.constants.Names

class CopperOre extends BlockBase(Names.Ore.Copper) {
  setHardness(3.0F)
  setResistance(5.0F)
  setHarvestLevel("pickaxe", 1)
}
