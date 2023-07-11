package com.mythicalnetwork.mythicalmod.content.radar

import com.cobblemon.mod.common.pokemon.Pokemon
import com.mythicalnetwork.mythicalmod.content.landmark.AspectData
import dev.onyxstudios.cca.api.v3.component.ComponentV3
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer

interface RadarItemComponent : ComponentV3 {
    fun isActive(): Boolean
    fun setActive(active: Boolean)
    fun isEnabled(): Boolean
    fun setEnabled(enabled: Boolean)
    fun getLastTickedTime(): Long
    fun setLastTickedTime(time: Long)
    fun getSpecies(): String
    fun setSpecies(species: String)
    fun getAspects(): List<AspectData>
    fun setAspects(aspects: List<AspectData>)
    fun getLevelRange(): IntRange
    fun setLevelRange(levelRange: IntRange)
    fun getChainLength(): Int
    fun setChainLength(chainLength: Int)
    fun getMaxLength(): Int
    fun setMaxLength(maxLevel: Int)
    fun getTimerTicks(): Int
    fun setTimerTicks(timerTicks: Int)
    fun tick(player: ServerPlayer)
    fun canSpawn(): Boolean
    fun setCanSpawn(canSpawn: Boolean)
    fun applyChainModifiers(level: ServerLevel, pokemon: Pokemon)
}