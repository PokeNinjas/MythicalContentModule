package com.mythicalnetwork.mythicalmod.content.landmark

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag

class AspectData(val aspect: String, val chance: Float) {
    fun toNbt(): CompoundTag {
        val nbt = CompoundTag()
        nbt.putString("aspect", aspect)
        nbt.putFloat("chance", chance)
        return nbt
    }

    companion object {
        val CODEC: Codec<AspectData> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("aspect").forGetter { it.aspect },
                Codec.FLOAT.fieldOf("chance").forGetter { it.chance }
            ).apply(instance, ::AspectData)
        }

        fun fromNbt(nbt: CompoundTag): AspectData {
            return AspectData(nbt.getString("aspect"), nbt.getFloat("chance"))
        }
    }
}