package com.aergonaut.lifeaquatic.recipe

import cofh.lib.util.helpers.ItemHelper
import net.minecraft.item.ItemStack

class CalcinatorRecipe(val input: ItemStack, val output: ItemStack) {
  def matches(stack: ItemStack): Boolean =
    ItemHelper.craftingEquivalent(stack, input, ItemHelper.getOreName(input), null)
}

object CalcinatorRecipe {
  def apply(input: ItemStack, output: ItemStack): CalcinatorRecipe = new CalcinatorRecipe(input, output)
}
