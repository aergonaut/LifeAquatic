package com.aergonaut.lifeaquatic.client

import com.aergonaut.lifeaquatic.client.gui.{GuiCalcinator, GuiAlmanac, GuiVat}
import com.aergonaut.lifeaquatic.common.CommonProxy
import com.aergonaut.lifeaquatic.constants.Guis
import com.aergonaut.lifeaquatic.tileentity.{TileEntityCalcinator, TileEntityVat}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraftforge.common.MinecraftForge

class ClientProxy extends CommonProxy {
  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = ID match {
    case Guis.Almanac => GuiAlmanac.Instance

    case Guis.Vat => new GuiVat(player.inventory, world.getTileEntity(x, y, z).asInstanceOf[TileEntityVat])
    case Guis.Calcinator => new GuiCalcinator(player.inventory, world.getTileEntity(x, y, z).asInstanceOf[TileEntityCalcinator])

    case _ => null
  }

  override def preInit(): Boolean = {
    MinecraftForge.EVENT_BUS.register(ClientEventHandler)

    true
  }
}
