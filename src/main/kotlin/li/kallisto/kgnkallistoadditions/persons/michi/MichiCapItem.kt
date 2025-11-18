package li.kallisto.kgnkallistoadditions.persons.michi

import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.setItemModId
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType

class MichiCapItem : Item(
    Properties()
        .humanoidArmor(MichiCap.ARMOR_MATERIAL, ArmorType.HELMET)
        .setItemModId(MichiCap.ID)
)