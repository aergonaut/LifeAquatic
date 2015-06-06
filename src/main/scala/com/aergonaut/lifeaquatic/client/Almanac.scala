package com.aergonaut.lifeaquatic.client

import com.aergonaut.lib.manual._

class Almanac extends ManualBase {
  override val localizationScope: String = "almanac"

  override val title: String = formatText("title")
  override val titleColor: Int = 0x60ACFF

  override val linkColor: Int = textColor
  override val hoverColor: Int = 0x60ACFF

  override val index: ManualIndex = ManualIndex(this, title, Array(
    ManualChapter(this, formatText("chapters.introduction.title"), Array(
      ManualPage(formatText("chapters.introduction.page0")),
      ManualPage(formatText("chapters.introduction.page1"))
    ))
  ))
}
