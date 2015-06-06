package com.aergonaut.lib.manual.gui

import com.aergonaut.lib.manual.ManualChapter
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton

abstract class GuiPagination(gui: TGuiManual, controlledChapter: ManualChapter, id: Int, x: Int, y: Int) extends GuiButton(id, x, y, 21, 9, "") with TGuiHoverable {
  final val texture = gui.texture

  val xOffset: Int

  override def drawButton(minecraft: Minecraft, mx: Int, my: Int): Unit = {
    val yOffset = if (isHovering(mx, my)) height else 0
    minecraft.getTextureManager.bindTexture(texture)
    drawTexturedModalRect(x, y, xOffset, gui.guiHeight + yOffset, width, height)
  }
}

object GuiPagination {
  object Back {
    def apply(gui: TGuiManual, controlledChapter: ManualChapter, id: Int, x: Int, y: Int): GuiPagination =
      new GuiPagination(gui, controlledChapter, id, x, y) {
        override val xOffset: Int = 0
      }
  }

  object Forward {
    def apply(gui: TGuiManual, controlledChapter: ManualChapter, id: Int, x: Int, y: Int): GuiPagination =
      new GuiPagination(gui, controlledChapter, id, x, y) {
        override val xOffset: Int = 21
      }
  }
}
