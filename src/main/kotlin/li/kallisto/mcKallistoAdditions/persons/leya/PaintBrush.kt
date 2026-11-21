package li.kallisto.mcKallistoAdditions.persons.leya

import li.kallisto.mcKallistoAdditions.McKallistoAdditions
import li.kallisto.mcKallistoAdditions.registry.RegistryHelper.registerItem
import li.kallisto.mcKallistoAdditions.registry.RegistryHelper.setItemModId
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ToolMaterial
import net.minecraft.world.level.Level
import kotlin.math.cos
import kotlin.math.sin

object PaintBrush {
    val ID = "paintbrush_sword"

    // SimpleParticleType's constructor is protected (NeoForge patches it open); a tiny
    // subclass is the vanilla-compatible way to instantiate it.
    class ColorSplashParticleType : SimpleParticleType(true)

    // The concrete SimpleParticleType, kept separately from its Holder since vanilla's
    // Registry.registerForHolder only preserves the registry's own generic (ParticleType<*>),
    // not the concrete subtype NeoForge's DeferredRegister used to hand back.
    val COLOR_SPLASH_PARTICLE = ColorSplashParticleType()

    val ITEM = registerItem(ID,
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
                                    COLOR_SPLASH_PARTICLE,
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
    )

    val COLOR_SPLASH_PARTICLE_HOLDER: Holder.Reference<ParticleType<*>> = run {
        val key = ResourceKey.create(Registries.PARTICLE_TYPE, McKallistoAdditions.locate("color_splash"))
        val particleType: ParticleType<*> = COLOR_SPLASH_PARTICLE
        Registry.registerForHolder(BuiltInRegistries.PARTICLE_TYPE, key, particleType)
    }

    // Forces class-init in a fixed order from the main entrypoint.
    fun register() {
    }
}

