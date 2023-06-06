package com.mythicalnetwork.mythicalmod.systems.multiblock

import com.mythicalnetwork.mythicalmod.content.base.MythicalBlockEntity
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.util.BlockHelper
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState


open class MultiblockComponentEntity(pos: BlockPos, state: BlockState) : MythicalBlockEntity(MythicalBlockEntities.MULTIBLOCK_COMPONENT!!, pos, state) {
    var corePos: BlockPos? = null

    override fun saveAdditional(tag: CompoundTag) {
        if (corePos != null) {
            BlockHelper.saveBlockPos(tag!!, corePos!!, "core_position_")
        }
        super.saveAdditional(tag)
    }

    override fun load(tag: CompoundTag) {
        corePos = BlockHelper.loadBlockPos(tag, "core_position_")
        super.load(tag)
    }

    override fun onUse(player: Player, hand: InteractionHand): InteractionResult {
        return if (corePos != null && level!!.getBlockEntity(corePos) is MultiblockCoreEntity) {
            val core = level!!.getBlockEntity(corePos) as MultiblockCoreEntity
            core.onUse(player!!, hand!!)
        } else super.onUse(player!!, hand!!)
    }

    override fun onBreak(player: Player?) {
        if (corePos != null && level!!.getBlockEntity(corePos) is MultiblockCoreEntity) {
            val core = level!!.getBlockEntity(corePos) as MultiblockCoreEntity
            core.onBreak(player)
        }
        super.onBreak(player)
    }
}