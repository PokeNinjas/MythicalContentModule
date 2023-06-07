package com.mythicalnetwork.mythicalmod.content.landmark

import com.mythicalnetwork.mythicalmod.content.base.MythicalEntityBlock
import com.mythicalnetwork.mythicalmod.systems.multiblock.IMultiblockComponent
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockComponentBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.stream.Stream

class LandmarkEmptyBlock(properties: Properties) : MythicalEntityBlock<LandmarkEmptyBlockEntity>(properties), IMultiblockComponent {
    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.INVISIBLE
    }

    override fun propagatesSkylightDown(state: BlockState, world: BlockGetter, pos: BlockPos): Boolean {
        return true
    }

    @Deprecated("Deprecated in Java", ReplaceWith("1.0f"))
    override fun getShadeBrightness(state: BlockState, world: BlockGetter, pos: BlockPos): Float {
        return 1.0f
    }
}