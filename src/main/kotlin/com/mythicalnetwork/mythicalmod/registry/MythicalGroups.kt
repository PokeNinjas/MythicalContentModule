package com.mythicalnetwork.mythicalmod.registry

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import org.quiltmc.qsl.item.group.api.QuiltItemGroup

object MythicalGroups {
    val MYTHICAL_ITEMS: CreativeModeTab = QuiltItemGroup.builder(ResourceLocation("mythicalmod", "items")).icon{ MythicalItems.PLATINUM_INGOT.defaultInstance }.build()
    val MYTHICAL_BLOCKS: CreativeModeTab = QuiltItemGroup.builder(ResourceLocation("mythicalmod", "blocks")).icon { MythicalItems.BAYSTONE.defaultInstance }.build()
}