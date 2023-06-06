package com.mythicalnetwork.mythicalmod.content.landmark

import com.mythicalnetwork.mythicalmod.content.base.MythicalHFacingEntityBlock
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.systems.multiblock.IMultiblockComponent
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class LandmarkBlock(properties: Properties) : MythicalHFacingEntityBlock<LandmarkBlockEntity>(properties), IMultiblockComponent {

    override fun <T : BlockEntity?> getTicker(
        world: Level,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T> {
        return BlockEntityTicker { world, pos, state, entity ->
            if(entity is LandmarkBlockEntity) {
                entity.tick(world, pos, state, entity)
            }
        }
    }
}