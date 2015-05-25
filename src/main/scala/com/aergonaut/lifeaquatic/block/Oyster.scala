package com.aergonaut.lifeaquatic.block

import java.util

import com.aergonaut.lifeaquatic.item.ModItems
import com.aergonaut.lifeaquatic.constants.Names
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.item.ItemStack
import net.minecraft.util.IIcon
import net.minecraft.world.World

class Oyster extends BlockBase(Names.Blocks.Oyster) {
  setHardness(3.0F)
  setResistance(5.0F)

  var iconSide, iconTop: IIcon = _

  val MAX_PEARLS = 5

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

  override def getDrops(world: World, x: Int, y: Int, z: Int, metadata: Int, fortune: Int): util.ArrayList[ItemStack] = {
    val count = Math.max(world.rand.nextInt(fortune + MAX_PEARLS), MAX_PEARLS)
    val array = new util.ArrayList[ItemStack]()
    array.add(new ItemStack(ModItems.Pearl, count))
    array
  }
}
