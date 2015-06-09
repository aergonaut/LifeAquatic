package com.aergonaut.lib.manual.page

import com.aergonaut.lib.manual.TManual
import com.aergonaut.lib.manual.gui.TGuiManual
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.item.ItemStack
import org.lwjgl.opengl.{GL12, GL11}

class ItemPage(val stack: ItemStack, override val text: Option[String]) extends ManualPage(text) {
  override def renderPage(gui: TGuiManual, manual: TManual, yOffset: Int): Unit = {
    GL11.glEnable(GL12.GL_RESCALE_NORMAL)
    RenderHelper.enableGUIStandardItemLighting()

    val scale = 2.0F
    GL11.glScalef(scale, scale, scale)
    RenderItem.getInstance().renderWithColor = true
    RenderItem.getInstance().renderItemIntoGUI(gui.font, Minecraft.getMinecraft.renderEngine, stack, gui.left - (gui.guiWidth / 4) - 4, yOffset - 5)
    GL11.glScalef(1/scale, 1/scale, 1/scale)

    RenderHelper.disableStandardItemLighting()
    GL11.glDisable(GL12.GL_RESCALE_NORMAL)
    GL11.glEnable(GL11.GL_BLEND)

//    text.foreach(s => gui.font.drawSplitString(s, 10 + gui.left, yOffset + 40 + gui.top, 120, manual.textColor))
    super.renderPage(gui, manual, yOffset + 40)
  }
}

object ItemPage {
  def apply(stack: ItemStack, text: String): ItemPage = new ItemPage(stack, Some(text))
}
