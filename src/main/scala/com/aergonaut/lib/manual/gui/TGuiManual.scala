package com.aergonaut.lib.manual.gui

import com.aergonaut.lib.manual.{TManual, TManualSection}
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.{FontRenderer, GuiButton, GuiScreen}
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent
import net.minecraftforge.common.MinecraftForge
import org.lwjgl.opengl.GL11

import scala.collection.mutable

abstract class TGuiManual extends GuiScreen {
  val guiWidth: Int
  val guiHeight: Int

  var left: Int = 0
  var top: Int = 0

  val texture: ResourceLocation

  val manual: TManual

  var mouseX: Int = 0
  var mouseY: Int = 0

  protected var activeSection: TManualSection = _

  protected var buttons: Vector[GuiButton] = Vector[GuiButton]()

  protected val history: mutable.Stack[TManualSection] = mutable.Stack()

  override def initGui(): Unit = {
    left = (width - guiWidth) / 2
    top = (height - guiHeight) / 2

    buttonList.clear()
    buttons = Vector[GuiButton]()

    // add the buttons from the activeSection
    activeSection.addButtons(this)
  }

  override def drawScreen(mx: Int, my: Int, uselessFloat: Float): Unit = {
    mouseX = mx
    mouseY = my

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)

    // draw the book
    Minecraft.getMinecraft.getTextureManager.bindTexture(texture)
    drawTexturedModalRect(left, top, 0, 0, guiWidth, guiHeight)

    activeSection.renderSection(this)

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

  def addButton(button: GuiButton): Unit = {
    buttons = buttons :+ button
  }

  def nextButtonId: Int = buttonList.size

  def setActiveSection(target: TManualSection): Boolean = {
    history.push(activeSection)
    activeSection = target
    true
  }

  def clearActiveSection(): Unit = {
    activeSection = manual.index
  }

  override def actionPerformed(button: GuiButton): Unit = {
    initGui()
  }

  def navigateBack(): Boolean = {
    activeSection = history.pop()
    true
  }

  override def doesGuiPauseGame(): Boolean = false

  def drawTooltip(p_146285_1_ : ItemStack, p_146285_2_ : Int, p_146285_3_ : Int): Unit = renderToolTip(p_146285_1_, p_146285_2_, p_146285_3_)
}
