package li.kallisto.kgnkallistoadditions.persons.cosimo

import dev.tocraft.walkers.api.PlayerShape
import li.kallisto.kgnkallistoadditions.ability.UseAbilityPacket
import li.kallisto.kgnkallistoadditions.persons.cosimo.Druid.DRUID_ATTACHMENT
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.animal.wolf.Wolf
import net.neoforged.neoforge.network.handling.IPayloadContext

object AbilityPacketHandler {
    fun handle(message: UseAbilityPacket, context: IPayloadContext) {
        val player = context.player() as? ServerPlayer ?: return

        if (!player.getData(DRUID_ATTACHMENT).isDruid) return

        val wolf = EntityType.WOLF.create(player.level(), EntitySpawnReason.COMMAND)
        if (PlayerShape.getCurrentShape(player) is Wolf)
            PlayerShape.updateShapes(player, null)
        else
            PlayerShape.updateShapes(player, wolf)

        player.level().sendParticles(
            ParticleTypes.PALE_OAK_LEAVES,
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