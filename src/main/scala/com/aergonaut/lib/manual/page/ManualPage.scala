package com.aergonaut.lib.manual.page

import com.aergonaut.lib.manual.TManual
import com.aergonaut.lib.manual.gui.TGuiManual

class ManualPage(val text: Option[String]) {
  def renderPage(gui: TGuiManual, manual: TManual, yOffset: Int): Unit = {
    text.foreach(s => gui.font.drawSplitString(s, 10 + gui.left, yOffset + gui.top, 120, manual.textColor))
  }

  def addButtons(gui: TGuiManual): Unit = {}
}

object ManualPage {
  def apply(text: String): ManualPage = new ManualPage(Some(text))
}
