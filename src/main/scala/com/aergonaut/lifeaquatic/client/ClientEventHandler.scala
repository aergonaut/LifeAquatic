package com.aergonaut.lifeaquatic.client

import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.constants.Textures
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.client.event.TextureStitchEvent

object ClientEventHandler {
  @SubscribeEvent()
  def textureStitch(textureStitchEvent: TextureStitchEvent.Pre): Unit = {
    if (textureStitchEvent.map.getTextureType == 0) {
      ModBlocks.AqueousCopper.setIcons(textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_copper"), textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_copper_flow"))
      ModBlocks.AqueousTin.setIcons(textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_tin"), textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_tin_flow"))
      ModBlocks.AqueousIron.setIcons(textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_iron"), textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_iron_flow"))
      ModBlocks.AqueousGold.setIcons(textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_gold"), textureStitchEvent.map.registerIcon(s"${Textures.ResourcePrefix}fluid/liquid_gold_flow"))
    }
  }
}
