package com.aergonaut.lifeaquatic.block

import java.util

import com.aergonaut.lifeaquatic.constants.Names
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class NickelOre extends BlockBase(Names.Ore.Nickel) {
  setHardness(3.0F)
  setResistance(5.0F)
  setHarvestLevel("pickaxe", 1)

  override def getDrops(world: World, x: Int, y: Int, z: Int, metadata: Int, fortune: Int): util.ArrayList[ItemStack] = {
    val ary = new util.ArrayList[ItemStack]()
    ary.add(new ItemStack(ModBlocks.NickelOre))
    ary
  }
}
