package li.kallisto.kgnkallistoadditions.registry

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.registry.creativemodetabs.ModCreativeModeTabs
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModRegistries {
    val ITEMS = DeferredRegister.createItems(KGNKallistoAdditions.ID)
    val BLOCKS = DeferredRegister.createBlocks(KGNKallistoAdditions.ID)

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
        BLOCKS.register(eventBus)
        ModCreativeModeTabs.register(eventBus)
    }
}

