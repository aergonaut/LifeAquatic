package com.aergonaut.lifeaquatic.recipe

import cofh.api.core.IInitializer
import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.item.ModItems
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

import scala.collection.mutable

object CalcinatorRecipes extends IInitializer {
  val recipes: mutable.ArrayBuffer[CalcinatorRecipe] = mutable.ArrayBuffer()

  def findRecipe(input: ItemStack): Option[CalcinatorRecipe] = recipes.find(_.matches(input))

  override def preInit(): Boolean = true

  override def postInit(): Boolean = {
    recipes ++= Array(
      CalcinatorRecipe(ItemHelper.stack(ModBlocks.Ore, 1, 0), ItemHelper.cloneStack(ModItems.CopperAsh, 2)),
      CalcinatorRecipe(ItemHelper.stack(ModBlocks.Ore, 1, 1), ItemHelper.cloneStack(ModItems.TinAsh, 2)),
      CalcinatorRecipe(ItemHelper.stack(ModBlocks.Ore, 1, 2), ItemHelper.cloneStack(ModItems.NickelAsh, 2)),
      CalcinatorRecipe(ItemHelper.stack(Blocks.iron_ore), ItemHelper.cloneStack(ModItems.IronAsh, 2)),
      CalcinatorRecipe(ItemHelper.stack(Blocks.gold_ore), ItemHelper.cloneStack(ModItems.GoldAsh, 2))
    )

    true
  }

  override def initialize(): Boolean = true
}
