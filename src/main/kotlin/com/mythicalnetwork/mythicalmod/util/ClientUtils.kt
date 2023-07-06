package com.mythicalnetwork.mythicalmod.util

import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer

object ClientUtils {
    fun getPlayer(): LocalPlayer {
        return Minecraft.getInstance().player!!
    }

    fun isShiftDown(): Boolean {
        // check if the shift key is down in a tooltip
        return getPlayer().isShiftKeyDown
    }
}