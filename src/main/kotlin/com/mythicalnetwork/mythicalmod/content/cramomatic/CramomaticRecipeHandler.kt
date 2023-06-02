package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mythicalnetwork.mythicalmod.MythicalContent
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack

object CramomaticRecipeHandler {
    var RECIPES: MutableMap<ResourceLocation, CramomaticRecipe> = mutableMapOf()


    fun addRecipe(resourceLocation: ResourceLocation, recipe: CramomaticRecipe) {
        RECIPES[resourceLocation] = recipe
    }

    fun removeRecipe(resourceLocation: ResourceLocation) {
        RECIPES.remove(resourceLocation)
    }

    fun getRecipes(): MutableMap<ResourceLocation, CramomaticRecipe> {
        return RECIPES
    }

    fun setRecipes(recipes: MutableMap<ResourceLocation, CramomaticRecipe>) {
        RECIPES = recipes
    }

    fun clearRecipes() {
        RECIPES.clear()
    }

    fun getRecipe(input: MutableList<ItemStack>): CramomaticRecipe? {
        for (recipe in RECIPES) {
            // check if input matches recipe, regardless of order without using containsAll
            if (input.size == recipe.value.input.size) {
                var matches = true
                for (itemStack in recipe.value.input) {
                    var found = false
                    for (inputItemStack in input) {
                        if (inputItemStack.item == itemStack.item) {
                            if(inputItemStack.count < itemStack.count && itemStack.count - inputItemStack.count > 0){
                                matches = false
                                break
                            }
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        matches = false
                        break
                    }
                }
                if (matches) {
                    return recipe.value
                }
            }
        }
        return null
    }

    fun getClosestRecipe(input: MutableList<ItemStack>): CramomaticRecipe? {
        // taking the items in the input list, find the recipe that matches the most items, regardless of order, even if some items are missing
        MythicalContent.LOGGER.info("Finding closest recipe for input: $input")
        var closestRecipe: CramomaticRecipe? = null
        var closestRecipeMatches = 0
        for (recipe in RECIPES) {
            var matches = 0
            for (itemStack in recipe.value.input) {
                for (inputItemStack in input) {
                    if (inputItemStack.item == itemStack.item) {
                        if(inputItemStack.count < itemStack.count && itemStack.count - inputItemStack.count > 0){
                            MythicalContent.LOGGER.info("Found item: ${inputItemStack.displayName.string}. But count is too low. Resetting count.")
                            matches = 0
                            break
                        }
                        MythicalContent.LOGGER.info("Found item: ${inputItemStack.displayName.string}. Increasing count by 1.")
                        matches++
                        break
                    }
                }
            }
            if (matches > closestRecipeMatches) {
                MythicalContent.LOGGER.info("Found recipe with more matches: ${recipe.key}")
                closestRecipe = recipe.value
                closestRecipeMatches = matches
            }
        }
        return closestRecipe
    }
}