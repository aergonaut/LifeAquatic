package com.aergonaut.lifeaquatic.common

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

trait TProxy extends IGuiHandler

class CommonProxy extends TProxy {
  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = null

  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = null
}

