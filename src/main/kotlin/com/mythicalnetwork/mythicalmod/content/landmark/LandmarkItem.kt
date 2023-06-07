package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.api.types.ElementalType
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockItem
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockStructure
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.easing.EasingType
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil
import java.util.function.Supplier

class LandmarkItem(block: Block, properties: Item.Properties, var elementalType: ElementalType, override val structure: Supplier<out MultiblockStructure>) : MultiblockItem(block, properties, structure), IAnimatable {
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