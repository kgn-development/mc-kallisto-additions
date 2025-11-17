package li.kallisto.kgntemplatemod.datagen.providers

import li.kallisto.kgntemplatemod.block.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block

class ModBlockLootTableSubProvider(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {
    override fun generate() {

    }

    override fun getKnownBlocks(): Iterable<Block> {
        return ModBlocks.BLOCKS.entries.map { it.get() }
    }
}