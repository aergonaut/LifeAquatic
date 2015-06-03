package com.aergonaut.lifeaquatic.client.gui

import com.aergonaut.lifeaquatic.client.Almanac
import com.aergonaut.lifeaquatic.constants.Textures
import net.minecraft.util.ResourceLocation

class GuiAlmanac extends GuiManualBase {
  override val guiWidth: Int = 148
  override val guiHeight: Int = 208
  override val texture: ResourceLocation = new ResourceLocation(Textures.Gui.Manual)

  override val manual: Almanac = new Almanac
}

object GuiAlmanac {
  final val Instance = new GuiAlmanac
}
