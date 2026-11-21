package li.kallisto.mcKallistoAdditions.persons.michi

import li.kallisto.mcKallistoAdditions.registry.RegistryHelper
import li.kallisto.mcKallistoAdditions.registry.RegistryHelper.registerItem
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.minecraft.util.Util
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
import java.util.*
import java.util.function.Consumer

object MichiCap {
    val ID = "michi_cap"

    object Tags {
        val REPARIS_MICHI_HAT: TagKey<Item> = RegistryHelper.createTag("repairs_${ID}")
    }

    // ARMOR_MATERIAL must be initialized before ITEM: MichiCapItem's constructor reads
    // it eagerly now that registration happens at class-init instead of being deferred
    // to a later registry-event phase like NeoForge's DeferredRegister did.
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
        Tags.REPARIS_MICHI_HAT,
        ASSET
    )

    val ITEM = registerItem(ID, MichiCapItem())

    fun register() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(ServerLivingEntityEvents.AllowDamage { entity, source, amount ->
            val player = entity as? ServerPlayer ?: return@AllowDamage true

            val helmet = player.getItemBySlot(EquipmentSlot.HEAD)
            if (helmet.item != ITEM.value()) return@AllowDamage true

            if (player.cooldowns.isOnCooldown(helmet)) return@AllowDamage true

            val currentHealth = player.health + player.absorptionAmount
            val maxHealth = player.maxHealth + player.absorptionAmount

            if (currentHealth <= amount && currentHealth == maxHealth) {
                player.health = 1.0f
                player.absorptionAmount = 0f
                player.cooldowns.addCooldown(helmet, 5 * 60 * 20)
                player.level().playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1f, 1f)
                return@AllowDamage false
            }

            true
        })
    }
}
