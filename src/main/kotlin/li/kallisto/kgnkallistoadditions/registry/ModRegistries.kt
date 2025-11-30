package li.kallisto.kgnkallistoadditions.registry

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.registry.creativemodetabs.ModCreativeModeTabs
import net.minecraft.core.registries.BuiltInRegistries
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries

object ModRegistries {
    val ITEMS = DeferredRegister.createItems(KGNKallistoAdditions.ID)
    val BLOCKS = DeferredRegister.createBlocks(KGNKallistoAdditions.ID)
    val ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, KGNKallistoAdditions.ID)
    val PARTICLE_TYPE = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, KGNKallistoAdditions.ID)

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
        BLOCKS.register(eventBus)
        ATTACHMENT_TYPES.register(eventBus)
        PARTICLE_TYPE.register(eventBus)
        ModCreativeModeTabs.register(eventBus)
    }
}

