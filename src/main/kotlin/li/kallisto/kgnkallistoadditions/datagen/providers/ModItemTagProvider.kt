package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.persons.joel.JoelMustache
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrush
import li.kallisto.kgnkallistoadditions.persons.luca.LucaBeanie
import li.kallisto.kgnkallistoadditions.persons.michi.MichiCap
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.references.BlockItemIds
import net.minecraft.references.ItemIds
import net.minecraft.tags.ItemTags
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(
    output: FabricPackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>
) : FabricTagsProvider.ItemTagsProvider(output, lookupProvider) {

    // TagAppender takes ResourceKeys only since 26.2, so vanilla entries come from
    // the ItemIds/BlockItemIds reference classes and ours from each holder's key.
    override fun addTags(provider: HolderLookup.Provider) {
        this.builder(MichiCap.Tags.REPARIS_MICHI_HAT).add(BlockItemIds.WOOL.red().item())
        this.builder(LucaBeanie.Tags.REPARIS_LUCA_BEANIE).add(BlockItemIds.WOOL.gray().item())
        this.builder(JoelMustache.Tags.REPARIS_JOEL_MUSTACHE).add(ItemIds.NETHER_STAR)
        this.builder(ItemTags.SWORDS).add(PaintBrush.ITEM.key())
        this.builder(ItemTags.HEAD_ARMOR)
            .addAll(listOf(MichiCap.ITEM.key(), LucaBeanie.ITEM.key(), JoelMustache.ITEM.key()))
    }
}
