package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.api.events.pokemon.PokeballCaptureConditionsEvent
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.minecraft.network.chat.Component

object AlphaHelper {
    fun alphaTest(event: PokeballCaptureConditionsEvent) {
        if(!event.pokemonEntity.pokemon.aspects.contains("alpha_defeated") && event.pokemonEntity.pokemon.aspects.contains("alpha")){
            event.setFailMessage(Component.literal("This Pokémon is too strong to capture right now!").red())
            event.cancel()
        }
    }
}