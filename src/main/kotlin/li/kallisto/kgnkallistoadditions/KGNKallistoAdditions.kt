package li.kallisto.kgnkallistoadditions

import li.kallisto.kgnkallistoadditions.ability.UseAbilityPacket
import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrush
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import li.kallisto.kgnkallistoadditions.registry.creativemodetabs.ModCreativeModeTabs
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.resources.Identifier
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object KGNKallistoAdditions : ModInitializer {
    const val ID = "kgnkallistoadditions"

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
                li.kallisto.kgnkallistoadditions.persons.luca.AbilityPacketHandler.handle(context.player())
                li.kallisto.kgnkallistoadditions.persons.joel.AbilityPacketHandler.handle(context.player())
            }
        }
    }

    fun locate(name: String): Identifier {
        return Identifier.fromNamespaceAndPath(ID, name)
    }
}
