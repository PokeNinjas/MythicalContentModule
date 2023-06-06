package com.mythicalnetwork.mythicalmod.content.base

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.phys.BlockHitResult
import java.util.function.Supplier

open class MythicalHFacingEntityBlock<T : MythicalBlockEntity?>(properties: Properties) : HorizontalDirectionalBlock(properties), EntityBlock {
    var blockEntityType: Supplier<BlockEntityType<T>>? = null
    var blockEntityTicker: BlockEntityTicker<T>? = null
    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return hasTileEntity(state).let { if (it) blockEntityType!!.get().create(pos, state) else null }
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun getStateForPlacement(ctx: BlockPlaceContext): BlockState? {
        return this.defaultBlockState().setValue(FACING, ctx.horizontalDirection.clockWise.clockWise)
    }

    fun hasTileEntity(state: BlockState): Boolean {
        return blockEntityType != null
    }

    override fun <T : BlockEntity?> getTicker(
        world: Level,
        state: BlockState,
        type: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return blockEntityTicker as BlockEntityTicker<T>?
    }

    fun setBlockEntity(type: Supplier<BlockEntityType<T>>): MythicalHFacingEntityBlock<T> {
        this.blockEntityType = type
        this.blockEntityTicker = BlockEntityTicker { _, _, _, entity ->
            entity!!.tick()
        }
        return this
    }

    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult
    ): InteractionResult {
        if(hasTileEntity(state)) {
            if(level.getBlockEntity(pos) is MythicalBlockEntity) {
                return (level.getBlockEntity(pos) as MythicalBlockEntity).onUse(player, hand)
            }
        }
        return super.use(state, level, pos, player, hand, hit)
    }
}