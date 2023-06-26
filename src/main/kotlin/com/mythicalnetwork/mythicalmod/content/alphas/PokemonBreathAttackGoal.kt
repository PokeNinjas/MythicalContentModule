package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.isHeadLookingAt
import com.cobblemon.mod.common.util.isLookingAt
import com.cobblemon.mod.common.util.math.toRGB
import com.cobblemon.mod.common.util.sendParticlesServer
import com.mojang.math.Vector3f
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.projectile.SmallFireball
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import kotlin.math.cos
import kotlin.math.sin

class PokemonBreathAttackGoal(val mob: PokemonEntity, val speed: Double, val attackInterval: Int, val maxRange: Float, val breathDuration: Int, val breathCooldown: Int) :
    Goal() {
    private var target: LivingEntity? = null
    private var targetNotVisibleTicks: Int = 0
    private var cooldown: Int = -1
    private var squaredMaxRange: Float = maxRange * maxRange
    private var left: Boolean = false
    private var backwards: Boolean = false
    private var isBreathing: Boolean = false
    private var breathTicks: Int = 0
    private var attackCooldownTicks: Int = 0
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

    fun isTargetTooClose(): Boolean {
        return getAttackDistance(this.target!!) < 4.0
    }

    fun getAttackDistance(entity: LivingEntity): Double {
        val dist = mob.getDimensions(Pose.STANDING).width - 0.1f
        return (dist * 2.0 * dist * 2.0 + target!!.getDimensions(Pose.STANDING).width).toDouble()
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
        this.attackCooldownTicks--
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
        if(distance > getSquaredMaxAttackDistance(target)) {
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed * 1.85)
        }
        if (distance < getSquaredMaxAttackDistance(target) && !isBreathing) {
            if (this.mob.random.nextFloat() < 0.25 && canMove()) {
                var toTakeoff: Int = if(this.mob.random.nextFloat() < 0.25) {
                    this.mob.setBehaviourFlag(PokemonBehaviourFlag.FLYING, true)
                    this.mob.random.nextInt(10)
                } else { 0 }
                this.mob.moveControl.setWantedPosition(target.x, target.y + toTakeoff , target.z, this.speed)
            }
        }  else if (distance < this.squaredMaxRange && canSee && this.cooldown <= 0) {
            if(!isBreathing && this.mob.random.nextFloat() < 0.25f){
                isBreathing = true
                breathTicks = breathDuration
                this.mob.playSound(SoundEvents.ENDER_DRAGON_GROWL, 1.0f, 1.0f)
            }
        } else if (this.targetNotVisibleTicks < 5) {
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed)
        }
        if (isBreathing){
            breathTicks--
            this.mob.lookControl.setLookAt(target, 30.0f, 30.0f)
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed)
            for (i in 0 until 10) {
                spawnParticles()
            }
            if(breathTicks == 0){
                isBreathing = false
            }
            if(!isBreathing && breathTicks == 0){
                this.cooldown = this.mob.random.nextInt(this.breathCooldown / 2, this.breathCooldown)
            }
            if(distance < this.squaredMaxRange) {
                if (canSee && this.mob.isHeadLookingAt(target, maxRange)) {
                    this.mob.lookControl.setLookAt(target, 30.0f, 30.0f)
                    this.target!!.hurt(DamageSource(this.mob.pokemon.primaryType.name+"_breath"), 1.0f)
                }
            }
        } else if (cooldown > 0) {
            this.mob.moveControl.strafe(if (left) -0.5f else 0.5f, if (backwards) -0.5f else 0.5f)
            this.mob.lookControl.setLookAt(target, 30.0f, 30.0f)
        }
    }

    private fun spawnParticles() {
        // spawn particles in a straight line from the mob to the target
        val level: Level = this.mob.level
        val startPos: Vec3 = this.mob.position().add(0.0, this.mob.eyeHeight.toDouble(), 0.0)
        val direction: Vec3 = Vec3.directionFromRotation(0.0F, this.mob.yHeadRot)
        val endPos: Vec3 = startPos.add(direction.scale(this.maxRange.toDouble()))
        val distance: Double = startPos.distanceTo(endPos).coerceAtMost(this.maxRange.toDouble())
        val step: Double = 0.5
        val steps: Int = (distance / step).toInt()
        val color = mob.pokemon.primaryType.hue.toRGB()
        for (i in 0 until steps) {
            if(this.mob.random.nextFloat() < 0.75) continue
            val pos = startPos.add(direction.scale(step * i))
            val particle: ParticleOptions = AlphaHelper.TYPE_PARTICLES[this.mob.pokemon.primaryType]!!
            level.sendParticlesServer(
                particle,
                pos,
                1,
                Vec3((mob.random.nextDouble() - 0.5)*i/10.0, (mob.random.nextDouble() - 0.5)*i/10.0, (mob.random.nextDouble() - 0.5)*i/10.0),
                0.025
            )
        }
    }
}