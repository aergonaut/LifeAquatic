package com.aergonaut.lifeaquatic.client

import com.aergonaut.lib.manual.TManual
import com.aergonaut.lifeaquatic.constants.Constants
import net.minecraft.util.StatCollector

abstract class ManualBase extends TManual {
  final val manualBaseScope = s"${Constants.ModID}.manuals"
  val localizationScope: String

  def formatText(localizationKey: String): String = {
    val translated = StatCollector.translateToLocal(s"${manualBaseScope}.${localizationScope}.${localizationKey}")
    translated.replaceAll("<br>", "\n")
  }
}
