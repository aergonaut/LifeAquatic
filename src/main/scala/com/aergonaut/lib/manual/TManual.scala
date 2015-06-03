package com.aergonaut.lib.manual

trait TManual {
  val title: String
  val titleColor: Int

  val linkColor: Int
  val hoverColor: Int

  val textColor: Int = 0x555555

  val index: ManualIndex
}
