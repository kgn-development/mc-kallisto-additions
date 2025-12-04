package li.kallisto.kgnkallistoadditions.persons.luca

import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.setItemModId
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType

class LucaBeanieItem : Item(
    Properties()
        .humanoidArmor(LucaBeanie.ARMOR_MATERIAL, ArmorType.HELMET)
        .setItemModId(LucaBeanie.ID)
)