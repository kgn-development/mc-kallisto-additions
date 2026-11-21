package li.kallisto.mcKallistoAdditions.persons.joel.client

import li.kallisto.mcKallistoAdditions.McKallistoAdditions
import li.kallisto.mcKallistoAdditions.client.HeadArmorRenderer
import li.kallisto.mcKallistoAdditions.persons.joel.JoelMustache
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry
import net.minecraft.client.model.geom.ModelLayerLocation

object JoelMustacheClient {
    val MODEL_LAYER_LOCATION = ModelLayerLocation(McKallistoAdditions.locate(JoelMustache.ID), "main")

    fun register() {
        ModelLayerRegistry.registerModelLayer(MODEL_LAYER_LOCATION) { JoelMustacheModel.createHelmetLayer() }

        ArmorRenderer.register(
            ArmorRenderer.Factory { context ->
                HeadArmorRenderer(
                    context,
                    JoelMustacheModel(context.bakeLayer(MODEL_LAYER_LOCATION)).modelPart,
                    JoelMustache.ASSET
                )
            },
            JoelMustache.ITEM.value()
        )
    }
}
