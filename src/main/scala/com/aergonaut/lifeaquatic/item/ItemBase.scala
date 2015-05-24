package com.aergonaut.lifeaquatic.item

import com.aergonaut.lifeaquatic.util.constants.{Textures, Constants}
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.item.{ItemStack, Item}

class ItemBase(name: String) extends Item {
  setUnlocalizedName(name)

  override def getUnlocalizedName(): String = s"item.${Textures.ResourcePrefix}${getUnwrappedUnlocalizedName(super.getUnlocalizedName())}"

  override def getUnlocalizedName(stack: ItemStack): String = getUnlocalizedName()

  @SideOnly(Side.CLIENT)
  override def registerIcons(iconRegister: IIconRegister): Unit = {
    itemIcon = iconRegister.registerIcon(getUnlocalizedName().split('.').last)
  }

  protected def getUnwrappedUnlocalizedName(unlocalizedName: String) = unlocalizedName.split('.').last
}
