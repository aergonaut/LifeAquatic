package com.aergonaut.lifeaquatic.block

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lifeaquatic.constants.Textures
import com.aergonaut.lifeaquatic.creative.ModCreativeTabs
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{ItemStack, Item}
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

import java.util
import scala.collection.mutable

class BlockBase(val name: String, material: Material, val subnames: Seq[String]) extends Block(material) {
  setBlockName(name)
  setCreativeTab(ModCreativeTabs.LifeAquaticTab)

  val icons: mutable.ArrayBuffer[IIcon] = mutable.ArrayBuffer()

  def this(name: String) = this(name, Material.rock, Vector())

  override def getUnlocalizedName: String = s"tile.${Textures.ResourcePrefix}${getUnwrappedUnlocalizedName(super.getUnlocalizedName())}"

  def getUnwrappedUnlocalizedName(unlocalizedName: String): String = unlocalizedName.split("\\.", 2).last

  override def registerBlockIcons(iconRegister: IIconRegister): Unit = {
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

  override def getIcon(side: Int, meta: Int): IIcon = {
    if (meta >= 0 && meta < icons.size) {
      return icons(meta)
    }
    icons(0)
  }

  override def getIcon(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = {
    val meta = world.getBlockMetadata(x, y, z)
    getIcon(side, meta)
  }

  override def getSubBlocks(item: Item, tab: CreativeTabs, list: util.List[_]): Unit = {
    if (subnames.nonEmpty) {
      val l2 = list.asInstanceOf[util.List[ItemStack]]
      for ( i <- subnames.indices) l2.add(ItemHelper.stack(item, 1, i))
    } else {
      list.asInstanceOf[util.List[ItemStack]].add(ItemHelper.stack(item))
    }
  }

  override def damageDropped(meta: Int): Int = meta
}
