package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.cobblemon.mod.common.util.giveOrDropItemStack
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level

class CramomaticPlayerHandler(private var level: Level) {
    private var players: MutableMap<Player, CramomaticInstance> = mutableMapOf()

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
                if (players[player]!!.isComplete) {
                    players[player]!!.onComplete {
                        onComplete(it)
                        removePlayer(player)
                    }
                }
            }
        }
    }

    fun getLevel(): Level {
        return level
    }

    fun onComplete(cramomaticInstance: CramomaticInstance){
        val player = players.filterValues { it == cramomaticInstance }.keys.first()
        cramomaticInstance.output?.let {
            player.giveOrDropItemStack(it, true)
        }
        cramomaticInstance.getCurrentItems().let {
            if(it.isNotEmpty()){
                for (itemStack in it) {
                    player.giveOrDropItemStack(itemStack, true)
                }
            }
        }
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

    fun getPlayers(): MutableMap<Player, CramomaticInstance> {
        return players
    }

    fun setPlayers(players: MutableMap<Player, CramomaticInstance>) {
        this.players = players
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