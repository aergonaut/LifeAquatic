package com.aergonaut.lifeaquatic.util

import cpw.mods.fml.common.FMLLog
import org.apache.logging.log4j.Level

object Logger {
  def log(level: Level, msg: String): Unit = {
    FMLLog.log(Constants.modid, level, msg)
  }

  def info(msg: String) = log(Level.INFO, msg)
}
