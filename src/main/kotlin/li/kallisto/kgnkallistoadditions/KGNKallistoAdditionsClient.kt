package li.kallisto.kgnkallistoadditions

import com.mojang.blaze3d.platform.InputConstants
import li.kallisto.kgnkallistoadditions.ability.UseAbilityPacket
import li.kallisto.kgnkallistoadditions.persons.joel.client.JoelMustacheClient
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrushClient
import li.kallisto.kgnkallistoadditions.persons.luca.client.LucaBeanieClient
import li.kallisto.kgnkallistoadditions.persons.michi.client.MichiCapClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.KeyMapping
import org.lwjgl.glfw.GLFW

object KGNKallistoAdditionsClient : ClientModInitializer {
    val ABILITY_CATEGORY = KeyMapping.Category(KGNKallistoAdditions.locate("gameplay"))

    val ABILITY_KEY = KeyMapping(
        "key.${KGNKallistoAdditions.ID}.ability",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_G,
        ABILITY_CATEGORY
    )

    override fun onInitializeClient() {
        KeyMappingHelper.registerKeyMapping(ABILITY_KEY)

        MichiCapClient.register()
        LucaBeanieClient.register()
        JoelMustacheClient.register()
        PaintBrushClient.register()

        ClientTickEvents.END_CLIENT_TICK.register { _ ->
            while (ABILITY_KEY.consumeClick()) {
                ClientPlayNetworking.send(UseAbilityPacket())
            }
        }
    }
}
