package com.aergonaut.lifeaquatic.block

import com.aergonaut.lifeaquatic.constants.Names

class TinOre extends BlockBase(Names.Ore.Tin) {
  setHardness(3.0F)
  setResistance(5.0F)
  setHarvestLevel("pickaxe", 1)
}
