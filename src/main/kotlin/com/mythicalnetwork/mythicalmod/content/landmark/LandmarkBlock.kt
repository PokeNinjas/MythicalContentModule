package com.mythicalnetwork.mythicalmod.content.landmark

import com.mythicalnetwork.mythicalmod.content.base.MythicalHFacingEntityBlock
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.systems.multiblock.IMultiblockComponent
import com.mythicalnetwork.mythicalmod.util.BlockHelper
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
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

    override fun setPlacedBy(
        world: Level,
        pos: BlockPos,
        state: BlockState,
        placer: LivingEntity?,
        itemStack: ItemStack
    ) {
        super.setPlacedBy(world, pos, state, placer, itemStack)
        if(world.isClientSide) return
        val blockEntity = world.getBlockEntity(pos)
        if(blockEntity is LandmarkBlockEntity) {
            if(placer is Player){
                blockEntity.setOwner(placer.uuid)
                BlockHelper.updateAndNotifyState(world, pos)
            }
        }
    }
}