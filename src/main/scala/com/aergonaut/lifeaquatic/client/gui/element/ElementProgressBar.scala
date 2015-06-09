package com.aergonaut.lifeaquatic.client.gui.element

import cofh.lib.gui.GuiBase
import cofh.lib.gui.element.ElementBase
import cofh.lib.render.RenderHelper
import org.lwjgl.opengl.GL11

class ElementProgressBar(gui: GuiBase, x: Int, y: Int, w: Int, h: Int, progress: Float) extends ElementBase(gui, x, y, w, h) {
  override def drawBackground(mouseX: Int, mouseY: Int, gameTicks: Float): Unit = {
    RenderHelper.bindTexture(texture)
    drawTexturedModalRect(x, y, 176, 0, (w * progress).round, h)
  }

  override def drawForeground(mouseX: Int, mouseY: Int): Unit = {}
}
