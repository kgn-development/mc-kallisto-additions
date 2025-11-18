package li.kallisto.kgnkallistoadditions.registry.creativemodetabs

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModCreativeModeTabs {
    val CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KGNKallistoAdditions.ID)

    val KALLISTO_ABILITIES_TAB = CREATIVE_MODE_TAB.register(
        "kallisto_abilities_tab"
    ) { _ ->
        CreativeModeTab.builder()
            .icon { ItemStack(Items.NETHER_STAR) }
            .title(Component.translatable("creativetab.${KGNKallistoAdditions.ID}.kallisto_abilities_tab"))
            .displayItems { _, output ->
                ModRegistries.ITEMS.entries.forEach {
                    output.accept(it.get())
                }
                ModRegistries.BLOCKS.entries.forEach {
                    output.accept(it.get())
                }
            }
            .build()
    }

    fun register(eventBus: IEventBus) {
        CREATIVE_MODE_TAB.register(eventBus)
    }
}