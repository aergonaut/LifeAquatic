package com.aergonaut.lifeaquatic.item

import com.aergonaut.lifeaquatic.util.Constants
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.item.{ItemStack, Item}

class ItemBase(name: String) extends Item {
  setUnlocalizedName(name)

  override def getUnlocalizedName(): String = s"item.${Constants.resource_prefix}${getUnwrappedUnlocalizedName(super.getUnlocalizedName())}"

  override def getUnlocalizedName(stack: ItemStack) = getUnlocalizedName()

  @SideOnly(Side.CLIENT)
  override def registerIcons(iconRegister: IIconRegister): Unit = {
    itemIcon = iconRegister.registerIcon(getUnlocalizedName().split('.').last)
  }

  def getUnwrappedUnlocalizedName(unlocalizedName: String): String = {
    unlocalizedName.split('.').last
  }
}
