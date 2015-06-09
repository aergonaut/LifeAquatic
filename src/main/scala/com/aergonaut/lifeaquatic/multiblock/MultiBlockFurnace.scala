package com.aergonaut.lifeaquatic.multiblock

import cofh.lib.util.helpers.ItemHelper
import com.aergonaut.lib.block.BlockSide
import com.aergonaut.lib.multiblock.TMultiBlock
import com.aergonaut.lifeaquatic.block.ModBlocks
import com.aergonaut.lifeaquatic.block.furnace.CalcinatorBrick
import com.aergonaut.lifeaquatic.tileentity.TileEntityCalcinator
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object MultiBlockFurnace extends TMultiBlock {
  override def isBlockTrigger(block: Block, meta: Int): Boolean = block.isInstanceOf[CalcinatorBrick]

  override def createStructure_Impl(world: World, x: Int, y: Int, z: Int, side: Int, player: EntityPlayer): Boolean = {
    for (layer <- 0 until 4) {
      for (h <- -1 to 0) {
        for (w <- -1 to 1) {
          val dy = h
          val dx = side match {
            case BlockSide.West => layer
            case BlockSide.East => -layer
            case BlockSide.North => -w
            case _ => w
          }
          val dz = side match {
            case BlockSide.North => layer
            case BlockSide.South => -layer
            case BlockSide.East => -w
            case _ => w
          }

          val xx = x + dx
          val yy = y + dy
          val zz = z + dz
          val block = world.getBlock(xx, yy, zz)

          if (!block.isInstanceOf[CalcinatorBrick] || !world.getTileEntity(xx, yy, zz).isInstanceOf[TileEntityCalcinator])
            return false
        }
      }
    }

    for (layer <- 0 until 4) {
      for (h <- -1 to 0) {
        for (w <- -1 to 1) {
          val dy = h
          val dx = side match {
            case BlockSide.West => layer
            case BlockSide.East => -layer
            case BlockSide.North => -w
            case _ => w
          }
          val dz = side match {
            case BlockSide.North => layer
            case BlockSide.South => -layer
            case BlockSide.East => -w
            case _ => w
          }

          val xx = x + dx
          val yy = y + dy
          val zz = z + dz

          world.getTileEntity(xx, yy, zz) match {
            case tile: TileEntityCalcinator => {
              tile.formed = true
              tile.masterCoords = Some(x, y, z)
            }
          }

          world.markBlockForUpdate(xx, yy, zz)
        }
      }
    }
    true
  }

  override val structure: Array[Array[Array[ItemStack]]] = Array.fill[ItemStack](2, 4, 3)(ItemHelper.stack(ModBlocks.FurnaceBlock))

  override val width: Int = 3
  override val height: Int = 2
  override val length: Int = 4
}
