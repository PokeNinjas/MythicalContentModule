package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mythicalnetwork.mythicalmod.MythicalContent
import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

class CramomaticInstance(private var cramomaticBlock: CramomaticBlockEntity? = null) {
    private var currentItems: MutableList<ItemStack> = mutableListOf()
    var output: ItemStack? = null
    var isComplete: Boolean = false
    private var toComplete: Boolean = false
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
        if(toComplete){
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
            output = recipe.output
            currentItems.clear()
            toComplete = true
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
        if(containsItemType(item)){
            for (itemStack in currentItems) {
                if (itemStack.item == item.item) {
                    itemStack.count += item.count
                }
            }
        } else {
            currentItems.add(item.copy())
        }
    }

    fun containsItemType(item: ItemStack): Boolean {
        for (itemStack in currentItems) {
            if (itemStack.item == item.item) {
                return true
            }
        }
        return false
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
        MythicalContent.LOGGER.info("Saving cramomatic instance")
        val tag = CompoundTag()
        tag.putInt("ticks", ticks)
        tag.putInt("maxTicks", maxTicks)
        tag.putBoolean("isComplete", isComplete)
        tag.putBoolean("wasTicked", wasTicked)
        output?.save(CompoundTag())?.let { tag.put("output", it) }
        val items = CompoundTag()
        for(item in currentItems){
            items.put(item.displayName.string, item.save(CompoundTag()))
        }
        tag.put("items", items)
        return tag
    }

    companion object {
        fun load(tag: CompoundTag): CramomaticInstance {
            MythicalContent.LOGGER.info("Loading cramomatic instance")
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