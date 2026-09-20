package li.kallisto.kgnkallistoadditions.persons.luca

import li.kallisto.kgnkallistoadditions.registry.RegistryHelper
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.registerBlockWithItem
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.registerItem
import net.minecraft.util.Util
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
import java.util.*
import java.util.function.Consumer

object LucaBeanie {
    val ID = "luca_beanie"

    object Tags {
        val REPARIS_LUCA_BEANIE: TagKey<Item> = RegistryHelper.createTag("repairs_${ID}")
    }

    // ARMOR_MATERIAL must be initialized before ITEM: LucaBeanieItem's constructor
    // reads it eagerly now that registration happens at class-init instead of being
    // deferred to a later registry-event phase like NeoForge's DeferredRegister did.
    var ASSET: ResourceKey<EquipmentAsset> = EquipmentAssets.createId(ID)

    val ARMOR_MATERIAL = ArmorMaterial(
        37,
        Util.make(
            EnumMap(ArmorType::class.java),
            Consumer { attribute: EnumMap<ArmorType, Int> ->
                attribute[ArmorType.HELMET] = 3
            }),
        15,
        SoundEvents.ARMOR_EQUIP_NETHERITE,
        3f,
        0.1f,
        Tags.REPARIS_LUCA_BEANIE,
        ASSET
    )

    val ITEM = registerItem(ID, LucaBeanieItem())

    val BLOCK =
        run {
            val properties = BlockBehaviour.Properties.of()
                .noOcclusion()
                .instabreak()
                .lightLevel { 12 }
                .sound(SoundType.CROP)
                .noCollision()

            registerBlockWithItem(
                "cannabeanikum_solaris",
                { FireflyBushBlock(properties) },
                properties,
                Item.Properties()
            )
        }

    // Forces class-init in a fixed order from the main entrypoint.
    fun register() {
    }
}
