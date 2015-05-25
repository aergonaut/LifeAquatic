package com.aergonaut.lifeaquatic.item.armor

import com.aergonaut.lifeaquatic.constants.Textures
import com.aergonaut.lifeaquatic.creative.ModCreativeTabs
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.Entity
import net.minecraft.item.ItemArmor.ArmorMaterial
import net.minecraft.item.{ItemArmor, ItemStack}

class ItemArmorBase(name: String, material: ArmorMaterial, armorType: Int, textureName: String) extends ItemArmor(material, 0, armorType) {
  setUnlocalizedName(name)
  setCreativeTab(ModCreativeTabs.LifeAquaticTab)

  override def getUnlocalizedName: String = s"item.${Textures.ResourcePrefix}${getUnwrappedUnlocalizedName(super.getUnlocalizedName())}"

  override def getUnlocalizedName(stack: ItemStack): String = getUnlocalizedName()

  @SideOnly(Side.CLIENT)
  override def registerIcons(iIconRegister: IIconRegister): Unit = {
    itemIcon = iIconRegister.registerIcon(getUnlocalizedName().split('.').last)
  }

  protected def getUnwrappedUnlocalizedName(unlocalizedName: String) = unlocalizedName.split('.').last

  override def getArmorTexture(itemStack: ItemStack, entity: Entity, slot: Int, armorType: String): String =
    Textures.Armor.ArmorSheetFormat.format(textureName, if (slot == 2) 1 else 0)
}
