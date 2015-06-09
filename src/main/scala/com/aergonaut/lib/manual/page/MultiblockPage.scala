package com.aergonaut.lib.manual.page

import cofh.lib.render.RenderHelper
import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.manual.TManual
import com.aergonaut.lib.manual.gui.TGuiManual
import com.aergonaut.lib.multiblock.TMultiBlock
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import org.lwjgl.opengl.{GL11, GL12}

class MultiblockPage(text: Option[String], val multiblock: TMultiBlock) extends ManualPage(text) {
  var tick = 0
  var paused = false

  override def renderPage(gui: TGuiManual, manual: TManual, yOffset: Int): Unit = {
    if (!paused) tick += 1

    GL11.glDisable(GL11.GL_DEPTH_TEST)
    GL11.glEnable(GL12.GL_RESCALE_NORMAL)
    RenderHelper.enableGUIStandardItemLighting()
    RenderItem.getInstance().renderWithColor = true

    val structure = multiblock.structure

    GL11.glPushMatrix()

    val xOffset = gui.left + gui.guiWidth / 2

    GL11.glTranslatef(xOffset, gui.top + yOffset, 0)

    val displayWidth = multiblock.width * 10 - multiblock.length * 10
    val halfDW = displayWidth / 2

    val displayHeight = (multiblock.height - 1) * 12 + multiblock.width * 5 + multiblock.length * 5

    var i = 0

    val limiter = (tick / 40) % (multiblock.width * multiblock.height * multiblock.length)

    var highlighted: Option[ItemStack] = None

    for (h <- structure.indices) {
      val layerStructure = structure(h)
      for (l <- layerStructure.length - 1 to 0 by -1) {
        val lenStructure = layerStructure(l)
        for (w <- lenStructure.length - 1 to 0 by -1) {
          val currentBlock = lenStructure(w)

          val x = halfDW - 10 * w + 10 * l - 7
          val y = displayHeight - 5 * w - 5 * l - 12 * h

          GL11.glTranslatef(0, 0, 1)
          if (!currentBlock.isItemEqual(ItemHelper.stack(Blocks.air)) && i <= limiter) {
            i += 1
            RenderItem.getInstance().renderItemIntoGUI(gui.font, Minecraft.getMinecraft.renderEngine, currentBlock, x, y)

            val xx = xOffset + x
            val xxx = xx + 16
            val yy = gui.top + yOffset + y
            val yyy = yy + 16
            if (gui.mouseX >= xx && gui.mouseX < xxx && gui.mouseY >= yy && gui.mouseY < yyy) highlighted = Some(lenStructure(w))
          }
        }
      }
    }

    GL11.glPopMatrix()

    GL11.glDisable(GL12.GL_RESCALE_NORMAL)
    GL11.glEnable(GL11.GL_BLEND)
    GL11.glEnable(GL11.GL_DEPTH_TEST)

    highlighted.foreach(highlightedStack => gui.drawTooltip(highlightedStack, gui.mouseX, gui.mouseY))

    super.renderPage(gui, manual, yOffset + displayHeight + 32)
  }
}

object MultiblockPage {
  def apply(text: String, mb: TMultiBlock): MultiblockPage = new MultiblockPage(Some(text), mb)
}
