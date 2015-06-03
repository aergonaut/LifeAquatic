package com.aergonaut.lifeaquatic.client.gui

import com.aergonaut.lib.manual.gui.TGuiManual
import com.aergonaut.lifeaquatic.client.ClientUtil
import net.minecraft.client.gui.FontRenderer

abstract class GuiManualBase extends TGuiManual {
  override def font: FontRenderer = ClientUtil.font
}
