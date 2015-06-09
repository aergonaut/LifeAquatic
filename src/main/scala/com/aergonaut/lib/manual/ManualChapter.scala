package com.aergonaut.lib.manual

import com.aergonaut.lib.manual.gui.{GuiPagination, TGuiManual}
import com.aergonaut.lib.manual.page.ManualPage

class ManualChapter(override val manual: TManual, override val parent: Option[TManualSection], override val name: String, pages: Seq[ManualPage]) extends TManualSection {
  private var activePage = 0

  override def renderSection(gui: TGuiManual): Unit = {
    val fontHeight = gui.fontHeight

    renderTitle(gui)

    val yOffset = 3 * fontHeight

    pages(activePage).renderPage(gui, manual, yOffset)
  }

  override def addButtons(gui: TGuiManual): Unit = {
    if (pages.size > 1) {
      // prev and next
      gui.addButton(GuiPagination.Previous(gui, this, gui.nextButtonId, gui.left + 0, gui.top + gui.guiHeight + 2))
      gui.addButton(GuiPagination.Forward(gui, this, gui.nextButtonId, gui.left + gui.guiWidth - 21, gui.top + gui.guiHeight + 2))
    }
    pages(activePage).addButtons(gui)
    super.addButtons(gui)
  }

  def nextPage(): Boolean = {
    if (activePage < pages.size - 1) {
      activePage += 1
      true
    } else {
      false
    }
  }

  def previousPage(): Boolean = {
    if (activePage > 0) {
      activePage -= 1
      true
    } else {
      false
    }
  }
}

object ManualChapter {
  def apply(theManual: TManual, parent: Option[TManualSection], localizedName: String, pages: Seq[ManualPage]): ManualChapter = new ManualChapter(theManual, parent, localizedName, pages)

  def apply(theManual: TManual, parent: Option[TManualSection], localizedName: String): ManualChapter = apply(theManual, parent, localizedName, Array[ManualPage]())
}
