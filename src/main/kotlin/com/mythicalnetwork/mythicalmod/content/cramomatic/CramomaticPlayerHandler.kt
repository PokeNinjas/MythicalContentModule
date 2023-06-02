package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.cobblemon.mod.common.util.giveOrDropItemStack
import com.mythicalnetwork.mythicalmod.MythicalContent
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
                if(!players[player]!!.isPaused()){
                    players[player]!!.tick()
                    if(players[player]!!.isComplete && players[player]!!.getTime()%5==0){
                        onComplete(players[player]!!)
                        removePlayer(player)
                    }
                }
            }
        }
    }

    fun getLevel(): Level {
        return level
    }

    fun pausePlayer(player: Player){
        players[player]?.pause()
    }

    fun resumePlayer(player: Player){
        players[player]?.resume()
    }

    fun onComplete(cramomaticInstance: CramomaticInstance){
        MythicalContent.LOGGER.info("Cramomatic instance completed")
        val player = players.filterValues { it == cramomaticInstance }.keys.first()
        cramomaticInstance.output?.let { instance ->
            CramomaticRewardPoolEntry.getRandomWithWeight(instance).let {
                MythicalContent.LOGGER.info("Giving player ${player.displayName} ${it.displayName.string}")
                player.giveOrDropItemStack(it, true)
            }
        }
        cramomaticInstance.getCurrentItems().let {
            if(it.isNotEmpty()){
                for (itemStack in it) {
                    MythicalContent.LOGGER.info("Giving player ${player.displayName} ${itemStack.displayName.string} back")
                    player.giveOrDropItemStack(itemStack, true)
                }
            }
        }
        cramomaticInstance.clear()
        cramomaticInstance.getBlock()?.update(player, cramomaticInstance)
    }

    fun addPlayer(player: Player, instance: CramomaticInstance): CramomaticInstance {
        MythicalContent.LOGGER.info("Adding player ${player.displayName} to handler")
        players[player] = instance
        return instance
    }

    fun removePlayer(player: Player) {
        MythicalContent.LOGGER.info("Removing player ${player.displayName} from handler")
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