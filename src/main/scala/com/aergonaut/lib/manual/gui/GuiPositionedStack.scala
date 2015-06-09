package com.aergonaut.lib.manual.gui

import net.minecraft.item.ItemStack

class GuiPositionedStack(stacks: Option[Seq[Any]], val x: Int, val y: Int) {
  def this(stack: ItemStack, x: Int, y: Int) = this(Some(Vector(stack)), x, y)

  def getStack(): Option[ItemStack] = stacks.map(stacks => {
    stacks.size match {
      case 1 => stacks(0).asInstanceOf[ItemStack]
      case size => stacks((System.nanoTime() / 1000000000 % size).toInt).asInstanceOf[ItemStack]
    }
  })
}
