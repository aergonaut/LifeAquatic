package com.aergonaut.lifeaquatic.constants

object Names {
  object Blocks {
    final val Oyster = "Oyster"

    final val Ore = "ore"

    object Storage {
      final val Pearl = "storage.pearl"

      final val Copper = "storage.copper"
      final val Tin = "storage.tin"
      final val Nickel = "storage.nickel"
      final val Bronze = "storage.bronze"
      final val Brass = "storage.brass"
    }

    object Machine {
      final val VatCasing = "machine.vatCasing"
      final val VatSiding = "machine.vatSiding"

      final val CalcinatorBrick = "machine.calcinatorBrick"
    }
  }

  object Items {
    final val BronzeCasing = "bronzeCasing"
    final val BrassCasing = "brassCasing"

    final val Ingot = "metal.ingot"

    final val Calx = "metal.calx"

    object Material {
      object Armor {
        final val Linen = "LinenArmor"
        final val SwimTrunk = "SwimTrunkArmor"
      }

      final val Pearl = "material.pearl"
    }

    object Armor {
      final val SwimTrunkFaceMask = "SwimTrunkFaceMask"
      final val SwimTrunkFins = "SwimTrunkFins"
      final val SwimTrunkShorts = "SwimTrunkShorts"
      final val LinenHelmet = "LinenHelmet"
      final val LinenChest = "LinenChest"
    }

    object Tool {
      final val Almanac = "ItemAlmanac"
      final val Wrench = "tool.wrench"
      final val Lens = "tool.lens"
      final val LavaPearl = "tool.lavaPearl"
    }

    final val IronCasing = "ironCasing"
  }

  object Features {
    final val Oysters = s"${Constants.ModID}_oysters"
  }

  object TileEntities {
    final val Vat = s"${Constants.ModID}:vat"
    final val Calcinator = s"${Constants.ModID}:calcinator"
  }
}
