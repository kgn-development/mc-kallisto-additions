package li.kallisto.kgnkallistoadditions.persons.leya

import net.minecraft.client.particle.ParticleProvider
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent

object PaintBrushClient {
    fun register(eventBus: IEventBus) {
        eventBus.addListener(::registerParticleFactories)
    }

    fun registerParticleFactories(event: RegisterParticleProvidersEvent) {
        event.registerSpriteSet(PaintBrush.COLOR_SPLASH_PARTICLE.get()) { spriteSet ->
            ParticleProvider { _, level, x, y, z, xSpeed, ySpeed, zSpeed ->
                ColorSmokeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet).apply {
                    pickSprite(spriteSet)
                }
            }
        }
    }
}