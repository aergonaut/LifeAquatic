package com.aergonaut.lib.manual

import com.aergonaut.lib.manual.gui.TGuiManual

class ManualIndex(theManual: TManual, localizedName: String, sections: Seq[TManualSection]) extends TManualSection {
  override val name: String = localizedName

  override val manual: TManual = theManual

  override val parent: Option[TManualSection] = None

  override def renderSection(gui: TGuiManual): Unit = {
    renderTitle(gui)
  }

  override def addButtons(gui: TGuiManual): Unit = {
    val fontHeight = gui.fontHeight
    sections.foreach(section => {
      val idx = sections indexOf section
      section.renderOnIndex(gui, 10, (3*fontHeight) + idx * fontHeight)
    })
    super.addButtons(gui)
  }
}

object ManualIndex {
  def apply(theManual: TManual, localizedName: String, sections: Seq[TManualSection]): ManualIndex = new ManualIndex(theManual, localizedName, sections)
}
