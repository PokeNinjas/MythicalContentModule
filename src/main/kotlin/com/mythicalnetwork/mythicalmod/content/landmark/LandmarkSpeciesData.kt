package com.mythicalnetwork.mythicalmod.content.landmark

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import java.util.Optional

class LandmarkSpeciesData(
    var species: String,
    var aspects: List<AspectData>,
    var levelRange: Optional<IntRange> = Optional.empty(),
    var weight: Int,
    var shinyChance: Optional<Float>,
    var canSwim: Optional<Boolean> = Optional.empty(),
    var canFly: Optional<Boolean> = Optional.empty(),
    var canWalk: Optional<Boolean> = Optional.empty()
) {
    fun copy(): LandmarkSpeciesData {
        return LandmarkSpeciesData(species, aspects, levelRange, weight, shinyChance)
    }

    companion object {
        val CODEC: Codec<LandmarkSpeciesData> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.STRING.fieldOf("species").forGetter { it.species },
                AspectData.CODEC.listOf().fieldOf("aspects").forGetter { it.aspects },
                Codec.STRING.optionalFieldOf("level_range").xmap(
                    { str -> str.get().split("-").let { IntRange(it[0].toInt(), it[1].toInt()) } },
                    { Optional.of("${it.first}-${it.last}") }
                ).forGetter { it.levelRange.get() },
                Codec.INT.fieldOf("weight").forGetter { it.weight },
                Codec.FLOAT.optionalFieldOf("shiny_chance").forGetter { it.shinyChance },
                Codec.BOOL.optionalFieldOf("can_swim").forGetter { it.canSwim },
                Codec.BOOL.optionalFieldOf("can_fly").forGetter { it.canFly },
                Codec.BOOL.optionalFieldOf("can_walk").forGetter { it.canWalk }
            ).apply(instance){ species, aspects, levelRange, weight, shinyChance, canSwim, canFly, canWalk ->
                LandmarkSpeciesData(species, aspects, Optional.of(levelRange), weight, shinyChance, canSwim, canFly, canWalk)}
        }
    }

    override fun toString(): String {
        return "LandmarkSpeciesData(species=$species, aspects=$aspects, levelRange=$levelRange, weight=$weight, shinyChance=$shinyChance)"
    }
}