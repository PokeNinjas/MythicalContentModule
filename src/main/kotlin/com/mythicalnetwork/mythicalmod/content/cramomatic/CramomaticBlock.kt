package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mythicalnetwork.mythicalmod.content.base.MythicalEntityBlock
import com.mythicalnetwork.mythicalmod.content.base.MythicalHFacingEntityBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState

class CramomaticBlock(pProperties: Properties) : MythicalHFacingEntityBlock<CramomaticBlockEntity>(pProperties) {

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return CramomaticBlockEntity(pos, state)
    }

    override fun <T : BlockEntity?> getTicker(
        world: Level,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return BlockEntityTicker { world, pos, state, entity ->
            if(entity is CramomaticBlockEntity) {
                entity.tick(world, pos, state, entity)
            }
        }
    }

    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.ENTITYBLOCK_ANIMATED
    }
}