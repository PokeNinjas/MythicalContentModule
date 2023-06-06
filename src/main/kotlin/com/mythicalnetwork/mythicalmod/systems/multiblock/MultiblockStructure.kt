package com.mythicalnetwork.mythicalmod.systems.multiblock

import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import java.util.function.Consumer


class MultiblockStructure(var structurePieces: List<StructurePiece>?) {

    fun canPlace(context: BlockPlaceContext): Boolean {
        return structurePieces!!.stream().allMatch { p: StructurePiece ->
            p.canPlace(
                context
            )
        }
    }

    fun place(context: BlockPlaceContext) {
        structurePieces!!.forEach(Consumer { s: StructurePiece ->
            s.place(
                context.clickedPos,
                context.level
            )
        })
    }

    companion object{
        fun of(vararg pieces: StructurePiece?): MultiblockStructure {
            return MultiblockStructure(pieces.map { p -> p!! }.toList())
        }
    }


    class StructurePiece(xOffset: Int, yOffset: Int, zOffset: Int, state: BlockState) {
        val offset: Vec3i
        val state: BlockState

        init {
            offset = Vec3i(xOffset, yOffset, zOffset)
            this.state = state
        }

        fun canPlace(context: BlockPlaceContext): Boolean {
            val level: Level = context.level
            val player: Player? = context.player
            val pos = context.clickedPos.offset(offset)
            val existingState = context.level.getBlockState(pos)
            val collisioncontext = if (player == null) CollisionContext.empty() else CollisionContext.of(player)
            return existingState.material.isReplaceable && level.isUnobstructed(state, pos, collisioncontext)
        }

        fun place(core: BlockPos, level: Level) {
            place(core, level, state)
        }

        fun place(core: BlockPos, level: Level, state: BlockState?) {
            val pos = core.offset(offset)
            level.setBlock(pos, state, 3)
            if (level.getBlockEntity(pos) is MultiblockComponentEntity) {
                val component = level.getBlockEntity(pos) as MultiblockComponentEntity
                component.corePos = core
            }
        }
    }

}