package com.aergonaut.lib.manual.gui

import net.minecraft.client.gui.GuiButton

trait TGuiHoverable extends GuiButton {
  def isHovering(mx: Int, my: Int): Boolean = {
    mx >= this.xPosition && my >= this.yPosition && mx < this.xPosition + this.width && my < this.yPosition + this.height
  }
}
