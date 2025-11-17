package li.kallisto.kgntemplatemod.block

import li.kallisto.kgntemplatemod.KGNTemplateMod
import li.kallisto.kgntemplatemod.item.ModItems
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModBlocks {
    val BLOCKS = DeferredRegister.createBlocks(KGNTemplateMod.ID)

    fun registerBlockWithItem(
        name: String,
        blockSupplier: Supplier<Block>,
        blockProperties: BlockBehaviour.Properties,
        itemProperties: Item.Properties
    ): DeferredBlock<Block> {
        val blockKey = ResourceKey.create(Registries.BLOCK, KGNTemplateMod.locate(name))
        blockProperties.setId(blockKey)
        val itemKey = ResourceKey.create(Registries.ITEM, KGNTemplateMod.locate(name))
        itemProperties.useBlockDescriptionPrefix().setId(itemKey)
        val block = BLOCKS.register(name, blockSupplier)
        ModItems.ITEMS.register(name) { _ -> BlockItem(block.get(), itemProperties) }
        return block;
    }

    fun register(eventBus: IEventBus) {
        BLOCKS.register(eventBus)
    }
}