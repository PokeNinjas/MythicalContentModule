package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.sendParticlesServer
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.phys.Vec3

class PokemonTeleportGoal(val mob: PokemonEntity, val speed: Double, val teleportCooldown: Int) :
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
        if(distance > getSquaredMaxAttackDistance(target)) {
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed * 1.85)
            if(this.mob.random.nextFloat() < 0.3 && this.cooldown <= 0) {
                val pos: BlockPos? = LandmarkBlockEntity.checkSpawnConditions(this.mob, false, false, true, 8, this.target!!.onPos, this.mob.level)
                if(pos != null){
                    this.mob.level.sendParticlesServer(ParticleTypes.WARPED_SPORE, this.mob.position(), 128, Vec3(0.5, 0.5, 0.5), 1.0)
                    this.mob.teleportTo(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
                    this.mob.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.65f, 1.5f)
                    this.mob.level.sendParticlesServer(ParticleTypes.WARPED_SPORE, this.mob.position(), 128, Vec3(0.5, 0.5, 0.5), -1.0)
                    this.cooldown = this.mob.random.nextIntBetweenInclusive(teleportCooldown, teleportCooldown * 2)
                }
            }
        }
    }
}