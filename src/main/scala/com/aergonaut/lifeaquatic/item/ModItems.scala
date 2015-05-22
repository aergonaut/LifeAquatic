package com.aergonaut.lifeaquatic.item

import cpw.mods.fml.common.registry.GameRegistry

object ModItems {
  final val pearl: ItemBase = new Pearl

  def init(): Unit = {
    GameRegistry.registerItem(pearl, "Pearl")
  }
}
