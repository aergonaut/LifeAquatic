package com.aergonaut.lifeaquatic.util

import com.aergonaut.lifeaquatic.util.constants.Constants
import cpw.mods.fml.common.FMLLog
import org.apache.logging.log4j.Level

object Logger {
  protected def log(level: Level, msg: String): Unit = {
    FMLLog.log(Constants.ModID, level, msg)
  }

  def info(msg: String): Unit = log(Level.INFO, msg)
}
