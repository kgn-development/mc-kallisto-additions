package li.kallisto.kgnkallistoadditions.registry

import net.minecraft.core.Holder
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object ModRegistries {
    // Fabric has no DeferredRegister equivalent - registration happens eagerly at
    // class-init. These lists exist purely so the creative tab and datagen providers
    // (which used DeferredRegister#getEntries under NeoForge) have something to iterate.
    val items: MutableList<Holder.Reference<Item>> = mutableListOf()
    val blocks: MutableList<Holder.Reference<Block>> = mutableListOf()
}
