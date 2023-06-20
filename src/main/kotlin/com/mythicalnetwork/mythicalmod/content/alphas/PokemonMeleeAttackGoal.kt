package com.mythicalnetwork.mythicalmod.content.alphas

import com.cobblemon.mod.common.CobblemonEntities
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal

class PokemonMeleeAttackGoal(val pokemonEntity: PokemonEntity) : MeleeAttackGoal(pokemonEntity, 1.0, true) {
    private var lastUpdateTime: Long = 0

    fun canMove() = pokemonEntity.behaviour.moving.walk.canWalk || pokemonEntity.behaviour.moving.fly.canFly// TODO probably depends on whether we're underwater or not

    override fun getAttackReachSqr(entity: LivingEntity): Double {
        val dist = pokemonEntity.getDimensions(Pose.STANDING).width - 0.1f
        return (dist * 2.0 * dist * 2.0 + entity.getDimensions(Pose.STANDING).width).toDouble()
    }

    override fun canContinueToUse(): Boolean {
        return super.canContinueToUse() && canMove() && !pokemonEntity.pokemon.aspects.contains("alpha_defeated") && !pokemonEntity.isBusy
    }

    override fun canUse(): Boolean {
        return canStartCheck() && canMove() && !pokemonEntity.pokemon.aspects.contains("alpha_defeated") && !pokemonEntity.isBusy
    }

    private fun canStartCheck() : Boolean {
        val updateTime = this.mob.level.gameTime
        if(updateTime - this.lastUpdateTime < 20L) {
            return false
        } else {
            this.lastUpdateTime = updateTime
            val target: LivingEntity = this.mob.target ?: return false
            if(!target.isAlive) {
                return false
            } else {
                return this.getAttackReachSqr(target) >= this.mob.distanceToSqr(target.x, target.y, target.z)
            }
        }
    }
}