package com.aergonaut.lib.manual

import com.aergonaut.lib.manual.gui.TGuiManual

class ManualIndex(theManual: TManual, localizedName: String, sections: Seq[TManualSection]) extends TManualSection {
  override val name: String = localizedName

  override val manual: TManual = theManual

  override def renderSection(gui: TGuiManual): Unit = {
    val fontHeight = gui.fontHeight

    renderTitle(gui)

    sections.foreach(section => {
      val idx = sections indexOf section
      section.renderOnIndex(gui, 10, (3*fontHeight) + idx * fontHeight)
    })
  }
}

object ManualIndex {
  def apply(theManual: TManual, localizedName: String, sections: Seq[TManualSection]): ManualIndex = new ManualIndex(theManual, localizedName, sections)
}
