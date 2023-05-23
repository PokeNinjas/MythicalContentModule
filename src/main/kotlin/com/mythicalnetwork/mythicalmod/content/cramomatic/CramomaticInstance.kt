package com.mythicalnetwork.mythicalmod.content.cramomatic

import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

class CramomaticInstance(private var cramomaticBlock: CramomaticBlockEntity? = null) {
    private var currentItems: MutableList<ItemStack> = mutableListOf()
    var output: ItemStack? = null
    var isComplete: Boolean = false
    private var ticks: Int = 0
    private var maxTicks: Int = 200
    private var wasTicked: Boolean = false

    fun tick() {
        if (ticks < maxTicks) {
            ticks++
            if(ticks % 5 == 0){
                testRecipe()
            }
        } else {
            isComplete = true
        }
        if(ticks % 20 == 0){
            val builder: StringBuilder = StringBuilder()
            for(itemStack in currentItems){
                builder.append(itemStack.displayName.string)
                builder.append(", ")
            }
            println("Current items: ${builder.toString()}")
            println("Output: ${output?.displayName?.string}")
            println("Current item size: ${currentItems.size}")
            println(currentItems.toString())
        }
        wasTicked = true
    }

    fun wasTicked(): Boolean {
        return wasTicked
    }

    fun setWasTicked(wasTicked: Boolean) {
        this.wasTicked = wasTicked
    }

    fun getBlock(): CramomaticBlockEntity? {
        return cramomaticBlock
    }

    fun setBlock(cramomaticBlock: CramomaticBlockEntity?) {
        this.cramomaticBlock = cramomaticBlock
    }

    private fun testRecipe(){
        val recipe = CramomaticRecipeHandler.getRecipe(currentItems)
        if (recipe != null) {
            output = recipe.getOutput()
            currentItems.clear()
        }
    }

    fun onComplete(completeAction: (CramomaticInstance) -> Unit) {
        completeAction(this)
    }

    fun getCurrentItems(): MutableList<ItemStack> {
        return currentItems
    }

    fun setCurrentItems(currentItems: MutableList<ItemStack>) {
        this.currentItems = currentItems
    }

    fun addItem(item: ItemStack) {
        currentItems.add(item)
        println("Added item: ${item.displayName.string}")
    }

    fun getTime(): Int {
        return ticks
    }

    fun setTime(seconds: Int) {
        this.ticks = seconds
    }

    fun getMaxTime(): Int {
        return maxTicks
    }

    fun setMaxTime(maxTime: Int) {
        this.maxTicks = maxTime
    }

    fun clear() {
        currentItems.clear()
        output = null
        isComplete = false
        ticks = 0
        maxTicks = 0
    }

    fun size(): Int {
        return currentItems.size
    }

    fun save(): CompoundTag {
        val tag = CompoundTag()
        tag.putInt("ticks", ticks)
        tag.putInt("maxTicks", maxTicks)
        tag.putBoolean("isComplete", isComplete)
        tag.putBoolean("wasTicked", wasTicked)
        output?.save(CompoundTag())?.let { tag.put("output", it) }
        val items = CompoundTag()
        for (i in currentItems.indices) {
            items.put("item$i", currentItems[i].save(CompoundTag()))
        }
        tag.put("items", items)
        return tag
    }

    companion object {
        fun load(tag: CompoundTag): CramomaticInstance {
            val instance = CramomaticInstance()
            instance.ticks = tag.getInt("ticks")
            instance.maxTicks = tag.getInt("maxTicks")
            instance.isComplete = tag.getBoolean("isComplete")
            instance.wasTicked = tag.getBoolean("wasTicked")
            instance.output = ItemStack.of(tag.getCompound("output"))
            val items = tag.getCompound("items")
            for (i in items.allKeys) {
                instance.currentItems.add(ItemStack.of(items.getCompound(i)))
            }
            return instance
        }
    }
}