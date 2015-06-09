package com.aergonaut.lifeaquatic.block.vat

import java.util

import cofh.api.block.IBlockDebug
import cofh.lib.util.UtilLiquidMover
import com.aergonaut.lib.block.BlockSide
import com.aergonaut.lifeaquatic.LifeAquatic
import com.aergonaut.lifeaquatic.block.BlockBase
import com.aergonaut.lifeaquatic.constants.{Guis, Textures}
import com.aergonaut.lifeaquatic.tileentity.TileEntityVat
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.block.ITileEntityProvider
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{IChatComponent, IIcon}
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.common.util.ForgeDirection

abstract class VatBlockBase(name: String) extends BlockBase(name: String) with ITileEntityProvider with IBlockDebug {
  var iconSide: IIcon = _
  var iconTop: IIcon = _
  var iconBottom: IIcon = _

  override def registerBlockIcons(iIconRegister: IIconRegister): Unit = {
    iconBottom = iIconRegister.registerIcon(s"${Textures.ResourcePrefix}machine/vat.0")
    iconTop = iIconRegister.registerIcon(s"${Textures.ResourcePrefix}machine/vat.1")
    iconSide = registerIconSide(iIconRegister)
  }

  protected def registerIconSide(iIconRegister: IIconRegister): IIcon

  @SideOnly(Side.CLIENT)
  override def getIcon(par1: Int, par2: Int): IIcon = par1 match {
    case BlockSide.Top => iconTop
    case BlockSide.Bottom => iconBottom
    case _ => iconSide
  }

  override def createNewTileEntity(world: World, meta: Int): TileEntity = new TileEntityVat

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    if (world.getTileEntity(x, y, z).isInstanceOf[TileEntityVat] && !player.isSneaking) {
      val tev = world.getTileEntity(x, y, z).asInstanceOf[TileEntityVat]
      if (tev.formed) {
        val heldItem = Option(player.getHeldItem)
        heldItem match {
          case Some(item) => {
            if (tev.allowBucketFill(item)) {
              UtilLiquidMover.manuallyFillTank(tev, player)
              return true
            }
          }

          case None => {
            tev.master.foreach(master => {
              if (!world.isRemote) {
                player.openGui(LifeAquatic, Guis.Vat, world, master.xCoord, master.yCoord, master.zCoord)
              }
              return true
            })
          }
        }
      }
    }
    false
  }

  override def debugBlock(world: IBlockAccess, x: Int, y: Int, z: Int, side: ForgeDirection, player: EntityPlayer): Unit =
    Option(world.getTileEntity(x, y, z)) match {
      case Some(te: TileEntityVat) => {
        val messages = new util.ArrayList[IChatComponent]()
        te.getTileInfo(messages, side, player, true)
        for (i <- 0 until messages.size()) {
          player.addChatMessage(messages.get(i))
        }
      }

      case _ => {}
    }
}
