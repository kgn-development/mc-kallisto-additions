package li.kallisto.kgnkallistoadditions.persons.cosimo

import dev.tocraft.walkers.api.PlayerShape
import li.kallisto.kgnkallistoadditions.ability.UseAbilityPacket
import li.kallisto.kgnkallistoadditions.persons.cosimo.Druid.DRUID_ATTACHMENT
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ambient.Bat
import net.neoforged.neoforge.network.handling.IPayloadContext

object AbilityPacketHandler {
    fun handle(message: UseAbilityPacket, context: IPayloadContext) {
        val player = context.player() as? ServerPlayer ?: return

        if (!player.getData(DRUID_ATTACHMENT).isDruid) return

        val bat = EntityType.BAT.create(player.level(), EntitySpawnReason.COMMAND)
        if (PlayerShape.getCurrentShape(player) is Bat)
            PlayerShape.updateShapes(player, null)
        else
            PlayerShape.updateShapes(player, bat)

        player.level().sendParticles(
            ParticleTypes.SQUID_INK,
            player.x,
            player.y,
            player.z,
            500,
            0.5,
            0.5,
            0.5,
            0.2
        )
    }
}