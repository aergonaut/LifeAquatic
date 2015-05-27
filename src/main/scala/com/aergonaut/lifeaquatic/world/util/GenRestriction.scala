package com.aergonaut.lifeaquatic.world.util

object GenRestriction {
  sealed trait Restriction

  case object Blacklist extends Restriction
  case object Whitelist extends Restriction
  case object None extends Restriction
}
