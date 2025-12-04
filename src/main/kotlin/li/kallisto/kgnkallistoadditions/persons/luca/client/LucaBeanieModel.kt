package li.kallisto.kgnkallistoadditions.persons.luca.client

import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState

class LucaBeanieModel(modelPart: ModelPart) : EntityModel<LivingEntityRenderState>(modelPart) {

    var modelPart: ModelPart = root.getChild("luca_beanie")

    companion object {
        fun createHelmetLayer(): LayerDefinition {
            val meshDefinition = MeshDefinition()
            val root = meshDefinition.root

            root.addOrReplaceChild(
                "luca_beanie",
                CubeListBuilder.create().texOffs(0, 0)
                    .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, CubeDeformation(0.5F))
                    .texOffs(0, 17).addBox(-3.5F, -9.5F, -3.5F, 7.0F, 1.0F, 7.0F, CubeDeformation(0.0F))
                    .texOffs(0, 26).addBox(-3.5F, -10.5F, -3.5F, 5.0F, 1.0F, 5.0F, CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
            )

            return LayerDefinition.create(meshDefinition, 64, 64)
        }
    }
}