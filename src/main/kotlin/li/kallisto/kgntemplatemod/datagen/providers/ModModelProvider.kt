package li.kallisto.kgntemplatemod.datagen.providers

import li.kallisto.kgntemplatemod.KGNTemplateMod
import li.kallisto.kgntemplatemod.block.ModBlocks
import li.kallisto.kgntemplatemod.item.ModItems
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.ModelProvider
import net.minecraft.core.Holder
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import java.util.stream.Stream

class ModModelProvider(output: PackOutput) : ModelProvider(output, KGNTemplateMod.ID) {

    override fun registerModels(blockModels: BlockModelGenerators, itemModels: ItemModelGenerators) {

    }

    override fun getKnownBlocks(): Stream<out Holder<Block?>?> {
        return ModBlocks.BLOCKS.entries.stream().filter { true }
    }

    override fun getKnownItems(): Stream<out Holder<Item?>?> {
        return ModItems.ITEMS.entries.stream().filter { true }
    }
}