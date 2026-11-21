package li.kallisto.mcKallistoAdditions.registry.creativemodetabs

import li.kallisto.mcKallistoAdditions.McKallistoAdditions
import li.kallisto.mcKallistoAdditions.registry.ModRegistries
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

object ModCreativeModeTabs {
    val KALLISTO_ABILITIES_TAB = run {
        val key = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB, McKallistoAdditions.locate("kallisto_abilities_tab")
        )

        val tab = FabricCreativeModeTab.builder()
            .icon { ItemStack(Items.NETHER_STAR) }
            .title(Component.translatable("creativetab.${McKallistoAdditions.ID}.kallisto_abilities_tab"))
            .displayItems { _, output ->
                // Items only: registerBlockWithItem puts every block's BlockItem into
                // ModRegistries.items as well, so also iterating blocks would offer each
                // of those twice. Creative tabs throw on a duplicate entry since 26.1
                // instead of silently dropping it.
                ModRegistries.items.forEach {
                    output.accept(it.value())
                }
            }
            .build()

        Registry.registerForHolder(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab)
    }

    fun register() {
        // Forces class-init so the tab registers; call from the mod initializer.
    }
}