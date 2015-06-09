package com.aergonaut.lib.block

object BlockSide {
  final val Bottom: Int = 0
  final val Top: Int = 1
  final val North: Int = 2
  final val South: Int = 3
  final val West: Int = 4
  final val East: Int = 5

  def adjacentBlockOnSide(side: Int, x: Int, y: Int, z: Int): (Int, Int, Int) = side match {
    case BlockSide.Top => (x, y + 1, z)
    case BlockSide.Bottom => (x, y - 1, z)
    case BlockSide.North => (x, y, z - 1)
    case BlockSide.South => (x, y, z + 1)
    case BlockSide.East => (x + 1, y, z)
    case BlockSide.West => (x - 1, y, z)
  }
}
