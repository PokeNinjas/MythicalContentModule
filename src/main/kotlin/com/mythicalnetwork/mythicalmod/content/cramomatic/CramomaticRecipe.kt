package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack

class CramomaticRecipe(var input: MutableList<ItemStack>, var output: MutableList<CramomaticRewardPoolEntry>?) {

    companion object {
        val CODEC: Codec<CramomaticRecipe> = RecordCodecBuilder.create { instance ->
            instance.group(
                ItemStack.CODEC.listOf().fieldOf("inputs").forGetter { it.input },
                CramomaticRewardPoolEntry.CODEC.listOf().fieldOf("output").forGetter { it.output }
            ).apply(instance) { input, output ->
                CramomaticRecipe(input, output)
            }
        }

        fun load(tag: CompoundTag): CramomaticRecipe {
            val inputTag = tag.getCompound("input")
            val input = mutableListOf<ItemStack>()
            for (i in 0 until inputTag.size()) {
                input.add(ItemStack.of(inputTag.getCompound("input$i")))
            }
            val outputTag = tag.getCompound("output")
            val output = mutableListOf<CramomaticRewardPoolEntry>()
            for (i in 0 until outputTag.size()) {
                output.add(CramomaticRewardPoolEntry.load(outputTag.getCompound("output$i")))
            }
            return CramomaticRecipe(input, output)
        }
    }

    fun save(): CompoundTag {
        val tag = CompoundTag()
        val inputTag = CompoundTag()
        for (i in input.indices) {
            inputTag.put("input$i", input[i].save(CompoundTag()))
        }
        tag.put("input", inputTag)
        val outputTag = CompoundTag()
        for (i in output!!.indices) {
            outputTag.put("output$i", output!![i].save())
        }
        tag.put("output", outputTag)
        return tag
    }
    fun getOutputCountFromInput(input: MutableList<ItemStack>): Int {
        // check how many result items from this recipe the input list can make.
        // this is used to check if the input list can make the recipe, and to check if the input list can make the recipe multiple times
        var outputCount = 0
        for (itemStack in input) {
            for (recipeItemStack in this.input) {
                if (itemStack.item == recipeItemStack.item) {
                    if (itemStack.count >= recipeItemStack.count) {
                        outputCount++
                    }
                }
            }
        }
        return outputCount
    }

    fun simulateCraft(input: MutableList<ItemStack>) {
        // simulate crafting the recipe, reducing the input list by the recipe's input list, and removing empty stacks
        for (recipeItemStack in this.input) {
            for (itemStack in input) {
                if (itemStack.item == recipeItemStack.item) {
                    if (itemStack.count >= recipeItemStack.count) {
                        itemStack.shrink(recipeItemStack.count)
                        if (itemStack.count <= 0) {
                            input.remove(itemStack)
                        }
                        break
                    }
                }
            }
        }
    }
}