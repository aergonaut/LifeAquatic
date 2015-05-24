package com.aergonaut.lifeaquatic.recipe

import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.item.ModItems
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

object Recipes {
  def init(): Unit = {
    // pearl block
    GameRegistry.addRecipe(new ItemStack(ModBlocks.PearlBlock),
      "ppp",
      "ppp",
      "ppp",
      char2Character('p'), new ItemStack(ModItems.Pearl)
    )

    // linen helmet
    GameRegistry.addRecipe(new ItemStack(ModItems.LinenHelmet),
      "rrr",
      "r r",
      "   ",
      char2Character('r'), new ItemStack(Blocks.wool, 1, 14)
    )
    GameRegistry.addRecipe(new ItemStack(ModItems.LinenHelmet),
      "   ",
      "rrr",
      "r r",
      char2Character('r'), new ItemStack(Blocks.wool, 1, 14)
    )
  }
}
