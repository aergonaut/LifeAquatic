package com.aergonaut.lifeaquatic.client

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.FontRenderer

object ClientUtil {
  def font: FontRenderer = {
    Minecraft.getMinecraft.fontRenderer
  }
}
