package com.aergonaut.lib.manual.gui

import com.aergonaut.lib.manual.{TManual, TManualSection}
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.{FontRenderer, GuiButton, GuiScreen}
import net.minecraft.util.{EnumChatFormatting, ResourceLocation}
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent
import net.minecraftforge.common.MinecraftForge
import org.lwjgl.opengl.GL11

abstract class TGuiManual extends GuiScreen {
  val guiWidth: Int
  val guiHeight: Int

  var left: Int = _
  var top: Int = _

  val texture: ResourceLocation

  val manual: TManual

  protected var activeSection: Option[TManualSection] = None

  protected var buttons: Vector[GuiButton] = Vector[GuiButton]()

  override def initGui(): Unit = {
    left = (width - guiWidth) / 2
    top = (height - guiHeight) / 2

    buttons = Vector[GuiButton]()
  }

  override def drawScreen(mx: Int, my: Int, uselessFloat: Float): Unit = {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)

    // draw the book
    Minecraft.getMinecraft.getTextureManager.bindTexture(texture)
    drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight)

    // determine how to render the active section
    activeSection match {
      // if there is an active section, use its render method
      case Some(section) => section.renderSection(this)

      // in the case there is no active page, we render the "home page"
      case None => {
        // render the book's title
        val title = manual.title
        val titleColor = manual.titleColor
        drawCenteredStringScaled(s"${EnumChatFormatting.BOLD}${title}", left + (guiWidth/2), top + 16, titleColor, 1, true)

        // now render the index
        manual.index.renderSection(this)
      }
    }

    // handling button drawing on our own...
    buttons.foreach(_.drawButton(mc, mx, my))

    super.drawScreen(mx, my, uselessFloat)
  }

  override def mouseClicked(x: Int, y: Int, buttonPressed: Int): Unit = {
    super.mouseClicked(x, y, buttonPressed)

    // also handling button clicking on our own...
    if (buttonPressed == 0) {
      buttons.filter(_.mousePressed(mc, x, y)).filterNot(b => {
        val event = new ActionPerformedEvent.Pre(this, b, buttonList)
        // if post returns true, the event got canceled
        MinecraftForge.EVENT_BUS.post(event)
      }).foreach(b => {
        b.func_146113_a(this.mc.getSoundHandler)
        actionPerformed(b)
        if (this.equals(mc.currentScreen)) {
          val event = new ActionPerformedEvent.Post(this, b, buttonList)
          MinecraftForge.EVENT_BUS.post(event)
        }
      })
    }
  }

  def drawCenteredStringScaled(s: String, x: Int, y: Int, color: Int, scale: Int, shadow: Boolean): Unit = {
    val xx = math.floor((x / scale) - (font.getStringWidth(s) / 2)).toInt
    val yy = math.floor((y / scale) - (font.FONT_HEIGHT / 2)).toInt

    if (scale != 1) GL11.glScalef(scale, scale, scale)

    font.drawString(s, xx, yy, color, shadow)

    if (scale != 1) GL11.glScalef(1/scale, 1/scale, 1/scale)
  }

  def font: FontRenderer

  def fontHeight: Int = font.FONT_HEIGHT

  def addButton(button: GuiButton): Unit = buttons = buttons :+ button

  def nextButtonId: Int = buttonList.size

  def setActiveSection(target: TManualSection): Unit = {
    activeSection = Some(target)
  }

  def clearActiveSection(): Unit = {
    activeSection = None
  }
}
