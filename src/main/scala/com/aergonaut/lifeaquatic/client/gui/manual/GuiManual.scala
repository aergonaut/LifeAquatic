package com.aergonaut.lifeaquatic.client.gui.manual

import com.aergonaut.lifeaquatic.constants.Textures
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

class GuiManual extends GuiScreen {
  final val guiWidth = 148
  final val guiHeight = 208

  var left, top: Int = _

  final val guiResource = new ResourceLocation(Textures.Gui.Manual)

  override def initGui(): Unit = {
    left = (width - guiWidth) / 2
    top = (height - guiHeight) / 2

    buttonList.clear()
  }

  override def drawScreen(mx: Int, my: Int, uselessFloat: Float): Unit = {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)

    Minecraft.getMinecraft.getTextureManager.bindTexture(guiResource)
    drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight)

    super.drawScreen(mx, my, uselessFloat)
  }
}

object GuiManual {
  final val Instance = new GuiManual
}
