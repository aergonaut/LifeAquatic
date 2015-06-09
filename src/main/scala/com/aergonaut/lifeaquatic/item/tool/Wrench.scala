package com.aergonaut.lifeaquatic.item.tool

import cofh.api.item.IToolHammer
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.item.ItemBase
import com.aergonaut.lifeaquatic.multiblock.ModMultiBlocks
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class Wrench extends ItemBase(Names.Items.Tool.Wrench) with IToolHammer {
  setMaxStackSize(1)

  override def isUsable(item: ItemStack, user: EntityLivingBase, x: Int, y: Int, z: Int): Boolean = true

  override def toolUsed(item: ItemStack, user: EntityLivingBase, x: Int, y: Int, z: Int): Unit = {
    user.asInstanceOf[EntityPlayer].swingItem()
  }

  override def onItemUseFirst(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    Option(world.getBlock(x, y, z)) match {
      case None => false

      case Some(block) => {
        if (!player.isSneaking) {
          if (!world.isRemote) {
            val anyFormed = ModMultiBlocks.multiblocks.find(mb => mb.isBlockTrigger(block, world.getBlockMetadata(x, y, z)) && mb.createStructure(world, x, y, z, side, player))
            if (anyFormed.nonEmpty) return true
          }
        }
        false
      }
    }
  }

  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    if (world.isRemote) {
      player.swingItem()
    }
    super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ)
  }

  override def doesSneakBypassUse(world: World, x: Int, y: Int, z: Int, player: EntityPlayer): Boolean = true
}
