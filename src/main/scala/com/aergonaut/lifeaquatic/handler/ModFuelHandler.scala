package com.aergonaut.lifeaquatic.handler

import cofh.api.core.IInitializer
import com.aergonaut.lifeaquatic.item.tool.LavaPearl
import cpw.mods.fml.common.IFuelHandler
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack

class ModFuelHandler extends IFuelHandler {
  override def getBurnTime(fuel: ItemStack): Int = fuel.getItem match {
    case fuel: LavaPearl => 20000 // same as a bucket of lava
    case _ => 0
  }
}

object ModFuelHandler extends IInitializer {
  override def preInit(): Boolean = true

  override def initialize(): Boolean = {
    GameRegistry.registerFuelHandler(new ModFuelHandler())

    true
  }

  override def postInit(): Boolean = true
}
