package com.aergonaut.lifeaquatic.config

import java.io.File

import net.minecraftforge.common.config.Configuration

object ConfigHandler {
  private var configFile: Configuration = _

  private def loadConfiguration(): Unit = {
    Config.World.oysterRarity = configFile.getInt(
      "Oyster Rarity",
      Config.Category.World,
      Config.World.oysterRarity,
      0,
      100,
      "The percent chance of generating a cluster of oysters."
    )

    Config.World.oysterClusterSize = configFile.getInt(
      "Oyster Cluster Size",
      Config.Category.World,
      Config.World.oysterClusterSize,
      0,
      5,
      "The number of oysters per cluster."
    )

    Config.World.maxPearlsDropped = configFile.getInt(
      "Max Pearls Dropped",
      Config.Category.World,
      Config.World.maxPearlsDropped,
      0,
      5,
      "The maximum number of pearls an oyster may drop."
    )

    if (configFile.hasChanged) {
      configFile.save()
    }
  }

  def init(file: File): Unit = {
    configFile = new Configuration(file)
    loadConfiguration()
  }
}
