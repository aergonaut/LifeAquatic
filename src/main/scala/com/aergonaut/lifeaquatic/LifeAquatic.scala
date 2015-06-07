package com.aergonaut.lifeaquatic

import cofh.api.core.IInitializer
import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.common.TProxy
import com.aergonaut.lifeaquatic.config.ConfigHandler
import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.recipe.Recipes
import com.aergonaut.lifeaquatic.util.Logger
import com.aergonaut.lifeaquatic.constants.Constants
import com.aergonaut.lifeaquatic.world.ModWorldGen
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

@Mod(name = Constants.Name, version = Constants.Version, modid = Constants.ModID, modLanguage = "scala")
object LifeAquatic {
  private final val initializers: Vector[IInitializer] = Vector(ModItems, ModBlocks, ModWorldGen, Recipes)

  @SidedProxy(clientSide = "com.aergonaut.lifeaquatic.client.ClientProxy", serverSide = "com.aergonaut.lifeaquatic.server.ServerProxy")
  var proxy: TProxy = _

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Logger.info(s"${Constants.Name} pre-initialization starting")

    ConfigHandler.init(event.getSuggestedConfigurationFile)

    initializers.foreach(_.preInit())
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy)

    initializers.foreach(_.initialize())
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    initializers.foreach(_.postInit())

    Logger.info(s"${Constants.Name} has successfully loaded.")
  }
}
