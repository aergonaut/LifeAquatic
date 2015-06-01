package com.aergonaut.lib.manual.gui

import com.aergonaut.lib.manual.TManual
import com.aergonaut.lifeaquatic.client.ClientUtil
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.util.{EnumChatFormatting, ResourceLocation}
import org.lwjgl.opengl.GL11

abstract class TGuiManual extends GuiScreen {
  val guiWidth: Int
  val guiHeight: Int

  var left, top: Int = _

  val texture: ResourceLocation

  val manual: TManual

  override def initGui(): Unit = {
    left = (width - guiWidth) / 2
    top = (height - guiHeight) / 2

    buttonList.clear()
  }

  override def drawScreen(mx: Int, my: Int, uselessFloat: Float): Unit = {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)

    Minecraft.getMinecraft.getTextureManager.bindTexture(texture)
    drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight)

    val title = manual.title
    val titleColor = manual.titleColor
    drawCenteredStringScaled(s"${EnumChatFormatting.BOLD}${title}", left + (guiWidth/2), top + 16, titleColor, 1, true)

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

