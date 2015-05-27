package com.aergonaut.lifeaquatic.world.feature

import java.util.Random

import cofh.lib.util.WeightedRandomBlock
import cofh.lib.util.helpers.BlockHelper
import com.aergonaut.lifeaquatic.world.util.GenRestriction
import net.minecraft.block.material.Material
import net.minecraft.world.World
import net.minecraft.world.gen.feature.WorldGenerator

class FeatureGenUnderwater(name: String, worldGen: WorldGenerator, materials: List[WeightedRandomBlock], count: Int, biomesRestriction: GenRestriction.Restriction, regen: Boolean, dimensionsRestriction: GenRestriction.Restriction)
  extends FeatureGenBase(name, biomesRestriction, regen, dimensionsRestriction) {

  addDimensions(Set(-1, 1))

  // Find the deepest underwater y-coord at the given x and z
  //
  // This will search down at a given x and z until it finds a block that is not water and that is underneath a block
  // that is water.
  //
  // Returns Some(y) if a y-coord is found, None otherwise.
  protected def deepestUnderwaterY(random: Random, x: Int, z: Int, world: World): Option[Int] = {
    val y = BlockHelper.getSurfaceBlockY(world, x, z) + 1
    val block = world.getBlock(x, y, z)
    materials.find(mat => {
      world.getBlock(x, y + 1, z).getMaterial == Material.water &&
      block.isReplaceableOreGen(world, x, y, z, mat.block)
    }).map(_ => y)
  }

  // Perform one generation of the given feature.
  //
  // This will first search for a suitable underwater y-coord and then generate the feature at the selected (x, y, z)
  // if a y-coord is found.
  //
  // Returns false if no suitable y-coord is found, otherwise returns the result of the world gen operation
  protected def generateOneRound(random: Random, x: Int, z: Int, world: World): Boolean =
    deepestUnderwaterY(random, x, z, world).exists(y => worldGen.generate(world, random, x, y, z))

  // Potentially generate a feature.
  //
  // This will calculate a random x and z in the chunk to generate at. Then it will determine if the biome at that
  // (x, y, z) is suitable for this feature. Only then will it attempt to generate the feature.
  //
  // Returns false if the biome is unsuitable, otherwise the result of attempting to generate the feature.
  protected def maybeGenerateOneRound(random: Random, blockX: Int, blockZ: Int, world: World): Boolean = {
    val x = blockX + random.nextInt(16)
    val z = blockZ + random.nextInt(16)

    canGenerateInBiome(world, x, z, random) && generateOneRound(random, x, z, world)
  }

  override def generateFeature(random: Random, chunkX: Int, chunkZ: Int, world: World): Boolean = {
    val blockX = chunkX * 16
    val blockZ = chunkZ * 16

    (0 until count).map(_ => maybeGenerateOneRound(random, blockX, blockZ, world)).fold[Boolean](false)(_ || _)
  }
}
