package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class ModBlockLootTableSubProvider(
    output: FabricPackOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
) : FabricBlockLootSubProvider(output, registriesFuture) {

    override fun generate() {
        dropSelf(LucaBeanie.BLOCK.value())
        dropSelf(JoelMustache.BLOCK.value())
    }

    // FabricBlockLootSubProvider's own generate(BiConsumer) skips the random_sequence
    // assignment vanilla's own LootTableProvider normally applies per table - restore it
    // here rather than losing it silently, without disturbing its strict-validation check.
    override fun generate(exporter: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) {
        super.generate(BiConsumer { key, builder -> exporter.accept(key, builder.setRandomSequence(key.identifier())) })
    }
}
