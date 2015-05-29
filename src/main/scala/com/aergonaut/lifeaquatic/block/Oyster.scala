package com.aergonaut.lifeaquatic.block

import java.util

import com.aergonaut.lifeaquatic.config.Config
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
    val count = Math.min(world.rand.nextInt(fortune + Oyster.maxPearls), Oyster.maxPearls)
    val array = new util.ArrayList[ItemStack]()
    array.add(new ItemStack(ModItems.Pearl, count))
    array
  }
}

object Oyster {
  def rarity: Int = Config.World.oysterRarity
  def clusterSize: Int = Config.World.oysterClusterSize
  def maxPearls: Int = Config.World.maxPearlsDropped
}
