package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.leya.PaintBrush
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider

class ModParticleProvider(
    output: PackOutput
) : ParticleDescriptionProvider(output) {

    override fun addDescriptions() {
        sprite(
            PaintBrush.COLOR_SPLASH_PARTICLE.get(),
            KGNKallistoAdditions.locate("color_splash")
        )
    }
}