package com.aergonaut.lib.manual

class ManualPage(theText: String) {
  final val text: String = theText
}

object ManualPage {
  def apply(text: String): ManualPage = new ManualPage(text)
}
