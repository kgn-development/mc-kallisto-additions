package li.kallisto.kgnkallistoadditions.datagen

import li.kallisto.kgnkallistoadditions.datagen.providers.ModBlockLootTableSubProvider
import li.kallisto.kgnkallistoadditions.datagen.providers.ModItemTagProvider
import li.kallisto.kgnkallistoadditions.datagen.providers.ModModelProvider
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
