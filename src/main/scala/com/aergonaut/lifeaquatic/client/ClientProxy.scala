package com.aergonaut.lifeaquatic.client

import com.aergonaut.lifeaquatic.client.gui.manual.GuiManual
import com.aergonaut.lifeaquatic.common.CommonProxy
import com.aergonaut.lifeaquatic.constants.Guis
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

class ClientProxy extends CommonProxy {
  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = ID match {
    case Guis.Manual => GuiManual.Instance
    case _ => null
  }
}
