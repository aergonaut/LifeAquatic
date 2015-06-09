package com.aergonaut.lifeaquatic.common.gui

import cofh.lib.gui.slot.{SlotRemoveOnly, SlotAcceptValid}
import com.aergonaut.lib.gui.container.ContainerBase
import com.aergonaut.lifeaquatic.tileentity.TileEntityCalcinator
import com.aergonaut.lifeaquatic.util.Logger
import net.minecraft.entity.player.{InventoryPlayer, EntityPlayer}
import net.minecraft.item.ItemStack

class ContainerCalcinator(playerInventory: InventoryPlayer, tile: TileEntityCalcinator) extends ContainerBase {
  addSlotToContainer(new SlotAcceptValid(tile, TileEntityCalcinator.InputSlot, 62, 14))

  addSlotToContainer(new SlotAcceptValid(tile, TileEntityCalcinator.FuelSlot, 62, 54))

  addSlotToContainer(new SlotRemoveOnly(tile, TileEntityCalcinator.OutputSlot, 122, 33))

  addPlayerSlots(playerInventory, 8, 84)

  override def canInteractWith(p_75145_1_ : EntityPlayer): Boolean = true

  override def transferStackInSlot(player: EntityPlayer, slot: Int): ItemStack = null
}
