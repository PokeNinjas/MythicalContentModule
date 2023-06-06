package com.mythicalnetwork.mythicalmod.registry

import com.cobblemon.mod.common.api.types.ElementalTypes
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticBlockEntity
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkBlockEntity
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkEmptyBlockEntity
import com.mythicalnetwork.mythicalmod.registry.MythicalBlocks.CRAMOMATIC
import com.mythicalnetwork.mythicalmod.systems.multiblock.IMultiblockComponent
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockComponentEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder
import java.util.*
import kotlin.collections.ArrayList


object MythicalBlockEntities {

    var CRAMOMATIC_BLOCK_ENTITY: BlockEntityType<CramomaticBlockEntity>? = null
    var NORMAL_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var FIRE_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var WATER_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var GRASS_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var ELECTRIC_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var ICE_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var FIGHTING_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var POISON_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var GROUND_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var FLYING_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var PSYCHIC_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var BUG_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var ROCK_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var GHOST_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var DRAGON_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var DARK_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var STEEL_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null
    var FAIRY_LANDMARK: BlockEntityType<LandmarkBlockEntity>? = null

    var MULTIBLOCK_COMPONENT: BlockEntityType<MultiblockComponentEntity>? = null
    var EMPTY_LANDMARK: BlockEntityType<LandmarkEmptyBlockEntity>? = null

    fun init() {
        EMPTY_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "empty_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> LandmarkEmptyBlockEntity(pos!!, state!!) }, MythicalBlocks.EMPTY_LANDMARK_BLOCK
            ).build(null)
        )

        MULTIBLOCK_COMPONENT = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "multiblock_component"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> MultiblockComponentEntity(pos!!, state!!) }, *getBlocks(IMultiblockComponent::class.java)
            ).build(null)
        )

        CRAMOMATIC_BLOCK_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "cramomatic_block_entity"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> CramomaticBlockEntity(pos!!, state!!) }, CRAMOMATIC
            ).build(null)
        )

        NORMAL_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "normal_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> LandmarkBlockEntity(NORMAL_LANDMARK!!, pos!!, state!!, ElementalTypes.NORMAL) }, MythicalBlocks.NORMAL_LANDMARK
            ).build(null)
        )

        ELECTRIC_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "electric_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> LandmarkBlockEntity(ELECTRIC_LANDMARK!!, pos!!, state!!, ElementalTypes.ELECTRIC) }, MythicalBlocks.ELECTRIC_LANDMARK
            ).build(null)
        )

        DRAGON_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "dragon_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> LandmarkBlockEntity(DRAGON_LANDMARK!!, pos!!, state!!, ElementalTypes.DRAGON) }, MythicalBlocks.DRAGON_LANDMARK
            ).build(null)
        )
    }

    fun getBlocks(vararg blockClasses: Class<*>?): Array<Block> {
        val blocks = Registry.BLOCK
        val matchingBlocks: ArrayList<Block> = ArrayList<Block>()
        for (block in blocks) {
            if (Arrays.stream(blockClasses).anyMatch { b -> b?.isInstance(block) == true }) {
                matchingBlocks.add(block)
            }
        }
        return matchingBlocks.toArray(arrayOfNulls<Block>(0))
    }
}
