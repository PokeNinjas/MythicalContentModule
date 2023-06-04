package com.mythicalnetwork.mythicalmod.content.base

import dev.onyxstudios.cca.api.v3.component.ComponentV3
import net.minecraft.nbt.CompoundTag

interface SavedNBTComponent : ComponentV3 {
    fun saveNBT(): CompoundTag
    fun loadNBT(tag: CompoundTag)
    fun setHandler(handler: Any?)

    fun getHandler(): Any?
}