package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import com.mythicalnetwork.mythicalmod.MythicalContent
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
import net.minecraft.util.profiling.ProfilerFiller

class CramomaticRecipeJsonListener : SimpleJsonResourceReloadListener(GSON, "cramomatic_recipes") {
    companion object {
        val GSON: Gson = Gson()
        val INSTANCE: CramomaticRecipeJsonListener = CramomaticRecipeJsonListener()
    }
    override fun apply(
        prepared: MutableMap<ResourceLocation, JsonElement>,
        manager: ResourceManager,
        profiler: ProfilerFiller
    ) {
        MythicalContent.LOGGER.info("Loading cramomatic recipes")
        CramomaticRecipeHandler.clearRecipes()
        prepared.forEach { (id, json) ->
            val data: DataResult<CramomaticRecipe> = CramomaticRecipe.CODEC.parse(JsonOps.INSTANCE, json)
            if(data.error().isPresent){
                MythicalContent.LOGGER.error("Error parsing cramomatic recipe for $id: ${data.error().get()}")
                return@forEach
            }
            val dataHolder: CramomaticRecipe = data.result().get()
            MythicalContent.LOGGER.info("Loaded cramomatic recipe for $id: ${dataHolder.input}")
            CramomaticRecipeHandler.addRecipe(id, dataHolder)
        }
    }
}