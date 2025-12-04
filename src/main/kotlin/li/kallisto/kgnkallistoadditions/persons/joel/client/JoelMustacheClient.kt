package li.kallisto.kgnkallistoadditions.persons.joel.client

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import net.minecraft.client.model.geom.ModelLayerLocation
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent

object JoelMustacheClient {
    val MODEL_LAYER_LOCATION = ModelLayerLocation(KGNKallistoAdditions.locate(JoelMustache.ID), "main")

    fun register(eventBus: IEventBus) {
        eventBus.addListener(::onRegisterClientExtensions)
        eventBus.addListener(::onRegisterLayerDefinitions)
    }

    fun onRegisterLayerDefinitions(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(
            MODEL_LAYER_LOCATION
        ) { JoelMustacheModel.createHelmetLayer() }
    }

    fun onRegisterClientExtensions(event: RegisterClientExtensionsEvent) {
        if (!event.isItemRegistered(JoelMustache.ITEM.get())) {
            event.registerItem(JoelMustacheItemExtensions, JoelMustache.ITEM.get())
        }
    }
}