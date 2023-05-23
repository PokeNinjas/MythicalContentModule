package com.mythicalnetwork.mythicalmod.content.cramomatic

import net.minecraft.world.item.ItemStack

object CramomaticRecipeHandler {
    var RECIPES: MutableList<CramomaticRecipe> = mutableListOf()

    fun addRecipe(recipe: CramomaticRecipe) {
        RECIPES.add(recipe)
    }

    fun removeRecipe(recipe: CramomaticRecipe) {
        RECIPES.remove(recipe)
    }

    fun getRecipes(): MutableList<CramomaticRecipe> {
        return RECIPES
    }

    fun setRecipes(recipes: MutableList<CramomaticRecipe>) {
        RECIPES = recipes
    }

    fun clearRecipes() {
        RECIPES.clear()
    }

    fun getRecipe(input: MutableList<ItemStack>): CramomaticRecipe? {
        for (recipe in RECIPES) {
            // check if input matches recipe, regardless of order
            if (recipe.getInput().containsAll(input) && input.containsAll(recipe.getInput())) {
                return recipe
            }
        }
        return null
    }
}