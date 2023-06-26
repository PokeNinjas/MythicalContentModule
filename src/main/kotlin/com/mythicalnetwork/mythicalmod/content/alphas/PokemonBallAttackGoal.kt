package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.util.math.toRGB
import com.cobblemon.mod.common.util.sendParticlesServer
import com.mythicalnetwork.mythicalmod.content.alphas.trackers.FakeProjectile
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3

class PokemonBallAttackGoal(val mob: PokemonEntity, val speed: Double, val attackCooldown: Int) :
    Goal() {
    private var target: LivingEntity? = null
    private var targetNotVisibleTicks: Int = 0
    private var cooldown: Int = -1
    private var left: Boolean = false
    private var backwards: Boolean = false
    private var projectiles: MutableList<FakeProjectile> = mutableListOf()
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
        return !this.mob.pokemon.aspects.contains("alpha_defeated") && projectiles.isEmpty()
    }

    private fun getSquaredMaxAttackDistance(entity: LivingEntity?): Double {
        val dist = mob.getDimensions(Pose.STANDING).width - 0.1f
        return (dist * 2.0 * dist * 2.0 + entity!!.getDimensions(Pose.STANDING).width).toDouble()
    }

    override fun tick() {
        tickProjectiles()
        if (this.mob.pokemon.aspects.contains("alpha_defeated")) return
        this.cooldown--
        val target: LivingEntity = this.target ?: return
        val canSee: Boolean = this.mob.hasLineOfSight(target)
        if (canSee) {
            this.targetNotVisibleTicks = 0
        } else {
            this.targetNotVisibleTicks++
        }
        if (this.mob.random.nextFloat() < 0.3) {
            this.left = !this.left
        }
        if (this.mob.random.nextFloat() < 0.3) {
            this.backwards = !this.backwards
        }
        val distance: Double = this.mob.distanceToSqr(target)
        if (distance > getSquaredMaxAttackDistance(target) && canSee) {
            this.mob.moveControl.setWantedPosition(target.x, target.y, target.z, this.speed * 1.85)
            if (this.mob.random.nextFloat() < 0.3 && this.cooldown <= 0) {
                this.mob.playSound(SoundEvents.ALLAY_THROW, 1.0f, 1.1f)
                this.mob.playSound(SoundEvents.WITCH_THROW, 1.0f, 0.2f)
                this.cooldown = this.attackCooldown
                summonAttackEntity()
            }
        }
    }

    fun summonAttackEntity() {
        // summon an invisible vanilla entity with a particle trail that arcs towards the target
        // when it hits the target, it explodes and deals damage
        // the entity is summoned at the pokemon's position, and is given a motion vector that arcs towards the target
        val starPos: Vec3 = this.mob.position().add(0.0, this.mob.eyeHeight.toDouble(), 0.0)
        val direction =
            this.target!!.position().add(0.0, this.target!!.eyeHeight.toDouble() / 2f, 0.0).subtract(starPos)
                .normalize()
        val distance: Double =
            starPos.distanceTo(this.target!!.position().add(0.0, this.target!!.eyeHeight.toDouble() / 2f, 0.0))
                .coerceAtLeast(8.0)
        val step: Double = distance / 8.0
        val steps: Int = (distance / step).toInt()
        val fakeProjectile: FakeProjectile = FakeProjectile(length = steps.toDouble(), stepSize = step)
        for (i in 0..steps) {
            val pos = starPos.add(direction.scale(step * i))
            fakeProjectile.positions.add(pos)
        }
        projectiles.add(fakeProjectile)
//        entity.isSilent = true
//        entity.isInvisible = true
    }

    fun tickProjectiles() {
        val color = mob.pokemon.primaryType.hue.toRGB()
        val particle: ParticleOptions = AlphaHelper.TYPE_PARTICLES[this.mob.pokemon.primaryType]!!
        val toRemove: MutableList<FakeProjectile> = mutableListOf()
        var tracker: Int = 0
        for (projectile in projectiles) {
            if (projectile.isNotEmpty()) {
                val pos = projectile.positions[0]
                this.mob.level.sendParticlesServer(
                    particle,
                    pos,
                    5,
                    Vec3.ZERO.add(0.4, 0.4, 0.4),
                    0.0
                )
                projectile.tick()
                for (i in 0..3.coerceAtMost(projectile.positions.size - 1)) {
                    if (projectile.positions.size - 1 < i) continue
                    val pos = projectile.positions[i.coerceAtMost(projectile.positions.size - 1)]
                    this.mob.level.sendParticlesServer(
                        particle,
                        pos,
                        5,
                        Vec3.ZERO.add(0.2, 0.2, 0.2),
                        0.0
                    )
                    projectile.tick()
                    tracker++
                }
                if (projectile.positions.size == 0) continue
                this.mob.level.getEntities(null, AABB.ofSize(projectile.positions[0], 0.25, 0.25, 0.25))
                    .filterIsInstance<Player>()
                    .also { players ->
                        if(players.isNotEmpty()){
                            this.mob.level.sendParticlesServer(
                                particle,
                                projectile.positions[0],
                                50,
                                Vec3.ZERO.add(3.0, 3.0, 3.0),
                                1.0
                            )
                            this.mob.level.getEntities(this.mob, AABB.ofSize(projectile.positions[0], 3.0, 3.0, 3.0))
                                .filterIsInstance<LivingEntity>()
                                .forEach { entity ->
                                    this.mob.doHurtTarget(entity)
                                }
                            projectile.stop()
                        }
                    }
                    .forEach { player ->
                        this.mob.doHurtTarget(player as Player)
                        player.hurt(DamageSource(this.mob.pokemon.primaryType.name+"_ball"), 8.0f)
                    }
            }
        }
        projectiles.removeAll(toRemove)
    }
}