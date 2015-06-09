package com.aergonaut.lib.util

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.FontRenderer
import net.minecraft.util.ResourceLocation

object ClientUtil {
  def font: FontRenderer = {
    Minecraft.getMinecraft.fontRenderer
  }

  def bindTexture(texture: String): Unit = bindTexture(new ResourceLocation(texture))

  def bindTexture(texture: ResourceLocation): Unit = {
    Minecraft.getMinecraft.getTextureManager.bindTexture(texture)
  }
}
