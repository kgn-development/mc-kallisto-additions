package li.kallisto.mcKallistoAdditions.persons.joel

import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EquipmentSlot

object AbilityPacketHandler {
    fun handle(player: ServerPlayer) {
        val headItem = player.getItemBySlot(EquipmentSlot.HEAD)
        if (headItem.`is`(JoelMustache.ITEM) && !player.cooldowns.isOnCooldown(headItem)) {
            player.removeAllEffects()
            player.cooldowns.addCooldown(headItem, 900)
        }
    }
}
