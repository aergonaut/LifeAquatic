package com.aergonaut.lifeaquatic.world.feature

import cofh.lib.util.WeightedRandomBlock
import cofh.lib.world.biome.BiomeInfo
import com.aergonaut.lifeaquatic.block.Oyster
import com.aergonaut.lifeaquatic.constants.Names
import com.aergonaut.lifeaquatic.world.WorldGenOysters
import com.aergonaut.lifeaquatic.world.util.GenRestriction
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

class FeatureGenOysters extends
  FeatureGenUnderwater(
    Names.Features.Oysters,
    new WorldGenOysters(),
    List(new WeightedRandomBlock(new ItemStack(Blocks.water))),
    Oyster.clusterSize,
    GenRestriction.Whitelist,
    false,
    GenRestriction.Blacklist) {

  addBiome(new BiomeInfo("Ocean"))
  addBiome(new BiomeInfo("Deep Ocean"))

  setRarity(Oyster.rarity)
}
