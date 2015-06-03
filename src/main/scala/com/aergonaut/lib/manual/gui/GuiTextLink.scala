package com.aergonaut.lib.manual.gui

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton

class GuiTextLink(gui: TGuiManual, id: Int, x: Int, y: Int, w: Int, h: Int, text: String) extends GuiButton(id, x, y, w, h, "") with TGuiHoverable {
  override def drawButton(minecraft: Minecraft, mx: Int, my: Int): Unit = {
    val color = if (isHovering(mx, my)) gui.manual.hoverColor else gui.manual.linkColor
    gui.font.drawString(text, x, y, color, false)
  }
}
