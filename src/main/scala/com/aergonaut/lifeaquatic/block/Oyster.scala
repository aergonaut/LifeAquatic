package com.aergonaut.lifeaquatic.block

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon

class Oyster extends BlockBase("Oyster") {
  var iconSide, iconTop: IIcon = _

  override def registerBlockIcons(iIconRegister: IIconRegister): Unit = {
    iconTop = iIconRegister.registerIcon(getUnlocalizedName().split('.').last + ".0")
    iconSide = iIconRegister.registerIcon(getUnlocalizedName().split('.').last + ".1")
  }

  @SideOnly(Side.CLIENT)
  override def getIcon(par1: Int, par2: Int): IIcon = par1 match {
    case 0 => iconTop
    case 1 => iconTop
    case _ => iconSide
  }
}
