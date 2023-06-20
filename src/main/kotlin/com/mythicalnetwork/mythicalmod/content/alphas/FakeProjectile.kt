package com.mythicalnetwork.mythicalmod.content.alphas

import net.minecraft.core.BlockPos
import net.minecraft.world.phys.Vec3

class FakeProjectile(
    var positions: MutableList<Vec3> = mutableListOf(),
    var length: Double = 1.0,
    var speed: Double = 0.0,
    var stepSize: Double = 0.1,
    var steps: Int = length.div(stepSize).toInt()
    ) {

    fun tick() {
        positions = positions.drop(1).toMutableList()
    }

    fun add(pos: Vec3) {
        positions.add(pos)
    }

    fun addAll(pos: List<Vec3>) {
        positions.addAll(pos)
    }

    fun isEmpty() = positions.isEmpty()

    fun isNotEmpty() = positions.isNotEmpty()

    fun getFirst() = positions.first()
}