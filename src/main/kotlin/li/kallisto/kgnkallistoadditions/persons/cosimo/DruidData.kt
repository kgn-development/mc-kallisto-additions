package li.kallisto.kgnkallistoadditions.persons.cosimo

import net.minecraft.core.BlockPos

data class DruidData(
    var isDruid: Boolean = false,
    var teleportPos: BlockPos? = null
)