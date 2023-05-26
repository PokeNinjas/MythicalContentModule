package com.mythicalnetwork.mythicalmod.content.cramomatic

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
}