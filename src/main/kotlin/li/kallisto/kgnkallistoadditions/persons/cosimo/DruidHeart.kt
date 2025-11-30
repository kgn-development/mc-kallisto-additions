package li.kallisto.kgnkallistoadditions.persons.cosimo

import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult

class DruidHeart(properties: Properties) : Block(properties) {
    override fun useItemOn(
        stack: ItemStack,
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hitResult: BlockHitResult
    ): InteractionResult {
        val druidData = player.getData(Druid.DRUID_ATTACHMENT)
        if (!druidData.isDruid || level.isClientSide || state.getValue(ACTIVE))
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult)

        if (druidData.teleportPos != null) {
            val oldBlockPos = druidData.teleportPos!!
            if (level.getBlockState(oldBlockPos).`is`(this)) {
                level.setBlock(oldBlockPos, state.setValue(ACTIVE, false), 3)
                level.playSound(null, oldBlockPos, SoundEvents.CREAKING_HEART_BREAK, SoundSource.BLOCKS)
            }
        }

        druidData.teleportPos = pos
        level.setBlock(pos, state.setValue(ACTIVE, true), 3)
        level.playSound(null, pos, SoundEvents.CREAKING_HEART_SPAWN, SoundSource.BLOCKS)

        return InteractionResult.SUCCESS
    }

    companion object {
        val ACTIVE: BooleanProperty = BooleanProperty.create("active")
    }

    init {
        registerDefaultState(
            this.stateDefinition.any()
                .setValue(ACTIVE, false)
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(ACTIVE)
    }
}