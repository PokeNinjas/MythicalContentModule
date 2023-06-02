package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mythicalnetwork.mythicalmod.MythicalContent
import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

class CramomaticInstance(private var cramomaticBlock: CramomaticBlockEntity? = null) {
    private var currentItems: MutableList<ItemStack> = mutableListOf()
    var output: MutableList<CramomaticRewardPoolEntry>? = null
    var isComplete: Boolean = false
    private var toComplete: Boolean = false
    private var ticks: Int = 0
    private var maxTicks: Int = 6000
    private var wasTicked: Boolean = false
    private var recipeGuess: CramomaticRecipe? = null
    private var paused: Boolean = false

    fun tick() {
        if (ticks < maxTicks) {
            ticks++
            if(ticks % 5 == 0){
                testRecipe()
            }
        } else {
            isComplete = true
            MythicalContent.CRAMOMATIC_HANDLER!!.players.filterValues { it == this }.keys.first().let {
                cramomaticBlock?.update(it, this)
            }
        }
        if(toComplete){
            isComplete = true
            MythicalContent.CRAMOMATIC_HANDLER!!.players.filterValues { it == this }.keys.first().let {
                cramomaticBlock?.update(it, this)
            }
        }
        wasTicked = true
    }

    fun pause(){
        paused = true
    }

    fun resume(){
        paused = false
    }

    fun isPaused(): Boolean {
        return paused
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
            MythicalContent.LOGGER.info("Recipe found!")
            output = recipe.output
            for(itemStack in recipe.input) {
                for (i in 0 until itemStack.count) {
                    currentItems.first { it.item == itemStack.item }.let {
                        if (it.count > 1) {
                            it.count -= 1
                        } else {
                            currentItems.remove(it)
                        }
                    }
                }
            }
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
        MythicalContent.LOGGER.info("Attempting to add item: ${item.displayName.string}")
        if(containsItemType(item)){
            for (itemStack in currentItems) {
                if (itemStack.item == item.item) {
                    MythicalContent.LOGGER.info("Found item: ${itemStack.displayName.string}. Increasing count by 1.")
                    itemStack.count += 1
                }
            }
        } else {
            MythicalContent.LOGGER.info("Item not found. Adding new item.")
            val newStack = item.copy()
            newStack.count = 1
            currentItems.add(newStack)
        }
        recipeGuess = guessClosestRecipe()
        MythicalContent.LOGGER.info("Current recipe: ${recipeGuess?.save().toString()}")
    }

    fun guessClosestRecipe(): CramomaticRecipe? {
        return CramomaticRecipeHandler.getClosestRecipe(currentItems)
    }

    fun getRecipeGuess(): CramomaticRecipe? {
        return recipeGuess
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
        MythicalContent.LOGGER.info("Clearing cramomatic instance.")
        currentItems.clear()
        output = null
        isComplete = false
        ticks = 0
        recipeGuess = null
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
        val items = CompoundTag()
        for(item in currentItems){
            items.put(item.displayName.string, item.save(CompoundTag()))
        }
        val outputItems = CompoundTag()
        output?.let {
            for (item in this.output!!) {
                outputItems.put(item.getWeight().toString(), item.getItemStack().save(CompoundTag()))
            }
        }
        tag.put("output", outputItems)
        tag.put("items", items)
        recipeGuess?.let {
            tag.put("recipeGuess", it.save())
        }
        return tag
    }

    companion object {
        fun load(tag: CompoundTag): CramomaticInstance {
            val instance = CramomaticInstance()
            instance.ticks = tag.getInt("ticks")
            instance.maxTicks = tag.getInt("maxTicks")
            instance.isComplete = tag.getBoolean("isComplete")
            instance.wasTicked = tag.getBoolean("wasTicked")
            val outputItems = tag.getCompound("output")
            if(outputItems.size() > 0){
                instance.output = mutableListOf()
                for (i in outputItems.allKeys) {
                    instance.output?.add(CramomaticRewardPoolEntry(ItemStack.of(outputItems.getCompound(i)), i.toInt()))
                }
            }
            val items = tag.getCompound("items")
            for (i in items.allKeys) {
                instance.currentItems.add(ItemStack.of(items.getCompound(i)))
            }
            if(tag.contains("recipeGuess")){
                instance.recipeGuess = CramomaticRecipe.load(tag.getCompound("recipeGuess"))
            }
            return instance
        }
    }
}