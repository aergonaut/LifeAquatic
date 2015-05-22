package com.aergonaut.lifeaquatic

import com.aergonaut.lifeaquatic.common.Proxy
import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.util.Logger
import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

@Mod(name = LifeAquatic.name, version = LifeAquatic.version, modid = LifeAquatic.modid, modLanguage = "scala")
object LifeAquatic {
  final val name = "Life Aquatic"
  final val version = "1.7.10-0.0.1"
  final val modid = "lifeaquatic"

  @SidedProxy(clientSide = "com.aergonaut.lifeaquatic.client.ClientProxy", serverSide = "com.aergonaut.lifeaquatic.server.ServerProxy")
  var proxy: Proxy = null

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Logger.info(s"${LifeAquatic.name} pre-initialization starting")

    ModItems.init()
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {

  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    Logger.info(s"${LifeAquatic.name} has successfully loaded.")
  }
}
