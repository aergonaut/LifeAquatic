package com.aergonaut.lifeaquatic.util

import com.aergonaut.lifeaquatic.LifeAquatic
import cpw.mods.fml.common.FMLLog
import org.apache.logging.log4j.{Level, Logger => Log4JLogger}

object Logger {
  def log(level: Level, msg: String): Unit = {
    FMLLog.log(LifeAquatic.modid, level, msg)
  }

  def info(msg: String) = log(Level.INFO, msg)
}
