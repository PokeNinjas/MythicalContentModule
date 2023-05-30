package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.cobblemon.mod.common.util.giveOrDropItemStack
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level

class CramomaticPlayerHandler(private var level: Level) {
    var players: MutableMap<Player, CramomaticInstance> = mutableMapOf()

    fun tick() {
        val playersToTick = (size()/20).coerceAtLeast(1)
        val playersToTickList = mutableListOf<Player>()
        for (i in 0 until playersToTick) {
            if (players.isNotEmpty()) {
                val player = players.keys.first()
                playersToTickList.add(player)
            }
        }
        if (playersToTickList.isEmpty()) {
            for (player in players.keys) {
                players[player]!!.setWasTicked(false)
            }
        } else {
            for (player in playersToTickList) {
                players[player]!!.tick()
            }
        }
    }

    fun getLevel(): Level {
        return level
    }

    fun onComplete(cramomaticInstance: CramomaticInstance){
        val player = players.filterValues { it == cramomaticInstance }.keys.first()
        cramomaticInstance.output?.let {
            for (itemStack in it) {
                player.giveOrDropItemStack(itemStack, true)
            }
        }
        cramomaticInstance.getCurrentItems().let {
            if(it.isNotEmpty()){
                for (itemStack in it) {
                    player.giveOrDropItemStack(itemStack, true)
                }
            }
        }
        cramomaticInstance.clear()
        cramomaticInstance.getBlock()?.update(player, cramomaticInstance)
    }

    fun addPlayer(player: Player, instance: CramomaticInstance): CramomaticInstance {
        players[player] = instance
        return instance
    }

    fun removePlayer(player: Player) {
        players.remove(player)
    }

    fun getPlayer(player: Player): CramomaticInstance? {
        return players[player]
    }

    fun hasPlayer(player: Player): Boolean {
        return players.containsKey(player)
    }

    fun clearPlayers() {
        players.clear()
    }

    fun size(): Int {
        return players.size
    }

    fun isEmpty(): Boolean {
        return players.isEmpty()
    }

    fun containsPlayer(player: Player): Boolean {
        return players.containsKey(player)
    }

    fun containsInstance(instance: CramomaticInstance): Boolean {
        return players.containsValue(instance)
    }
}