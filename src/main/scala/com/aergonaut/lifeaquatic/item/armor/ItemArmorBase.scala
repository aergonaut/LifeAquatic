package com.aergonaut.lifeaquatic.item.armor

import com.aergonaut.lifeaquatic.util.Logger
import com.aergonaut.lifeaquatic.util.constants.{Textures, Constants}
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.Entity
import net.minecraft.item.{ItemStack, ItemArmor}
import net.minecraft.item.ItemArmor.ArmorMaterial

class ItemArmorBase(name: String, material: ArmorMaterial, armorType: Int, textureName: String) extends ItemArmor(material, 0, armorType) {
  setUnlocalizedName(name)

  override def getUnlocalizedName(): String = s"item.${Textures.ResourcePrefix}${getUnwrappedUnlocalizedName(super.getUnlocalizedName())}"

  override def getUnlocalizedName(stack: ItemStack): String = getUnlocalizedName()

  @SideOnly(Side.CLIENT)
  override def registerIcons(iIconRegister: IIconRegister): Unit = {
    itemIcon = iIconRegister.registerIcon(getUnlocalizedName().split('.').last)
  }

  protected def getUnwrappedUnlocalizedName(unlocalizedName: String) = unlocalizedName.split('.').last

  override def getArmorTexture(itemStack: ItemStack, entity: Entity, slot: Int, armorType: String): String =
    Textures.Armor.ArmorSheetFormat.format(textureName, slot)
}
