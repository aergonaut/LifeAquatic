package com.aergonaut.lifeaquatic.block.vat

import com.aergonaut.lifeaquatic.constants.{Textures, Names}
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon

class VatSiding extends VatBlockBase(Names.Blocks.Machine.VatSiding) {
  override protected def registerIconSide(iIconRegister: IIconRegister): IIcon =
    iIconRegister.registerIcon(s"${Textures.ResourcePrefix}machine/vat.1")
}
