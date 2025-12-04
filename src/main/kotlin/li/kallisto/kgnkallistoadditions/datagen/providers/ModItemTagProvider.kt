package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrush
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items
import net.neoforged.neoforge.common.data.ItemTagsProvider
import java.util.concurrent.CompletableFuture


class ModItemTagProvider(
    packOutput: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : ItemTagsProvider(packOutput, lookupProvider, KGNKallistoAdditions.ID) {

    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(MichiCap.Tags.REPARIS_MICHI_HAT).add(Items.RED_WOOL)
        this.tag(LucaBeanie.Tags.REPARIS_LUCA_BEANIE).add(Items.GRAY_WOOL)
        this.tag(JoelMustache.Tags.REPARIS_JOEL_MUSTACHE).add(Items.NETHER_STAR)
        this.tag(ItemTags.SWORDS).add(PaintBrush.ITEM.get())
        this.tag(ItemTags.HEAD_ARMOR)
            .addAll(listOf(MichiCap.ITEM.get(), LucaBeanie.ITEM.get(), JoelMustache.ITEM.get()))
    }
}


