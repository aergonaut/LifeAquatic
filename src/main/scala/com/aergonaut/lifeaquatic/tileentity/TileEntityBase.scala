package com.aergonaut.lifeaquatic.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.{NetworkManager, Packet}
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

abstract class TileEntityBase extends TileEntity {
  override def readFromNBT(nBTTagCompound: NBTTagCompound): Unit = {
    super.readFromNBT(nBTTagCompound)
    readFromNBTCustom(nBTTagCompound)
  }

  protected def readFromNBTCustom(nBTTagCompound: NBTTagCompound): Unit

  override def writeToNBT(nBTTagCompound: NBTTagCompound): Unit = {
    super.writeToNBT(nBTTagCompound)
    writeToNBTCustom(nBTTagCompound)
  }

  protected def writeToNBTCustom(nBTTagCompound: NBTTagCompound): Unit

  override def getDescriptionPacket(): Packet = {
    val nBTTagCompound = new NBTTagCompound()
    writeToNBTCustom(nBTTagCompound)
    new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 3, nBTTagCompound)
  }

  override def onDataPacket(net: NetworkManager, packet: S35PacketUpdateTileEntity): Unit = {
    readFromNBTCustom(packet.func_148857_g())
  }
}
