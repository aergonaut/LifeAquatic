package com.aergonaut.lifeaquatic.item

import java.util

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lifeaquatic.constants.{Constants, Textures}
import com.aergonaut.lifeaquatic.creative.ModCreativeTabs
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.IIcon

import scala.collection.mutable

abstract class ItemBase(val name: String, val subnames: Seq[String]) extends Item {
  setUnlocalizedName(name)
  setCreativeTab(ModCreativeTabs.LifeAquaticTab)
  setHasSubtypes(subnames.nonEmpty)

  val icons: mutable.ArrayBuffer[IIcon] = mutable.ArrayBuffer()

  def this(name: String) = this(name, Vector())

  @SideOnly(Side.CLIENT)
  override def registerIcons(iconRegister: IIconRegister): Unit = {
    val texture = name.replaceAll("\\.", "/")
    icons.clear()
    if (subnames.nonEmpty) {
      subnames.foreach(subname => {
        icons += iconRegister.registerIcon(s"${Textures.ResourcePrefix}${texture}/${subname}")
      })
    } else {
      icons += iconRegister.registerIcon(s"${Textures.ResourcePrefix}${texture}")
    }
  }

  override def getUnlocalizedName: String = s"item.${Constants.ModID}:${name}"

  override def getUnlocalizedName(stack: ItemStack): String = {
    if (subnames.nonEmpty) {
      val meta = stack.getItemDamage
      if (meta >= 0 && meta < subnames.size) {
        return s"${getUnlocalizedName()}.${subnames(meta)}"
      }
    }
    getUnlocalizedName()
  }

  override def getIconFromDamage(meta: Int): IIcon = {
    if (subnames.nonEmpty) {
      if (meta >= 0 && meta < icons.size) {
        return icons(meta)
      }
    }
    icons(0)
  }

  @SideOnly(Side.CLIENT)
  override def getSubItems(stack: Item, tab: CreativeTabs, list: util.List[_] ): Unit = {
    if (subnames.nonEmpty) {
      val l2 = list.asInstanceOf[util.List[ItemStack]]
      for (i <- subnames.indices) l2.add(ItemHelper.stack(this, 1, i))
    } else {
      list.asInstanceOf[util.List[ItemStack]].add(ItemHelper.stack(this))
    }
  }
}
