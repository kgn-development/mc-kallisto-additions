package li.kallisto.kgnkallistoadditions.persons.ginger

import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgnkallistoadditions.registry.ModRegistries.SOUND_EVENTS
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.setItemModId
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUseAnimation
import net.minecraft.world.level.Level
import net.neoforged.bus.api.IEventBus

object Trumpet {
    val ID = "trumpet"

    val TRUMPET_SOUND = SOUND_EVENTS.register("trumpet_sound", SoundEvent::createVariableRangeEvent)

    val ITEM = ModRegistries.ITEMS.register(ID) { _ ->
        object : Item(Properties().setItemModId(ID)) {
            override fun getUseAnimation(stack: ItemStack): ItemUseAnimation {
                return ItemUseAnimation.TOOT_HORN
            }

            override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
                if (level !is ServerLevel) return InteractionResult.PASS

                val pitch = player.xRot * -1
                val normalized = (pitch + 90f) / 180f
                val soundPitch = 0.5f + normalized * 1.5f

                val eyePos = player.eyePosition
                val lookDir = player.lookAngle
                val distance = 0.8
                val soundPos = eyePos.add(
                    lookDir.x * distance,
                    lookDir.y * distance,
                    lookDir.z * distance
                )

                level.playSound(
                    null,
                    soundPos.x,
                    soundPos.y,
                    soundPos.z,
                    TRUMPET_SOUND.get(),
                    SoundSource.PLAYERS,
                    1f,
                    soundPitch
                )

                return InteractionResult.SUCCESS
            }
        }
    }

    fun register(eventBus: IEventBus) {
    }
}