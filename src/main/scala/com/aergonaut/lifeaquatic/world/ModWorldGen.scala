package com.aergonaut.lifeaquatic.world

import java.util.Random

import cofh.api.world.{IFeatureGenerator, IFeatureHandler}
import cpw.mods.fml.common.IWorldGenerator
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkProvider

import scala.collection.mutable.HashMap

class ModWorldGen extends IWorldGenerator with IFeatureHandler {
  var features = new HashMap[String, IFeatureGenerator]

  registerFeature(new FeatureGenOysters)

  override def generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkProvider, chunkProvider: IChunkProvider): Unit = {
    for (feature <- features.valuesIterator) {
      feature.generateFeature(random, chunkX, chunkZ, world, true)
    }
  }

  override def registerFeature(feature: IFeatureGenerator): Boolean = {
    if (features contains feature.getFeatureName) {
      false
    } else {
      features += (feature.getFeatureName -> feature)
      true
    }
  }
}

object ModWorldGen {
  def init(): Unit = {
    GameRegistry.registerWorldGenerator(new ModWorldGen, 0)
  }
}
