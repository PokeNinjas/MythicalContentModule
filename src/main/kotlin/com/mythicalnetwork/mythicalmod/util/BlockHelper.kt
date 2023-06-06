package com.mythicalnetwork.mythicalmod.util

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3


object BlockHelper {
    fun fromBlockPos(pos: BlockPos): Vec3 {
        return Vec3(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
    }

    fun saveBlockPos(compoundNBT: CompoundTag, pos: BlockPos) {
        compoundNBT.putInt("X", pos.x)
        compoundNBT.putInt("Y", pos.y)
        compoundNBT.putInt("Z", pos.z)
    }

    /**
     * Saves a block position to nbt with extra text to differentiate it.
     */
    fun saveBlockPos(compoundNBT: CompoundTag, pos: BlockPos, extra: String) {
        compoundNBT.putInt(extra + "_X", pos.x)
        compoundNBT.putInt(extra + "_Y", pos.y)
        compoundNBT.putInt(extra + "_Z", pos.z)
    }

    /**
     * Loads a block position from nbt.
     */
    fun loadBlockPos(tag: CompoundTag): BlockPos? {
        return if (tag.contains("X")) BlockPos(tag.getInt("X"), tag.getInt("Y"), tag.getInt("Z")) else null
    }

    /**
     * Loads a block position from nbt with extra text as input.
     */
    fun loadBlockPos(tag: CompoundTag, extra: String): BlockPos? {
        return if (tag.contains(extra + "_X")) BlockPos(
            tag.getInt(extra + "_X"),
            tag.getInt(extra + "_Y"),
            tag.getInt(extra + "_Z")
        ) else null
    }

    fun updateAndNotifyState(level: Level, pos: BlockPos) {
        updateAndNotifyState(level.getBlockState(pos), level, pos)
    }

    fun updateAndNotifyState(state: BlockState, level: Level, pos: BlockPos) {
        updateState(state, level, pos)
        state.updateNeighbourShapes(level, pos, 2)
        level.updateNeighbourForOutputSignal(pos, state.block)
    }

    fun updateState(state: BlockState, level: Level, pos: BlockPos) {
        level.sendBlockUpdated(pos, state, state, 2)
        level.blockEntityChanged(pos)
    }
}