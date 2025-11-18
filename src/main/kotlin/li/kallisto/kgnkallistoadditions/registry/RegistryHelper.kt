package li.kallisto.kgnkallistoadditions.registry

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.neoforge.registries.DeferredBlock
import java.util.function.Supplier

object RegistryHelper {
    fun Item.Properties.setItemModId(name: String): Item.Properties {
        return this.setId(ResourceKey.create(Registries.ITEM, KGNKallistoAdditions.locate(name)))
    }

    fun createTag(name: String): TagKey<Item?> {
        return ItemTags.create(KGNKallistoAdditions.locate(name))
    }

    fun registerBlockWithItem(
        name: String,
        blockSupplier: Supplier<Block>,
        blockProperties: BlockBehaviour.Properties,
        itemProperties: Item.Properties
    ): DeferredBlock<Block> {
        val blockKey = ResourceKey.create(Registries.BLOCK, KGNKallistoAdditions.locate(name))
        blockProperties.setId(blockKey)
        val itemKey = ResourceKey.create(Registries.ITEM, KGNKallistoAdditions.locate(name))
        itemProperties.useBlockDescriptionPrefix().setId(itemKey)
        val block = ModRegistries.BLOCKS.register(name, blockSupplier)
        ModRegistries.ITEMS.register(name) { _ -> BlockItem(block.get(), itemProperties) }
        return block;
    }
}