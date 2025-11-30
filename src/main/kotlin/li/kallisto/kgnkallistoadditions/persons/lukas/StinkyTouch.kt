package li.kallisto.kgnkallistoadditions.persons.lukas

import com.mojang.serialization.Codec
import li.kallisto.kgnkallistoadditions.registry.ModRegistries.ATTACHMENT_TYPES
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.event.RegisterCommandsEvent
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import java.util.*

object StinkyTouch {

    val DEHEEEB = UUID.fromString("543b1632-5d09-442a-a2cb-7f1ee969cf83")

    val STINKY_ATTACHMENT = ATTACHMENT_TYPES.register("stinky_attachment") { _ ->
        AttachmentType.builder { _ -> false }.serialize(Codec.BOOL.fieldOf("stinky")).build()
    }

    fun register(eventBus: IEventBus) {
        eventBus.addListener(this::onLivingDamage)
        eventBus.addListener(this::registerCommands)
        eventBus.addListener(this::onPlayerClone)
    }

    fun onPlayerClone(event: PlayerEvent.Clone) {
        if (!event.isWasDeath) return

        event.entity.setData(STINKY_ATTACHMENT, event.original.getData(STINKY_ATTACHMENT))
    }

    fun registerCommands(event: RegisterCommandsEvent) {
        event.dispatcher.register(
            Commands.literal("stinky")
                .requires { it.hasPermission(2) }
                .then(
                    Commands.argument("player", EntityArgument.player())
                        .executes { ctx ->
                            val target = EntityArgument.getPlayer(ctx, "player")
                            val current = target.getData(STINKY_ATTACHMENT)

                            target.setData(STINKY_ATTACHMENT, !current)

                            ctx.source.sendSuccess({
                                Component.literal("${target.name.string} is ${if (current) "no longer" else "now"} STINKY!")
                            }, false)

                            1
                        }
                )
        )
    }

    fun onLivingDamage(event: LivingDamageEvent.Pre) {
        val player = event.source.directEntity
        if (player !is ServerPlayer) return

        if (player.uuid == DEHEEEB && event.entity.getData(STINKY_ATTACHMENT)) {
            event.entity.addEffect(
                MobEffectInstance(
                    MobEffects.NAUSEA,
                    200,
                    0,
                    false,
                    false,
                    false
                )
            )
            event.entity.addEffect(
                MobEffectInstance(
                    MobEffects.DARKNESS,
                    200,
                    0,
                    false,
                    false,
                    false
                )
            )
            event.entity.addEffect(
                MobEffectInstance(
                    MobEffects.LEVITATION,
                    200,
                    0,
                    false,
                    false,
                    false
                )
            )
        }
    }
}