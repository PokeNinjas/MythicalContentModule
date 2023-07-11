package com.mythicalnetwork.mythicalmod.registry

import com.cobblemon.mod.common.CobblemonBlockEntities.equals
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticItem
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkBlockEntity
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkItem
import com.mythicalnetwork.mythicalmod.content.misc.rocketboots.RocketBootsItem
import com.mythicalnetwork.mythicalmod.content.misc.tms.TMItem
import com.mythicalnetwork.mythicalmod.content.radar.RadarItem
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockItem
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockStructure
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings
import java.util.function.Supplier

object MythicalItems {
    val ITEMS: MutableMap<ResourceLocation, Item> = mutableMapOf()
    var PLATINUM_INGOT: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "platinum_ingot")

    val POKEMON_EGG: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "pokemon_egg")

    val EASTER_EGG: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "easter_egg")

    val RAINBOW_SHARD: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "rainbow_shard")

    val RADIANT_CRYSTAL: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "radiant_crystal")

    val BLAZING_GEM: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "blazing_gem")

    val GALAXY_STAR: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "galaxy_star")

    val GILDED_CUBE: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gilded_cube")

    val MAGMA_CRYSTAL: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "magma_crystal")

    val MATRIX_CUBE: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "matrix_cube")

    val SHADOW_ORB: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "shadow_orb")

    val VIRUS_CRYSTAL: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "virus_crystal")

    val ROCKET_BOOTS: Item =
        RocketBootsItem(QuiltItemSettings().stacksTo(1).tab(MythicalGroups.MYTHICAL_ITEMS) as QuiltItemSettings, "rocketboots")

    val RADAR: Item =
        RadarItem(QuiltItemSettings().stacksTo(1).tab(MythicalGroups.MYTHICAL_ITEMS) as QuiltItemSettings, "radar")

    // Boxes
    val GIFT_BOX_1: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_1")

    val GIFT_BOX_2: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_2")

    val GIFT_BOX_3: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_3")

    val GIFT_BOX_4: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_4")

    val GIFT_BOX_5: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_5")

    val GIFT_BOX_6: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_6")

    val GIFT_BOX_7: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_7")

    val GIFT_BOX_8: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_8")

    val GIFT_BOX_9: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_9")

    val GIFT_BOX_10: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_10")

    val GIFT_BOX_11: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_11")

    val GIFT_BOX_12: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_12")

    val GIFT_BOX_13: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_13")

    val GIFT_BOX_14: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_14")

    val GIFT_BOX_15: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_15")

    val GIFT_BOX_16: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_16")

    val GIFT_BOX_17: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_17")

    val GIFT_BOX_18: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "gift_box_18")

    val TM: Item =
        TMItem(QuiltItemSettings().stacksTo(1).tab(MythicalGroups.MYTHICAL_ITEMS) as QuiltItemSettings, "tm")

    private fun Item(tab: Item.Properties?, s: String): Item {
        val item = tab?.let { Item(it) }
        ITEMS[ResourceLocation("mythicalmod", s)] = item!!
        return item
    }

    private fun TMItem(tab: Item.Properties?, s: String): Item {
        val item = tab?.let { TMItem() }
        ITEMS[ResourceLocation("mythicalmod", s)] = item!!
        return item
    }

    private fun RocketBootsItem(tab: QuiltItemSettings?, s: String): RocketBootsItem {
        val item = tab?.let { RocketBootsItem(
            it.customDamage { stack, amount, entity, callback ->
                if(entity != MythicalContent.entity!!){
                    0
                } else {
                    amount
                }
            },
        )}
        ITEMS[ResourceLocation("mythicalmod", s)] = item!!
        return item
    }

    private fun RadarItem(tab: QuiltItemSettings?, s: String): RadarItem {
        val item = tab?.let { RadarItem(it) }
        ITEMS[ResourceLocation("mythicalmod", s)] = item!!
        return item
    }

    private fun BlockItem(block: Block, tab: Item.Properties?, s: String): BlockItem {
        val blockItem = tab?.let { BlockItem(block, it) }
        ITEMS[ResourceLocation("mythicalmod", s)] = blockItem!!
        return blockItem
    }

    private fun LandmarkItem(block: Block, tab: Item.Properties?, s: String, type: ElementalType, structure: Supplier<out MultiblockStructure>): MultiblockItem {
        val blockItem = tab?.let { LandmarkItem(block, it, type, structure) }
        ITEMS[ResourceLocation("mythicalmod", s)] = blockItem!!
        return blockItem
    }

    private fun CramomaticItem(block: Block, tab: Item.Properties?, s: String): CramomaticItem {
        val blockItem = tab?.let { CramomaticItem(block, it) }
        ITEMS[ResourceLocation("mythicalmod", s)] = blockItem!!
        return blockItem
    }

    var NORMAL_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.NORMAL_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "normal_landmark",
        ElementalTypes.NORMAL,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var ELECTRIC_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.ELECTRIC_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "electric_landmark",
        ElementalTypes.ELECTRIC,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var DRAGON_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.DRAGON_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dragon_landmark",
        ElementalTypes.DRAGON,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var FIRE_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.FIRE_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "fire_landmark",
        ElementalTypes.FIRE,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var WATER_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.WATER_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "water_landmark",
        ElementalTypes.WATER,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var GRASS_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.GRASS_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "grass_landmark",
        ElementalTypes.GRASS,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var ICE_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.ICE_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ice_landmark",
        ElementalTypes.ICE,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var FIGHTING_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.FIGHTING_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "fighting_landmark",
        ElementalTypes.FIGHTING,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var POISON_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.POISON_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poison_landmark",
        ElementalTypes.POISON,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var GROUND_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.GROUND_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ground_landmark",
        ElementalTypes.GROUND,
        Supplier { LandmarkBlockEntity.STRUC }
    )

