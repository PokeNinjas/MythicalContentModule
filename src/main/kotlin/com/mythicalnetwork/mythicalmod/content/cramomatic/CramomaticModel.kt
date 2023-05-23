package com.mythicalnetwork.mythicalmod.content.cramomatic

import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class CramomaticModel : AnimatedGeoModel<CramomaticBlockEntity>() {
    companion object {
        final val MODEL_LOCATION = ResourceLocation("mythicalmod", "geo/cramomatic.geo.json")
        final val TEXTURE_LOCATION = ResourceLocation("mythicalmod", "textures/block/cramomatic.png")
        final val ANIMATION_LOCATION = ResourceLocation("mythicalmod", "animations/cramomatic.animation.json")
    }
    override fun getModelResource(`object`: CramomaticBlockEntity?): ResourceLocation {
        return MODEL_LOCATION
    }

    override fun getTextureResource(`object`: CramomaticBlockEntity?): ResourceLocation {
        return TEXTURE_LOCATION
    }

    override fun getAnimationResource(animatable: CramomaticBlockEntity?): ResourceLocation {
        return ANIMATION_LOCATION
    }
}