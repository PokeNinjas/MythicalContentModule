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
                { pos: BlockPos?, state: BlockState? -> LandmarkEmptyBlockEntity(pos!!, state!!) },
                MythicalBlocks.EMPTY_LANDMARK_BLOCK
            ).build(null)
        )

        MULTIBLOCK_COMPONENT = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "multiblock_component"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? -> MultiblockComponentEntity(pos!!, state!!) },
                *getBlocks(IMultiblockComponent::class.java)
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
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        NORMAL_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.NORMAL
                    )
                }, MythicalBlocks.NORMAL_LANDMARK
            ).build(null)
        )

        ELECTRIC_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "electric_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        ELECTRIC_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.ELECTRIC
                    )
                }, MythicalBlocks.ELECTRIC_LANDMARK
            ).build(null)
        )

        DRAGON_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "dragon_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        DRAGON_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.DRAGON
                    )
                }, MythicalBlocks.DRAGON_LANDMARK
            ).build(null)
        )

        DARK_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "dark_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        DARK_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.DARK
                    )
                }, MythicalBlocks.DARK_LANDMARK
            ).build(null)
        )

        STEEL_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "steel_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        STEEL_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.STEEL
                    )
                }, MythicalBlocks.STEEL_LANDMARK
            ).build(null)
        )

        ROCK_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "rock_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        ROCK_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.ROCK
                    )
                }, MythicalBlocks.ROCK_LANDMARK
            ).build(null)
        )

        BUG_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "bug_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        BUG_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.BUG
                    )
                }, MythicalBlocks.BUG_LANDMARK
            ).build(null)
        )

        PSYCHIC_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "psychic_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        PSYCHIC_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.PSYCHIC
                    )
                }, MythicalBlocks.PSYCHIC_LANDMARK
            ).build(null)
        )

        GHOST_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "ghost_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        GHOST_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.GHOST
                    )
                }, MythicalBlocks.GHOST_LANDMARK
            ).build(null)
        )

        ICE_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "ice_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        ICE_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.ICE
                    )
                }, MythicalBlocks.ICE_LANDMARK
            ).build(null)
        )

        GRASS_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "grass_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        GRASS_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.GRASS
                    )
                }, MythicalBlocks.GRASS_LANDMARK
            ).build(null)
        )

        GROUND_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "ground_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        GROUND_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.GROUND
                    )
                }, MythicalBlocks.GROUND_LANDMARK
            ).build(null)
        )

        FAIRY_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "fairy_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        FAIRY_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.FAIRY
                    )
                }, MythicalBlocks.FAIRY_LANDMARK
            ).build(null)
        )

        FIGHTING_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "fighting_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        FIGHTING_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.FIGHTING
                    )
                }, MythicalBlocks.FIGHTING_LANDMARK
            ).build(null)
        )

        FIRE_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "fire_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        FIRE_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.FIRE
                    )
                }, MythicalBlocks.FIRE_LANDMARK
            ).build(null)
        )

        FLYING_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "flying_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        FLYING_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.FLYING
                    )
                }, MythicalBlocks.FLYING_LANDMARK
            ).build(null)
        )

        POISON_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "poison_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        POISON_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.POISON
                    )
                }, MythicalBlocks.POISON_LANDMARK
            ).build(null)
        )

        WATER_LANDMARK = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            ResourceLocation("mythicalmod", "water_landmark"),
            QuiltBlockEntityTypeBuilder.create(
                { pos: BlockPos?, state: BlockState? ->
                    LandmarkBlockEntity(
                        WATER_LANDMARK!!,
                        pos!!,
                        state!!,
                        ElementalTypes.WATER
                    )
                }, MythicalBlocks.WATER_LANDMARK
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
