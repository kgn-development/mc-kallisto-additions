package li.kallisto.kgnkallistoadditions

import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import li.kallisto.kgnkallistoadditions.persons.michi.client.MichiCapClient
import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgntemplatemod.datagen.ModDataGenerator
import net.minecraft.resources.ResourceLocation
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
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

        MichiCap.register(NeoForge.EVENT_BUS)
        runForDist(clientTarget = {
            MichiCapClient.register(MOD_BUS)
        }, serverTarget = {

        })
    }

    fun locate(name: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(ID, name)
    }
}
