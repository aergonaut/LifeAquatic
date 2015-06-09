package com.aergonaut.lifeaquatic.client.gui

import cofh.lib.gui.GuiBase
import com.aergonaut.lifeaquatic.client.gui.element.{ElementBurnTime, ElementProgressBar}
import com.aergonaut.lifeaquatic.common.gui.ContainerCalcinator
import com.aergonaut.lifeaquatic.constants.Textures
import com.aergonaut.lifeaquatic.tileentity.TileEntityCalcinator
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation

class GuiCalcinator(playerInvetory: InventoryPlayer, tile: TileEntityCalcinator)
  extends GuiBase(new ContainerCalcinator(playerInvetory, tile), new ResourceLocation(Textures.Gui.Calcinator)) {

  drawTitle = false
  drawInventory = false

  override def updateElementInformation(): Unit = {
    elements.clear()

    val progress = tile.cookTime / tile.CookTimeRequired.toFloat
    val progressBar = new ElementProgressBar(this, 88, 33, 22, 16, progress)
    progressBar.setTexture(Textures.Gui.Calcinator, 256, 256)
    addElement(progressBar)

    val remainingBurn = tile.burnTime / tile.lastBurnTime.toFloat
    val burnTime = new ElementBurnTime(this, 65, 36, 9, 12, remainingBurn)
    burnTime.setTexture(Textures.Gui.Calcinator, 256, 256)
    addElement(burnTime)
  }
}
