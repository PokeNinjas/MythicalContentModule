package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.item.ItemStack

class CramomaticRecipe(var input: MutableList<ItemStack>, var output: ItemStack?) {

    companion object {
        val CODEC: Codec<CramomaticRecipe> = RecordCodecBuilder.create { instance ->
            instance.group(
                ItemStack.CODEC.listOf().fieldOf("inputs").forGetter { it.input },
                ItemStack.CODEC.fieldOf("output").forGetter { it.output }
            ).apply(instance) { input, output ->
                CramomaticRecipe(input, output)
            }
        }
    }
}