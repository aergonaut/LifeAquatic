package com.aergonaut.lib.manual

import com.aergonaut.lib.manual.gui.{GuiPagination, TGuiManual}

class ManualChapter(theManual: TManual, theParent: Option[TManualSection], localizedName: String, pages: Seq[ManualPage]) extends TManualSection {
  override val name: String = localizedName

  override val manual: TManual = theManual

  override val parent: Option[TManualSection] = theParent

  private var activePage = 0

  override def renderSection(gui: TGuiManual): Unit = {
    val fontHeight = gui.fontHeight

    if (activePage == 0) {
      renderTitle(gui)
    }

    val yOffset = if (activePage == 0) 3 * fontHeight else 16

    gui.font.drawSplitString(pages(activePage).text, 10 + gui.left, yOffset + gui.top, 120, manual.textColor)
  }

  override def addButtons(gui: TGuiManual): Unit = {
    if (pages.size > 1) {
      // prev and next
      gui.addButton(GuiPagination.Previous(gui, this, gui.nextButtonId, gui.left + 0, gui.top + gui.guiHeight + 2))
      gui.addButton(GuiPagination.Forward(gui, this, gui.nextButtonId, gui.left + gui.guiWidth - 21, gui.top + gui.guiHeight + 2))
    }
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
