package com.mythicalnetwork.mythicalmod.systems.multiblock

import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import java.util.function.Consumer


interface IMultiblockCore {
    fun getComponentPositions(): ArrayList<BlockPos?>

    fun getStructure(): MultiblockStructure

    fun setupMultiblock(pos: BlockPos) {
        getStructure().structurePieces?.forEach { p ->
            val offset: Vec3i = p.offset
            getComponentPositions().add(pos.offset(offset))
        }
    }

    fun destroyMultiblock(player: Player?, level: Level, pos: BlockPos?) {
        getComponentPositions().forEach(Consumer<BlockPos?> { p: BlockPos? ->
            if (level.getBlockEntity(p) is MultiblockComponentEntity) {
                level.setBlock(p, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL)
            }
        })
        val dropBlock = player == null || !player.isCreative()
        if (level.getBlockEntity(pos) is MultiblockCoreEntity) {
            if (dropBlock) {
                level.destroyBlock(pos, dropBlock)
            } else {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL)
            }
        }
    }
}