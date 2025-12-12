package li.kallisto.kgnkallistoadditions.persons.joel

import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.registerBlockWithItem
import net.minecraft.Util
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.EquipmentAssets
import net.minecraft.world.level.block.FireflyBushBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.bus.api.IEventBus
import java.util.*
import java.util.function.Consumer

object JoelMustache {
    val ID = "joel_mustache"

    object Tags {
        val REPARIS_JOEL_MUSTACHE: TagKey<Item?> = RegistryHelper.createTag("repairs_${ID}")
    }

    val ITEM = ModRegistries.ITEMS.register(ID, ::JoelMustacheItem)

    val BLOCK =
        run {
            val properties = BlockBehaviour.Properties.of()
                .noOcclusion()
                .instabreak()
                .lightLevel { 12 }
                .sound(SoundType.CROP)
                .noCollission()

            registerBlockWithItem("moubatzius_lunar", { FireflyBushBlock(properties) }, properties, Item.Properties())
        }

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