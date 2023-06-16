package com.mythicalnetwork.mythicalmod.content.misc.rocketboots

import com.cobblemon.mod.common.util.math.FloatRange
import com.mythicalnetwork.mythicalmod.content.base.ArmorMaterials
import com.mythicalnetwork.mythicalmod.util.TooltipHelper
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.*
import net.minecraft.world.level.Level
import org.quiltmc.qsl.item.setting.api.CustomDamageHandler
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil
import java.util.function.Consumer


class RocketBootsItem(properties: Item.Properties) : ArmorItem(ArmorMaterials.ROCKET_BOOTS, EquipmentSlot.FEET, properties.durability(1800)){
//    private val factory: AnimationFactory = GeckoLibUtil.createFactory(this)
    private val colors: Map<FloatRange, Int> = mapOf(
        FloatRange(0.667f, 1.0f) to 0x25f55c,
        FloatRange(0.334f, 0.666f) to 0xf59025,
        FloatRange(0.0f, 0.333f) to 0xFF0000
    )

//    override fun registerControllers(p0: AnimationData) {
//        p0.addAnimationController(AnimationController(this, "controller", 20.0F, this::predicate))
//    }

    init {
    }

    override fun isValidRepairItem(stack: ItemStack, ingredient: ItemStack): Boolean {
        return false;
    }

    override fun isEnchantable(stack: ItemStack): Boolean {
        return false
    }
    private fun <P : IAnimatable?> predicate(event: AnimationEvent<P>): PlayState {
        event.controller.setAnimation(
            AnimationBuilder().addAnimation(
                "idle",
                EDefaultLoopTypes.LOOP
            )
        )
        return PlayState.CONTINUE
    }

    override fun getBarColor(stack: ItemStack): Int {
        return colors.entries.firstOrNull { (1 - stack.damageValue.toFloat()/stack.maxDamage.toFloat()) in it.key }?.value ?: 0x25f55c
    }
//    override fun getFactory(): AnimationFactory {
//        return factory
//    }

    override fun canBeDepleted(): Boolean {
        return false
    }

    override fun getName(stack: ItemStack): Component {
        return Component.literal("Rocket Boots").withStyle { s -> s.withColor(ChatFormatting.GOLD) }
    }

    override fun appendHoverText(
        stack: ItemStack,
        world: Level?,
        tooltip: MutableList<Component>,
        context: TooltipFlag
    ) {
        tooltip.add(Component.literal("Double tap space to take flight!").withStyle { s -> s.withColor(ChatFormatting.BLUE) })
        tooltip.add(Component.literal("Fuel: ").withStyle { s -> s.withColor(0xAAAAAA) }.append(TooltipHelper.makeProgressBar(1-stack.damageValue.toFloat()/1800F, getBarColor(stack), 0x777777)).append(
            Component.literal("  ${(((1800F - stack.damageValue)/1800F)*100).toInt()}%").withStyle { s -> s.withColor(0xAAAAAA) }))
        super.appendHoverText(stack, world, tooltip, context)
    }

}