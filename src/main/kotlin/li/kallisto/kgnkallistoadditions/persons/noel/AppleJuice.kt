package li.kallisto.kgnkallistoadditions.persons.noel

import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.setItemModId
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item
import net.minecraft.world.item.component.Consumables.defaultDrink
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent
import net.neoforged.neoforge.event.tick.EntityTickEvent

object AppleJuice {
    val ID = "apple_juice"

    val ITEM = ModRegistries.ITEMS.register(ID) { _ ->
        Item(
            Item.Properties().setItemModId("apple_juice")
                .food(
                    FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.5f)
                        .build(),
                    defaultDrink().build()
                )
        )
    }
    val RULE_SHOULD_EXPLODE =
        GameRules.register("doesAppleJuiceExplode", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false))

    fun register(eventBus: IEventBus) {
        eventBus.addListener(CauldronItemHandler::onEntityJoin)
        eventBus.addListener(CauldronItemHandler::onEntityTick)
    }

    object CauldronItemHandler {
        private val tracked = mutableSetOf<ItemEntity>()

        fun onEntityJoin(event: EntityJoinLevelEvent) {
            val entity = event.entity
            val level = entity.level()
            if (level !is ServerLevel) return

            if (entity is ItemEntity && entity.item.`is`(AppleJuice.ITEM)) {
                tracked.add(entity)
            }
        }

        @SubscribeEvent
        fun onEntityTick(event: EntityTickEvent.Pre) {
            val entity = event.entity
            val level = entity.level()
            if (level !is ServerLevel) return

            if (entity !is ItemEntity) return
            if (!tracked.contains(entity)) return
            if (level.gameRules.getBoolean(AppleJuice.RULE_SHOULD_EXPLODE) == false) return

            val pos = entity.blockPosition()
            val blockState: BlockState = level.getBlockState(pos)

            if (blockState.`is`(Blocks.WATER_CAULDRON)) {
                if (!level.isClientSide) {
                    level.explode(
                        null,
                        pos.x + 0.5,
                        pos.y + 0.5,
                        pos.z + 0.5,
                        5.0f,
                        false,
                        Level.ExplosionInteraction.NONE
                    )
                }

                entity.remove(Entity.RemovalReason.KILLED)

                tracked.remove(entity)
            } else {
                if (entity.isRemoved) {
                    tracked.remove(entity)
                }
            }
        }
    }
}

