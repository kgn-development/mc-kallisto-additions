package li.kallisto.kgnkallistoadditions.datagen.providers

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import li.kallisto.kgnkallistoadditions.persons.ginger.Trumpet
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.SoundDefinition
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider

class ModSoundDefinitionsProvider(
    packOutput: PackOutput
) : SoundDefinitionsProvider(packOutput, KGNKallistoAdditions.ID) {

    override fun registerSounds() {
        add(
            Trumpet.TRUMPET_SOUND,
            SoundDefinition.definition()
                .with(
                    sound(
                        KGNKallistoAdditions.locate("${Trumpet.ID}_sound"),
                        SoundDefinition.SoundType.SOUND
                    )
                        .attenuationDistance(12)
                        .stream(false)
                        .preload(true),
                )
                .replace(true)
        )

    }
}