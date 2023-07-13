package com.mythicalnetwork.mythicalmod.util

import com.mythicalnetwork.mythicalmod.MythicalContent
import com.pokeninjas.kingdoms.fabric.annotations.FabricEventHandler
import com.pokeninjas.kingdoms.fabric.events.impl.player.PlayerJoinEvent

object KingdomRegistry {

    fun init() {

    }

    @FabricEventHandler(priority = 100)
    fun onPlayerJoin(event: PlayerJoinEvent) {
        MythicalContent.onPlayerJoin(event.player, event.sender)
    }
}