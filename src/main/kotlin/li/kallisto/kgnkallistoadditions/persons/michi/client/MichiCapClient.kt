package li.kallisto.kgnkallistoadditions.persons.michi.client

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import net.minecraft.client.model.geom.ModelLayerLocation
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent

object MichiCapClient {
    val MODEL_LAYER_LOCATION = ModelLayerLocation(KGNKallistoAdditions.locate(MichiCap.ID), "main")

    fun register(eventBus: IEventBus) {
        eventBus.addListener(::onRegisterClientExtensions)
        eventBus.addListener(::onRegisterLayerDefinitions)
    }

    fun onRegisterLayerDefinitions(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(
            MODEL_LAYER_LOCATION
        ) { MichiCapModel.createHelmetLayer() }
    }

    fun onRegisterClientExtensions(event: RegisterClientExtensionsEvent) {
        if (!event.isItemRegistered(MichiCap.ITEM.get())) {
            event.registerItem(MichiCapItemExtensions, MichiCap.ITEM.get())
        }
    }
}