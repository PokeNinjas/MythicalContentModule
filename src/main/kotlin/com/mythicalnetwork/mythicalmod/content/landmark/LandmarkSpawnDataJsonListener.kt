package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import com.mythicalnetwork.mythicalmod.MythicalContent
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
import net.minecraft.util.profiling.ProfilerFiller

class LandmarkSpawnDataJsonListener : SimpleJsonResourceReloadListener(GSON, "landmark_spawns") {
    companion object {
        val GSON: Gson = Gson()
        val INSTANCE: LandmarkSpawnDataJsonListener = LandmarkSpawnDataJsonListener()
    }

    override fun apply(
        prepared: MutableMap<ResourceLocation, JsonElement>,
        manager: ResourceManager,
        profiler: ProfilerFiller
    ) {
        MythicalContent.LOGGER.info("Loading landmark spawns")
        LandmarkSpawnData.clearSpawnData()
        prepared.forEach { (id, json) ->
            val data: DataResult<LandmarkSpawnData> = LandmarkSpawnData.CODEC.parse(JsonOps.INSTANCE, json)
            if (data.error().isPresent) {
                MythicalContent.LOGGER.error("Error parsing landmark spawn for $id: ${data.error().get()}")
                return@forEach
            }
            val dataHolder: LandmarkSpawnData = data.result().get()
            MythicalContent.LOGGER.info("Loaded landmark spawn for $id")
            val type: ElementalType? = ElementalTypes.get(id.path.toString())
            if (type == null) {
                MythicalContent.LOGGER.error("Error parsing landmark spawn for $id: Invalid type")
                return@forEach
            }
            MythicalContent.LOGGER.info("Loaded landmark spawn for $id: $dataHolder")
            LandmarkSpawnData.addSpawnData(type, dataHolder)
        }
    }
}