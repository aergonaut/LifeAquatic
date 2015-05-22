package com.aergonaut.lifeaquatic

import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.common.Proxy
import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.util.{Constants, Logger}
import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

@Mod(name = Constants.name, version = Constants.version, modid = Constants.modid, modLanguage = "scala")
object LifeAquatic {

  @SidedProxy(clientSide = "com.aergonaut.lifeaquatic.client.ClientProxy", serverSide = "com.aergonaut.lifeaquatic.server.ServerProxy")
  var proxy: Proxy = null

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Logger.info(s"${Constants.name} pre-initialization starting")

    ModItems.init()
    ModBlocks.init()
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {

  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    Logger.info(s"${Constants.name} has successfully loaded.")
  }
}
