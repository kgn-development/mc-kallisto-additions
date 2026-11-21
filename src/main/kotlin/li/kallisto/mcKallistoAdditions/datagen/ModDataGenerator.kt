package li.kallisto.mcKallistoAdditions.datagen

import li.kallisto.mcKallistoAdditions.datagen.providers.ModBlockLootTableSubProvider
import li.kallisto.mcKallistoAdditions.datagen.providers.ModItemTagProvider
import li.kallisto.mcKallistoAdditions.datagen.providers.ModModelProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack

class ModDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()

        pack.addProvider(FabricDataGenerator.Pack.Factory(::ModModelProvider))
        pack.addProvider(FabricDataGenerator.Pack.RegistryDependentFactory(::ModItemTagProvider))
        pack.addProvider(FabricDataGenerator.Pack.RegistryDependentFactory(::ModBlockLootTableSubProvider))
    }
}
