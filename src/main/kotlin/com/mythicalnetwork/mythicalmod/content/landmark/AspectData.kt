package com.mythicalnetwork.mythicalmod.content.landmark

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

class AspectData(val aspect: String, val chance: Float) {
    companion object {
        val CODEC: Codec<AspectData> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("aspect").forGetter { it.aspect },
                Codec.FLOAT.fieldOf("chance").forGetter { it.chance }
            ).apply(instance, ::AspectData)
        }
    }
}