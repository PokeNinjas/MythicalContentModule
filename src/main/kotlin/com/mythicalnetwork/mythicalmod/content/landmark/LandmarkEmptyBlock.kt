package com.mythicalnetwork.mythicalmod.content.landmark

import com.mythicalnetwork.mythicalmod.content.base.MythicalEntityBlock
import com.mythicalnetwork.mythicalmod.systems.multiblock.IMultiblockComponent
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockComponentBlock
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.state.BlockState

class LandmarkEmptyBlock(properties: Properties) : MythicalEntityBlock<LandmarkEmptyBlockEntity>(properties), IMultiblockComponent {
    override fun getRenderShape(state: BlockState): RenderShape {
        return RenderShape.INVISIBLE
    }
}