package com.aergonaut.lib.manual.page

import java.util

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.manual.TManual
import com.aergonaut.lib.manual.gui.{TGuiManual, GuiPositionedStack}
import com.aergonaut.lib.util.ClientUtil
import com.aergonaut.lifeaquatic.util.Logger
import cpw.mods.fml.relauncher.ReflectionHelper
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.RenderHelper
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{ShapedRecipes, ShapelessRecipes, IRecipe, CraftingManager}
import net.minecraftforge.oredict.{ShapedOreRecipe, ShapelessOreRecipe}
import org.lwjgl.opengl.{GL12, GL11}

import scala.collection.JavaConverters._
import scala.collection.mutable

class CraftingPage(val recipes: Seq[IRecipe]) extends ManualPage(None) {
  private def makePositionedStacks(recipe: IRecipe): Option[Seq[GuiPositionedStack]] = extractIngredientsAndSize(recipe) match {
    case Some((ingredients, w, h)) => {
      val positionedStacks = mutable.ArrayBuffer[GuiPositionedStack]()

      positionedStacks += new GuiPositionedStack(recipe.getRecipeOutput, 19, -19)

      for (hh <- 0 until h) {
        for (ww <- 0 until w) {
          positionedStacks += new GuiPositionedStack(ingredients(hh * w + ww), ww * 19, hh * 19)
        }
      }

      Some(positionedStacks.toSeq)
    }

    case _ => None
  }

  private def extractIngredientsAndSize(recipe: IRecipe): Option[(Seq[Option[Seq[Any]]], Int, Int)] = recipe match {
    case recipe: ShapelessRecipes => {
      val ingredients = recipe.recipeItems.asScala.map(el => Some(Vector(el.asInstanceOf[ItemStack])))
      val w = if (recipe.getRecipeSize > 6) 3 else if (recipe.getRecipeSize > 1) 2 else 1
      val h = if (recipe.getRecipeSize > 4) 3 else if (recipe.getRecipeSize > 2) 2 else 1
      Some((ingredients, w, h))
    }

    case recipe: ShapedRecipes => {
      val ingredients = recipe.recipeItems.map(el => Some(Vector(el))).toVector
      val w = recipe.recipeWidth
      val h = recipe.recipeHeight
      Some((ingredients, w, h))
    }

    case recipe: ShapelessOreRecipe => {
      val ingredients: Vector[Option[Vector[Any]]] = recipe.getInput.asScala.map({
        case oreList: util.List[ItemStack] => Some(oreList.asScala.toVector)
        case stack: ItemStack => Some(Vector(stack))
        case _ => None
      }).toVector
      val w = if (ingredients.length > 6) 3 else if (ingredients.length > 1) 2 else 1
      val h = if (ingredients.length > 4) 3 else if (ingredients.length > 2) 2 else 1
      Some((ingredients, w, h))
    }

    case recipe: ShapedOreRecipe => {
      val ingredients: Vector[Option[Vector[Any]]] = recipe.getInput.map({
        case oreList: util.List[ItemStack] => Some(oreList.asScala.toVector)
        case stack: ItemStack => Some(Vector(stack))
        case _ => None
      }).toVector
      val w: Int = ReflectionHelper.getPrivateValue(classOf[ShapedOreRecipe], recipe, "width")
      val h: Int = ReflectionHelper.getPrivateValue(classOf[ShapedOreRecipe], recipe, "height")
      Some((ingredients, w, h))
    }

    case _ => None
  }

  override def renderPage(gui: TGuiManual, manual: TManual, yOffset: Int): Unit = {
    ClientUtil.bindTexture("lifeaquatic:textures/gui/craftingOverlay.png")
    gui.drawTexturedModalRect(gui.left + 1, gui.top + yOffset, 0, 0, gui.guiWidth, gui.guiHeight)

    GL11.glEnable(GL12.GL_RESCALE_NORMAL)
    RenderHelper.enableGUIStandardItemLighting()

    val yBase = gui.top + 37
    var yOff = yOffset

    var highlighted: Option[ItemStack] = None

    RenderItem.getInstance().renderWithColor = true

    val positioned = recipes.map(recipe => makePositionedStacks(recipe))

    positioned.foreach(_.foreach(recipe => {
      val w = if (recipe.length > 6) 3 else if (recipe.length > 1) 2 else 1
      val h = if (recipe.length > 4) 3 else if (recipe.length > 2) 2 else 1

      val xBase = gui.left + gui.guiWidth / 2 - (w * 20) / 2

      recipe.foreach(stack => stack.getStack() match {
        case Some(item) => {
          RenderItem.getInstance().renderItemIntoGUI(gui.font, Minecraft.getMinecraft.renderEngine, item, xBase + stack.x, yBase + yOffset + stack.y)
          RenderItem.getInstance().renderItemOverlayIntoGUI(gui.font, Minecraft.getMinecraft.renderEngine, item, xBase + stack.x, yBase + yOffset + stack.y)

          if (gui.mouseX >= xBase + stack.x && gui.mouseX < xBase + stack.x + 19 && gui.mouseY >= yBase + yOffset + stack.y && gui.mouseY < yBase + yOffset + stack.y + 19) highlighted = Some(item)
        }

        case _ => {}
      })

      yOff += h * 18 + 18
    }))

    GL11.glEnable(GL11.GL_BLEND)

    highlighted.foreach(stack => gui.drawTooltip(stack, gui.mouseX, gui.mouseY))
  }
}

object CraftingPage {
  def apply(recipes: Seq[IRecipe]): CraftingPage = new CraftingPage(recipes)
}
