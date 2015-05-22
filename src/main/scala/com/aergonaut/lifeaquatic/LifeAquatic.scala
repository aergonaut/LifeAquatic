package com.aergonaut.lifeaquatic

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import org.apache.logging.log4j.{Level, Logger}

@Mod(name = LifeAquatic.name, version = LifeAquatic.version, modid = LifeAquatic.modid, modLanguage = "scala")
object LifeAquatic {
  final val name = "Life Aquatic"
  final val version = "1.7.10-0.0.1"
  final val modid = "lifeaquatic"

  var logger: Logger = null

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    logger = event.getModLog
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {

  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    logger.log(Level.INFO, s"${LifeAquatic.name} has loaded successfully.")
  }
}
