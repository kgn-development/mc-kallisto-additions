package li.kallisto.kgntemplatemod.datagen

import li.kallisto.kgnkallistoadditions.datagen.providers.*
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.neoforged.neoforge.data.event.GatherDataEvent

object ModDataGenerator {
    fun onGatherClientData(event: GatherDataEvent.Client) {
        val generator = event.generator
        val packOutput = generator.packOutput
        val lookupProvider = event.lookupProvider

        generator.addProvider(true, ModModelProvider(packOutput))
        generator.addProvider(true, ModRecipeProvider.Runner(packOutput, lookupProvider))
        generator.addProvider(true, ModItemTagProvider(packOutput, lookupProvider))
        generator.addProvider(true, ModEquipmentAssetProvider(packOutput))
        generator.addProvider(true, ModSoundDefinitionsProvider(packOutput))
        generator.addProvider(true, ModParticleProvider(packOutput))
        generator.addProvider(
            true, LootTableProvider(
                packOutput,
                setOf(),
                listOf(
                    SubProviderEntry(
                        ::ModBlockLootTableSubProvider,
                        LootContextParamSets.BLOCK
                    )
                ),
                lookupProvider
            )
        )
    }
}