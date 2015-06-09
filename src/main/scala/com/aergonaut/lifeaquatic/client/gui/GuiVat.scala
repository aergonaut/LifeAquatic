package com.aergonaut.lifeaquatic.client.gui

import cofh.lib.gui.GuiBase
import com.aergonaut.lifeaquatic.client.gui.element.{ElementProgressBar, ElementTankGauge}
import com.aergonaut.lifeaquatic.common.gui.ContainerVat
import com.aergonaut.lifeaquatic.constants.Textures
import com.aergonaut.lifeaquatic.tileentity.TileEntityVat
import com.aergonaut.lifeaquatic.util.Logger
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation

class GuiVat(inventoryPlayer: InventoryPlayer, tile: TileEntityVat)
  extends GuiBase(new ContainerVat(inventoryPlayer, tile), new ResourceLocation(Textures.Gui.Vat)) {

  drawTitle = false
  drawInventory = false

  override def updateElementInformation(): Unit = {
    elements.clear()

    val waterTank: ElementTankGauge = new ElementTankGauge(this, 24, 16, tile.waterTank)
    addElement(waterTank)

    val outputTank: ElementTankGauge = new ElementTankGauge(this, 131, 16, tile.outputTank)
    addElement(outputTank)

    val progress = (tile.maxProcessTime - tile.processTime)/tile.maxProcessTime.toFloat
    val progressBar: ElementProgressBar = new ElementProgressBar(this, 92, 33, 22, 16, if (progress < 1) progress else 0)
    progressBar.setTexture(Textures.Gui.Vat, 256, 256)
    addElement(progressBar)
  }
}
