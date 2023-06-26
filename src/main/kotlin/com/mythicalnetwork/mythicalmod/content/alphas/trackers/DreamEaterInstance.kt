package com.mythicalnetwork.mythicalmod.content.alphas.trackers

import net.minecraft.core.BlockPos
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.player.Player
import java.time.Duration
import java.util.UUID

class DreamEaterInstance(val player: Player, var duration: Int, val damage: Float) {
    fun tick() {
        duration--
        if(duration % 10 == 0) {
            player.hurt(DREAM_EATER_SOURCE, damage)
        }
    }

    companion object {
        val DREAM_EATER_SOURCE: DamageSource = DamageSource("dream_eater")
    }
}