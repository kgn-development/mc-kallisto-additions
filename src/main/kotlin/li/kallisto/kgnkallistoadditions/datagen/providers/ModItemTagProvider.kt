package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Items
import net.neoforged.neoforge.common.data.ItemTagsProvider
import java.util.concurrent.CompletableFuture


class ModItemTagProvider(
    packOutput: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : ItemTagsProvider(packOutput, lookupProvider, KGNKallistoAdditions.ID) {

    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(MichiCap.Tags.REPARIS_MICHI_HAT).add(Items.RED_WOOL)
    }
}

