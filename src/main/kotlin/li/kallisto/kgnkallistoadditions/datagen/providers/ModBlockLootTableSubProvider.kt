package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.persons.cosimo.Druid
import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block

class ModBlockLootTableSubProvider(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {
    override fun generate() {
        dropSelf(Druid.DRUID_HEART.get())
    }

    override fun getKnownBlocks(): Iterable<Block> {
        return ModRegistries.BLOCKS.entries.map { it.get() }
    }
}