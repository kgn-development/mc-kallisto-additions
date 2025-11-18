package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block

class ModBlockLootTableSubProvider(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {
    override fun generate() {

    }

    override fun getKnownBlocks(): Iterable<Block> {
        return ModRegistries.BLOCKS.entries.map { it.get() }
    }
}