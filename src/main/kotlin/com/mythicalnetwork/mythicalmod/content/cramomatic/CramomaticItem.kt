package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mythicalnetwork.mythicalmod.registry.MythicalBlocks
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.easing.EasingType
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil

class CramomaticItem(block: Block, properties: Properties) : BlockItem(MythicalBlocks.CRAMOMATIC, properties), IAnimatable {
    private var animationFactory: AnimationFactory = GeckoLibUtil.createFactory(this)
    override fun registerControllers(p0: AnimationData) {
        p0.addAnimationController(AnimationController(this, "controller", 0.0F, this::predicate))
    }

    private fun <E> predicate(animationEvent: AnimationEvent<E>): PlayState
            where E : IAnimatable, E : Item {
        val controller = animationEvent.controller
        controller.transitionLengthTicks = 10.0
        controller.easingType = EasingType.EaseInCubic
        return PlayState.CONTINUE
    }

    override fun getFactory(): AnimationFactory {
        return animationFactory
    }
}