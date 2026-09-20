package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrush
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.references.BlockItemIds
import net.minecraft.references.ItemIds
import net.minecraft.tags.ItemTags
import net.neoforged.neoforge.common.data.ItemTagsProvider
import java.util.concurrent.CompletableFuture


class ModItemTagProvider(
    packOutput: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : ItemTagsProvider(packOutput, lookupProvider, KGNKallistoAdditions.ID) {

    // TagAppender takes ResourceKeys only since 26.2, so vanilla entries come from
    // the ItemIds/BlockItemIds reference classes and ours from each holder's key.
    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(MichiCap.Tags.REPARIS_MICHI_HAT).add(BlockItemIds.WOOL.red().item())
        this.tag(LucaBeanie.Tags.REPARIS_LUCA_BEANIE).add(BlockItemIds.WOOL.gray().item())
        this.tag(JoelMustache.Tags.REPARIS_JOEL_MUSTACHE).add(ItemIds.NETHER_STAR)
        this.tag(ItemTags.SWORDS).add(PaintBrush.ITEM.key)
        this.tag(ItemTags.HEAD_ARMOR)
            .addAll(listOf(MichiCap.ITEM.key, LucaBeanie.ITEM.key, JoelMustache.ITEM.key))
    }
}
