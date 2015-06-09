package com.aergonaut.lifeaquatic.block.furnace

import java.util

import cofh.api.block.IBlockDebug
import com.aergonaut.lifeaquatic.LifeAquatic
import com.aergonaut.lifeaquatic.block.BlockBase
import com.aergonaut.lifeaquatic.constants.{Textures, Guis, Names}
import com.aergonaut.lifeaquatic.tileentity.TileEntityCalcinator
import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.block.ITileEntityProvider
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{IIcon, IChatComponent}
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.common.util.ForgeDirection

class CalcinatorBrick extends BlockBase(Names.Blocks.Machine.CalcinatorBrick) with ITileEntityProvider with IBlockDebug {
  var masterFaceIcon: IIcon = _

  override def registerBlockIcons(iconRegister: IIconRegister): Unit = {
    super.registerBlockIcons(iconRegister)

    masterFaceIcon = iconRegister.registerIcon(s"${Textures.ResourcePrefix}machine/calcinatorBrick_master")
  }

  @SideOnly(Side.CLIENT)
  override def getIcon(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = world.getTileEntity(x, y, z) match {
    case te: TileEntityCalcinator => {
      if (te.formed && te.isMaster() && side > 1) return masterFaceIcon
      super.getIcon(world, x, y, z, side)
    }
    case _ => super.getIcon(world, x, y, z, side)
  }

  override def createNewTileEntity(p_149915_1_ : World, p_149915_2_ : Int): TileEntity = new TileEntityCalcinator

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    if (!player.isSneaking) {
      world.getTileEntity(x, y, z) match {
        case tile: TileEntityCalcinator => {
          if (tile.formed) {
            tile.master.foreach(master => {
              player.openGui(LifeAquatic, Guis.Calcinator, world, master.xCoord, master.yCoord, master.zCoord)
            })
            return true
          }
        }

        case _ => {}
      }
    }
    false
  }

  override def debugBlock(world: IBlockAccess, x: Int, y: Int, z: Int, side: ForgeDirection, player: EntityPlayer): Unit = {
    Option(world.getTileEntity(x, y, z)) match {
      case Some(tile: TileEntityCalcinator) => {
        val messages = new util.ArrayList[IChatComponent]()
        tile.getTileInfo(messages, side, player, true)
        for (i <- 0 until messages.size()) {
          player.addChatComponentMessage(messages.get(i))
        }
      }

      case _ => {}
    }
  }
}
