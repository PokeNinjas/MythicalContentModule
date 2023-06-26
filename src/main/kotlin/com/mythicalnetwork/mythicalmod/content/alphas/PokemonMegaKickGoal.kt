package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.goal.Goal
import kotlin.math.round

class PokemonMegaKickGoal(val mob: PokemonEntity, val speed: Double, val kickCooldown: Int) :
    Goal() {
    private var target: LivingEntity? = null
    private var targetNotVisibleTicks: Int = 0
    private var cooldown: Int = -1
    private var left: Boolean = false
    private var backwards: Boolean = false
    override fun canUse(): Boolean {
        val livingEntity: LivingEntity = this.mob.target ?: return false
        if (livingEntity.isAlive && canMove() && this.mob.canAttack(livingEntity)) {
            this.target = livingEntity
            return true
        }
        return false
    }

    fun canMove() =
        mob.behaviour.moving.walk.canWalk || mob.behaviour.moving.fly.canFly// TODO probably depends on whether we're underwater or not

    override fun canContinueToUse(): Boolean {
        return (canUse() || this.target?.isAlive ?: false && !this.mob.navigation.isDone) && canMove() && !mob.pokemon.aspects.contains(
            "alpha_defeated"
        ) && !mob.isBusy
    }

    override fun stop() {
        this.target = null
        this.targetNotVisibleTicks = 0
        this.cooldown = -1
    }

    override fun requiresUpdateEveryTick(): Boolean {
    return !this.mob.pokemon.aspects.contains("alpha_defeated")
    }

    private fun getSquaredMaxAttackDistance(entity: LivingEntity?): Double {
        val dist = mob.getDimensions(Pose.STANDING).width - 0.1f
        return (dist * 2.0 * dist * 2.0 + entity!!.getDimensions(Pose.STANDING).width).toDouble()
    }

    override fun tick() {
        if(this.mob.pokemon.aspects.contains("alpha_defeated")) return
        this.cooldown--
        val target: LivingEntity = this.target ?: return
        val canSee: Boolean = this.mob.hasLineOfSight(target)
        if (canSee) {
            this.targetNotVisibleTicks = 0
        } else {
            this.targetNotVisibleTicks++
        }
        if(this.mob.random.nextFloat() < 0.3) {
            this.left = !this.left
        }
        if(this.mob.random.nextFloat() < 0.3) {
            this.backwards = !this.backwards
        }
        val distance: Double = this.mob.distanceToSqr(target)
        if(distance < getSquaredMaxAttackDistance(target)) {
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed * 1.85)
            if(this.mob.random.nextFloat() < 0.3 && this.cooldown <= 0) {
                var x: Double = if(this.mob.random.nextFloat() < 0.5) 5.0 else 0.0
                var y: Double = if(this.mob.random.nextFloat() < 0.5) 5.0 else 0.0
                var z: Double = if(this.mob.random.nextFloat() < 0.5) 5.0 else 0.0
                // randomly invert the direction
                x = if(this.mob.random.nextFloat() < 0.5) -x else x
                z = if(this.mob.random.nextFloat() < 0.5) -z else z
                this.target!!.push(x, y, z)
                this.target!!.hurt(DamageSource("mega_kick"), 5.0f)
                this.mob.playSound(SoundEvents.PLAYER_ATTACK_STRONG, 0.8f, 0.65f)
                this.cooldown = this.mob.random.nextIntBetweenInclusive(kickCooldown, kickCooldown * 2)
            }
        } else {
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed)
        }
    }
}