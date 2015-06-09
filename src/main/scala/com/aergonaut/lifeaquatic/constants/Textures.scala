package com.aergonaut.lifeaquatic.constants

object Textures {
  final val ResourcePrefix = s"${Constants.ModID}:"

  object Armor {
    private final val ArmorSheetsDir = s"${ResourcePrefix}textures/armor"

    final val ArmorSheetFormat = s"${ArmorSheetsDir}/%s.%s.png"

    final val LinenArmor = "LinenArmor"
    final val SwimTrunkArmor = "SwimTrunkArmor"

  }

  object Gui {
    private final val GuiSheetsDir = s"${ResourcePrefix}textures/gui"

    final val Manual = s"${GuiSheetsDir}/manual.png"

    final val Vat = s"${GuiSheetsDir}/vat.png"

    final val Calcinator = s"${GuiSheetsDir}/furnace.png"

    object Elements {
      private final val ElementsDir = s"${GuiSheetsDir}/elements"

      final val FluidTank = s"${ElementsDir}/fluidTank.png"
    }
  }
}
