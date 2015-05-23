package com.aergonaut.lifeaquatic.recipe

import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.item.ModItems
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack

object Recipes {
  def init(): Unit = {
    // pearl block
    GameRegistry.addRecipe(new ItemStack(ModBlocks.pearlBlock),
      "ppp",
      "ppp",
      "ppp",
      'p':java.lang.Character, new ItemStack(ModItems.pearl)
    )
  }
}
