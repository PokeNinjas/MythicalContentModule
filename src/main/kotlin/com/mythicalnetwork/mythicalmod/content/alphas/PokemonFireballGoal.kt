package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.isHeadLookingAt
import com.cobblemon.mod.common.util.math.toRGB
import com.cobblemon.mod.common.util.sendParticlesServer
import com.mojang.math.Vector3f
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.projectile.SmallFireball
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

class PokemonFireballGoal(val mob: PokemonEntity, val speed: Double, val maxRange: Float, val fireballCooldown: Int) :
    Goal() {
    private var target: LivingEntity? = null
    private var targetNotVisibleTicks: Int = 0
    private var cooldown: Int = -1
    private var squaredMaxRange: Float = maxRange * maxRange
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
        }  else if (distance < this.squaredMaxRange && canSee && this.cooldown <= 0) {
            if (this.mob.random.nextFloat() > 0.25 && this.mob.random.nextFloat() < 0.5){
                val xVel: Double = this.mob.random.triangle(target.x - this.mob.x, 2.297)
                val zVel: Double = this.mob.random.triangle(target.z - this.mob.z, 2.297)
                val yVel: Double = target.getY(0.5) - this.mob.getY(0.5)
                val smallFireballEntity: SmallFireball = SmallFireball(this.mob.level, this.mob, xVel, yVel, zVel)
                smallFireballEntity.setPos(this.mob.x, this.mob.y + this.mob.eyeHeight.toDouble(), this.mob.z)
                this.mob.level.addFreshEntity(smallFireballEntity)
                this.mob.playSound(SoundEvents.BLAZE_SHOOT, 1.0f, 1.0f)
                this.cooldown = this.mob.random.nextInt(this.fireballCooldown / 2, this.fireballCooldown)
            }
        } else if (this.targetNotVisibleTicks < 5) {
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed)
        } else if (cooldown > 0) {
            if(distance < this.squaredMaxRange) {
                if (canSee && this.mob.isHeadLookingAt(target, maxRange)) {
                    this.mob.lookControl.setLookAt(target, 30.0f, 30.0f)
                    this.mob.doHurtTarget(target)
                }
            } else {
                this.mob.moveControl.strafe(if (left) -0.5f else 0.5f, if (backwards) -0.5f else 0.5f)
                this.mob.lookControl.setLookAt(target, 30.0f, 30.0f)
            }
        }
    }
}