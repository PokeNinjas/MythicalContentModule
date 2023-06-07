package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.api.types.ElementalType
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class LandmarkItemModel : AnimatedGeoModel<LandmarkItem>() {
    companion object {
        fun getModelLocation(type: ElementalType): ResourceLocation {
            return ResourceLocation("mythicalmod", "geo/landmark/landmark_${type.name}.geo.json")
        }
        fun getTextureLocation(type: ElementalType): ResourceLocation {
            return ResourceLocation("mythicalmod", "textures/block/landmark/landmark_${type.name}.png")
        }
        fun getAnimationLocation(type: ElementalType): ResourceLocation {
            return ResourceLocation("mythicalmod", "animations/landmark/landmark_${type.name}.animation.json")
        }
    }
    override fun getModelResource(`object`: LandmarkItem): ResourceLocation {
        return getModelLocation(`object`.elementalType)
    }

    override fun getTextureResource(`object`: LandmarkItem): ResourceLocation {
        return getTextureLocation(`object`.elementalType)
    }

    override fun getAnimationResource(animatable: LandmarkItem): ResourceLocation {
        return getAnimationLocation(animatable.elementalType)
    }
}