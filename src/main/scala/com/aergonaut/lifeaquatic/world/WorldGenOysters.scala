package com.aergonaut.lifeaquatic.world

import java.util.Random

import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.util.Logger
import net.minecraft.world.World
import net.minecraft.world.gen.feature.WorldGenerator

class WorldGenOysters extends WorldGenerator {
  override def generate(world: World, random: Random, x: Int, y: Int, z: Int): Boolean = {
    Logger.info(s"Generating an oyster at (${x}, ${y}, ${z})")
    world.setBlock(x, y, z, ModBlocks.Oyster)
  }
}
