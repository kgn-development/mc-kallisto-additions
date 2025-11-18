package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import net.minecraft.client.data.models.EquipmentAssetProvider
import net.minecraft.client.resources.model.EquipmentClientInfo
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.equipment.EquipmentAsset
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class ModEquipmentAssetProvider(output: PackOutput) : EquipmentAssetProvider(output) {
    private val pathProvider: PackOutput.PathProvider =
        output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment")

    override fun run(cachedOutput: CachedOutput): CompletableFuture<*> {
        val map: MutableMap<ResourceKey<EquipmentAsset?>?, EquipmentClientInfo?> = HashMap()
        bootstrap { asset: ResourceKey<EquipmentAsset?>?, info: EquipmentClientInfo? ->
            check(
                map.putIfAbsent(
                    asset,
                    info
                ) == null
            ) { "Tried to register equipment asset twice for id: " + asset }
        }

        return DataProvider.saveAll(cachedOutput, EquipmentClientInfo.CODEC, this.pathProvider::json, map)
    }

    companion object {
        fun bootstrap(output: BiConsumer<ResourceKey<EquipmentAsset?>, EquipmentClientInfo>) {
            output.accept(
                MichiCap.ASSET,
                EquipmentClientInfo.builder()
                    .addMainHumanoidLayer(KGNKallistoAdditions.locate(MichiCap.ID), false)
                    .build()
            )
        }
    }
}