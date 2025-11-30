package li.kallisto.kgnkallistoadditions.persons.cosimo

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgnkallistoadditions.registry.ModRegistries.ATTACHMENT_TYPES
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.state.BlockBehaviour
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.attachment.AttachmentType
import net.neoforged.neoforge.event.RegisterCommandsEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import java.util.*

object Druid {

    val DRUID_ATTACHMENT = ATTACHMENT_TYPES.register("druid") { _ ->
        AttachmentType.builder { _ -> DruidData() }
            .serialize(
                RecordCodecBuilder.mapCodec { instance ->
                    instance.group(
                        Codec.BOOL.fieldOf("druid").forGetter { it.isDruid },
                        BlockPos.CODEC.optionalFieldOf("teleportPos")
                            .forGetter { Optional.ofNullable(it.teleportPos) }
                    ).apply(instance) { druid, pos ->
                        DruidData(druid, pos.orElse(null))
                    }
                }
            )
            .build()
    }

    val DRUID_HEART = run {
        val properties = BlockBehaviour.Properties.of()
            .strength(3f)

        RegistryHelper.registerBlockWithItem("druid_heart", { DruidHeart(properties) }, properties, Item.Properties())
    }

    val DRUID_STAFF = ModRegistries.ITEMS.register("druid_staff", ::DruidStaff)

    fun register(eventBus: IEventBus) {
        eventBus.addListener(this::registerCommands)
        eventBus.addListener(this::onPlayerClone)
    }

    fun onPlayerClone(event: PlayerEvent.Clone) {
        if (!event.isWasDeath) return

        event.entity.setData(DRUID_ATTACHMENT, event.original.getData(DRUID_ATTACHMENT))
    }

    fun registerCommands(event: RegisterCommandsEvent) {
        event.dispatcher.register(
            Commands.literal("druid")
                .requires { it.hasPermission(2) }
                .then(
                    Commands.argument("player", EntityArgument.player())
                        .executes { ctx ->
                            val target = EntityArgument.getPlayer(ctx, "player")
                            val druidData = target.getData(DRUID_ATTACHMENT)

                            target.setData(DRUID_ATTACHMENT, druidData.apply { isDruid = !druidData.isDruid })

                            ctx.source.sendSuccess({
                                Component.literal("${target.name.string} is ${if (druidData.isDruid) "now" else "no longer"} a druid!")
                            }, false)

                            1
                        }
                )
        )
    }
}

