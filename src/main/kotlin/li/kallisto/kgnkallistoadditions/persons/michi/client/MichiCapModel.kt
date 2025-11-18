package li.kallisto.kgnkallistoadditions.persons.michi.client

import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class MichiCapModel(modelPart: ModelPart) : EntityModel<LivingEntityRenderState>(modelPart) {

    var modelPart: ModelPart = root.getChild("michi_cap")

    companion object {
        fun createHelmetLayer(): LayerDefinition {
            val meshDefinition = MeshDefinition()
            meshDefinition.root.addOrReplaceChild(
                "michi_cap",
                CubeListBuilder.create().texOffs(0, 0)
                    .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, CubeDeformation(0.5F))
                    .texOffs(0, 16).addBox(-4.0F, -11.0F, -5.0F, 8.0F, 4.0F, 9.0F, CubeDeformation(0.0F))
                    .texOffs(0, 29).addBox(-4.0F, -8.0F, -10.0F, 8.0F, 2.0F, 6.0F, CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
            )
            return LayerDefinition.create(meshDefinition, 64, 64)
        }
    }
}