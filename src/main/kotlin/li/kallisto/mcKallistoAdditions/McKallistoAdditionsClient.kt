package li.kallisto.mcKallistoAdditions

import com.mojang.blaze3d.platform.InputConstants
import li.kallisto.mcKallistoAdditions.ability.UseAbilityPacket
import li.kallisto.mcKallistoAdditions.persons.joel.client.JoelMustacheClient
import li.kallisto.mcKallistoAdditions.persons.leya.PaintBrushClient
import li.kallisto.mcKallistoAdditions.persons.luca.client.LucaBeanieClient
import li.kallisto.mcKallistoAdditions.persons.michi.client.MichiCapClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.KeyMapping

object McKallistoAdditionsClient : ClientModInitializer {
    val ABILITY_CATEGORY = KeyMapping.Category(McKallistoAdditions.locate("gameplay"))

    val ABILITY_KEY = KeyMapping(
        "key.${McKallistoAdditions.ID}.ability",
        InputConstants.KEY_G,
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
