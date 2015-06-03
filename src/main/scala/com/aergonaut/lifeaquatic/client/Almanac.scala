package com.aergonaut.lifeaquatic.client

import com.aergonaut.lib.manual._

class Almanac extends ManualBase {
  override val title: String = "Undersea Almanac"
  override val titleColor: Int = 0x60ACFF

  override val linkColor: Int = textColor
  override val hoverColor: Int = 0x60ACFF

  override val index: ManualIndex = new ManualIndex(this, "root", Array(
    ManualChapter(this, "Ores and Resources"),
    ManualChapter(this, "Aquatic Enchantment")
  ))
}
