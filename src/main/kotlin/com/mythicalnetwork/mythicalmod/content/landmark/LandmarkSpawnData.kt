package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.mythicalnetwork.mythicalmod.MythicalContent
import net.minecraft.world.level.Level

class LandmarkSpawnData(
    var species: List<LandmarkSpeciesData>,
    var maxDelay: Int,
    var minDelay: Int,
    var maxNearbyEntities: Int,
    var requiredPlayerRange: Int,
    var cooldown: Int,
    var duration: Int
) {
    companion object {
        val SPAWN_DATA: MutableMap<ElementalType, LandmarkSpawnData> = mutableMapOf()
        val CODEC: Codec<LandmarkSpawnData> = RecordCodecBuilder.create { instance ->
            instance.group(
                LandmarkSpeciesData.CODEC.listOf().fieldOf("species").forGetter { it.species },
                Codec.INT.fieldOf("max_delay").forGetter { it.maxDelay },
                Codec.INT.fieldOf("min_delay").forGetter { it.minDelay },
                Codec.INT.fieldOf("max_nearby_entities").forGetter { it.maxNearbyEntities },
                Codec.INT.fieldOf("player_range").forGetter { it.requiredPlayerRange },
                Codec.INT.fieldOf("cooldown").forGetter { it.cooldown },
                Codec.INT.fieldOf("duration").forGetter { it.duration }
            ).apply(instance, ::LandmarkSpawnData
            )
        }
        fun clearSpawnData() {
            SPAWN_DATA.clear()
        }

        fun addSpawnData(type: ElementalType, data: LandmarkSpawnData) {
            SPAWN_DATA[type] = data
        }
        fun getForType(type: ElementalType): LandmarkSpawnData? = SPAWN_DATA[type]

    }
    fun getRandomWithWeight(level: Level): LandmarkSpeciesData {
        MythicalContent.LOGGER.info("Getting weighted random species. Species: $species")
        val totalWeight = species.sumOf { it.weight}
        MythicalContent.LOGGER.info("Total weight: $totalWeight")
        var random = level.random.nextInt(totalWeight)
        MythicalContent.LOGGER.info("Random: $random")
        for (species in species) {
            random -= species.weight
            MythicalContent.LOGGER.info("Species: ${species.weight}")
            MythicalContent.LOGGER.info("Random: $random")
            if (random <= 0) {
                MythicalContent.LOGGER.info("Returning species: $species")
                return species
            }
        }
        MythicalContent.LOGGER.info("Returning species: ${species[0]}")
        return species[0]
    }
    override fun toString(): String {
        return "LandmarkSpawnData(species=$species, maxDelay=$maxDelay, minDelay=$minDelay, maxNearbyEntities=$maxNearbyEntities, requiredPlayerRange=$requiredPlayerRange)"
    }
}
