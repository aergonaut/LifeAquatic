package com.aergonaut.lifeaquatic.item

import com.aergonaut.lifeaquatic.block.BlockBase
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{ItemStack, Item, ItemBlock}

import java.util

class ItemBlockBase(block: Block) extends ItemBlock(block) {
  setHasSubtypes(block.asInstanceOf[BlockBase].subnames.nonEmpty)

  override def getMetadata(meta: Int): Int = meta

  override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]): Unit = {
    field_150939_a.getSubBlocks(item, tab, list)
  }

  override def getUnlocalizedName(stack: ItemStack): String = {
    val subnames = field_150939_a.asInstanceOf[BlockBase].subnames
    if (subnames.nonEmpty) {
      val meta = stack.getItemDamage
      if (meta >= 0 && meta < subnames.size) {
        return s"${getUnlocalizedName()}.${subnames(meta)}"
      }
    }
    getUnlocalizedName()
  }
}
