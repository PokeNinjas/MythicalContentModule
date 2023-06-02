package com.mythicalnetwork.mythicalmod.registry

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object MythicalItems {
    val ITEMS: MutableMap<ResourceLocation, Item> = mutableMapOf()
    var PLATINUM_INGOT: Item =
        Item(Item.Properties().stacksTo(64).tab(MythicalGroups.MYTHICAL_ITEMS), "platinum_ingot")

    val POKEMON_EGG: Item =
        Item(Item.Properties().stacksTo(1).tab(MythicalGroups.MYTHICAL_ITEMS), "pokemon_egg")

    private fun Item(tab: Item.Properties?, s: String): Item {
        val item = tab?.let { Item(it) }
        ITEMS[ResourceLocation("mythicalmod", s)] = item!!
        return item
    }

    private fun BlockItem(block: Block, tab: Item.Properties?, s: String): BlockItem {
        val blockItem = tab?.let { BlockItem(block, it) }
        ITEMS[ResourceLocation("mythicalmod", s)] = blockItem!!
        return blockItem
    }

    var CRAMOMATIC: BlockItem = BlockItem(
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

    fun registerItems() {
        for(item in ITEMS){
            registerItem(item.value, item.key)
        }
    }

    private fun registerItem(item: Item, rl: ResourceLocation) {
        Registry.register(Registry.ITEM, rl, item)
    }
}