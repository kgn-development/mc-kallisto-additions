package li.kallisto.kgnkallistoadditions.persons.luca.client

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import net.minecraft.client.model.geom.ModelLayerLocation
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent

object LucaBeanieClient {
    val MODEL_LAYER_LOCATION = ModelLayerLocation(KGNKallistoAdditions.locate(LucaBeanie.ID), "main")

    fun register(eventBus: IEventBus) {
        eventBus.addListener(::onRegisterClientExtensions)
        eventBus.addListener(::onRegisterLayerDefinitions)
    }

    fun onRegisterLayerDefinitions(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(
            MODEL_LAYER_LOCATION
        ) { LucaBeanieModel.createHelmetLayer() }
    }

    fun onRegisterClientExtensions(event: RegisterClientExtensionsEvent) {
        if (!event.isItemRegistered(LucaBeanie.ITEM.get())) {
            event.registerItem(LucaBeanieItemExtensions, LucaBeanie.ITEM.get())
        }
    }
}