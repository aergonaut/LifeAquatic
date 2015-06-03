package com.aergonaut.lib.manual

class ManualPage(text: String)

object ManualPage {
  def apply(text: String): ManualPage = new ManualPage(text)
}
