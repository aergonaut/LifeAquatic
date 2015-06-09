package com.aergonaut.lifeaquatic.client.gui.element

import cofh.lib.gui.GuiBase
import cofh.lib.gui.element.ElementFluidTank
import com.aergonaut.lifeaquatic.constants.Textures
import net.minecraftforge.fluids.IFluidTank

class ElementTankGauge(gui: GuiBase, posX: Int, posY: Int, fluidTank: IFluidTank)
  extends ElementFluidTank(gui, posX, posY, fluidTank, Textures.Gui.Elements.FluidTank) {

  sizeX = 20
  sizeY = 51
}
