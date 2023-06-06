package com.mythicalnetwork.mythicalmod.systems.multiblock

import com.mythicalnetwork.mythicalmod.content.base.MythicalBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState


open class MultiblockCoreEntity(type: BlockEntityType<*>, pos: BlockPos, state: BlockState, private val structure: MultiblockStructure) : MythicalBlockEntity(type, pos, state), IMultiblockCore {
    private var componentPositions = ArrayList<BlockPos?>()

    init {
        setupMultiblock(pos)
    }

    override fun getStructure(): MultiblockStructure {
        return structure
    }

    override fun getComponentPositions(): ArrayList<BlockPos?> {
        return componentPositions
    }

    override fun onBreak(player: Player?) {
        destroyMultiblock(player, level!!, worldPosition)
    }
}