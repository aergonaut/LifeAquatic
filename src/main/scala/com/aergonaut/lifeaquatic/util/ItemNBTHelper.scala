package com.aergonaut.lifeaquatic.util

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

object ItemNBTHelper {
  def getNBT(stack: ItemStack): NBTTagCompound = {
    initNBT(stack)
    stack.getTagCompound
  }

  private def initNBT(stack: ItemStack): Unit = {
    if (!stack.hasTagCompound) {
      stack.setTagCompound(new NBTTagCompound())
    }
  }

  def setBoolean(stack: ItemStack, tag: String, b: Boolean): Unit = getNBT(stack).setBoolean(tag, b)

  def setString(stack: ItemStack, tag: String, s: String): Unit = getNBT(stack).setString(tag, s)

  def setInt(stack: ItemStack, tag: String, i: Int): Unit = getNBT(stack).setInteger(tag, i)

  def getBoolean(stack: ItemStack, tag: String): Boolean = getNBT(stack).getBoolean(tag)

  def getString(stack: ItemStack, tag: String): String = getNBT(stack).getString(tag)

  def getInt(stack: ItemStack, tag: String): Int = getNBT(stack).getInteger(tag)
}
