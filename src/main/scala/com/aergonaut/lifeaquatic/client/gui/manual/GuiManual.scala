package com.aergonaut.lifeaquatic.client.gui.manual

import com.aergonaut.lifeaquatic.client.ClientUtil
import com.aergonaut.lifeaquatic.constants.Textures
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.util.{EnumChatFormatting, ResourceLocation}
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

    val title = "Undersea Almanac"
    drawCenteredStringScaled(s"${EnumChatFormatting.BOLD}${title}", left + (guiWidth/2), top + 16, 0x60ACFF, 1, true)

    super.drawScreen(mx, my, uselessFloat)
  }

  def drawCenteredStringScaled(s: String, x: Int, y: Int, color: Int, scale: Int, shadow: Boolean): Unit = {
    val font = ClientUtil.font

    val xx = math.floor((x / scale) - (font.getStringWidth(s) / 2)).toInt
    val yy = math.floor((y / scale) - (font.FONT_HEIGHT / 2)).toInt

    if (scale != 1) GL11.glScalef(scale, scale, scale)

    font.drawString(s, xx, yy, color, shadow)

    if (scale != 1) GL11.glScalef(1/scale, 1/scale, 1/scale)
  }
}

object GuiManual {
  final val Instance = new GuiManual
}
