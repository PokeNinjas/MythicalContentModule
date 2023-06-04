package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.SavedNBTComponent
import net.minecraft.nbt.CompoundTag
import org.quiltmc.qkl.library.nbt.string

class CramomaticSaveComponent : SavedNBTComponent {
    var handler: CramomaticPlayerHandler? = null
    override fun saveNBT(): CompoundTag {
        val tag = CompoundTag()
        if(handler != null) {
            tag.put("players", handler!!.save())
        }
        return tag
    }

    override fun loadNBT(tag: CompoundTag) {
        if(tag.contains("players")){
            handler = CramomaticPlayerHandler.load(tag.getCompound("players"))
        }
    }

    override fun setHandler(handler: Any?) {
        this.handler = handler as CramomaticPlayerHandler
    }

    override fun getHandler(): Any? {
        return handler
    }

    override fun readFromNbt(tag: CompoundTag) {
        loadNBT(tag.getCompound("players"))
        MythicalContent.LOGGER.info("Loading CramomaticPlayerHandler: ${tag.getCompound("players").string}")
    }

    override fun writeToNbt(tag: CompoundTag) {
        tag.put("players", saveNBT())
        MythicalContent.LOGGER.info("Saving CramomaticPlayerHandler: ${tag.string}")
    }
}