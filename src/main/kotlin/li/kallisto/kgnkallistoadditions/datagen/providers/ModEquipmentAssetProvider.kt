package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import net.minecraft.client.data.models.EquipmentAssetProvider
import net.minecraft.client.resources.model.EquipmentClientInfo
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.equipment.EquipmentAsset
import java.util.function.BiConsumer

class ModEquipmentAssetProvider(output: PackOutput) : EquipmentAssetProvider(output) {

    // Vanilla's run() builds the map and writes it; only the registrations are ours.
    // Deliberately does not call super, so vanilla's own assets are not re-emitted.
    override fun registerModels(output: BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo>) {
        output.accept(
            MichiCap.ASSET,
            EquipmentClientInfo.builder()
                .addMainHumanoidLayer(KGNKallistoAdditions.locate(MichiCap.ID), false)
                .build()
        )

        output.accept(
            LucaBeanie.ASSET,
            EquipmentClientInfo.builder()
                .addMainHumanoidLayer(KGNKallistoAdditions.locate(LucaBeanie.ID), false)
                .build()
        )

        output.accept(
            JoelMustache.ASSET,
            EquipmentClientInfo.builder()
                .addMainHumanoidLayer(KGNKallistoAdditions.locate(JoelMustache.ID), false)
                .build()
        )
    }
}
