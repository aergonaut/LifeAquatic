package com.aergonaut.lib.manual.gui

import com.aergonaut.lib.manual.TManualSection
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton

class GuiTextLink(gui: TGuiManual, target: TManualSection, id: Int, x: Int, y: Int, w: Int, h: Int) extends GuiButton(id, x, y, w, h, "") with TGuiHoverable {
  override def drawButton(minecraft: Minecraft, mx: Int, my: Int): Unit = {
    val color = if (isHovering(mx, my)) gui.manual.hoverColor else gui.manual.linkColor
    val text = target.name
    gui.font.drawString(text, x, y, color, false)
  }

  override def mousePressed(minecraft: Minecraft, x: Int, y: Int): Boolean = {
    if (super.mousePressed(minecraft, x, y)) {
      gui.setActiveSection(target)
      true
    } else {
      false
    }
  }
}
