package li.kallisto.kgntemplatemod.item

import li.kallisto.kgntemplatemod.KGNTemplateMod
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister

object ModCreativeModeTabs {
    val CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KGNTemplateMod.ID)

    val KALLISTO_MUSIC_TAB = CREATIVE_MODE_TAB.register(
        "${KGNTemplateMod.ID}_tab"
    ) { _ ->
        CreativeModeTab.builder()
            .icon({ ItemStack(Items.NETHER_STAR) })
            .title(Component.translatable("creativetab.${KGNTemplateMod.ID}.creative_tab"))
            .displayItems { itemDisplayParameters, output ->
//                output.accept(ModItems.item)
            }
            .build()
    }

    fun register(eventBus: IEventBus) {
        CREATIVE_MODE_TAB.register(eventBus)
    }
}