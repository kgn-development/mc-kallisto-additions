package li.kallisto.kgnkallistoadditions.persons.joel.client

import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class JoelMustacheModel(modelPart: ModelPart) : EntityModel<LivingEntityRenderState>(modelPart) {

    var modelPart: ModelPart = root.getChild("joel_mustache")

    companion object {
        fun createHelmetLayer(): LayerDefinition {
            val meshDefinition = MeshDefinition()
            val root = meshDefinition.root

            root.addOrReplaceChild(
                "joel_mustache",
                CubeListBuilder.create().texOffs(0, 0)
                    .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
            )

            return LayerDefinition.create(meshDefinition, 64, 64)
        }
    }
}