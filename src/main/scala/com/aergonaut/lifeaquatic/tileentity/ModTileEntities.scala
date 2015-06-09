package com.aergonaut.lifeaquatic.tileentity

import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.constants.Names
import cpw.mods.fml.common.registry.GameRegistry

object ModTileEntities extends TInitializer {
  override def preInit(): Boolean = {
    GameRegistry.registerTileEntity(classOf[TileEntityVat], Names.TileEntities.Vat)
    GameRegistry.registerTileEntity(classOf[TileEntityCalcinator], Names.TileEntities.Calcinator)

    true
  }
}
