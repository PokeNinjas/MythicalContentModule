package com.mythicalnetwork.mythicalmod.content.landmark

import com.mythicalnetwork.mythicalmod.MythicalContent
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent
import net.minecraft.nbt.CompoundTag

class LandmarkCooldownComponent : LandmarkCooldown, ServerTickingComponent, PlayerComponent<LandmarkCooldownComponent> {
    private var cooldown = 0
    private var activeCount = 0
    override fun getCooldown(): Int {
        return cooldown
    }

    override fun setCooldown(cooldown: Int) {
        this.cooldown = cooldown
    }

    override fun getActiveCount(): Int {
        return activeCount
    }

    override fun setActiveCount(countdown: Int) {
        activeCount = countdown
    }

    override fun addActiveCount(countdown: Int) {
        if(activeCount < MythicalContent.CONFIG.maxPlayerLandmarkCount()) {
            activeCount += countdown
        }
    }

    override fun readFromNbt(tag: CompoundTag) {
        cooldown = tag.getInt("cooldown")
        activeCount = tag.getInt("activeCount")
    }

    override fun writeToNbt(tag: CompoundTag) {
        tag.putInt("cooldown", cooldown)
        tag.putInt("activeCount", activeCount)
    }

    override fun serverTick() {
        if (cooldown > 0) {
            cooldown--
        }
        if (cooldown == 0 && activeCount > 0) {
            activeCount--
        }
    }
}