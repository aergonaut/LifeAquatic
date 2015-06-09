package com.aergonaut.lifeaquatic.recipe

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.core.TInitializer
import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.item.tool.LavaPearl
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.{Blocks, Items}
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}

object Recipes extends TInitializer {

  var recipeManual: IRecipe = _
  var recipePearlBlock: IRecipe = _
  var recipeLinenHelmet: IRecipe = _
  var recipeLinenChest: IRecipe = _
  var recipeWrench: IRecipe = _
  var recipeLens: IRecipe = _
  var recipeIronCasing: IRecipe = _
  var recipeBronzeCasing: IRecipe = _
  var recipeBrassCasing: IRecipe = _
  var recipeLavaPearl: IRecipe = _
  var recipeFurnaceBlock: IRecipe = _

  def initShapelessRecipes(): Unit = {
    GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ToolManual),
      new ItemStack(Items.book),
      new ItemStack(ModItems.Pearl)
    )
    recipeManual = getLastRecipeAdded()
  }

  def initShapedRecipes(): Unit = {
    // pearl block
    GameRegistry.addRecipe(new ItemStack(ModBlocks.PearlBlock),
      "ppp",
      "ppp",
      "ppp",
      char2Character('p'), new ItemStack(ModItems.Pearl)
    )
    recipePearlBlock = getLastRecipeAdded()

    // swim trunk - face mask
    GameRegistry.addRecipe(new ItemStack(ModItems.SwimTrunkFaceMask),
      "lll",
      "lgl",
      "lll",
      char2Character('l'), new ItemStack(Items.leather),
      char2Character('g'), new ItemStack(Blocks.glass_pane)
    )

    // swim trunk - fins
    GameRegistry.addRecipe(new ItemStack(ModItems.SwimTrunkFins),
      "l l",
      "l l",
      "l l",
      char2Character('l'), new ItemStack(Items.leather)
    )

    // swim trunk - fins
    GameRegistry.addRecipe(new ItemStack(ModItems.SwimTrunkShorts),
      "lll",
      "l l",
      "   ",
      char2Character('l'), new ItemStack(Items.leather)
    )
    GameRegistry.addRecipe(new ItemStack(ModItems.SwimTrunkShorts),
      "   ",
      "lll",
      "l l",
      char2Character('l'), new ItemStack(Items.leather)
    )

    // linen helmet
    GameRegistry.addRecipe(new ItemStack(ModItems.LinenHelmet),
      "rrr",
      "r r",
      "   ",
      char2Character('r'), new ItemStack(Blocks.wool, 1, 14)
    )
    recipeLinenHelmet = getLastRecipeAdded()

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
    recipeLinenChest = getLastRecipeAdded()

    ItemHelper.addRecipe(ItemHelper.ShapedRecipe(ItemHelper.stack(ModItems.Wrench),
      " i ",
      " ti",
      "p  ",
      char2Character('i'), "ingotIron",
      char2Character('t'), "ingotTin",
      char2Character('p'), ItemHelper.stack(ModItems.Pearl)
    ))
    recipeWrench = getLastRecipeAdded()

    ItemHelper.addRecipe(ItemHelper.ShapedRecipe(ItemHelper.stack(ModItems.Lens),
      "glg",
      " t ",
      " p ",
      char2Character('g'), "ingotGold",
      char2Character('l'), ItemHelper.stack(Blocks.glass_pane),
      char2Character('t'), "ingotTin",
      char2Character('p'), ItemHelper.stack(ModItems.Pearl)
    ))
    recipeLens = getLastRecipeAdded()

    ItemHelper.addShapedOreRecipe(ItemHelper.stack(ModItems.IronCasing),
      "tit",
      "i i",
      "tit",
      char2Character('t'), "ingotTin",
      char2Character('i'), "ingotIron"
    )
    recipeIronCasing = getLastRecipeAdded()

    ItemHelper.addShapedOreRecipe(ItemHelper.stack(ModItems.BronzeCasing),
      "cbc",
      "b b",
      "cbc",
      char2Character('c'), "ingotCopper",
      char2Character('b'), "ingotBronze"
    )
    recipeBronzeCasing = getLastRecipeAdded()

    ItemHelper.addShapedOreRecipe(ItemHelper.stack(ModItems.BrassCasing),
      "zbz",
      "b b",
      "zbz",
      char2Character('z'), "ingotNickel",
      char2Character('b'), "ingotBrass"
    )
    recipeBrassCasing = getLastRecipeAdded()

    val emptyPearl = ItemHelper.stack(ModItems.LavaPearl, 1, 1000)
    ModItems.LavaPearl.asInstanceOf[LavaPearl].setEnergy(emptyPearl, 0)
    ItemHelper.addShapedOreRecipe(ItemHelper.cloneStack(emptyPearl),
      " o ",
      "lpl",
      " o ",
      char2Character('o'), ItemHelper.stack(Blocks.obsidian),
      char2Character('l'), Items.lava_bucket,
      char2Character('p'), ItemHelper.stack(ModItems.Pearl)
    )
    recipeLavaPearl = getLastRecipeAdded()

    ItemHelper.addShapedOreRecipe(ItemHelper.stack(ModBlocks.FurnaceBlock, 4),
      "bnb",
      "nmn",
      "bnb",
      char2Character('b'), Blocks.brick_block,
      char2Character('n'), Blocks.nether_brick,
      char2Character('m'), Items.magma_cream
    )
    recipeFurnaceBlock = getLastRecipeAdded()
  }

  override def initialize(): Boolean = {
    initShapedRecipes()

    initShapelessRecipes()

    true
  }

  def getLastRecipeAdded(): IRecipe = CraftingManager.getInstance().getRecipeList.get(CraftingManager.getInstance().getRecipeList.size() - 1).asInstanceOf[IRecipe]
}
