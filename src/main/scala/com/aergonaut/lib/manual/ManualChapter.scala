package com.aergonaut.lib.manual

import com.aergonaut.lib.manual.gui.TGuiManual

class ManualChapter(theManual: TManual, localizedName: String, pages: Seq[ManualPage]) extends TManualSection {
  override val name: String = localizedName

  override val manual: TManual = theManual

  override def renderSection(gui: TGuiManual): Unit = {
    renderTitle(gui)
  }
}

object ManualChapter {
  def apply(theManual: TManual, localizedName: String, pages: Seq[ManualPage]): ManualChapter = new ManualChapter(theManual, localizedName, pages)

  def apply(theManual: TManual, localizedName: String): ManualChapter = apply(theManual, localizedName, Array[ManualPage]())
}
