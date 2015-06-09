package com.aergonaut.lifeaquatic.client.gui.element

import cofh.lib.gui.GuiBase
import cofh.lib.gui.element.ElementBase
import cofh.lib.render.RenderHelper

class ElementBurnTime(gui: GuiBase, x: Int, y: Int, w: Int, h: Int, remaining: Float) extends ElementBase(gui, x, y, w, h) {
  override def drawBackground(mouseX: Int, mouseY: Int, gameTicks: Float): Unit = {
    RenderHelper.bindTexture(texture)
    val hh = (h * remaining).round
    val dv = h - hh
    val dy = dv
    drawTexturedModalRect(x, y + dy, 176, 18 + dv, w, hh)
  }

  override def drawForeground(mouseX: Int, mouseY: Int): Unit = {}
}
