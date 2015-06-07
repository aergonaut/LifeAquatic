package com.aergonaut.lib.core

import cofh.api.core.IInitializer

trait TInitializer extends IInitializer {
  override def preInit(): Boolean = true
  override def initialize(): Boolean = true
  override def postInit(): Boolean = true
}
