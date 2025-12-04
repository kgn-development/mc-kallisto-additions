package li.kallisto.kgnkallistoadditions.persons.joel

import li.kallisto.kgnkallistoadditions.ability.UseAbilityPacket
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EquipmentSlot
import net.neoforged.neoforge.network.handling.IPayloadContext

object AbilityPacketHandler {
    fun handle(message: UseAbilityPacket, context: IPayloadContext) {
        val player = context.player() as? ServerPlayer ?: return

        val headItem = player.getItemBySlot(EquipmentSlot.HEAD)
        if (headItem.`is`(JoelMustache.ITEM) && !player.cooldowns.isOnCooldown(headItem)) {
            player.removeAllEffects()
            player.cooldowns.addCooldown(headItem, 900)
        }
    }
}