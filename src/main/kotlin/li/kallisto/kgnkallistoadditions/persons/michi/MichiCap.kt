package li.kallisto.kgnkallistoadditions.persons.michi

import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper
import net.minecraft.Util
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.EquipmentAsset
import net.minecraft.world.item.equipment.EquipmentAssets
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent
import java.util.*
import java.util.function.Consumer

object MichiCap {
    val ID = "michi_cap"

    object Tags {
        val REPARIS_MICHI_HAT: TagKey<Item?> = RegistryHelper.createTag("repairs_${ID}")
    }

    val ITEM = ModRegistries.ITEMS.register(ID, ::MichiCapItem)

    var ASSET: ResourceKey<EquipmentAsset?> = EquipmentAssets.createId(ID)

    val ARMOR_MATERIAL = ArmorMaterial(
        37,
        Util.make(
            EnumMap(ArmorType::class.java),
            Consumer { attribute: EnumMap<ArmorType?, Int?>? ->
                attribute!![ArmorType.HELMET] = 3
            }),
        15,
        SoundEvents.ARMOR_EQUIP_NETHERITE,
        3f,
        0.1f,
        Tags.REPARIS_MICHI_HAT,
        ASSET
    )

    fun register(eventBus: IEventBus) {
        eventBus.addListener(::onLivingHurt)
    }

    fun onLivingHurt(event: LivingDamageEvent.Pre) {
        val player = event.entity
        if (player !is ServerPlayer) return

        val helmet = player.getItemBySlot(EquipmentSlot.HEAD)
        val helmetItem = MichiCap.ITEM.get()
        if (helmet.item != helmetItem) return

        if (player.cooldowns.isOnCooldown(helmet)) return

        val damage = event.newDamage
        val currentHealth = player.health + player.absorptionAmount
        val maxHealth = player.maxHealth + player.absorptionAmount

        if (currentHealth <= damage && currentHealth == maxHealth) {
            event.newDamage = 0f
            player.health = 1.0f
            player.absorptionAmount = 0f
            player.cooldowns.addCooldown(helmet, 5 * 60 * 20)
            player.level().playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1f, 1f)
        }
    }
}

