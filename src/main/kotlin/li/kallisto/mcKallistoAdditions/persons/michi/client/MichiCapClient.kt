package li.kallisto.mcKallistoAdditions.persons.michi.client

import li.kallisto.mcKallistoAdditions.McKallistoAdditions
import li.kallisto.mcKallistoAdditions.client.HeadArmorRenderer
import li.kallisto.mcKallistoAdditions.persons.michi.MichiCap
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry
import net.minecraft.client.model.geom.ModelLayerLocation

object MichiCapClient {
    val MODEL_LAYER_LOCATION = ModelLayerLocation(McKallistoAdditions.locate(MichiCap.ID), "main")

    fun register() {
        ModelLayerRegistry.registerModelLayer(MODEL_LAYER_LOCATION) { MichiCapModel.createHelmetLayer() }

        ArmorRenderer.register(
            ArmorRenderer.Factory { context ->
                HeadArmorRenderer(
                    context,
                    MichiCapModel(context.bakeLayer(MODEL_LAYER_LOCATION)).modelPart,
                    MichiCap.ASSET
                )
            },
            MichiCap.ITEM.value()
        )
    }
}
