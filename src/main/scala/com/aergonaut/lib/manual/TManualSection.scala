package com.aergonaut.lib.manual

import com.aergonaut.lib.manual.gui.{GuiTextLink, TGuiManual}

trait TManualSection {
  val name: String

  val manual: TManual

  def renderSection(gui: TGuiManual): Unit

  def renderOnIndex(gui: TGuiManual, x: Int, y: Int): Unit = {
    gui.addButton(new GuiTextLink(gui, this, gui.nextButtonId, x + gui.left, y + gui.top, gui.guiWidth, gui.fontHeight))
  }
}
