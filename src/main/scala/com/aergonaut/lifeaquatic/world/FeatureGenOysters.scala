package com.aergonaut.lifeaquatic.world

import cofh.lib.util.WeightedRandomBlock
import cofh.lib.world.biome.BiomeInfo
import com.aergonaut.lifeaquatic.world.util.GenRestriction
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

class FeatureGenOysters extends
  FeatureGenUnderwater("lifeaquatic_oysters",
    new WorldGenOysters(),
    List(new WeightedRandomBlock(new ItemStack(Blocks.water))),
    5,
    GenRestriction.Whitelist,
    false,
    GenRestriction.Blacklist) {

  addBiome(new BiomeInfo("Ocean"))
  addBiome(new BiomeInfo("Deep Ocean"))

  setRarity(20)
}
