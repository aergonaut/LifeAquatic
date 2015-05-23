package com.aergonaut.lifeaquatic.item

import cpw.mods.fml.common.registry.GameRegistry

object ModItems {
  final val pearl: ItemBase = new Pearl
  final val redCap: ItemBase = new RedCap

  def init(): Unit = {
    GameRegistry.registerItem(pearl, "Pearl")
    GameRegistry.registerItem(redCap, "RedCap")
  }
}
