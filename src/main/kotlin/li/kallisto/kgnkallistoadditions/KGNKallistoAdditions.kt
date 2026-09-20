package li.kallisto.kgnkallistoadditions

import li.kallisto.kgnkallistoadditions.ability.UseAbilityPacket
import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.joel.client.JoelMustacheClient
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrush
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrushClient
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import li.kallisto.kgnkallistoadditions.persons.luca.client.LucaBeanieClient
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import li.kallisto.kgnkallistoadditions.persons.michi.client.MichiCapClient
import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgntemplatemod.datagen.ModDataGenerator
import net.minecraft.resources.Identifier
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist


@Mod(KGNKallistoAdditions.ID)
object KGNKallistoAdditions {
    const val ID = "kgnkallistoadditions"

    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.log(Level.INFO, "initializing ${ID}")

        ModRegistries.register(MOD_BUS)

        MOD_BUS.addListener(ModDataGenerator::onGatherClientData)
        MOD_BUS.addListener(::registerPayloads)

        MichiCap.register(NeoForge.EVENT_BUS)
        LucaBeanie.register(NeoForge.EVENT_BUS)
        JoelMustache.register(NeoForge.EVENT_BUS)
        PaintBrush.register(NeoForge.EVENT_BUS)

        runForDist(clientTarget = {
            MichiCapClient.register(MOD_BUS)
            LucaBeanieClient.register(MOD_BUS)
            JoelMustacheClient.register(MOD_BUS)
            PaintBrushClient.register(MOD_BUS)
            KGNKallistoAdditionsClient.register(MOD_BUS, NeoForge.EVENT_BUS)
        }, serverTarget = {

        })
    }

    fun registerPayloads(event: RegisterPayloadHandlersEvent) {
        val registrar = event.registrar(ID)

        registrar.playToServer(
            UseAbilityPacket.TYPE,
            UseAbilityPacket.STREAM_CODEC,
            UseAbilityPacket::handle
        )
    }

    fun locate(name: String): Identifier {
        return Identifier.fromNamespaceAndPath(ID, name)
    }
}

