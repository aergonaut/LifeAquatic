package com.aergonaut.lib.manual.gui

import com.aergonaut.lib.manual.TManual
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import org.lwjgl.opengl.GL11

class GuiBackButton(gui: TGuiManual, manual: TManual, id: Int, x: Int, y: Int) extends GuiButton(id, x, y, 12, 9, "") with TGuiHoverable {
  final val texture = gui.texture

  override def drawButton(minecraft: Minecraft, mx: Int, my: Int): Unit = {
    val xOffset = 42
    val yOffset = if (isHovering(mx, my)) height else 0
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
    minecraft.getTextureManager.bindTexture(texture)
    drawTexturedModalRect(x, y, xOffset, gui.guiHeight + yOffset, width, height)
  }

  override def mousePressed(minecraft: Minecraft, mx: Int, my: Int): Boolean = {
    super.mousePressed(minecraft, mx, my) && gui.navigateBack()
  }
}
