package com.aergonaut.lifeaquatic

import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.common.Proxy
import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.recipe.Recipes
import com.aergonaut.lifeaquatic.util.Logger
import com.aergonaut.lifeaquatic.util.constants.Constants
import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

@Mod(name = Constants.Name, version = Constants.Version, modid = Constants.ModID, modLanguage = "scala")
object LifeAquatic {

  @SidedProxy(clientSide = "com.aergonaut.lifeaquatic.client.ClientProxy", serverSide = "com.aergonaut.lifeaquatic.server.ServerProxy")
  var proxy: Proxy = null

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Logger.info(s"${Constants.Name} pre-initialization starting")

    ModItems.init()
    ModBlocks.init()
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    Recipes.init()
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    Logger.info(s"${Constants.Name} has successfully loaded.")
  }
}
