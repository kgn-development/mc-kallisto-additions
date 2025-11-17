package li.kallisto.kgntemplatemod.item

import li.kallisto.kgntemplatemod.KGNTemplateMod
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModItems {
    val ITEMS = DeferredRegister.createItems(KGNTemplateMod.ID)

    fun register(eventBus: IEventBus) {
        ITEMS.register(eventBus)
    }
}

