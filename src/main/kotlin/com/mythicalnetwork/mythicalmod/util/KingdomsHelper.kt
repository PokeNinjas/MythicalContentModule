package com.mythicalnetwork.mythicalmod.util

import com.pokeninjas.kingdoms.fabric.dto.database.impl.User
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player

object KingdomsHelper {

    fun isInKingdom(player: Player): Boolean {
        return User.get((player as ServerPlayer).uuid).kingdomAtLocation != null
    }
}