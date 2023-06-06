package com.mythicalnetwork.mythicalmod.systems.multiblock

import com.mythicalnetwork.mythicalmod.content.base.MythicalEntityBlock
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import java.util.function.Supplier

open class MultiblockComponentBlock(properties: Properties) : MythicalEntityBlock<MultiblockComponentEntity>(properties), IMultiblockComponent {
    init {
        setBlockEntity { MythicalBlockEntities.MULTIBLOCK_COMPONENT!! }
    }
}