package com.aergonaut.lifeaquatic.block

import com.aergonaut.lifeaquatic.constants.Names

class NickelOre extends BlockBase(Names.Ore.Nickel) {
  setHardness(3.0F)
  setResistance(5.0F)
  setHarvestLevel("pickaxe", 1)
}
