package com.aergonaut.lifeaquatic.creative

import com.aergonaut.lifeaquatic.constants.Constants
import com.aergonaut.lifeaquatic.item.ModItems
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object ModCreativeTabs {
  final val LifeAquaticTab: CreativeTabs = new CreativeTabs(Constants.ModID) {
    override def getTabIconItem: Item = ModItems.LinenHelmet

    override def getTranslatedTabLabel: String = "The Life Aquatic"
  }
}
