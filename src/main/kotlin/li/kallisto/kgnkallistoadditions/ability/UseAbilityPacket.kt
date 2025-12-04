package li.kallisto.kgnkallistoadditions.ability

import li.kallisto.kgnkallistoadditions.KGNKallistoAdditions
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.neoforge.network.handling.IPayloadContext

class UseAbilityPacket() : CustomPacketPayload {
    override fun type() = TYPE

    companion object {
        val ID = KGNKallistoAdditions.locate("use_ability")
        val TYPE = CustomPacketPayload.Type<UseAbilityPacket>(ID)

        val STREAM_CODEC: StreamCodec<FriendlyByteBuf, UseAbilityPacket> =
            StreamCodec.of(
                { buf, msg -> },
                { buf -> UseAbilityPacket() }
            )

        fun handle(message: UseAbilityPacket, context: IPayloadContext) {
            li.kallisto.kgnkallistoadditions.persons.cosimo.AbilityPacketHandler.handle(message, context)
            li.kallisto.kgnkallistoadditions.persons.luca.AbilityPacketHandler.handle(message, context)
            li.kallisto.kgnkallistoadditions.persons.joel.AbilityPacketHandler.handle(message, context)
        }
    }
}