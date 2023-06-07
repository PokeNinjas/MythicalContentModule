package com.mythicalnetwork.mythicalmod.content.landmark

import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockComponentEntity
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockCoreEntity
import foundry.veil.color.Color
import foundry.veil.color.ColorTheme
import foundry.veil.ui.Tooltippable
import foundry.veil.ui.VeilUIItemTooltipDataHolder
import foundry.veil.ui.anim.TooltipKeyframe
import foundry.veil.ui.anim.TooltipTimeline
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.state.BlockState

class LandmarkEmptyBlockEntity(pos: BlockPos, state: BlockState) : MultiblockComponentEntity(pos, state), Tooltippable {
    override fun getTooltip(): MutableList<Component> {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            return core.tooltip
        } else {
            return mutableListOf()
        }
    }

    override fun isTooltipEnabled(): Boolean {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            return core.isTooltipEnabled
        } else {
            return false
        }
    }

    override fun saveTooltipData(): CompoundTag {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            return core.saveTooltipData()
        } else {
            return CompoundTag()
        }
    }

    override fun loadTooltipData(p0: CompoundTag?) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            core.loadTooltipData(p0)
        }
    }

    override fun setTooltip(p0: MutableList<Component>?) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            core.tooltip = p0!!
        }
    }

    override fun addTooltip(p0: Component?) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            core.tooltip.add(p0!!)
        }
    }

    override fun addTooltip(p0: MutableList<Component>?) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            core.tooltip.addAll(p0!!)
        }
    }

    override fun addTooltip(p0: String?) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            core.tooltip.add(Component.nullToEmpty(p0))
        }
    }

    override fun getTheme(): ColorTheme {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            return core.theme
        } else {
            return ColorTheme()
        }
    }

    override fun setTheme(p0: ColorTheme?) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            core.theme = p0!!
        }
    }

    override fun setBackgroundColor(p0: Int) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            theme.colors.add(0, Color.of(p0))
        }
    }

    override fun setTopBorderColor(p0: Int) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            theme.colors.add(1, Color.of(p0))
        }
    }

    override fun setBottomBorderColor(p0: Int) {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            val core = level!!.getBlockEntity(corePos) as LandmarkBlockEntity
            theme.colors.add(2, Color.of(p0))
        }
    }

    override fun getWorldspace(): Boolean {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).worldspace
        } else {
            return false
        }
    }

    override fun getTimeline(): TooltipTimeline {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).timeline
        } else {
            return TooltipTimeline(arrayOf<TooltipKeyframe>(), 1.0f)
        }
    }

    override fun getStack(): ItemStack {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).stack
        } else {
            return ItemStack.EMPTY
        }
    }

    override fun getTooltipWidth(): Int {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).tooltipWidth
        } else {
            return 0
        }
    }

    override fun getTooltipHeight(): Int {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).tooltipHeight
        } else {
            return 0
        }
    }

    override fun getTooltipXOffset(): Int {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).tooltipXOffset
        } else {
            return 0
        }
    }

    override fun getTooltipYOffset(): Int {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).tooltipYOffset
        } else {
            return 0
        }
    }

    override fun getItems(): MutableList<VeilUIItemTooltipDataHolder> {
        if (corePos != null && level!!.getBlockEntity(corePos) is LandmarkBlockEntity) {
            return (level!!.getBlockEntity(corePos) as LandmarkBlockEntity).items
        } else {
            return mutableListOf()
        }
    }
}