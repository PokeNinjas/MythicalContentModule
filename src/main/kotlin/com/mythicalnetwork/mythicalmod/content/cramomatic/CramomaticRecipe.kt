package com.mythicalnetwork.mythicalmod.content.cramomatic

import net.minecraft.world.item.ItemStack

class CramomaticRecipe(private var input: MutableList<ItemStack>, private var output: ItemStack?) {

    fun getInput(): MutableList<ItemStack> {
        return input
    }

    fun setInput(input: MutableList<ItemStack>) {
        this.input = input
    }

    fun getOutput(): ItemStack? {
        return output
    }

    fun setOutput(output: ItemStack?) {
        this.output = output
    }
}