package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.api.types.ElementalType
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class LandmarkModel : AnimatedGeoModel<LandmarkBlockEntity>(){
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
    override fun getModelResource(`object`: LandmarkBlockEntity): ResourceLocation {
        return getModelLocation(`object`.pokemonType)
    }

    override fun getTextureResource(`object`: LandmarkBlockEntity): ResourceLocation {
        return getTextureLocation(`object`.pokemonType)
    }

    override fun getAnimationResource(animatable: LandmarkBlockEntity): ResourceLocation {
        return getAnimationLocation(animatable.pokemonType)
    }
}