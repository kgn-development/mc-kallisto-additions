package li.kallisto.kgnkallistoadditions.persons.leya

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry
import net.minecraft.client.particle.ParticleProvider

object PaintBrushClient {
    fun register() {
        ParticleProviderRegistry.getInstance().register(PaintBrush.COLOR_SPLASH_PARTICLE) { spriteSet ->
            ParticleProvider { _, level, x, y, z, xSpeed, ySpeed, zSpeed, _ ->
                ColorSmokeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet)
            }
        }
    }
}
