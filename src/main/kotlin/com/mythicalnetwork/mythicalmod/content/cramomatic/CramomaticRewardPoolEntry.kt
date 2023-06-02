package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack

class CramomaticRewardPoolEntry(private val itemStack: ItemStack, private val weight: Int) {

    companion object {
        val CODEC: Codec<CramomaticRewardPoolEntry> = RecordCodecBuilder.create { instance ->
            instance.group(
                ItemStack.CODEC.fieldOf("item").forGetter { it.itemStack },
                Codec.INT.fieldOf("weight").forGetter { it.weight }
            ).apply(instance) { item, weight ->
                CramomaticRewardPoolEntry(item, weight)
            }
        }

        // return an itemstack from the pool with a weighted chance, should never be empty unless the pool is empty
        fun getRandomWithWeight(pool: MutableList<CramomaticRewardPoolEntry>): ItemStack {
            var totalWeight = 0
            for (entry in pool) {
                totalWeight += entry.weight
            }
            var random = (Math.random() * totalWeight).toInt()
            for (entry in pool) {
                random -= entry.weight
                if (random <= 0) {
                    return entry.itemStack
                }
            }
            return ItemStack.EMPTY
        }

        fun load(tag: CompoundTag): CramomaticRewardPoolEntry {
            val itemStack = ItemStack.of(tag.getCompound("item"))
            val weight = tag.getInt("weight")
            return CramomaticRewardPoolEntry(itemStack, weight)
        }
    }
    fun getItemStack(): ItemStack {
        return itemStack
    }

    fun getWeight(): Int {
        return weight
    }

    fun save(): CompoundTag {
        val tag = CompoundTag()
        tag.put("item", itemStack.save(CompoundTag()))
        tag.putInt("weight", weight)
        return tag
    }
}