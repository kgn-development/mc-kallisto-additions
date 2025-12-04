package li.kallisto.kgnkallistoadditions.persons.joel

import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper
import net.minecraft.Util
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.EquipmentAssets
import net.neoforged.bus.api.IEventBus
import java.util.*
import java.util.function.Consumer

object JoelMustache {
    val ID = "joel_mustache"

    object Tags {
        val REPARIS_JOEL_MUSTACHE: TagKey<Item?> = RegistryHelper.createTag("repairs_${ID}")
    }

    val ITEM = ModRegistries.ITEMS.register(ID, ::JoelMustacheItem)

    var ASSET: ResourceKey<EquipmentAsset?> = EquipmentAssets.createId(ID)

    val ARMOR_MATERIAL = ArmorMaterial(
        -1,
        Util.make(
            EnumMap(ArmorType::class.java),
            Consumer { attribute: EnumMap<ArmorType?, Int?>? ->
                attribute!![ArmorType.HELMET] = 3
            }),
        15,
        SoundEvents.ARMOR_EQUIP_NETHERITE,
        3f,
        0.1f,
        Tags.REPARIS_JOEL_MUSTACHE,
        ASSET
    )

    fun register(eventBus: IEventBus) {
    }
}