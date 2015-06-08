package com.aergonaut.lifeaquatic.constants

object Names {
  object Blocks {
    final val Oyster = "Oyster"

    object Storage {
      final val Pearl = "storage.pearl"

      final val Copper = "storage.copper"
      final val Tin = "storage.tin"
      final val Nickel = "storage.nickel"
      final val Bronze = "storage.bronze"
      final val Brass = "storage.brass"
    }

    object Ore {
      final val Copper = "ore.copper"
      final val Tin = "ore.tin"
      final val Nickel = "ore.nickel"
    }
  }

  object Items {
    object Material {
      object Armor {
        final val Linen = "LinenArmor"
      }

      final val Pearl = "material.pearl"

      final val IngotCopper = "material.ingotCopper"
      final val IngotTin = "material.ingotTin"
      final val IngotNickel = "material.ingotNickel"
      final val IngotBronze = "material.ingotBronze"
      final val IngotBrass = "material.ingotBrass"
    }

    object Armor {
      final val LinenHelmet = "LinenHelmet"
      final val LinenChest = "LinenChest"
    }

    object Tools {
      final val Almanac = "ItemAlmanac"
    }
  }

  object Features {
    final val Oysters = s"${Constants.ModID}_oysters"
  }
}
