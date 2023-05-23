package com.mythicalnetwork.mythicalmod.registry

import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticBlockEntity
import com.mythicalnetwork.mythicalmod.registry.MythicalBlocks.CRAMOMATIC
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder

object MythicalBlockEntities {
    var CRAMOMATIC_BLOCK_ENTITY: BlockEntityType<CramomaticBlockEntity>? = null
    fun init() {
        CRAMOMATIC_BLOCK_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "cramomatic_block_entity"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> CramomaticBlockEntity(pos!!, state!!) }, CRAMOMATIC
            ).build(null)
        )
    }
}
