package com.aergonaut.lib.manual.gui

import com.aergonaut.lib.manual.ManualChapter
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import org.lwjgl.opengl.GL11

abstract class GuiPagination(gui: TGuiManual, controlledChapter: ManualChapter, id: Int, x: Int, y: Int) extends GuiButton(id, x, y, 21, 9, "") with TGuiHoverable {
  final val texture = gui.texture

  val xOffset: Int

  override def drawButton(minecraft: Minecraft, mx: Int, my: Int): Unit = {
    val yOffset = if (isHovering(mx, my)) height else 0
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
    minecraft.getTextureManager.bindTexture(texture)
    drawTexturedModalRect(x, y, xOffset, gui.guiHeight + yOffset, width, height)
  }
}

object GuiPagination {
  object Previous {
    def apply(gui: TGuiManual, controlledChapter: ManualChapter, id: Int, x: Int, y: Int): GuiPagination =
      new GuiPagination(gui, controlledChapter, id, x, y) {
        override val xOffset: Int = 0

        override def mousePressed(minecraft: Minecraft, mx: Int, my: Int): Boolean = {
          super.mousePressed(minecraft, mx, my) && controlledChapter.previousPage()
        }
      }
  }

  object Forward {
    def apply(gui: TGuiManual, controlledChapter: ManualChapter, id: Int, x: Int, y: Int): GuiPagination =
      new GuiPagination(gui, controlledChapter, id, x, y) {
        override val xOffset: Int = 21

        override def mousePressed(minecraft: Minecraft, mx: Int, my: Int): Boolean = {
          super.mousePressed(minecraft, mx, my) && controlledChapter.nextPage()
        }
      }
  }
}
