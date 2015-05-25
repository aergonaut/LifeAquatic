package com.aergonaut.lifeaquatic.block

import com.aergonaut.lifeaquatic.constants.Textures
import com.aergonaut.lifeaquatic.creative.ModCreativeTabs
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister

class BlockBase(name: String, material: Material) extends Block(material) {
  setBlockName(name)
  setCreativeTab(ModCreativeTabs.LifeAquaticTab)

  def this(name: String) = this(name, Material.rock)

  override def getUnlocalizedName: String = s"tile.${Textures.ResourcePrefix}${getUnwrappedUnlocalizedName(super.getUnlocalizedName())}"

  def getUnwrappedUnlocalizedName(unlocalizedName: String): String = unlocalizedName.split('.').last

  override def registerBlockIcons(iconRegister: IIconRegister): Unit = {
    blockIcon = iconRegister.registerIcon(getUnlocalizedName().split('.').last)
  }
}
