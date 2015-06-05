package com.aergonaut.lib.manual

import com.aergonaut.lib.manual.gui.{GuiTextLink, TGuiManual}
import net.minecraft.util.EnumChatFormatting

trait TManualSection {
  val name: String

  val manual: TManual

  def renderSection(gui: TGuiManual): Unit

  def renderOnIndex(gui: TGuiManual, x: Int, y: Int): Unit = {
    gui.addButton(new GuiTextLink(gui, this, gui.nextButtonId, x + gui.left, y + gui.top, gui.guiWidth, gui.fontHeight))
  }

  protected def renderTitle(gui: TGuiManual): Unit = {
    val title = name
    val titleColor = manual.titleColor
    gui.drawCenteredStringScaled(s"${EnumChatFormatting.BOLD}${title}", gui.left + (gui.guiWidth/2), gui.top + 16, titleColor, 1, true)
  }
}
