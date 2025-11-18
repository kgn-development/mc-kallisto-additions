package li.kallisto.kgnkallistoadditions.persons.michi.client

import net.minecraft.client.Minecraft
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.Model
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.client.resources.model.EquipmentClientInfo
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions
import java.util.Map

object MichiCapItemExtensions : IClientItemExtensions {
    override fun getHumanoidArmorModel(
        itemStack: ItemStack,
        layerType: EquipmentClientInfo.LayerType,
        original: Model
    ): Model {
        return HumanoidModel<HumanoidRenderState>(
            ModelPart(
                mutableListOf<ModelPart.Cube?>(), Map.of<String?, ModelPart?>(
                    "head",
                    ModelPart(
                        mutableListOf<ModelPart.Cube?>(),
                        Map.of<String?, ModelPart?>(
                            "head",
                            MichiCapModel(
                                Minecraft.getInstance().entityModels
                                    .bakeLayer(MichiCapClient.MODEL_LAYER_LOCATION)
                            ).modelPart,
                            "hat",
                            ModelPart(mutableListOf<ModelPart.Cube?>(), mutableMapOf<String?, ModelPart?>())
                        )
                    ),
                    "body",
                    ModelPart(mutableListOf<ModelPart.Cube?>(), mutableMapOf<String?, ModelPart?>()),
                    "right_arm",
                    ModelPart(mutableListOf<ModelPart.Cube?>(), mutableMapOf<String?, ModelPart?>()),
                    "left_arm",
                    ModelPart(mutableListOf<ModelPart.Cube?>(), mutableMapOf<String?, ModelPart?>()),
                    "right_leg",
                    ModelPart(mutableListOf<ModelPart.Cube?>(), mutableMapOf<String?, ModelPart?>()),
                    "left_leg",
                    ModelPart(mutableListOf<ModelPart.Cube?>(), mutableMapOf<String?, ModelPart?>())
                )
            )
        )

    }
}