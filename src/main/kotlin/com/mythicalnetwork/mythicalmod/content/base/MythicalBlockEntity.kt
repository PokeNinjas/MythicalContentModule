package com.mythicalnetwork.mythicalmod.content.base

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

open class MythicalBlockEntity(type: BlockEntityType<*>, pos: BlockPos, state: BlockState) : BlockEntity(type, pos, state) {
    var needsSync = false
    open fun onBreak(player: Player?){

    }

    fun onPlace(player: LivingEntity, stack: ItemStack){

    }

    fun onNeighbourUpdate(state: BlockState, pos: BlockPos, block: BlockState){

    }

    open fun onUse(player: Player, hand: InteractionHand): InteractionResult{
        return InteractionResult.PASS
    }

    fun onUse(player: Player, hand: InteractionHand, ray: BlockHitResult): InteractionResult{
        return InteractionResult.PASS
    }

    override fun getUpdateTag(): CompoundTag {
        return this.saveWithoutMetadata()
    }

    override fun load(nbt: CompoundTag) {
        needsSync = true
        super.load(nbt)
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener>? {
        return ClientboundBlockEntityDataPacket.create(this)
    }

     fun tick() {
        if(needsSync) {
            init()
            needsSync = false
        }
    }

    fun init() {

    }

}