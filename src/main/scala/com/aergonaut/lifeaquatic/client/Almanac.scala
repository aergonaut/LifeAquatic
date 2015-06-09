package com.aergonaut.lifeaquatic.client

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.manual.page._
import com.aergonaut.lib.manual._
import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.multiblock.MultiBlockFurnace
import com.aergonaut.lifeaquatic.recipe.Recipes

class Almanac extends ManualBase {
  override val localizationScope: String = "almanac"

  override val title: String = formatText("title")
  override val titleColor: Int = 0x60ACFF

  override val linkColor: Int = textColor
  override val hoverColor: Int = 0x60ACFF

  override val index: ManualIndex = ManualIndex(this, title, Array(
    ManualChapter(this, Some(index), formatText("chapters.introduction.title"), Array(
      ManualPage(formatText("chapters.introduction.page0")),
      ManualPage(formatText("chapters.introduction.page1"))
    )),
    ManualChapter(this, Some(index), formatText("chapters.ores.title"), Array(
      ManualPage(formatText("chapters.ores.page0")),
      ItemPage(ItemHelper.stack(ModBlocks.Ore, 1, 0), formatText("chapters.ores.page1")),
      ItemPage(ItemHelper.stack(ModBlocks.Ore, 1, 1), formatText("chapters.ores.page2")),
      ItemPage(ItemHelper.stack(ModBlocks.Ore, 1, 2), formatText("chapters.ores.page3"))
    )),

    ManualIndex(this, formatText("chapters.tools.title"), Array(
      ManualChapter(this, Some(index), formatText("chapters.tools.wrench.title"), Array(
        ItemPage(ItemHelper.stack(ModItems.Wrench, 1), formatText("chapters.tools.wrench.page0")),
        CraftingPage(Array(Recipes.recipeWrench))
      )),
      ManualChapter(this, Some(index), formatText("chapters.tools.lens.title"), Array(
        ItemPage(ItemHelper.stack(ModItems.Lens, 1), formatText("chapters.tools.lens.page0")),
        CraftingPage(Array(Recipes.recipeLens))
      ))
    )),

    ManualIndex(this, formatText("chapters.machines.title"), Array(
      ManualChapter(this, Some(index), formatText("chapters.machines.furnace.title"), Array(
        MultiblockPage(formatText("chapters.machines.furnace.page0"), MultiBlockFurnace),
        CraftingPage(Array(Recipes.recipeFurnaceBlock))
      ))
    ))
  ))
}
