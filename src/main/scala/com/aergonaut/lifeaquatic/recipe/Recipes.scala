package com.aergonaut.lifeaquatic.recipe

import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.item.ModItems
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack

object Recipes {

  def initShapelessRecipes(): Unit = {
    GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ToolManual),
      new ItemStack(Items.book),
      new ItemStack(ModItems.Pearl)
    )
  }

  def initShapedRecipes(): Unit = {
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

    // linen chest
    GameRegistry.addRecipe(new ItemStack(ModItems.LinenChest),
      "w w",
      "wbw",
      "www",
      char2Character('w'), new ItemStack(Blocks.wool, 1, 3),
      char2Character('b'), new ItemStack(Blocks.wooden_button)
    )
  }

  def init(): Unit = {
    initShapedRecipes()

    initShapelessRecipes()
  }
}
