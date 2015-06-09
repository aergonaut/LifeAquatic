package com.aergonaut.lifeaquatic.common.gui

import cofh.lib.gui.slot.SlotAcceptValid
import com.aergonaut.lib.gui.container.ContainerBase
import com.aergonaut.lifeaquatic.tileentity.TileEntityVat
import net.minecraft.entity.player.{EntityPlayer, InventoryPlayer}
import net.minecraft.inventory.ICrafting
import net.minecraft.item.ItemStack

class ContainerVat(inventoryPlayer: InventoryPlayer, tileEntity: TileEntityVat) extends ContainerBase {
  // add input slot
  addSlotToContainer(new SlotAcceptValid(tileEntity, TileEntityVat.InputSlot, 57, 18))

  // add catalyst slot
  addSlotToContainer(new SlotAcceptValid(tileEntity, TileEntityVat.CatalystSlot, 57, 49))

  addPlayerSlots(inventoryPlayer, 8, 84)

  override def canInteractWith(p_75145_1_ : EntityPlayer): Boolean = true

  override def transferStackInSlot(player: EntityPlayer, slot: Int): ItemStack = null
}
