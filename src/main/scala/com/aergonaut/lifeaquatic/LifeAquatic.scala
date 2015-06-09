package com.aergonaut.lifeaquatic

import cofh.api.core.IInitializer
import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.common.TProxy
import com.aergonaut.lifeaquatic.config.ConfigHandler
import com.aergonaut.lifeaquatic.handler.ModFuelHandler
import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.recipe.{CalcinatorRecipes, Recipes}
import com.aergonaut.lifeaquatic.tileentity.ModTileEntities
import com.aergonaut.lifeaquatic.util.Logger
import com.aergonaut.lifeaquatic.constants.Constants
import com.aergonaut.lifeaquatic.world.ModWorldGen
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

@Mod(name = Constants.Name, version = Constants.Version, modid = Constants.ModID, modLanguage = "scala")
object LifeAquatic {
  private final val Initializers: Vector[IInitializer] = Vector(
    ModItems, ModBlocks, ModTileEntities, ModWorldGen, Recipes, CalcinatorRecipes, ModFuelHandler
  )

  @SidedProxy(clientSide = "com.aergonaut.lifeaquatic.client.ClientProxy", serverSide = "com.aergonaut.lifeaquatic.server.ServerProxy")
  var proxy: TProxy = _

  @EventHandler
  def preInit(event: FMLPreInitializationEvent): Unit = {
    Logger.info(s"${Constants.Name} pre-initialization starting")

    ConfigHandler.init(event.getSuggestedConfigurationFile)

    Initializers.foreach(_.preInit())

    proxy.preInit()
  }

  @EventHandler
  def init(event: FMLInitializationEvent): Unit = {
    NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy)

    Initializers.foreach(_.initialize())

    proxy.initialize()
  }

  @EventHandler
  def postInit(event: FMLPostInitializationEvent): Unit = {
    Initializers.foreach(_.postInit())

    proxy.postInit()

    Logger.info(s"${Constants.Name} has successfully loaded.")
  }
}
