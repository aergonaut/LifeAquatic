package com.aergonaut.lib.util

class Point(val x: Int, val y: Int) extends Ordered[Point] {
  override def compare(that: Point): Int = x - that.x match {
    case 0 => y - that.y
    case r => r
  }
}
