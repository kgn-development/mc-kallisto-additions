package li.kallisto.kgnkallistoadditions

import com.mojang.blaze3d.platform.InputConstants
import li.kallisto.kgnkallistoadditions.ability.UseAbilityPacket
import net.minecraft.client.KeyMapping
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import net.neoforged.neoforge.client.network.ClientPacketDistributor
import net.neoforged.neoforge.client.settings.KeyConflictContext
import org.lwjgl.glfw.GLFW

object KGNKallistoAdditionsClient {
    val ABILITY_KEY = KeyMapping(
        "key.${KGNKallistoAdditions.ID}.ability",
        KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_G,
        "key.categories.${KGNKallistoAdditions.ID}.gameplay"
    )

    fun register(modEventBus: IEventBus, eventBus: IEventBus) {
        modEventBus.addListener(::registerKeys)
        eventBus.addListener(::onClientTick)
    }

    fun onClientTick(event: ClientTickEvent.Post) {
        if (ABILITY_KEY.consumeClick()) {
            ClientPacketDistributor.sendToServer(UseAbilityPacket())
        }
    }

    fun registerKeys(event: RegisterKeyMappingsEvent) {
        event.register(ABILITY_KEY)
    }
}