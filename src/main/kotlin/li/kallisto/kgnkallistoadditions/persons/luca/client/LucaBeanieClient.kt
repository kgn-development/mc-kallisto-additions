package li.kallisto.kgnkallistoadditions.persons.luca.client

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.client.HeadArmorRenderer
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry
import net.minecraft.client.model.geom.ModelLayerLocation

object LucaBeanieClient {
    val MODEL_LAYER_LOCATION = ModelLayerLocation(KGNKallistoAdditions.locate(LucaBeanie.ID), "main")

    fun register() {
        ModelLayerRegistry.registerModelLayer(MODEL_LAYER_LOCATION) { LucaBeanieModel.createHelmetLayer() }

        ArmorRenderer.register(
            ArmorRenderer.Factory { context ->
                HeadArmorRenderer(
                    context,
                    LucaBeanieModel(context.bakeLayer(MODEL_LAYER_LOCATION)).modelPart,
                    LucaBeanie.ASSET
                )
            },
            LucaBeanie.ITEM.value()
        )
    }
}