var FLYING_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.FLYING_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "flying_landmark",
        ElementalTypes.FLYING,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var PSYCHIC_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.PSYCHIC_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "psychic_landmark",
        ElementalTypes.PSYCHIC,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var BUG_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.BUG_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bug_landmark",
        ElementalTypes.BUG,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var ROCK_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.ROCK_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "rock_landmark",
        ElementalTypes.ROCK,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var GHOST_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.GHOST_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ghost_landmark",
        ElementalTypes.GHOST,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var DARK_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.DARK_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_landmark",
        ElementalTypes.DARK,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var STEEL_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.STEEL_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "steel_landmark",
        ElementalTypes.STEEL,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var FAIRY_LANDMARK: BlockItem = LandmarkItem(
        MythicalBlocks.FAIRY_LANDMARK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "fairy_landmark",
        ElementalTypes.FAIRY,
        Supplier { LandmarkBlockEntity.STRUC }
    )

    var CRAMOMATIC: CramomaticItem = CramomaticItem(
        MythicalBlocks.CRAMOMATIC,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cramomatic"
    )
    var DARK_MUD: BlockItem = BlockItem(
        MythicalBlocks.DARK_MUD,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_mud"
    )

    var DARK_MUD_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.DARK_MUD_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_mud_bricks"
    )
    var DARK_MUD_BRICKS_2: BlockItem = BlockItem(
        MythicalBlocks.DARK_MUD_BRICKS_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_mud_bricks_2"
    )
    var DRIED_DIRT: BlockItem = BlockItem(
        MythicalBlocks.DRIED_DIRT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dried_dirt"
    )
    var MOIST_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.MOIST_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "moist_bricks"
    )
    var MOIST_EARTH: BlockItem = BlockItem(
        MythicalBlocks.MOIST_EARTH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "moist_earth"
    )
    var MOIST_LAYERED_BRICK: BlockItem = BlockItem(
        MythicalBlocks.MOIST_LAYERED_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "moist_layered_brick"
    )
    var MUD: BlockItem =
        BlockItem(MythicalBlocks.MUD, Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS), "mud")
    var MUD_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.MUD_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mud_bricks"
    )
    var MUD_BRICKS_2: BlockItem = BlockItem(
        MythicalBlocks.MUD_BRICKS_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mud_bricks_2"
    )
    var POKEDIRT: BlockItem = BlockItem(
        MythicalBlocks.POKEDIRT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pokedirt"
    )
    var RICH_SOIL_1: BlockItem = BlockItem(
        MythicalBlocks.RICH_SOIL_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "rich_soil_1"
    )
    var RICH_SOIL_2: BlockItem = BlockItem(
        MythicalBlocks.RICH_SOIL_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "rich_soil_2"
    )
    var RICH_SOIL_3: BlockItem = BlockItem(
        MythicalBlocks.RICH_SOIL_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "rich_soil_3"
    )
    var RICH_SOIL_4: BlockItem = BlockItem(
        MythicalBlocks.RICH_SOIL_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "rich_soil_4"
    )
    var SMOOTH_MOIST_DIRT: BlockItem = BlockItem(
        MythicalBlocks.SMOOTH_MOIST_DIRT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "smooth_moist_dirt"
    )
    var SMOOTH_MOIST_DIRT_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.SMOOTH_MOIST_DIRT_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "smooth_moist_dirt_bricks"
    )
    var SMOOTH_MOIST_DIRT_BRICKS_2: BlockItem = BlockItem(
        MythicalBlocks.SMOOTH_MOIST_DIRT_BRICKS_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "smooth_moist_dirt_bricks_2"
    )
    var SUNBURNT_DIRT: BlockItem = BlockItem(
        MythicalBlocks.SUNBURNT_DIRT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunburnt_dirt"
    )
    var EARTH: BlockItem = BlockItem(
        MythicalBlocks.EARTH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "earth"
    )
    var BLACK_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.BLACK_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "black_shingles"
    )
    var BLUE_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.BLUE_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "blue_shingles"
    )
    var BROWN_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.BROWN_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "brown_shingles"
    )
    var CYAN_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.CYAN_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cyan_shingles"
    )
    var GRAY_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.GRAY_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gray_shingles"
    )
    var GREEN_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.GREEN_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "green_shingles"
    )
    var LIGHT_BLUE_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_BLUE_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_blue_shingles"
    )
    var LIGHT_GRAY_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_GRAY_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_gray_shingles"
    )
    var LIME_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.LIME_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "lime_shingles"
    )
    var MAGENTA_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.MAGENTA_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "magenta_shingles"
    )
    var ORANGE_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.ORANGE_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "orange_shingles"
    )
    var PINK_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.PINK_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pink_shingles"
    )
    var PURPLE_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.PURPLE_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "purple_shingles"
    )
    var RED_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.RED_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "red_shingles"
    )
    var WHITE_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.WHITE_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "white_shingles"
    )
    var YELLOW_SHINGLES: BlockItem = BlockItem(
        MythicalBlocks.YELLOW_SHINGLES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "yellow_shingles"
    )
    var BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks"
    )
    var BRICKS_PATH_1: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_PATH_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_path_1"
    )
    var BRICKS_PATH_2: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_PATH_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_path_2"
    )
    var BRICKS_PATH_3: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_PATH_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_path_3"
    )
    var BRICKS_PATH_4: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_PATH_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_path_4"
    )
    var BRICKS_PATH_MOSSY_1: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_PATH_MOSSY_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_path_mossy_1"
    )
    var BRICKS_PATH_MOSSY_2: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_PATH_MOSSY_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_path_mossy_2"
    )
    var BRICKS_PATH_MOSSY_3: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_PATH_MOSSY_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_path_mossy_3"
    )
    var BRICKS_VARIANT: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_VARIANT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_variant"
    )
    var BRICKS_VARIANT_INSET: BlockItem = BlockItem(
        MythicalBlocks.BRICKS_VARIANT_INSET,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bricks_variant_inset"
    )
    var MOSSY_BRICK: BlockItem = BlockItem(
        MythicalBlocks.MOSSY_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mossy_brick"
    )
    var SLATE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.SLATE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "slate_bricks"
    )
    var SLATE_BRICKS_LARGE: BlockItem = BlockItem(
        MythicalBlocks.SLATE_BRICKS_LARGE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "slate_bricks_large"
    )
    var SLATE_CHISELED: BlockItem = BlockItem(
        MythicalBlocks.SLATE_CHISELED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "slate_chiseled"
    )
    var SLATE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.SLATE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "slate_polished"
    )
    var SLATE_RAW: BlockItem = BlockItem(
        MythicalBlocks.SLATE_RAW,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "slate_raw"
    )
    var STRATA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.STRATA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "strata_bricks"
    )
    var STRATA_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.STRATA_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "strata_polished"
    )
    var STRATA_RAW: BlockItem = BlockItem(
        MythicalBlocks.STRATA_RAW,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "strata_raw"
    )
    var STRATA_RAW_LINES: BlockItem = BlockItem(
        MythicalBlocks.STRATA_RAW_LINES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "strata_raw_lines"
    )
    var BAYMOSS: BlockItem = BlockItem(
        MythicalBlocks.BAYMOSS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "baymoss"
    )
    var BLACK_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BLACK_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "black_bricks"
    )
    var BLACK_BRICKS2: BlockItem = BlockItem(
        MythicalBlocks.BLACK_BRICKS2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "black_bricks2"
    )
    var CASTLE_BRICK: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_brick"
    )
    var CASTLE_BLOCK: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_BLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_block"
    )
    var CASTLE_BLOCK_CRACKED: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_BLOCK_CRACKED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_block_cracked"
    )
    var CASTLE_BRICK_2: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_BRICK_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_brick_2"
    )
    var CASTLE_FLOOR: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_FLOOR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_floor"
    )
    var CASTLE_GREY: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_GREY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_grey"
    )
    var CASTLE_GREY_2: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_GREY_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_grey_2"
    )
    var CASTLE_WALL: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_WALL,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_wall"
    )
    var CASTLE_WALL_2: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_WALL_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_wall_2"
    )
    var CASTLE_WALL_3: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_WALL_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_wall_3"
    )
    var CASTLE_WALL_4: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_WALL_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_wall_4"
    )
    var CASTLE_WHITE: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_WHITE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_white"
    )
    var CASTLE_WHITE_2: BlockItem = BlockItem(
        MythicalBlocks.CASTLE_WHITE_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "castle_white_2"
    )
    var ELEVATOR_BLACK: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_BLACK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_black"
    )
    var ELEVATOR_BLUE: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_BLUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_blue"
    )
    var ELEVATOR_BROWN: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_BROWN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_brown"
    )
    var ELEVATOR_CYAN: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_CYAN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_cyan"
    )
    var ELEVATOR_DARK_GRAY: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_DARK_GRAY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_dark_gray"
    )
    var ELEVATOR_GRAY: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_GRAY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_gray"
    )
    var ELEVATOR_GREEN: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_GREEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_green"
    )
    var ELEVATOR_LIGHT_BLUE: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_LIGHT_BLUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_light_blue"
    )
    var ELEVATOR_LIGHT_GRAY: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_LIGHT_GRAY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_light_gray"
    )
    var ELEVATOR_LIME: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_LIME,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_lime"
    )
    var ELEVATOR_MAGENTA: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_MAGENTA,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_magenta"
    )
    var ELEVATOR_ORANGE: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_ORANGE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_orange"
    )
    var ELEVATOR_PINK: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_PINK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_pink"
    )
    var ELEVATOR_PURPLE: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_PURPLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_purple"
    )
    var ELEVATOR_RED: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_RED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_red"
    )
    var ELEVATOR_WHITE: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_WHITE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_white"
    )
    var ELEVATOR_YELLOW: BlockItem = BlockItem(
        MythicalBlocks.ELEVATOR_YELLOW,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "elevator_yellow"
    )
    var FROSTED_COOL_STONE: BlockItem = BlockItem(
        MythicalBlocks.FROSTED_COOL_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "frosted_cool_stone"
    )
    var GHOST_BRICK: BlockItem = BlockItem(
        MythicalBlocks.GHOST_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ghost_brick"
    )
    var GHOST_OBELISK: BlockItem = BlockItem(
        MythicalBlocks.GHOST_OBELISK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ghost_obelisk"
    )
    var GHOST_PLANK: BlockItem = BlockItem(
        MythicalBlocks.GHOST_PLANK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ghost_plank"
    )
    var HAUNTED_STEEL_BLOCK: BlockItem = BlockItem(
        MythicalBlocks.HAUNTED_STEEL_BLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "haunted_steel_block"
    )
    var HOUSE_FLOOR_1: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_1"
    )
    var HOUSE_FLOOR_2: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_2"
    )
    var HOUSE_FLOOR_3: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_3"
    )
    var HOUSE_FLOOR_4: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_4"
    )
    var HOUSE_FLOOR_5: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_5,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_5"
    )
    var HOUSE_FLOOR_6: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_6,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_6"
    )
    var HOUSE_FLOOR_7: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_7,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_7"
    )
    var HOUSE_FLOOR_8: BlockItem = BlockItem(
        MythicalBlocks.HOUSE_FLOOR_8,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "house_floor_8"
    )
    var ICE_STONE_BLOCK: BlockItem = BlockItem(
        MythicalBlocks.ICE_STONE_BLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ice_stone_block"
    )
    var ICE_BRICK: BlockItem = BlockItem(
        MythicalBlocks.ICE_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ice_brick"
    )
    var INSIDE_WALL_BOTTOM: BlockItem = BlockItem(
        MythicalBlocks.INSIDE_WALL_BOTTOM,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "inside_wall_bottom"
    )
    var INSIDE_WALL_MIDDLE: BlockItem = BlockItem(
        MythicalBlocks.INSIDE_WALL_MIDDLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "inside_wall_middle"
    )
    var INSIDE_WALL_TOP: BlockItem = BlockItem(
        MythicalBlocks.INSIDE_WALL_TOP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "inside_wall_top"
    )
    var INSIDE_WALL: BlockItem = BlockItem(
        MythicalBlocks.INSIDE_WALL,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "inside_wall"
    )
    var INSIDE_WALL_MOLDING: BlockItem = BlockItem(
        MythicalBlocks.INSIDE_WALL_MOLDING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "inside_wall_molding"
    )
    var MARBLE_BLACK: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_BLACK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_black"
    )
    var MARBLE_BLUE: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_BLUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_blue"
    )
    var MARBLE_BROWN: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_BROWN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_brown"
    )
    var MARBLE_CYAN: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_CYAN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_cyan"
    )
    var MARBLE_GRAY: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_GRAY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_gray"
    )
    var MARBLE_GREEN: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_GREEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_green"
    )
    var MARBLE_LIGHT_BLUE: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_LIGHT_BLUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_light_blue"
    )
    var MARBLE_LIME: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_LIME,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_lime"
    )
    var MARBLE_MAGENTA: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_MAGENTA,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_magenta"
    )
    var MARBLE_ORANGE: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_ORANGE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_orange"
    )
    var MARBLE_PINK: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_PINK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_pink"
    )
    var MARBLE_PURPLE: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_PURPLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_purple"
    )
    var MARBLE_RED: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_RED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_red"
    )
    var MARBLE_SILVER: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_SILVER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_silver"
    )
    var MARBLE_WHITE: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_WHITE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_white"
    )
    var MARBLE_YELLOW: BlockItem = BlockItem(
        MythicalBlocks.MARBLE_YELLOW,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "marble_yellow"
    )
    var OUTSIDE_WALL: BlockItem = BlockItem(
        MythicalBlocks.OUTSIDE_WALL,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "outside_wall"
    )
    var POKE_BRICK_BLACK: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_BLACK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_black"
    )
    var POKE_BRICK_BLUE: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_BLUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_blue"
    )
    var POKE_BRICK_BROWN: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_BROWN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_brown"
    )
    var POKE_BRICK_CYAN: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_CYAN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_cyan"
    )
    var POKE_BRICK_GRAY: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_GRAY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_gray"
    )
    var POKE_BRICK_GREEN: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_GREEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_green"
    )
    var POKE_BRICK_LIGHT_BLUE: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_LIGHT_BLUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_light_blue"
    )
    var POKE_BRICK_LIME: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_LIME,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_lime"
    )
    var POKE_BRICK_MAGENTA: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_MAGENTA,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_magenta"
    )
    var POKE_BRICK_ORANGE: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_ORANGE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_orange"
    )
    var POKE_BRICK_PINK: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_PINK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_pink"
    )
    var POKE_BRICK_PURPLE: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_PURPLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_purple"
    )
    var POKE_BRICK_RED: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_RED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_red"
    )
    var POKE_BRICK_SILVER: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_SILVER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_silver"
    )
    var POKE_BRICK_WHITE: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_WHITE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_white"
    )
    var POKE_BRICK_YELLOW: BlockItem = BlockItem(
        MythicalBlocks.POKE_BRICK_YELLOW,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "poke_brick_yellow"
    )
    var POKEGEM_BLOCK: BlockItem = BlockItem(
        MythicalBlocks.POKEGEM_BLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pokegem_block"
    )
    var POKEGEM_ORE: BlockItem = BlockItem(
        MythicalBlocks.POKEGEM_ORE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pokegem_ore"
    )
    var TEMPLE_BLOCK: BlockItem = BlockItem(
        MythicalBlocks.TEMPLE_BLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "temple_block"
    )
    var TEMPLE_BRICK: BlockItem = BlockItem(
        MythicalBlocks.TEMPLE_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "temple_brick"
    )
    var CARVED_RAVAGESTONE: BlockItem = BlockItem(
        MythicalBlocks.CARVED_RAVAGESTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_ravagestone"
    )
    var CHISELED_RED_SANDSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.CHISELED_RED_SANDSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "chiseled_red_sandstone_bricks"
    )
    var CLAYSTONE: BlockItem = BlockItem(
        MythicalBlocks.CLAYSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "claystone"
    )
    var CLAYSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.CLAYSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "claystone_bricks"
    )
    var CLAYSTONE_TILES: BlockItem = BlockItem(
        MythicalBlocks.CLAYSTONE_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "claystone_tiles"
    )
    var CRACKED_RED_SANDSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.CRACKED_RED_SANDSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cracked_red_sandstone_bricks"
    )
    var END_STONE_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.END_STONE_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "end_stone_diamond_pavers"
    )
    var END_STONE_TILED: BlockItem = BlockItem(
        MythicalBlocks.END_STONE_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "end_stone_tiled"
    )
    var GILDED_RED_SANDSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.GILDED_RED_SANDSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gilded_red_sandstone_bricks"
    )
    var NETHER_BRICK_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.NETHER_BRICK_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "nether_brick_diamond_pavers"
    )
    var NETHERRACK_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.NETHERRACK_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "netherrack_diamond_pavers"
    )
    var NETHERRACK_TILED: BlockItem = BlockItem(
        MythicalBlocks.NETHERRACK_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "netherrack_tiled"
    )
    var OBSIDIAN_DIAMOND_PAVER: BlockItem = BlockItem(
        MythicalBlocks.OBSIDIAN_DIAMOND_PAVER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "obsidian_diamond_paver"
    )
    var OBSIDIAN_TILED: BlockItem = BlockItem(
        MythicalBlocks.OBSIDIAN_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "obsidian_tiled"
    )
    var POLISHED_CLAYSTONE: BlockItem = BlockItem(
        MythicalBlocks.POLISHED_CLAYSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "polished_claystone"
    )
    var POLISHED_RAVAGESTONE: BlockItem = BlockItem(
        MythicalBlocks.POLISHED_RAVAGESTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "polished_ravagestone"
    )
    var PURPUR_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.PURPUR_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "purpur_diamond_pavers"
    )
    var QUARTZ_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.QUARTZ_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "quartz_diamond_pavers"
    )
    var RAVAGED_METAL_BLOCK: BlockItem = BlockItem(
        MythicalBlocks.RAVAGED_METAL_BLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ravaged_metal_block"
    )
    var RAVAGESTONE: BlockItem = BlockItem(
        MythicalBlocks.RAVAGESTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ravagestone"
    )
    var RAVAGESTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.RAVAGESTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ravagestone_bricks"
    )
    var RAVAGESTONE_TILES: BlockItem = BlockItem(
        MythicalBlocks.RAVAGESTONE_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ravagestone_tiles"
    )
    var RED_SANDSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.RED_SANDSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "red_sandstone_bricks"
    )
    var COOL_STONE_GRASS: BlockItem = BlockItem(
        MythicalBlocks.COOL_STONE_GRASS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cool_stone_grass"
    )
    var DRIED_DIRT_GRASS: BlockItem = BlockItem(
        MythicalBlocks.DRIED_DIRT_GRASS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dried_dirt_grass"
    )
    var GHOST_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.GHOST_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ghost_pillar"
    )
    var ICE_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.ICE_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "ice_pillar"
    )
    var MOSSY_BAYSTONE: BlockItem = BlockItem(
        MythicalBlocks.MOSSY_BAYSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mossy_baystone"
    )
    var STONY_GRASS: BlockItem = BlockItem(
        MythicalBlocks.STONY_GRASS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stony_grass"
    )
    var SUNBURNT_SAND: BlockItem = BlockItem(
        MythicalBlocks.SUNBURNT_SAND,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunburnt_sand"
    )
    var SUNBURNT_SANDSTONE: BlockItem = BlockItem(
        MythicalBlocks.SUNBURNT_SANDSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunburnt_sandstone"
    )
    var SUNBURNT_SANDSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.SUNBURNT_SANDSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunburnt_sandstone_bricks"
    )
    var SUNBURNT_SANDSTONE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.SUNBURNT_SANDSTONE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunburnt_sandstone_polished"
    )
    var SUNNY_SAND: BlockItem = BlockItem(
        MythicalBlocks.SUNNY_SAND,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunny_sand"
    )
    var SUNNY_SANDSTONE: BlockItem = BlockItem(
        MythicalBlocks.SUNNY_SANDSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunny_sandstone"
    )
    var SUNNY_SANDSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.SUNNY_SANDSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunny_sandstone_bricks"
    )
    var SUNNY_SANDSTONE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.SUNNY_SANDSTONE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunny_sandstone_polished"
    )
    var SUNNY_SAND_GRASS: BlockItem = BlockItem(
        MythicalBlocks.SUNNY_SAND_GRASS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "sunny_sand_grass"
    )
    var BAYSTONE: BlockItem = BlockItem(
        MythicalBlocks.BAYSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "baystone"
    )
    var BAYSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BAYSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "baystone_bricks"
    )
    var POLISHED_BAYSTONE: BlockItem = BlockItem(
        MythicalBlocks.POLISHED_BAYSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "polished_baystone"
    )
    var TILED_BAYSTONE: BlockItem = BlockItem(
        MythicalBlocks.TILED_BAYSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "tiled_baystone"
    )
    var KYANITE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.KYANITE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "kyanite_bricks"
    )
    var KYANITE_CHISELED: BlockItem = BlockItem(
        MythicalBlocks.KYANITE_CHISELED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "kyanite_chiseled"
    )
    var KYANITE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.KYANITE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "kyanite_polished"
    )
    var KYANITE_RAW: BlockItem = BlockItem(
        MythicalBlocks.KYANITE_RAW,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "kyanite_raw"
    )
    var MOSSY_BEACH_STONE: BlockItem = BlockItem(
        MythicalBlocks.MOSSY_BEACH_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mossy_beach_stone"
    )
    var MOSSY_BEACH_STONE_COBBLE: BlockItem = BlockItem(
        MythicalBlocks.MOSSY_BEACH_STONE_COBBLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mossy_beach_stone_cobble"
    )
    var BLEACH_STONE: BlockItem = BlockItem(
        MythicalBlocks.BLEACH_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bleach_stone"
    )
    var BLEACH_STONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BLEACH_STONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bleach_stone_bricks"
    )
    var BLEACH_STONE_LAYERED_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BLEACH_STONE_LAYERED_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bleach_stone_layered_bricks"
    )
    var BLEACH_STONE_TILED_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BLEACH_STONE_TILED_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bleach_stone_tiled_bricks"
    )
    var BLEACHED_STONE_CRUMBLED: BlockItem = BlockItem(
        MythicalBlocks.BLEACHED_STONE_CRUMBLED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "bleached_stone_crumbled"
    )
    var CARVED_GLOOMSTONE: BlockItem = BlockItem(
        MythicalBlocks.CARVED_GLOOMSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_gloomstone"
    )
    var COBBLE_RUINS_1: BlockItem = BlockItem(
        MythicalBlocks.COBBLE_RUINS_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cobble_ruins_1"
    )
    var COBBLE_RUINS_2: BlockItem = BlockItem(
        MythicalBlocks.COBBLE_RUINS_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cobble_ruins_2"
    )
    var COBBLE_RUINS_3: BlockItem = BlockItem(
        MythicalBlocks.COBBLE_RUINS_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cobble_ruins_3"
    )
    var COBBLE_RUINS_4: BlockItem = BlockItem(
        MythicalBlocks.COBBLE_RUINS_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cobble_ruins_4"
    )
    var COOL_STONE: BlockItem = BlockItem(
        MythicalBlocks.COOL_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cool_stone"
    )
    var COOL_STONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.COOL_STONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cool_stone_bricks"
    )
    var COOL_STONE_LAYERED_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.COOL_STONE_LAYERED_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cool_stone_layered_bricks"
    )
    var COOL_STONE_TILED_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.COOL_STONE_TILED_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cool_stone_tiled_bricks"
    )
    var GLOOMSTONE: BlockItem = BlockItem(
        MythicalBlocks.GLOOMSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gloomstone"
    )
    var GLOOMSTONE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.GLOOMSTONE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gloomstone_bricks"
    )
    var GLOOMSTONE_TILES: BlockItem = BlockItem(
        MythicalBlocks.GLOOMSTONE_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gloomstone_tiles"
    )
    var LAYERED_STONE: BlockItem = BlockItem(
        MythicalBlocks.LAYERED_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "layered_stone"
    )
    var POLISHED_BLEACH_STONE: BlockItem = BlockItem(
        MythicalBlocks.POLISHED_BLEACH_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "polished_bleach_stone"
    )
    var POLISHED_COOL_STONE: BlockItem = BlockItem(
        MythicalBlocks.POLISHED_COOL_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "polished_cool_stone"
    )
    var POLISHED_GLOOMSTONE: BlockItem = BlockItem(
        MythicalBlocks.POLISHED_GLOOMSTONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "polished_gloomstone"
    )
    var ANDESITE_CHISELED: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_CHISELED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_chiseled"
    )
    var ANDESITE_COBBLED: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_COBBLED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_cobbled"
    )
    var ANDESITE_COBBLED_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_COBBLED_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_cobbled_mossy"
    )
    var ANDESITE_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_diamond_pavers"
    )
    var ANDESITE_LARGE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_LARGE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_large_bricks"
    )
    var ANDESITE_LARGE_BRICKS_CRACKED: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_LARGE_BRICKS_CRACKED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_large_bricks_cracked"
    )
    var ANDESITE_LARGE_BRICKS_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_LARGE_BRICKS_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_large_bricks_mossy"
    )
    var ANDESITE_PANELS: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_PANELS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_panels"
    )
    var ANDESITE_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_pavers"
    )
    var ANDESITE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_polished"
    )
    var ANDESITE_SMALL_BRICK: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_SMALL_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_small_brick"
    )
    var ANDESITE_TILED: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_tiled"
    )
    var ANDESITE_CARVED_CREEPER: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_CARVED_CREEPER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_carved_creeper"
    )
    var ANDESITE_CARVED_DERP: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_CARVED_DERP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_carved_derp"
    )
    var ANDESITE_CARVED_VILLAGER: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_CARVED_VILLAGER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_carved_villager"
    )
    var ANDESITE_CARVED_WITHER: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_CARVED_WITHER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_carved_wither"
    )
    var ANDESITE_CARVED_WRITING: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_CARVED_WRITING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_carved_writing"
    )
    var ANDESITE_COLUMN: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_COLUMN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_column"
    )
    var ANDESITE_CUT: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_cut"
    )
    var ANDESITE_ENGRAVED: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_ENGRAVED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_engraved"
    )
    var ANDESITE_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_pillar"
    )
    var ANDESITE_ROUGH_CUT: BlockItem = BlockItem(
        MythicalBlocks.ANDESITE_ROUGH_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "andesite_rough_cut"
    )
    var DARK_PRISMARINE_CARVED_CREEPER: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_CARVED_CREEPER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_carved_creeper"
    )
    var DARK_PRISMARINE_CARVED_DERP: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_CARVED_DERP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_carved_derp"
    )
    var DARK_PRISMARINE_CARVED_VILLAGER: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_CARVED_VILLAGER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_carved_villager"
    )
    var DARK_PRISMARINE_CARVED_WITHER: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_CARVED_WITHER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_carved_wither"
    )
    var DARK_PRISMARINE_CARVED_WRITING: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_CARVED_WRITING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_carved_writing"
    )
    var DARK_PRISMARINE_CHISELED: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_CHISELED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_chiseled"
    )
    var DARK_PRISMARINE_COBBLED: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_COBBLED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_cobbled"
    )
    var DARK_PRISMARINE_COBBLED_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_COBBLED_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_cobbled_mossy"
    )
    var DARK_PRISMARINE_COLUMN: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_COLUMN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_column"
    )
    var DARK_PRISMARINE_CUT: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_cut"
    )
    var DARK_PRISMARINE_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_diamond_pavers"
    )
    var DARK_PRISMARINE_ENGRAVED: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_ENGRAVED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_engraved"
    )
    var DARK_PRISMARINE_LARGE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_LARGE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_large_bricks"
    )
    var DARK_PRISMARINE_LARGE_BRICKS_CRACKED: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_LARGE_BRICKS_CRACKED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_large_bricks_cracked"
    )
    var DARK_PRISMARINE_LARGE_BRICKS_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_LARGE_BRICKS_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_large_bricks_mossy"
    )
    var DARK_PRISMARINE_PANELS: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_PANELS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_panels"
    )
    var DARK_PRISMARINE_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_pavers"
    )
    var DARK_PRISMARINE_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_pillar"
    )
    var DARK_PRISMARINE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_polished"
    )
    var DARK_PRISMARINE_ROUGH_CUT: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_ROUGH_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_rough_cut"
    )
    var DARK_PRISMARINE_SMALL_BRICK: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_SMALL_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_small_brick"
    )
    var DARK_PRISMARINE_TILED: BlockItem = BlockItem(
        MythicalBlocks.DARK_PRISMARINE_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "dark_prismarine_tiled"
    )
    var DIORITE_CARVED_CREEPER: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_CARVED_CREEPER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_carved_creeper"
    )
    var DIORITE_CARVED_DERP: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_CARVED_DERP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_carved_derp"
    )
    var DIORITE_CARVED_VILLAGER: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_CARVED_VILLAGER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_carved_villager"
    )
    var DIORITE_CARVED_WITHER: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_CARVED_WITHER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_carved_wither"
    )
    var DIORITE_CARVED_WRITING: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_CARVED_WRITING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_carved_writing"
    )
    var DIORITE_CHISELED: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_CHISELED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_chiseled"
    )
    var DIORITE_COBBLED: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_COBBLED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_cobbled"
    )
    var DIORITE_COBBLED_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_COBBLED_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_cobbled_mossy"
    )
    var DIORITE_COLUMN: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_COLUMN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_column"
    )
    var DIORITE_CUT: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_cut"
    )
    var DIORITE_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_diamond_pavers"
    )
    var DIORITE_ENGRAVED: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_ENGRAVED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_engraved"
    )
    var DIORITE_LARGE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_LARGE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_large_bricks"
    )
    var DIORITE_LARGE_BRICKS_CRACKED: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_LARGE_BRICKS_CRACKED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_large_bricks_cracked"
    )
    var DIORITE_LARGE_BRICKS_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_LARGE_BRICKS_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_large_bricks_mossy"
    )
    var DIORITE_PANELS: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_PANELS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_panels"
    )
    var DIORITE_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_pavers"
    )
    var DIORITE_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_pillar"
    )
    var DIORITE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_polished"
    )
    var DIORITE_ROUGH_CUT: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_ROUGH_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_rough_cut"
    )
    var DIORITE_SMALL_BRICK: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_SMALL_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_small_brick"
    )
    var DIORITE_TILED: BlockItem = BlockItem(
        MythicalBlocks.DIORITE_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "diorite_tiled"
    )
    var GRANITE_CARVED_CREEPER: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_CARVED_CREEPER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_carved_creeper"
    )
    var GRANITE_CARVED_DERP: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_CARVED_DERP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_carved_derp"
    )
    var GRANITE_CARVED_VILLAGER: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_CARVED_VILLAGER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_carved_villager"
    )
    var GRANITE_CARVED_WITHER: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_CARVED_WITHER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_carved_wither"
    )
    var GRANITE_CARVED_WRITING: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_CARVED_WRITING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_carved_writing"
    )
    var GRANITE_CHISELED: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_CHISELED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_chiseled"
    )
    var GRANITE_COBBLED: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_COBBLED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_cobbled"
    )
    var GRANITE_COBBLED_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_COBBLED_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_cobbled_mossy"
    )
    var GRANITE_COLUMN: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_COLUMN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_column"
    )
    var GRANITE_CUT: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_cut"
    )
    var GRANITE_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_diamond_pavers"
    )
    var GRANITE_ENGRAVED: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_ENGRAVED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_engraved"
    )
    var GRANITE_LARGE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_LARGE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_large_bricks"
    )
    var GRANITE_LARGE_BRICKS_CRACKED: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_LARGE_BRICKS_CRACKED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_large_bricks_cracked"
    )
    var GRANITE_LARGE_BRICKS_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_LARGE_BRICKS_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_large_bricks_mossy"
    )
    var GRANITE_PANELS: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_PANELS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_panels"
    )
    var GRANITE_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_pavers"
    )
    var GRANITE_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_pillar"
    )
    var GRANITE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_polished"
    )
    var GRANITE_ROUGH_CUT: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_ROUGH_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_rough_cut"
    )
    var GRANITE_SMALL_BRICK: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_SMALL_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_small_brick"
    )
    var GRANITE_TILED: BlockItem = BlockItem(
        MythicalBlocks.GRANITE_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "granite_tiled"
    )
    var PRISMARINE_CARVED_CREEPER: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_CARVED_CREEPER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_carved_creeper"
    )
    var PRISMARINE_CARVED_DERP: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_CARVED_DERP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_carved_derp"
    )
    var PRISMARINE_CARVED_VILLAGER: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_CARVED_VILLAGER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_carved_villager"
    )
    var PRISMARINE_CARVED_WITHER: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_CARVED_WITHER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_carved_wither"
    )
    var PRISMARINE_CARVED_WRITING: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_CARVED_WRITING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_carved_writing"
    )
    var PRISMARINE_CHISELED: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_CHISELED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_chiseled"
    )
    var PRISMARINE_COBBLED: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_COBBLED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_cobbled"
    )
    var PRISMARINE_COBBLED_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_COBBLED_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_cobbled_mossy"
    )
    var PRISMARINE_COLUMN: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_COLUMN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_column"
    )
    var PRISMARINE_CUT: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_cut"
    )
    var PRISMARINE_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_diamond_pavers"
    )
    var PRISMARINE_ENGRAVED: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_ENGRAVED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_engraved"
    )
    var PRISMARINE_LARGE_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_LARGE_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_large_bricks"
    )
    var PRISMARINE_LARGE_BRICKS_CRACKED: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_LARGE_BRICKS_CRACKED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_large_bricks_cracked"
    )
    var PRISMARINE_LARGE_BRICKS_MOSSY: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_LARGE_BRICKS_MOSSY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_large_bricks_mossy"
    )
    var PRISMARINE_PANELS: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_PANELS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_panels"
    )
    var PRISMARINE_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_pavers"
    )
    var PRISMARINE_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_pillar"
    )
    var PRISMARINE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_polished"
    )
    var PRISMARINE_ROUGH_CUT: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_ROUGH_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_rough_cut"
    )
    var PRISMARINE_SMALL_BRICK: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_SMALL_BRICK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_small_brick"
    )
    var PRISMARINE_TILED: BlockItem = BlockItem(
        MythicalBlocks.PRISMARINE_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "prismarine_tiled"
    )
    var STONE_CARVED_CREEPER: BlockItem = BlockItem(
        MythicalBlocks.STONE_CARVED_CREEPER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_carved_creeper"
    )
    var STONE_CARVED_DERP: BlockItem = BlockItem(
        MythicalBlocks.STONE_CARVED_DERP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_carved_derp"
    )
    var STONE_CARVED_VILLAGER: BlockItem = BlockItem(
        MythicalBlocks.STONE_CARVED_VILLAGER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_carved_villager"
    )
    var STONE_CARVED_WITHER: BlockItem = BlockItem(
        MythicalBlocks.STONE_CARVED_WITHER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_carved_wither"
    )
    var STONE_CARVED_WRITING: BlockItem = BlockItem(
        MythicalBlocks.STONE_CARVED_WRITING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_carved_writing"
    )
    var STONE_COLUMN: BlockItem = BlockItem(
        MythicalBlocks.STONE_COLUMN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_column"
    )
    var STONE_CUT: BlockItem = BlockItem(
        MythicalBlocks.STONE_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_cut"
    )
    var STONE_DIAMOND_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.STONE_DIAMOND_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_diamond_pavers"
    )
    var STONE_ENGRAVED: BlockItem = BlockItem(
        MythicalBlocks.STONE_ENGRAVED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_engraved"
    )
    var STONE_PANELS: BlockItem = BlockItem(
        MythicalBlocks.STONE_PANELS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_panels"
    )
    var STONE_PAVERS: BlockItem = BlockItem(
        MythicalBlocks.STONE_PAVERS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_pavers"
    )
    var STONE_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.STONE_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_pillar"
    )
    var STONE_POLISHED: BlockItem = BlockItem(
        MythicalBlocks.STONE_POLISHED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_polished"
    )
    var STONE_ROUGH_CUT: BlockItem = BlockItem(
        MythicalBlocks.STONE_ROUGH_CUT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_rough_cut"
    )
    var STONE_TILED: BlockItem = BlockItem(
        MythicalBlocks.STONE_TILED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "stone_tiled"
    )
    var BLACK_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BLACK_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "black_terracotta_bricks"
    )
    var BLACK_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.BLACK_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "black_terracotta_pavement"
    )
    var BLACK_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.BLACK_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "black_terracotta_roof_tiles"
    )
    var BLACK_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.BLACK_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "black_terracotta_tiles"
    )
    var BLUE_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BLUE_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "blue_terracotta_bricks"
    )
    var BLUE_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.BLUE_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "blue_terracotta_pavement"
    )
    var BLUE_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.BLUE_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "blue_terracotta_roof_tiles"
    )
    var BLUE_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.BLUE_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "blue_terracotta_tiles"
    )
    var BROWN_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.BROWN_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "brown_terracotta_bricks"
    )
    var BROWN_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.BROWN_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "brown_terracotta_pavement"
    )
    var BROWN_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.BROWN_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "brown_terracotta_roof_tiles"
    )
    var BROWN_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.BROWN_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "brown_terracotta_tiles"
    )
    var CYAN_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.CYAN_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cyan_terracotta_bricks"
    )
    var CYAN_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.CYAN_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cyan_terracotta_pavement"
    )
    var CYAN_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.CYAN_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cyan_terracotta_roof_tiles"
    )
    var CYAN_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.CYAN_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "cyan_terracotta_tiles"
    )
    var GRAY_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.GRAY_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gray_terracotta_bricks"
    )
    var GRAY_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.GRAY_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gray_terracotta_pavement"
    )
    var GRAY_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.GRAY_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gray_terracotta_roof_tiles"
    )
    var GRAY_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.GRAY_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "gray_terracotta_tiles"
    )
    var GREEN_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.GREEN_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "green_terracotta_bricks"
    )
    var GREEN_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.GREEN_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "green_terracotta_pavement"
    )
    var GREEN_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.GREEN_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "green_terracotta_roof_tiles"
    )
    var GREEN_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.GREEN_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "green_terracotta_tiles"
    )
    var LIGHT_BLUE_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_blue_terracotta_bricks"
    )
    var LIGHT_BLUE_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_BLUE_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_blue_terracotta_pavement"
    )
    var LIGHT_BLUE_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_BLUE_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_blue_terracotta_roof_tiles"
    )
    var LIGHT_BLUE_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_BLUE_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_blue_terracotta_tiles"
    )
    var LIGHT_GRAY_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_gray_terracotta_bricks"
    )
    var LIGHT_GRAY_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_GRAY_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_gray_terracotta_pavement"
    )
    var LIGHT_GRAY_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_GRAY_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_gray_terracotta_roof_tiles"
    )
    var LIGHT_GRAY_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.LIGHT_GRAY_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "light_gray_terracotta_tiles"
    )
    var LIME_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.LIME_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "lime_terracotta_bricks"
    )
    var LIME_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.LIME_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "lime_terracotta_pavement"
    )
    var LIME_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.LIME_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "lime_terracotta_roof_tiles"
    )
    var LIME_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.LIME_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "lime_terracotta_tiles"
    )
    var MAGENTA_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.MAGENTA_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "magenta_terracotta_bricks"
    )
    var MAGENTA_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.MAGENTA_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "magenta_terracotta_pavement"
    )
    var MAGENTA_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.MAGENTA_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "magenta_terracotta_roof_tiles"
    )
    var MAGENTA_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.MAGENTA_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "magenta_terracotta_tiles"
    )
    var ORANGE_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.ORANGE_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "orange_terracotta_bricks"
    )
    var ORANGE_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.ORANGE_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "orange_terracotta_pavement"
    )
    var ORANGE_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.ORANGE_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "orange_terracotta_roof_tiles"
    )
    var ORANGE_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.ORANGE_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "orange_terracotta_tiles"
    )
    var PINK_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.PINK_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pink_terracotta_bricks"
    )
    var PINK_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.PINK_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pink_terracotta_pavement"
    )
    var PINK_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.PINK_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pink_terracotta_roof_tiles"
    )
    var PINK_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.PINK_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "pink_terracotta_tiles"
    )
    var PURPLE_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.PURPLE_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "purple_terracotta_bricks"
    )
    var PURPLE_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.PURPLE_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "purple_terracotta_pavement"
    )
    var PURPLE_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.PURPLE_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "purple_terracotta_roof_tiles"
    )
    var PURPLE_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.PURPLE_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "purple_terracotta_tiles"
    )
    var RED_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.RED_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "red_terracotta_bricks"
    )
    var RED_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.RED_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "red_terracotta_pavement"
    )
    var RED_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.RED_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "red_terracotta_roof_tiles"
    )
    var RED_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.RED_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "red_terracotta_tiles"
    )
    var TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "terracotta_bricks"
    )
    var TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "terracotta_pavement"
    )
    var TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "terracotta_roof_tiles"
    )
    var TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "terracotta_tiles"
    )
    var WHITE_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.WHITE_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "white_terracotta_bricks"
    )
    var WHITE_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.WHITE_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "white_terracotta_pavement"
    )
    var WHITE_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.WHITE_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "white_terracotta_roof_tiles"
    )
    var WHITE_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.WHITE_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "white_terracotta_tiles"
    )
    var YELLOW_TERRACOTTA_BRICKS: BlockItem = BlockItem(
        MythicalBlocks.YELLOW_TERRACOTTA_BRICKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "yellow_terracotta_bricks"
    )
    var YELLOW_TERRACOTTA_PAVEMENT: BlockItem = BlockItem(
        MythicalBlocks.YELLOW_TERRACOTTA_PAVEMENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "yellow_terracotta_pavement"
    )
    var YELLOW_TERRACOTTA_ROOF_TILES: BlockItem = BlockItem(
        MythicalBlocks.YELLOW_TERRACOTTA_ROOF_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "yellow_terracotta_roof_tiles"
    )
    var YELLOW_TERRACOTTA_TILES: BlockItem = BlockItem(
        MythicalBlocks.YELLOW_TERRACOTTA_TILES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "yellow_terracotta_tiles"
    )
    var ACACIA_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.ACACIA_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "acacia_braced_planks"
    )
    var ACACIA_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.ACACIA_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "acacia_supported_planks"
    )
    var BIRCH_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.BIRCH_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "birch_braced_planks"
    )
    var BIRCH_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.BIRCH_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "birch_supported_planks"
    )
    var CARVED_ACACIA_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_ACACIA_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_acacia_planks"
    )
    var CARVED_BIRCH_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_BIRCH_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_birch_planks"
    )
    var CARVED_CRIMSON_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_CRIMSON_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_crimson_planks"
    )
    var CARVED_DARK_OAK_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_DARK_OAK_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_dark_oak_planks"
    )
    var CARVED_JUNGLE_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_JUNGLE_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_jungle_planks"
    )
    var CARVED_OAK_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_OAK_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_oak_planks"
    )
    var CARVED_SPRUCE_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_SPRUCE_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_spruce_planks"
    )
    var CARVED_WARPED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CARVED_WARPED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "carved_warped_planks"
    )
    var CRIMSON_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CRIMSON_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "crimson_braced_planks"
    )
    var CRIMSON_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.CRIMSON_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "crimson_supported_planks"
    )
    var DARKOAK_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.DARKOAK_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "darkoak_braced_planks"
    )
    var DARKOAK_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.DARKOAK_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "darkoak_supported_planks"
    )
    var JUNGLE_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.JUNGLE_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "jungle_braced_planks"
    )
    var JUNGLE_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.JUNGLE_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "jungle_supported_planks"
    )
    var OAK_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.OAK_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "oak_braced_planks"
    )
    var OAK_CRATE_SIDE: BlockItem = BlockItem(
        MythicalBlocks.OAK_CRATE_SIDE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "oak_crate_side"
    )
    var OAK_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.OAK_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "oak_supported_planks"
    )
    var SPRUCE_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.SPRUCE_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "spruce_braced_planks"
    )
    var SPRUCE_CRATE_SIDE: BlockItem = BlockItem(
        MythicalBlocks.SPRUCE_CRATE_SIDE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "spruce_crate_side"
    )
    var SPRUCE_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.SPRUCE_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "spruce_supported_planks"
    )
    var VERTICAL_ACACIA_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_ACACIA_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_acacia_planks"
    )
    var VERTICAL_BIRCH_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_BIRCH_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_birch_planks"
    )
    var VERTICAL_CRIMSON_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_CRIMSON_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_crimson_planks"
    )
    var VERTICAL_DARK_OAK_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_DARK_OAK_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_dark_oak_planks"
    )
    var VERTICAL_JUNGLE_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_JUNGLE_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_jungle_planks"
    )
    var VERTICAL_OAK_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_OAK_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_oak_planks"
    )
    var VERTICAL_SPRUCE_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_SPRUCE_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_spruce_planks"
    )
    var VERTICAL_WARPED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.VERTICAL_WARPED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "vertical_warped_planks"
    )
    var WARPED_BRACED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.WARPED_BRACED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "warped_braced_planks"
    )
    var WARPED_SUPPORTED_PLANKS: BlockItem = BlockItem(
        MythicalBlocks.WARPED_SUPPORTED_PLANKS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "warped_supported_planks"
    )
    var GLOW_ORE: BlockItem = BlockItem(
        MythicalBlocks.GLOW_ORE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "glow_ore"
    )
    var MEW_1: BlockItem = BlockItem(
        MythicalBlocks.MEW_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mew_1"
    )
    var MEW_2: BlockItem = BlockItem(
        MythicalBlocks.MEW_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mew_2"
    )
    var MEW_3: BlockItem = BlockItem(
        MythicalBlocks.MEW_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "mew_3"
    )

    var PROGRESS_BAR: Item = Item(
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
        "progress_bar"
    )

    //Furniture
    var ACACIA_BENCH: BlockItem = BlockItem(
        MythicalBlocks.ACACIA_BENCH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "acacia_bench")
    var ANCHOR: BlockItem = BlockItem(
        MythicalBlocks.ANCHOR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "anchor")
    var ARROW_SIGN: BlockItem = BlockItem(
        MythicalBlocks.ARROW_SIGN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "arrow_sign")
    var ATM: BlockItem = BlockItem(
        MythicalBlocks.ATM,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "atm")
    var BALLOON: BlockItem = BlockItem(
        MythicalBlocks.BALLOON,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "balloon")
    var BALL_LAMP_1: BlockItem = BlockItem(
        MythicalBlocks.BALL_LAMP_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "ball_lamp_1")
    var BALL_LAMP_2: BlockItem = BlockItem(
        MythicalBlocks.BALL_LAMP_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "ball_lamp_2")
    var BAMBOO_BENCH: BlockItem = BlockItem(
        MythicalBlocks.BAMBOO_BENCH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bamboo_bench")
    var BAMBOO_DECORATION: BlockItem = BlockItem(
        MythicalBlocks.BAMBOO_DECORATION,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bamboo_decoration")
    var BANANA_1: BlockItem = BlockItem(
        MythicalBlocks.BANANA_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "banana_1")
    var BANANA_2: BlockItem = BlockItem(
        MythicalBlocks.BANANA_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "banana_2")
    var BANANA_3: BlockItem = BlockItem(
        MythicalBlocks.BANANA_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "banana_3")
    var BANNER: BlockItem = BlockItem(
        MythicalBlocks.BANNER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "banner")
    var BARRELS: BlockItem = BlockItem(
        MythicalBlocks.BARRELS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "barrels")
    var BARRIER: BlockItem = BlockItem(
        MythicalBlocks.BARRIER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "barrier")
    var BENCH: BlockItem = BlockItem(
        MythicalBlocks.BENCH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bench")
    var BENCH_2: BlockItem = BlockItem(
        MythicalBlocks.BENCH_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bench_2")
    var BENCH_3: BlockItem = BlockItem(
        MythicalBlocks.BENCH_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bench_3")
    var BIGGER_LOG: BlockItem = BlockItem(
        MythicalBlocks.BIGGER_LOG,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bigger_log")
    var BIG_BANNER: BlockItem = BlockItem(
        MythicalBlocks.BIG_BANNER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "big_banner")
    var BIG_LAMP: BlockItem = BlockItem(
        MythicalBlocks.BIG_LAMP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "big_lamp")
    var BIG_PLANT_POT: BlockItem = BlockItem(
        MythicalBlocks.BIG_PLANT_POT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "big_plant_pot")
    var BLASTOISE_FOUNTAIN: BlockItem = BlockItem(
        MythicalBlocks.BLASTOISE_FOUNTAIN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "blastoise_fountain")
    var BONSAI_TREE: BlockItem = BlockItem(
        MythicalBlocks.BONSAI_TREE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bonsai_tree")
    var BOOK_1_1: BlockItem = BlockItem(
        MythicalBlocks.BOOK_1_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_1_1")
    var BOOK_1_2: BlockItem = BlockItem(
        MythicalBlocks.BOOK_1_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_1_2")
    var BOOK_1_3: BlockItem = BlockItem(
        MythicalBlocks.BOOK_1_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_1_3")
    var BOOK_2_1: BlockItem = BlockItem(
        MythicalBlocks.BOOK_2_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_2_1")
    var BOOK_2_2: BlockItem = BlockItem(
        MythicalBlocks.BOOK_2_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_2_2")
    var BOOK_2_3: BlockItem = BlockItem(
        MythicalBlocks.BOOK_2_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_2_3")
    var BOOK_3_1: BlockItem = BlockItem(
        MythicalBlocks.BOOK_3_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_3_1")
    var BOOK_3_2: BlockItem = BlockItem(
        MythicalBlocks.BOOK_3_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "book_3_2")
    var BOUNTY_SIGN: BlockItem = BlockItem(
        MythicalBlocks.BOUNTY_SIGN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "bounty_sign")
    var BOXES_PILE: BlockItem = BlockItem(
        MythicalBlocks.BOXES_PILE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "boxes_pile")
    var BOX_KART: BlockItem = BlockItem(
        MythicalBlocks.BOX_KART,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "box_kart")
    var BROKEN_PILLAR: BlockItem = BlockItem(
        MythicalBlocks.BROKEN_PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "broken_pillar")
    var CHAIR_JUNGLE: BlockItem = BlockItem(
        MythicalBlocks.CHAIR_JUNGLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "chair_jungle")
    var CHAIR_OAK: BlockItem = BlockItem(
        MythicalBlocks.CHAIR_OAK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "chair_oak")
    var CHAIR_SPRUCE: BlockItem = BlockItem(
        MythicalBlocks.CHAIR_SPRUCE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "chair_spruce")
    var CHRISTMAS_TABLE: BlockItem = BlockItem(
        MythicalBlocks.CHRISTMAS_TABLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "christmas_table")
    var CHRISTMAS_TREE: BlockItem = BlockItem(
        MythicalBlocks.CHRISTMAS_TREE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "christmas_tree")
    var CHRISTMAS_WREATH: BlockItem = BlockItem(
        MythicalBlocks.CHRISTMAS_WREATH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "christmas_wreath")
    var CLOCK: BlockItem = BlockItem(
        MythicalBlocks.CLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "clock")
    var COMMODE: BlockItem = BlockItem(
        MythicalBlocks.COMMODE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "commode")
    var CONSTRUCTION_SIGN: BlockItem = BlockItem(
        MythicalBlocks.CONSTRUCTION_SIGN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "construction_sign")
    var COOKIES: BlockItem = BlockItem(
        MythicalBlocks.COOKIES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "cookies")
    var CORN_1: BlockItem = BlockItem(
        MythicalBlocks.CORN_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "corn_1")
    var CORN_2: BlockItem = BlockItem(
        MythicalBlocks.CORN_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "corn_2")
    var CORN_3: BlockItem = BlockItem(
        MythicalBlocks.CORN_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "corn_3")
    var COUCH: BlockItem = BlockItem(
        MythicalBlocks.COUCH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "couch")
    var CRATE: BlockItem = BlockItem(
        MythicalBlocks.CRATE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "crate")
    var CRATE_2: BlockItem = BlockItem(
        MythicalBlocks.CRATE_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "crate_2")
    var CRATE_LARGE: BlockItem = BlockItem(
        MythicalBlocks.CRATE_LARGE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "crate_large")
    var DITTO_BALLOON: BlockItem = BlockItem(
        MythicalBlocks.DITTO_BALLOON,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "ditto_balloon")
    var DOG_HOUSE: BlockItem = BlockItem(
        MythicalBlocks.DOG_HOUSE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "dog_house")
    var DRINKING_FOUNTAIN: BlockItem = BlockItem(
        MythicalBlocks.DRINKING_FOUNTAIN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "drinking_fountain")
    var DRINK_MACHINE: BlockItem = BlockItem(
        MythicalBlocks.DRINK_MACHINE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "drink_machine")
    var FIRE_BARREL: BlockItem = BlockItem(
        MythicalBlocks.FIRE_BARREL,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "fire_barrel")
    var FIRE_HYDRANT: BlockItem = BlockItem(
        MythicalBlocks.FIRE_HYDRANT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "fire_hydrant")
    var FIRE_HYDRANT_2: BlockItem = BlockItem(
        MythicalBlocks.FIRE_HYDRANT_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "fire_hydrant_2")
    var FISHING_STAND: BlockItem = BlockItem(
        MythicalBlocks.FISHING_STAND,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "fishing_stand")
    var FISH_ICE: BlockItem = BlockItem(
        MythicalBlocks.FISH_ICE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "fish_ice")
    var FLAG_POST: BlockItem = BlockItem(
        MythicalBlocks.FLAG_POST,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "flag_post")
    var FLOWER_BED_1: BlockItem = BlockItem(
        MythicalBlocks.FLOWER_BED_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "flower_bed_1")
    var FLOWER_BED_2: BlockItem = BlockItem(
        MythicalBlocks.FLOWER_BED_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "flower_bed_2")
    var FLOWER_BED_3: BlockItem = BlockItem(
        MythicalBlocks.FLOWER_BED_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "flower_bed_3")
    var FLOWER_BED_4: BlockItem = BlockItem(
        MythicalBlocks.FLOWER_BED_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "flower_bed_4")
    var FLOWER_BED_5: BlockItem = BlockItem(
        MythicalBlocks.FLOWER_BED_5,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "flower_bed_5")
    var FLOWER_CART: BlockItem = BlockItem(
        MythicalBlocks.FLOWER_CART,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "flower_cart")
    var FOODS: BlockItem = BlockItem(
        MythicalBlocks.FOODS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "foods")
    var FOOD_CART: BlockItem = BlockItem(
        MythicalBlocks.FOOD_CART,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "food_cart")
    var FOOD_STAND: BlockItem = BlockItem(
        MythicalBlocks.FOOD_STAND,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "food_stand")
    var FOUNTAIN_WATER: BlockItem = BlockItem(
        MythicalBlocks.FOUNTAIN_WATER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "fountain_water")
    var FREEZER: BlockItem = BlockItem(
        MythicalBlocks.FREEZER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "freezer")
    var GEM_BAG: BlockItem = BlockItem(
        MythicalBlocks.GEM_BAG,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "gem_bag")
    var GEM_CHEST: BlockItem = BlockItem(
        MythicalBlocks.GEM_CHEST,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "gem_chest")
    var GENGAR_BALLOON: BlockItem = BlockItem(
        MythicalBlocks.GENGAR_BALLOON,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "gengar_balloon")
    var GLASS_GARDEN: BlockItem = BlockItem(
        MythicalBlocks.GLASS_GARDEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "glass_garden")
    var GYARADOS_STATUE: BlockItem = BlockItem(
        MythicalBlocks.GYARADOS_STATUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "gyarados_statue")
    var HANGING_BUSH: BlockItem = BlockItem(
        MythicalBlocks.HANGING_BUSH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "hanging_bush")
    var HANGING_FLOWER: BlockItem = BlockItem(
        MythicalBlocks.HANGING_FLOWER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "hanging_flower")
    var HANGING_LAMP: BlockItem = BlockItem(
        MythicalBlocks.HANGING_LAMP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "hanging_lamp")
    var HANGING_PLANT: BlockItem = BlockItem(
        MythicalBlocks.HANGING_PLANT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "hanging_plant")
    var HAY_BALES: BlockItem = BlockItem(
        MythicalBlocks.HAY_BALES,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "hay_bales")
    var HAY_CART: BlockItem = BlockItem(
        MythicalBlocks.HAY_CART,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "hay_cart")
    var HORSEA: BlockItem = BlockItem(
        MythicalBlocks.HORSEA,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "horsea")
    var JAR_1: BlockItem = BlockItem(
        MythicalBlocks.JAR_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "jar_1")
    var JAR_2: BlockItem = BlockItem(
        MythicalBlocks.JAR_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "jar_2")
    var JAR_3: BlockItem = BlockItem(
        MythicalBlocks.JAR_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "jar_3")
    var JAR_4: BlockItem = BlockItem(
        MythicalBlocks.JAR_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "jar_4")
    var KINGDOMS_BANNER: BlockItem = BlockItem(
        MythicalBlocks.KINGDOMS_BANNER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "kingdoms_banner")
    var LADDER: BlockItem = BlockItem(
        MythicalBlocks.LADDER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "ladder")
    var LAMP: BlockItem = BlockItem(
        MythicalBlocks.LAMP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "lamp")
    var LAMPS: BlockItem = BlockItem(
        MythicalBlocks.LAMPS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "lamps")
    var LAPRAS_POOL: BlockItem = BlockItem(
        MythicalBlocks.LAPRAS_POOL,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "lapras_pool")
    var LAPTOP: BlockItem = BlockItem(
        MythicalBlocks.LAPTOP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "laptop")
    var LEAF_PILE_1: BlockItem = BlockItem(
        MythicalBlocks.LEAF_PILE_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "leaf_pile_1")
    var LEAF_PILE_2: BlockItem = BlockItem(
        MythicalBlocks.LEAF_PILE_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "leaf_pile_2")
    var LEAF_PILE_3: BlockItem = BlockItem(
        MythicalBlocks.LEAF_PILE_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "leaf_pile_3")
    var LEAF_PILE_4: BlockItem = BlockItem(
        MythicalBlocks.LEAF_PILE_4,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "leaf_pile_4")
    var LION_STATUE: BlockItem = BlockItem(
        MythicalBlocks.LION_STATUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "lion_statue")
    var LOG: BlockItem = BlockItem(
        MythicalBlocks.LOG,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "log")
    var LOGS_1: BlockItem = BlockItem(
        MythicalBlocks.LOGS_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "logs_1")
    var LOG_2: BlockItem = BlockItem(
        MythicalBlocks.LOG_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "log_2")
    var LOW_CHAIR: BlockItem = BlockItem(
        MythicalBlocks.LOW_CHAIR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "low_chair")
    var LOW_TABLE: BlockItem = BlockItem(
        MythicalBlocks.LOW_TABLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "low_table")
    var LOW_TABLE_2: BlockItem = BlockItem(
        MythicalBlocks.LOW_TABLE_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "low_table_2")
    var LUXURY_SPACER: BlockItem = BlockItem(
        MythicalBlocks.LUXURY_SPACER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "luxury_spacer")
    var MAGAZINE_STAND: BlockItem = BlockItem(
        MythicalBlocks.MAGAZINE_STAND,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "magazine_stand")
    var MAILBOX: BlockItem = BlockItem(
        MythicalBlocks.MAILBOX,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "mailbox")
    var MANNEQUIN: BlockItem = BlockItem(
        MythicalBlocks.MANNEQUIN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "mannequin")
    var MEDIUM_BANNER: BlockItem = BlockItem(
        MythicalBlocks.MEDIUM_BANNER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "medium_banner")
    var MEOWTH_BALLOON: BlockItem = BlockItem(
        MythicalBlocks.MEOWTH_BALLOON,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "meowth_balloon")
    var METAL_BEAMS: BlockItem = BlockItem(
        MythicalBlocks.METAL_BEAMS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "metal_beams")
    var METAL_LAMP_1: BlockItem = BlockItem(
        MythicalBlocks.METAL_LAMP_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "metal_lamp_1")
    var METAL_LAMP_2: BlockItem = BlockItem(
        MythicalBlocks.METAL_LAMP_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "metal_lamp_2")
    var METAL_LAMP_3: BlockItem = BlockItem(
        MythicalBlocks.METAL_LAMP_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "metal_lamp_3")
    var MINECART: BlockItem = BlockItem(
        MythicalBlocks.MINECART,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "minecart")
    var MUSEUM_DISPLAY_CASE_1: BlockItem = BlockItem(
        MythicalBlocks.MUSEUM_DISPLAY_CASE_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "museum_display_case_1")
    var MUSEUM_DISPLAY_CASE_2: BlockItem = BlockItem(
        MythicalBlocks.MUSEUM_DISPLAY_CASE_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "museum_display_case_2")
    var ORANGE_SIGN_1: BlockItem = BlockItem(
        MythicalBlocks.ORANGE_SIGN_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "orange_sign_1")
    var ORANGE_SIGN_2: BlockItem = BlockItem(
        MythicalBlocks.ORANGE_SIGN_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "orange_sign_2")
    var PICNIC_TABLE: BlockItem = BlockItem(
        MythicalBlocks.PICNIC_TABLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "picnic_table")
    var PIKACHU_SURFBOARD: BlockItem = BlockItem(
        MythicalBlocks.PIKACHU_SURFBOARD,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pikachu_surfboard")
    var PILLAR: BlockItem = BlockItem(
        MythicalBlocks.PILLAR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pillar")
    var PILLAR_GREEN: BlockItem = BlockItem(
        MythicalBlocks.PILLAR_GREEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pillar_green")
    var PILLAR_TOP: BlockItem = BlockItem(
        MythicalBlocks.PILLAR_TOP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pillar_top")
    var PLANKS_1: BlockItem = BlockItem(
        MythicalBlocks.PLANKS_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "planks_1")
    var PLANKS_2: BlockItem = BlockItem(
        MythicalBlocks.PLANKS_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "planks_2")
    var PLANKS_3: BlockItem = BlockItem(
        MythicalBlocks.PLANKS_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "planks_3")
    var PLUSHIE_MACHINE: BlockItem = BlockItem(
        MythicalBlocks.PLUSHIE_MACHINE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "plushie_machine")
    var POKEA_FURNITURE_SIGN: BlockItem = BlockItem(
        MythicalBlocks.POKEA_FURNITURE_SIGN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokea_furniture_sign")
    var POKEBALL_BLOCK: BlockItem = BlockItem(
        MythicalBlocks.POKEBALL_BLOCK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokeball_block")
    var POKEBALL_BLOCK_STONE: BlockItem = BlockItem(
        MythicalBlocks.POKEBALL_BLOCK_STONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokeball_block_stone")
    var POKEBALL_MANHOLE: BlockItem = BlockItem(
        MythicalBlocks.POKEBALL_MANHOLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokeball_manhole")
    var POKEBALL_STATUE_1: BlockItem = BlockItem(
        MythicalBlocks.POKEBALL_STATUE_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokeball_statue_1")
    var POKEBALL_STATUE_2: BlockItem = BlockItem(
        MythicalBlocks.POKEBALL_STATUE_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokeball_statue_2")
    var POKEBALL_STATUE_3: BlockItem = BlockItem(
        MythicalBlocks.POKEBALL_STATUE_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokeball_statue_3")
    var POKEMAXX_STORE: BlockItem = BlockItem(
        MythicalBlocks.POKEMAXX_STORE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pokemaxx_store")
    var POSE_STATUE: BlockItem = BlockItem(
        MythicalBlocks.POSE_STATUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "pose_statue")
    var POTTED_BUSH: BlockItem = BlockItem(
        MythicalBlocks.POTTED_BUSH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "potted_bush")
    var POTTED_PLANT: BlockItem = BlockItem(
        MythicalBlocks.POTTED_PLANT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "potted_plant")
    var PUFFER_STATUE: BlockItem = BlockItem(
        MythicalBlocks.PUFFER_STATUE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "puffer_statue")
    var REED: BlockItem = BlockItem(
        MythicalBlocks.REED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "reed")
    var ROCK_1: BlockItem = BlockItem(
        MythicalBlocks.ROCK_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "rock_1")
    var ROCK_2: BlockItem = BlockItem(
        MythicalBlocks.ROCK_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "rock_2")
    var ROCK_BIG: BlockItem = BlockItem(
        MythicalBlocks.ROCK_BIG,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "rock_big")
    var ROCK_SMALL: BlockItem = BlockItem(
        MythicalBlocks.ROCK_SMALL,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "rock_small")
    var ROOM_DIVIDER: BlockItem = BlockItem(
        MythicalBlocks.ROOM_DIVIDER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "room_divider")
    var SALAD_1: BlockItem = BlockItem(
        MythicalBlocks.SALAD_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "salad_1")
    var SALAD_2: BlockItem = BlockItem(
        MythicalBlocks.SALAD_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "salad_2")
    var SALAD_3: BlockItem = BlockItem(
        MythicalBlocks.SALAD_3,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "salad_3")
    var SCAFFOLDING: BlockItem = BlockItem(
        MythicalBlocks.SCAFFOLDING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "scaffolding")
    var SEA_SHELL_1: BlockItem = BlockItem(
        MythicalBlocks.SEA_SHELL_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sea_shell_1")
    var SEA_SHELL_2: BlockItem = BlockItem(
        MythicalBlocks.SEA_SHELL_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sea_shell_2")
    var SEA_SHELL_2_CYAN: BlockItem = BlockItem(
        MythicalBlocks.SEA_SHELL_2_CYAN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sea_shell_2_cyan")
    var SEA_SHELL_2_MAGENTA: BlockItem = BlockItem(
        MythicalBlocks.SEA_SHELL_2_MAGENTA,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sea_shell_2_magenta")
    var SHRINE_LAMP: BlockItem = BlockItem(
        MythicalBlocks.SHRINE_LAMP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "shrine_lamp")
    var SMALL_BANNER_1: BlockItem = BlockItem(
        MythicalBlocks.SMALL_BANNER_1,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "small_banner_1")
    var SMALL_BANNER_2: BlockItem = BlockItem(
        MythicalBlocks.SMALL_BANNER_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "small_banner_2")
    var SMALL_LILYPADS: BlockItem = BlockItem(
        MythicalBlocks.SMALL_LILYPADS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "small_lilypads")
    var SNOWMAN: BlockItem = BlockItem(
        MythicalBlocks.SNOWMAN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "snowman")
    var SOFA_BLACK: BlockItem = BlockItem(
        MythicalBlocks.SOFA_BLACK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_black")
    var SOFA_CHAIR_BLACK: BlockItem = BlockItem(
        MythicalBlocks.SOFA_CHAIR_BLACK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_chair_black")
    var SOFA_CHAIR_GREEN: BlockItem = BlockItem(
        MythicalBlocks.SOFA_CHAIR_GREEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_chair_green")
    var SOFA_CHAIR_GREY: BlockItem = BlockItem(
        MythicalBlocks.SOFA_CHAIR_GREY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_chair_grey")
    var SOFA_CHAIR_RED: BlockItem = BlockItem(
        MythicalBlocks.SOFA_CHAIR_RED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_chair_red")
    var SOFA_CHAIR_WHITE: BlockItem = BlockItem(
        MythicalBlocks.SOFA_CHAIR_WHITE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_chair_white")
    var SOFA_GREEN: BlockItem = BlockItem(
        MythicalBlocks.SOFA_GREEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_green")
    var SOFA_GREY: BlockItem = BlockItem(
        MythicalBlocks.SOFA_GREY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_grey")
    var SOFA_RED: BlockItem = BlockItem(
        MythicalBlocks.SOFA_RED,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_red")
    var SOFA_WHITE: BlockItem = BlockItem(
        MythicalBlocks.SOFA_WHITE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sofa_white")
    var SQUIRTLE_FOUNTAIN: BlockItem = BlockItem(
        MythicalBlocks.SQUIRTLE_FOUNTAIN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "squirtle_fountain")
    var STANDING_FLOWER: BlockItem = BlockItem(
        MythicalBlocks.STANDING_FLOWER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "standing_flower")
    var STANDING_LAMP: BlockItem = BlockItem(
        MythicalBlocks.STANDING_LAMP,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "standing_lamp")
    var STONE_SHRINE: BlockItem = BlockItem(
        MythicalBlocks.STONE_SHRINE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "stone_shrine")
    var SUGAR_QUARRY: BlockItem = BlockItem(
        MythicalBlocks.SUGAR_QUARRY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sugar_quarry")
    var SUMMER_CHAIR: BlockItem = BlockItem(
        MythicalBlocks.SUMMER_CHAIR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "summer_chair")
    var SUNLOUNGER: BlockItem = BlockItem(
        MythicalBlocks.SUNLOUNGER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "sunlounger")
    var SWING: BlockItem = BlockItem(
        MythicalBlocks.SWING,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "swing")
    var TABLE_JUNGLE: BlockItem = BlockItem(
        MythicalBlocks.TABLE_JUNGLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "table_jungle")
    var TABLE_OAK: BlockItem = BlockItem(
        MythicalBlocks.TABLE_OAK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "table_oak")
    var TABLE_SPRUCE: BlockItem = BlockItem(
        MythicalBlocks.TABLE_SPRUCE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "table_spruce")
    var TABLE_WITH_UMBRELLA: BlockItem = BlockItem(
        MythicalBlocks.TABLE_WITH_UMBRELLA,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "table_with_umbrella")
    var TELEPHONE_BOOTH: BlockItem = BlockItem(
        MythicalBlocks.TELEPHONE_BOOTH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "telephone_booth")
    var TELEPORT_PAD: BlockItem = BlockItem(
        MythicalBlocks.TELEPORT_PAD,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "teleport_pad")
    var TELEPORT_PAD_RINGS: BlockItem = BlockItem(
        MythicalBlocks.TELEPORT_PAD_RINGS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "teleport_pad_rings")
    var TENT: BlockItem = BlockItem(
        MythicalBlocks.TENT,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "tent")
    var TORII: BlockItem = BlockItem(
        MythicalBlocks.TORII,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "torii")
    var TRAFFIC_BARRIER: BlockItem = BlockItem(
        MythicalBlocks.TRAFFIC_BARRIER,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "traffic_barrier")
    var TRAFFIC_CONE: BlockItem = BlockItem(
        MythicalBlocks.TRAFFIC_CONE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "traffic_cone")
    var TRASH_CAN: BlockItem = BlockItem(
        MythicalBlocks.TRASH_CAN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "trash_can")
    var TRASH_CAN_2: BlockItem = BlockItem(
        MythicalBlocks.TRASH_CAN_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "trash_can_2")
    var TRASH_CAN_2_DIRTY: BlockItem = BlockItem(
        MythicalBlocks.TRASH_CAN_2_DIRTY,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "trash_can_2_dirty")
    var TROLLEY_WITH_BREAD: BlockItem = BlockItem(
        MythicalBlocks.TROLLEY_WITH_BREAD,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "trolley_with_bread")
    var TV_STAND_JUNGLE: BlockItem = BlockItem(
        MythicalBlocks.TV_STAND_JUNGLE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "tv_stand_jungle")
    var TV_STAND_OAK: BlockItem = BlockItem(
        MythicalBlocks.TV_STAND_OAK,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "tv_stand_oak")
    var TV_STAND_SPRUCE: BlockItem = BlockItem(
        MythicalBlocks.TV_STAND_SPRUCE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "tv_stand_spruce")
    var VENDING_MACHINE: BlockItem = BlockItem(
        MythicalBlocks.VENDING_MACHINE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "vending_machine")
    var WAGON: BlockItem = BlockItem(
        MythicalBlocks.WAGON,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wagon")
    var WALL_GREEN: BlockItem = BlockItem(
        MythicalBlocks.WALL_GREEN,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wall_green")
    var WALL_GREEN_LIPLESS: BlockItem = BlockItem(
        MythicalBlocks.WALL_GREEN_LIPLESS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wall_green_lipless")
    var WEATHERVANE: BlockItem = BlockItem(
        MythicalBlocks.WEATHERVANE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "weathervane")
    var WELL: BlockItem = BlockItem(
        MythicalBlocks.WELL,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "well")
    var WHEELBARROW: BlockItem = BlockItem(
        MythicalBlocks.WHEELBARROW,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wheelbarrow")
    var WOODEN_LOUNGE_CHAIR: BlockItem = BlockItem(
        MythicalBlocks.WOODEN_LOUNGE_CHAIR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wooden_lounge_chair")
    var WOOD_ARROWS: BlockItem = BlockItem(
        MythicalBlocks.WOOD_ARROWS,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_arrows")
    var WOOD_BENCH: BlockItem = BlockItem(
        MythicalBlocks.WOOD_BENCH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_bench")
    var WOOD_BOX: BlockItem = BlockItem(
        MythicalBlocks.WOOD_BOX,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_box")
    var WOOD_BOX_2: BlockItem = BlockItem(
        MythicalBlocks.WOOD_BOX_2,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_box_2")
    var WOOD_CHAIR: BlockItem = BlockItem(
        MythicalBlocks.WOOD_CHAIR,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_chair")
    var WOOD_FENCE: BlockItem = BlockItem(
        MythicalBlocks.WOOD_FENCE,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_fence")
    var WOOD_LOG_BENCH: BlockItem = BlockItem(
        MythicalBlocks.WOOD_LOG_BENCH,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_log_bench")
    var WOOD_PLATFORM: BlockItem = BlockItem(
        MythicalBlocks.WOOD_PLATFORM,
        Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_BLOCKS),
         "wood_platform")

    fun registerItems() {
        for(item in ITEMS){
            registerItem(item.value, item.key)
        }
    }

    private fun registerItem(item: Item, rl: ResourceLocation) {
        Registry.register(Registry.ITEM, rl, item)
    }
}