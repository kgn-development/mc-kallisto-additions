package li.kallisto.kgntemplatemod

import li.kallisto.kgntemplatemod.block.ModBlocks
import li.kallisto.kgntemplatemod.datagen.ModDataGenerator
import li.kallisto.kgntemplatemod.item.ModCreativeModeTabs
import li.kallisto.kgntemplatemod.item.ModItems
import net.minecraft.resources.ResourceLocation
import net.neoforged.fml.common.Mod
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

@Mod(KGNTemplateMod.ID)
object KGNTemplateMod {
    const val ID = "kgntemplatemod"

    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.log(Level.INFO, "initializing ${ID}")

        ModBlocks.register(MOD_BUS)
        ModItems.register(MOD_BUS)
        ModCreativeModeTabs.register(MOD_BUS)

        MOD_BUS.addListener(ModDataGenerator::onGatherClientData)

        runForDist(clientTarget = {

        }, serverTarget = {

        })
    }


    fun locate(name: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(ID, name)
    }
}
