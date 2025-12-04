package li.kallisto.kgnkallistoadditions.persons.joel

import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.setItemModId
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType

class JoelMustacheItem : Item(
    Properties()
        .humanoidArmor(JoelMustache.ARMOR_MATERIAL, ArmorType.HELMET)
        .setItemModId(JoelMustache.ID)
)