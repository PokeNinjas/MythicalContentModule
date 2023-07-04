package com.mythicalnetwork.mythicalmod.util

import com.cobblemon.mod.common.pokemon.Species
import com.mythicalnetwork.mythicalmod.registry.MythicalComponentRegistry.RADAR_ITEM
import com.mythicalnetwork.mythicalmod.registry.MythicalItems
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack


fun Player.getSpeciesRadar(string: Species): ItemStack {
    this.inventory.items.filter { it.item == MythicalItems.RADAR }.forEach {
        val component = RADAR_ITEM.get(it)
        if (component.getSpecies() == string.name) {
            return it
        }
    }
    return ItemStack.EMPTY
}

fun Player.runOnAllRadars(function: (ItemStack) -> Unit) {
    this.inventory.items.filter { it.item == MythicalItems.RADAR }.forEach {
        function(it)
    }
}

fun Player.getAllNonMatchingRadars(species: String): List<ItemStack> {
    val list = mutableListOf<ItemStack>()
    this.inventory.items.filter { it.item == MythicalItems.RADAR }.forEach {
        val component = RADAR_ITEM.get(it)
        if (component.getSpecies() != species) {
            list.add(it)
        }
    }
    return list
}