package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.cobblemon.mod.common.util.giveOrDropItemStack
import com.mythicalnetwork.mythicalmod.MythicalContent
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import java.util.*

class CramomaticPlayerHandler(private var level: Level) {
    var players: MutableMap<UUID, CramomaticInstance> = mutableMapOf()

    companion object {
        var toLoad: MutableMap<UUID, CramomaticInstance> = mutableMapOf()
        fun load(tag: CompoundTag): CramomaticPlayerHandler? {
            toLoad.clear()
            val handler = MythicalContent.SERVER?.overworld()?.let { CramomaticPlayerHandler(it) } ?: return null
            for (player in tag.allKeys) {
                val instance = CramomaticInstance.load(tag.getCompound(player))
                toLoad[UUID.fromString(player)] = instance
                println("Loading CramomaticInstance for $player with data $instance")
            }
            return handler
        }
    }

    fun tick() {
        val playersToTick = (size()/20).coerceAtLeast(1)
        val playersToTickList = mutableListOf<UUID>()
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

    fun pausePlayer(player: UUID){
        players[player]?.pause()
    }

    fun resumePlayer(player: UUID){
        players[player]?.resume()
        players[player]?.getBlock()?.update(player, players[player]!!)
    }

    fun onComplete(cramomaticInstance: CramomaticInstance){
        val playerUUID = players.filterValues { it == cramomaticInstance }.keys.first()
        val player = level.getPlayerByUUID(playerUUID) ?: return
        cramomaticInstance.output?.let { instance ->
            CramomaticRewardPoolEntry.getRandomWithWeight(instance).let {
                player.giveOrDropItemStack(it, true)
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
        cramomaticInstance.getBlock()?.update(player.uuid, cramomaticInstance)
    }

    fun addPlayer(player: UUID, instance: CramomaticInstance): CramomaticInstance {
        players[player] = instance
        return instance
    }

    fun removePlayer(player: UUID) {
        players.remove(player)
    }

    fun getPlayer(player: UUID): CramomaticInstance? {
        return players[player]
    }

    fun hasPlayer(player: UUID): Boolean {
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

    fun containsPlayer(player: UUID): Boolean {
        return players.containsKey(player)
    }

    fun containsInstance(instance: CramomaticInstance): Boolean {
        return players.containsValue(instance)
    }

    fun save(): CompoundTag {
        val tag = CompoundTag()
        for (player in players.keys) {
            tag.put(player.toString(), players[player]!!.save())
        }
        return tag
    }
}