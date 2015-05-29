package com.aergonaut.lifeaquatic.world.feature

import java.util.Random

import cofh.api.world.IFeatureGenerator
import cofh.lib.world.biome.{BiomeInfo, BiomeInfoSet}
import com.aergonaut.lifeaquatic.world.util.GenRestriction
import net.minecraft.world.World

abstract class FeatureGenBase(name: String, biomesRestriction: GenRestriction.Restriction, regen: Boolean, dimensionsRestriction: GenRestriction.Restriction) extends IFeatureGenerator {
  val biomes = new BiomeInfoSet()
  var dimensions: Set[Int] = Set()
  var rarity = 0

  def this(name: String, regen: Boolean) = this(name, GenRestriction.None, regen, GenRestriction.None)

  def this(name: String, biomesRestriction: GenRestriction.Restriction, regen: Boolean) =
    this(name, biomesRestriction, regen, GenRestriction.None)

  def this(name: String, regen: Boolean, dimensionsRestriction: GenRestriction.Restriction) =
    this(name, GenRestriction.None, regen, dimensionsRestriction)

  def addBiome(biome: BiomeInfo): FeatureGenBase = {
    biomes.add(biome)
    this
  }

  def addBiomes(otherBiomes: BiomeInfoSet): FeatureGenBase = {
    biomes.addAll(otherBiomes)
    this
  }

  def addDimension(dimId: Int): FeatureGenBase = {
    dimensions += dimId
    this
  }

  def addDimensions(dims: Set[Int]): FeatureGenBase = {
    dimensions ++= dims
    this
  }

  def setRarity(r: Int): FeatureGenBase = {
    rarity = r
    this
  }

  override def getFeatureName: String = name

  override def generateFeature(random: Random, chunkX: Int, chunkZ: Int, world: World, newGen: Boolean): Boolean = {
    (newGen || regen) &&
    canGenerateInDimension(world) &&
    random.nextInt(100) < rarity &&
    generateFeature(random, chunkX, chunkZ, world)
  }

  def generateFeature(random: Random, chunkX: Int, chunkZ: Int, world: World): Boolean

  protected def canGenerateInBiome(world: World, x: Int, z: Int, random: Random): Boolean = biomesRestriction match {
    case GenRestriction.None => true

    case _ => {
      val biome = world.getBiomeGenForCoords(x, z)
      !(biomesRestriction == GenRestriction.Blacklist == biomes.contains(biome, random))
    }
  }

  protected def canGenerateInDimension(world: World): Boolean = dimensionsRestriction match {
    case GenRestriction.None => true

    case _ => {
      val dimID = world.provider.dimensionId
      !(dimensionsRestriction == GenRestriction.Blacklist == (dimensions contains dimID))
    }
  }
}
