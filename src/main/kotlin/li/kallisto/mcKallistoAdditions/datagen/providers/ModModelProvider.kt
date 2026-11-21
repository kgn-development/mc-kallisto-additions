package li.kallisto.mcKallistoAdditions.datagen.providers

import li.kallisto.mcKallistoAdditions.persons.joel.JoelMustache
import li.kallisto.mcKallistoAdditions.persons.leya.PaintBrush
import li.kallisto.mcKallistoAdditions.persons.luca.LucaBeanie
import li.kallisto.mcKallistoAdditions.persons.michi.MichiCap
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates

class ModModelProvider(output: FabricPackOutput) : FabricModelProvider(output) {

    override fun generateBlockStateModels(blockModels: BlockModelGenerators) {
        // Blockstates/models are hand-authored under src/main/resources.
    }

    override fun generateItemModels(itemModels: ItemModelGenerators) {
        itemModels.generateFlatItem(MichiCap.ITEM.value(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(LucaBeanie.ITEM.value(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(LucaBeanie.BLOCK.value().asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JoelMustache.ITEM.value(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JoelMustache.BLOCK.value().asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(PaintBrush.ITEM.value(), ModelTemplates.FLAT_HANDHELD_ITEM)
    }
}
