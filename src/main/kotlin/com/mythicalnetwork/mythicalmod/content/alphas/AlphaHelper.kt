package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.api.events.pokemon.PokeballCaptureConditionsEvent
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.chat.Component
import net.minecraft.world.level.block.Blocks

object AlphaHelper {
    fun alphaTest(event: PokeballCaptureConditionsEvent) {
        if(!event.pokemonEntity.pokemon.aspects.contains("alpha_defeated") && event.pokemonEntity.pokemon.aspects.contains("alpha")){
            event.setFailMessage(Component.literal("This Pokémon is too strong to capture right now!").red())
            event.cancel()
        }
    }

    val TYPE_PARTICLES: Map<ElementalType, ParticleOptions> = mapOf(
        ElementalTypes.FIRE to ParticleTypes.FLAME,
        ElementalTypes.WATER to ParticleTypes.DRIPPING_WATER,
        ElementalTypes.GRASS to ParticleTypes.SPORE_BLOSSOM_AIR,
        ElementalTypes.ELECTRIC to ParticleTypes.ELECTRIC_SPARK,
        ElementalTypes.ICE to BlockParticleOption(ParticleTypes.BLOCK, Blocks.ICE.defaultBlockState()),
        ElementalTypes.FIGHTING to ParticleTypes.CRIT,
        ElementalTypes.POISON to ParticleTypes.DRIPPING_OBSIDIAN_TEAR,
        ElementalTypes.GROUND to BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()),
        ElementalTypes.FLYING to ParticleTypes.CLOUD,
        ElementalTypes.PSYCHIC to ParticleTypes.PORTAL,
        ElementalTypes.BUG to ParticleTypes.ITEM_SLIME,
        ElementalTypes.ROCK to BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()),
        ElementalTypes.GHOST to ParticleTypes.WARPED_SPORE,
        ElementalTypes.DRAGON to ParticleTypes.DRAGON_BREATH,
        ElementalTypes.DARK to ParticleTypes.MYCELIUM,
        ElementalTypes.STEEL to BlockParticleOption(ParticleTypes.BLOCK, Blocks.IRON_BLOCK.defaultBlockState()),
        ElementalTypes.FAIRY to ParticleTypes.CRIMSON_SPORE,
        ElementalTypes.NORMAL to ParticleTypes.ASH,
    )
}