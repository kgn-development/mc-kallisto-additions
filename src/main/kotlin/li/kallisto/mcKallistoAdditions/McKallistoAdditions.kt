package li.kallisto.mcKallistoAdditions

import li.kallisto.mcKallistoAdditions.ability.UseAbilityPacket
import li.kallisto.mcKallistoAdditions.persons.joel.JoelMustache
import li.kallisto.mcKallistoAdditions.persons.leya.PaintBrush
import li.kallisto.mcKallistoAdditions.persons.luca.LucaBeanie
import li.kallisto.mcKallistoAdditions.persons.michi.MichiCap
import li.kallisto.mcKallistoAdditions.registry.creativemodetabs.ModCreativeModeTabs
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.resources.Identifier
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object McKallistoAdditions : ModInitializer {
    const val ID = "mc-kallisto-additions"

    val LOGGER: Logger = LogManager.getLogger(ID)

    override fun onInitialize() {
        LOGGER.log(Level.INFO, "initializing ${ID}")

        // Order matches the previous NeoForge init block, preserving creative-tab order.
        MichiCap.register()
        LucaBeanie.register()
        JoelMustache.register()
        PaintBrush.register()
        ModCreativeModeTabs.register()

        PayloadTypeRegistry.serverboundPlay().register(UseAbilityPacket.TYPE, UseAbilityPacket.STREAM_CODEC)
        ServerPlayNetworking.registerGlobalReceiver(UseAbilityPacket.TYPE) { _, context ->
            context.server().execute {
                li.kallisto.mcKallistoAdditions.persons.luca.AbilityPacketHandler.handle(context.player())
                li.kallisto.mcKallistoAdditions.persons.joel.AbilityPacketHandler.handle(context.player())
            }
        }
    }

    fun locate(name: String): Identifier {
        return Identifier.fromNamespaceAndPath(ID, name)
    }
}
