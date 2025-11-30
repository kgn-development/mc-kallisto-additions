package li.kallisto.kgnkallistoadditions.persons.cosimo

import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.setItemModId
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import kotlin.math.*

class DruidStaff : Item(Properties().setItemModId("druid_staff")) {
    override fun getUseDuration(stack: ItemStack, entity: LivingEntity): Int {
        return 80
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        val druidData = player.getData(Druid.DRUID_ATTACHMENT)

        if (!druidData.isDruid) return InteractionResult.PASS

        player.startUsingItem(hand)

        return InteractionResult.SUCCESS
    }

    override fun onUseTick(
        level: Level,
        livingEntity: LivingEntity,
        stack: ItemStack,
        remainingUseDuration: Int
    ) {
        if (level !is ServerLevel) return

        val ticksSinceStartedUsing = this.getUseDuration(stack, livingEntity) - remainingUseDuration

        val exponent = (ticksSinceStartedUsing / 20.0).pow(2) + 7
        val slices = round(exponent).toInt()
        val height = (exponent / 4) - 1.7
        val distance = 0.5 + (ticksSinceStartedUsing / 20.0) / 4
        val speed = 0.02 * exponent

        val time = livingEntity.tickCount * speed

        for (i in 0 until slices) {
            val angle = time + (Math.PI * 2 * i / slices)

            val x = livingEntity.x + cos(angle) * distance
            val z = livingEntity.z + sin(angle) * distance

            val heightSteps = round(sqrt(height * (exponent / 4)) + 2).toInt()
            val stepSize = height / heightSteps
            for (y in 0 until heightSteps) {
                level.sendParticles(
                    ParticleTypes.PALE_OAK_LEAVES,
                    x,
                    livingEntity.y + y * stepSize + 0.05,
                    z,
                    1,
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                )
            }
        }

        super.onUseTick(level, livingEntity, stack, remainingUseDuration)
    }

    override fun finishUsingItem(stack: ItemStack, level: Level, livingEntity: LivingEntity): ItemStack {
        val druidData = livingEntity.getData(Druid.DRUID_ATTACHMENT)
        if (level is ServerLevel && livingEntity is Player && druidData.isDruid && druidData.teleportPos != null) {
            val heartPos = druidData.teleportPos!!
            if (level.getBlockState(heartPos).`is`(Druid.DRUID_HEART)) {
                val saveTeleportPos = findSafeTeleport(level, heartPos)
                if (saveTeleportPos != null) {
                    level.playSound(
                        null,
                        livingEntity.blockPosition(),
                        SoundEvents.PLAYER_TELEPORT,
                        SoundSource.PLAYERS
                    )
                    livingEntity.teleportTo(
                        saveTeleportPos.x + 0.5,
                        saveTeleportPos.y.toDouble(),
                        saveTeleportPos.z + 0.5
                    )
                }
            }
        }

        return super.finishUsingItem(stack, level, livingEntity)
    }

    fun findSafeTeleport(level: Level, center: BlockPos): BlockPos? {
        for (dy in 0..3) {
            val pos = center.above(dy)
            if (isSafePosition(level, pos)) return pos
        }

        val maxRadius = 5
        for (r in 1..maxRadius) {
            for (dx in -r..r) {
                for (dz in -r..r) {
                    val pos = center.offset(dx, 0, dz)
                    if (isSafePosition(level, pos)) return pos
                }
            }
        }

        for (dy in -1 downTo -3) {
            val pos = center.offset(0, dy, 0)
            if (isSafePosition(level, pos)) return pos
        }

        return null
    }

    fun isSafePosition(level: Level, pos: BlockPos): Boolean {
        val feet = level.getBlockState(pos)
        val head = level.getBlockState(pos.above())
        val ground = level.getBlockState(pos.below())

        return feet.isAir && head.isAir && ground.isSolid
    }
}