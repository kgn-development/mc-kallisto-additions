package li.kallisto.mcKallistoAdditions.client

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.HumanoidRenderState
import net.minecraft.client.resources.model.EquipmentClientInfo
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.equipment.EquipmentAsset

// Renders a helmet's custom geometry in place of the head, leaving texture/dye/trim
// resolution to vanilla's EquipmentLayerRenderer so the equipment asset JSON stays the
// single source of truth for the texture path. This is the same split NeoForge's
// IClientItemExtensions#getHumanoidArmorModel relied on: it only swapped the geometry.
class HeadArmorRenderer(
    context: EntityRendererProvider.Context,
    headGeometry: ModelPart,
    private val asset: ResourceKey<EquipmentAsset>
) : ArmorRenderer {
    private val equipmentRenderer = context.equipmentRenderer
    private val model = wrapAsHead(headGeometry)

    override fun render(
        poseStack: PoseStack,
        submitNodeCollector: SubmitNodeCollector,
        stack: ItemStack,
        humanoidRenderState: HumanoidRenderState,
        slot: EquipmentSlot,
        light: Int,
        contextModel: HumanoidModel<HumanoidRenderState>
    ) {
        if (slot != EquipmentSlot.HEAD) return

        // Always the humanoid layer: these helmets only ship a humanoid texture, and the
        // baby layer would resolve to a textures/entity/equipment/humanoid_baby/ path
        // that does not exist.
        equipmentRenderer.renderLayers(
            EquipmentClientInfo.LayerType.HUMANOID,
            asset,
            model,
            humanoidRenderState,
            stack,
            poseStack,
            submitNodeCollector,
            light,
            humanoidRenderState.outlineColor
        )
    }

    companion object {
        // Hangs the geometry off an otherwise-empty HumanoidModel's "head" part, so
        // HumanoidModel#setupAnim still applies head tracking to it.
        private fun wrapAsHead(headGeometry: ModelPart): HumanoidModel<HumanoidRenderState> {
            fun empty() = ModelPart(mutableListOf<ModelPart.Cube>(), mutableMapOf<String, ModelPart>())

            return HumanoidModel(
                ModelPart(
                    mutableListOf<ModelPart.Cube>(),
                    mutableMapOf(
                        "head" to ModelPart(
                            mutableListOf<ModelPart.Cube>(),
                            mutableMapOf("head" to headGeometry, "hat" to empty())
                        ),
                        "body" to empty(),
                        "right_arm" to empty(),
                        "left_arm" to empty(),
                        "right_leg" to empty(),
                        "left_leg" to empty()
                    )
                )
            )
        }
    }
}
