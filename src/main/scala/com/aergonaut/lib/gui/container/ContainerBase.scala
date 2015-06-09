package com.aergonaut.lib.gui.container

import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.{Container, Slot}

abstract class ContainerBase extends Container {
  protected def addPlayerSlots(inventoryPlayer: InventoryPlayer, x: Int, y: Int): Unit = {
    // add slots for player inventory
    for (i <- 0 until 3) {
      for (j <- 0 until 9) {
        addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, x + j * 18, y + i * 18))
      }
    }

    // add slots for player hotbar
    for (i <- 0 until 9) {
      addSlotToContainer(new Slot(inventoryPlayer, i, x + i * 18, y + 58))
    }
  }
}
