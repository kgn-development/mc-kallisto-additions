package li.kallisto.kgnkallistoadditions.registry

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import java.util.function.Supplier

object RegistryHelper {
    fun Item.Properties.setItemModId(name: String): Item.Properties {
        return this.setId(ResourceKey.create(Registries.ITEM, KGNKallistoAdditions.locate(name)))
    }

    fun BlockBehaviour.Properties.setBlockModId(name: String): BlockBehaviour.Properties {
        return this.setId(ResourceKey.create(Registries.BLOCK, KGNKallistoAdditions.locate(name)))
    }

    fun createTag(name: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, KGNKallistoAdditions.locate(name))
    }

    fun registerItem(name: String, item: Item): Holder.Reference<Item> {
        val key = ResourceKey.create(Registries.ITEM, KGNKallistoAdditions.locate(name))
        val holder = Registry.registerForHolder(BuiltInRegistries.ITEM, key, item)
        ModRegistries.items.add(holder)
        return holder
    }

    fun registerBlockWithItem(
        name: String,
        blockSupplier: Supplier<Block>,
        blockProperties: BlockBehaviour.Properties,
        itemProperties: Item.Properties
    ): Holder.Reference<Block> {
        val blockKey = ResourceKey.create(Registries.BLOCK, KGNKallistoAdditions.locate(name))
        blockProperties.setId(blockKey)
        val itemKey = ResourceKey.create(Registries.ITEM, KGNKallistoAdditions.locate(name))
        itemProperties.useBlockDescriptionPrefix().setId(itemKey)

        val blockHolder = Registry.registerForHolder(BuiltInRegistries.BLOCK, blockKey, blockSupplier.get())
        ModRegistries.blocks.add(blockHolder)

        // registerForHolder infers its return type from the value argument, so type the
        // value as Item explicitly - otherwise it comes back as Holder.Reference<BlockItem>,
        // which Kotlin's invariant Holder.Reference won't accept into a List<Holder.Reference<Item>>.
        val blockItem: Item = BlockItem(blockHolder.value(), itemProperties)
        val itemHolder = Registry.registerForHolder(BuiltInRegistries.ITEM, itemKey, blockItem)
        ModRegistries.items.add(itemHolder)

        return blockHolder
    }
}
