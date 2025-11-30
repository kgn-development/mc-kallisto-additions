package li.kallisto.kgnkallistoadditions.persons.leya

import li.kallisto.kgnkallistoadditions.registry.ModRegistries
import li.kallisto.kgnkallistoadditions.registry.RegistryHelper.setItemModId
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ToolMaterial
import net.minecraft.world.level.Level
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredHolder
import kotlin.math.cos
import kotlin.math.sin

object PaintBrush {
    val ID = "paintbrush_sword"

    val ITEM = ModRegistries.ITEMS.register(ID) { _ ->
        object : Item(
            Properties()
                .sword(ToolMaterial.NETHERITE, 3.0F, -2.4F)
                .fireResistant()
                .setItemModId("paintbrush_sword")
        ) {
            override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
                if (level is ServerLevel) {

                    val baseDir = player.lookAngle.normalize()

                    val arcDegrees = 100     // how wide the sweep is
                    val steps = 20           // how many particles form the arc

                    val startAngle = -arcDegrees / 2.0
                    val stepAngle = arcDegrees / steps.toDouble()

                    for (distance in 2..5) {
                        for (y in 0..5) {
                            for (i in 0..steps) {
                                val angle = Math.toRadians(startAngle + i * stepAngle)

                                val dx = baseDir.x * cos(angle) - baseDir.z * sin(angle)
                                val dz = baseDir.x * sin(angle) + baseDir.z * cos(angle)

                                val px = player.x + dx * distance
                                val py = player.y + y
                                val pz = player.z + dz * distance

                                level.sendParticles(
                                    COLOR_SPLASH_PARTICLE.get(),
                                    px, py, pz,
                                    1,
                                    0.05,
                                    0.05,
                                    0.05,
                                    0.2
                                )
                            }
                        }
                    }


                }

                return InteractionResult.SUCCESS
            }
        }
    }

    val COLOR_SPLASH_PARTICLE: DeferredHolder<ParticleType<*>?, SimpleParticleType?> =
        ModRegistries.PARTICLE_TYPE.register("color_splash") { _ -> SimpleParticleType(true) }

    fun register(eventBus: IEventBus) {
    }
}

