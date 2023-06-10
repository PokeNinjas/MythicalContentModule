package com.mythicalnetwork.mythicalmod.registry

import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.SavedNBTComponent
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticPlayerHandler
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticSaveComponent
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkCooldown
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkCooldownComponent
import dev.onyxstudios.cca.api.v3.component.ComponentKey
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy
import dev.onyxstudios.cca.api.v3.level.LevelComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.level.LevelComponentInitializer
import net.minecraft.server.MinecraftServer

object MythicalComponentRegistry : LevelComponentInitializer, EntityComponentInitializer {
    val CRAMOMATIC: ComponentKey<CramomaticSaveComponent> = ComponentRegistryV3.INSTANCE.getOrCreate(MythicalContent.asResource("cramomatic"), CramomaticSaveComponent::class.java)
    val LANDMARK_PLAYER_TRACKER: ComponentKey<LandmarkCooldownComponent> = ComponentRegistryV3.INSTANCE.getOrCreate(MythicalContent.asResource("landmark_player_tracker"), LandmarkCooldownComponent::class.java)
    override fun registerLevelComponentFactories(registry: LevelComponentFactoryRegistry) {
        registry.register(CRAMOMATIC) { CramomaticSaveComponent() }
    }

    fun getCramoHandler(level: MinecraftServer): CramomaticPlayerHandler {
        return CRAMOMATIC.maybeGet(level).get().getHandler() as CramomaticPlayerHandler
    }

    override fun registerEntityComponentFactories(registry: EntityComponentFactoryRegistry) {
        registry.registerForPlayers(LANDMARK_PLAYER_TRACKER, { LandmarkCooldownComponent() }, RespawnCopyStrategy.ALWAYS_COPY)
    }
}