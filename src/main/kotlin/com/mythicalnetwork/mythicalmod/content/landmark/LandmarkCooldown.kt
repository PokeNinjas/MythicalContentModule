package com.mythicalnetwork.mythicalmod.content.landmark

import dev.onyxstudios.cca.api.v3.component.ComponentV3

interface LandmarkCooldown : ComponentV3 {
    fun getCooldown(): Int
    fun setCooldown(cooldown: Int)
    fun getActiveCount(): Int
    fun setActiveCount(countdown: Int)
    fun addActiveCount(countdown: Int)
}