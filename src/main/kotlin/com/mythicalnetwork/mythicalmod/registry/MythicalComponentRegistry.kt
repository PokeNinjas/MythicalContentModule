package com.mythicalnetwork.mythicalmod.registry

import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.SavedNBTComponent
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticPlayerHandler
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticSaveComponent
import dev.onyxstudios.cca.api.v3.component.ComponentKey
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3
import dev.onyxstudios.cca.api.v3.level.LevelComponentFactoryRegistry
import dev.onyxstudios.cca.api.v3.level.LevelComponentInitializer
import net.minecraft.server.MinecraftServer

object MythicalComponentRegistry : LevelComponentInitializer {
    val CRAMOMATIC: ComponentKey<CramomaticSaveComponent> = ComponentRegistryV3.INSTANCE.getOrCreate(MythicalContent.asResource("cramomatic"), CramomaticSaveComponent::class.java)
    override fun registerLevelComponentFactories(registry: LevelComponentFactoryRegistry) {
        registry.register(CRAMOMATIC) { level -> CramomaticSaveComponent() }
    }

    fun getCramoHandler(level: MinecraftServer): CramomaticPlayerHandler {
        return CRAMOMATIC.maybeGet(level).get().getHandler() as CramomaticPlayerHandler
    }
}