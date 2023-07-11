package com.mythicalnetwork.mythicalmod.content.misc.tms

import com.mythicalnetwork.mythicalmod.registry.MythicalGroups
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext

class TMItem : Item(Properties().stacksTo(1).tab(MythicalGroups.MYTHICAL_ITEMS).fireResistant()) {
    init {

    }

    override fun getName(stack: ItemStack): Component {
        if(stack.hasTag()) {
            val tmOrHM: String = stack.tag!!.getString("type") ?: "TM"
            val tmNumber: String = stack.tag!!.getString("tmNumber") ?: "00"
            val move: String = stack.tag!!.getString("move") ?: "Move"
            return Component.literal("${tmOrHM.toUpperCase()}-$tmNumber: ${move.capitalize()}")
        }
        return Component.literal("TM-00: Unknown")
    }
}