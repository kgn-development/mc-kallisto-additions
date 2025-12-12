package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.cosimo.Druid
import li.kallisto.kgnkallistoadditions.persons.ginger.Trumpet
import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrush
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import li.kallisto.kgnkallistoadditions.persons.noel.AppleJuice
import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.ModelProvider
import net.minecraft.client.data.models.model.ModelTemplates
import net.minecraft.core.Holder
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import java.util.stream.Stream

class ModModelProvider(output: PackOutput) : ModelProvider(output, KGNKallistoAdditions.ID) {

    override fun registerModels(blockModels: BlockModelGenerators, itemModels: ItemModelGenerators) {
        itemModels.generateFlatItem(MichiCap.ITEM.get(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(LucaBeanie.ITEM.get(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(LucaBeanie.BLOCK.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JoelMustache.ITEM.get(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(JoelMustache.BLOCK.asItem(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(AppleJuice.ITEM.get(), ModelTemplates.FLAT_ITEM)
        itemModels.generateFlatItem(Trumpet.ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(PaintBrush.ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
        itemModels.generateFlatItem(Druid.DRUID_STAFF.get(), ModelTemplates.FLAT_HANDHELD_ITEM)
    }

    override fun getKnownBlocks(): Stream<out Holder<Block?>?> {
        return emptyList<Holder<Block?>>().stream()
    }

    override fun getKnownItems(): Stream<out Holder<Item?>?> {
        return ModRegistries.ITEMS.entries.stream().filter { true }
    }
}