package li.kallisto.mcKallistoAdditions.persons.joel

import li.kallisto.mcKallistoAdditions.registry.RegistryHelper.setItemModId
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType

class JoelMustacheItem : Item(
    Properties()
        .humanoidArmor(JoelMustache.ARMOR_MATERIAL, ArmorType.HELMET)
        .setItemModId(JoelMustache.ID)
)