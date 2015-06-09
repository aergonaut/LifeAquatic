package com.aergonaut.lifeaquatic.common

import cofh.api.core.IInitializer
import com.aergonaut.lifeaquatic.common.gui.{ContainerCalcinator, ContainerVat}
import com.aergonaut.lifeaquatic.constants.Guis
import com.aergonaut.lifeaquatic.tileentity.{TileEntityCalcinator, TileEntityVat}
import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

trait TProxy extends IGuiHandler with IInitializer {
  override def preInit(): Boolean = true

  override def postInit(): Boolean = true

  override def initialize(): Boolean = true
}

class CommonProxy extends TProxy {
  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = null

  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = ID match {
    case Guis.Vat => new ContainerVat(player.inventory, world.getTileEntity(x, y, z).asInstanceOf[TileEntityVat])
    case Guis.Calcinator => new ContainerCalcinator(player.inventory, world.getTileEntity(x, y, z).asInstanceOf[TileEntityCalcinator])
    case _ => null
  }
}
