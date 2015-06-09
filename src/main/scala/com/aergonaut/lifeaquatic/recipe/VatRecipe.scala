package com.aergonaut.lifeaquatic.recipe

import com.aergonaut.lifeaquatic.block.ModBlocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.{NBTTagList, NBTTagCompound}
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary

import scala.collection.mutable
import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer

class VatRecipe(val input: Seq[ItemStack], val output: FluidStack, val processTime: Int, val waterCost: Int, val catalystCost: Int) {
  def matchesInputs(item: ItemStack, waterAmount: Int, catalystAmount: Int): Boolean = {
    input.exists(_.isItemEqual(item)) &&
    waterAmount >= waterCost &&
    catalystAmount >= catalystCost
  }

  def writeToNBT(nbt: NBTTagCompound): NBTTagCompound = {
    val inputTag = new NBTTagList()
    input.foreach(stack => inputTag.appendTag(stack.writeToNBT(new NBTTagCompound())))
    nbt.setTag("input", inputTag)

    val outputTag = new NBTTagCompound()
    output.writeToNBT(outputTag)
    nbt.setTag("output", outputTag)

    nbt.setInteger("processTime", processTime)
    nbt.setInteger("waterCost", waterCost)
    nbt.setInteger("catalystCost", catalystCost)

    nbt
  }
}

object VatRecipe {
  val recipes: mutable.Set[VatRecipe] = mutable.Set()

  def initRecipes(): Unit = {
    recipes ++= Set(
      VatRecipe("oreCopper", new FluidStack(ModBlocks.AqueousCopper, 125), 40, 500, 250),
      VatRecipe("oreTin", new FluidStack(ModBlocks.AqueousTin, 125), 40, 500, 250),
      VatRecipe("oreIron", new FluidStack(ModBlocks.AqueousIron, 125), 40, 500, 250),
      VatRecipe("oreGold", new FluidStack(ModBlocks.AqueousGold, 125), 40, 500, 250)
    )
  }

  def apply(input: ItemStack, output: FluidStack, processTime: Int, waterCost: Int, catalystCost: Int): VatRecipe =
    new VatRecipe(Vector(input), output, processTime, waterCost, catalystCost)

  def apply(oreName: String, output: FluidStack, processTime: Int, waterCost: Int, catalystCost: Int): VatRecipe = {
    val inputs = OreDictionary.getOres(oreName).asScala.toVector
    new VatRecipe(inputs, output, processTime, waterCost, catalystCost)
  }

  def apply(input: Seq[ItemStack], output: FluidStack, processTime: Int, waterCost: Int, catalystCost: Int): VatRecipe =
    new VatRecipe(input, output, processTime, waterCost, catalystCost)

  def findRecipeForInput(input: Option[ItemStack], waterAmount: Int, catalystAmount: Int): Option[VatRecipe] = for {
    item <- input
    recipe <- recipes.find(recipe => recipe.matchesInputs(item, waterAmount, catalystAmount))
  } yield recipe

  def loadRecipeFromNBT(nbt: NBTTagCompound): Option[VatRecipe] = for {
    input <- Option(loadInputsFromNBT(nbt.getTagList("input", 0)))
    output <- Option(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag("output")))
    processTime <- Option(nbt.getInteger("processTime"))
    waterCost <- Option(nbt.getInteger("waterCost"))
    catalystCost <- Option(nbt.getInteger("catalystCost"))
  } yield VatRecipe(input, output, processTime, waterCost, catalystCost)

  private def loadInputsFromNBT(nbt: NBTTagList): Seq[ItemStack] = {
    val inputs = mutable.ArrayBuffer[ItemStack]()
    for (i <- 0 until nbt.tagCount()) {
      inputs += ItemStack.loadItemStackFromNBT(nbt.getCompoundTagAt(i))
    }
    inputs.toVector
  }
}
