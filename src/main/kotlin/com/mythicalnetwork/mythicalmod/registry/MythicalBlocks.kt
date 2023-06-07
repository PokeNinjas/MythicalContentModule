package com.mythicalnetwork.mythicalmod.registry

import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticBlock
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkBlock
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkEmptyBlock
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Material
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings

object MythicalBlocks {
    var BLOCKS: MutableMap<ResourceLocation, Block> = mutableMapOf()
    fun Block(requiresTool: QuiltBlockSettings?, s: String): Block {
        return requiresTool!!.let { Block(it).also { BLOCKS[ResourceLocation("mythicalmod", s)] = it } }
    }
    var DARK_MUD: Block =
        Block(QuiltBlockSettings.of(Material.DIRT).hardness(3.6F).requiresTool(), "dark_mud")
    var DARK_MUD_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_mud_bricks")
    var DARK_MUD_BRICKS_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_mud_bricks_2")
    var DRIED_DIRT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dried_dirt")
    var MOIST_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "moist_bricks")
    var MOIST_EARTH: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "moist_earth")
    var MOIST_LAYERED_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "moist_layered_brick")
    var MUD: Block = Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mud")
    var MUD_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mud_bricks")
    var MUD_BRICKS_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mud_bricks_2")
    var POKEDIRT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "pokedirt")
    var RICH_SOIL_1: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "rich_soil_1")
    var RICH_SOIL_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "rich_soil_2")
    var RICH_SOIL_3: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "rich_soil_3")
    var RICH_SOIL_4: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "rich_soil_4")
    var SMOOTH_MOIST_DIRT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "smooth_moist_dirt")
    var SMOOTH_MOIST_DIRT_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "smooth_moist_dirt_bricks")
    var SMOOTH_MOIST_DIRT_BRICKS_2: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "smooth_moist_dirt_bricks_2"
    )
    var SUNBURNT_DIRT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunburnt_dirt")
    var EARTH: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "earth")
    var BLACK_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "black_shingles")
    var BLUE_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "blue_shingles")
    var BROWN_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "brown_shingles")
    var CYAN_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cyan_shingles")
    var GRAY_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "gray_shingles")
    var GREEN_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "green_shingles")
    var LIGHT_BLUE_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "light_blue_shingles")
    var LIGHT_GRAY_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "light_gray_shingles")
    var LIME_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "lime_shingles")
    var MAGENTA_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "magenta_shingles")
    var ORANGE_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "orange_shingles")
    var PINK_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "pink_shingles")
    var PURPLE_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "purple_shingles")
    var RED_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "red_shingles")
    var WHITE_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "white_shingles")
    var YELLOW_SHINGLES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "yellow_shingles")
    var BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks")
    var BRICKS_PATH_1: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_path_1")
    var BRICKS_PATH_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_path_2")
    var BRICKS_PATH_3: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_path_3")
    var BRICKS_PATH_4: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_path_4")
    var BRICKS_PATH_MOSSY_1: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_path_mossy_1")
    var BRICKS_PATH_MOSSY_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_path_mossy_2")
    var BRICKS_PATH_MOSSY_3: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_path_mossy_3")
    var BRICKS_VARIANT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_variant")
    var BRICKS_VARIANT_INSET: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bricks_variant_inset")
    var MOSSY_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mossy_brick")
    var SLATE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "slate_bricks")
    var SLATE_BRICKS_LARGE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "slate_bricks_large")
    var SLATE_CHISELED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "slate_chiseled")
    var SLATE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "slate_polished")
    var SLATE_RAW: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "slate_raw")
    var STRATA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "strata_bricks")
    var STRATA_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "strata_polished")
    var STRATA_RAW: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "strata_raw")
    var STRATA_RAW_LINES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "strata_raw_lines")
    var BAYMOSS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "baymoss")
    var BLACK_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "black_bricks")
    var BLACK_BRICKS2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "black_bricks2")
    var CASTLE_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_brick")
    var CASTLE_BLOCK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_block")
    var CASTLE_BLOCK_CRACKED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_block_cracked")
    var CASTLE_BRICK_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_brick_2")
    var CASTLE_FLOOR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_floor")
    var CASTLE_GREY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_grey")
    var CASTLE_GREY_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_grey_2")
    var CASTLE_WALL: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_wall")
    var CASTLE_WALL_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_wall_2")
    var CASTLE_WALL_3: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_wall_3")
    var CASTLE_WALL_4: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_wall_4")
    var CASTLE_WHITE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_white")
    var CASTLE_WHITE_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "castle_white_2")
    var ELEVATOR_BLACK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_black")
    var ELEVATOR_BLUE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_blue")
    var ELEVATOR_BROWN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_brown")
    var ELEVATOR_CYAN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_cyan")
    var ELEVATOR_DARK_GRAY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_dark_gray")
    var ELEVATOR_GRAY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_gray")
    var ELEVATOR_GREEN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_green")
    var ELEVATOR_LIGHT_BLUE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_light_blue")
    var ELEVATOR_LIGHT_GRAY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_light_gray")
    var ELEVATOR_LIME: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_lime")
    var ELEVATOR_MAGENTA: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_magenta")
    var ELEVATOR_ORANGE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_orange")
    var ELEVATOR_PINK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_pink")
    var ELEVATOR_PURPLE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_purple")
    var ELEVATOR_RED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_red")
    var ELEVATOR_WHITE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_white")
    var ELEVATOR_YELLOW: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "elevator_yellow")
    var FROSTED_COOL_STONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "frosted_cool_stone")
    var GHOST_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ghost_brick")
    var GHOST_OBELISK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ghost_obelisk")
    var GHOST_PLANK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ghost_plank")
    var HAUNTED_STEEL_BLOCK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "haunted_steel_block")
    var HOUSE_FLOOR_1: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_1")
    var HOUSE_FLOOR_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_2")
    var HOUSE_FLOOR_3: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_3")
    var HOUSE_FLOOR_4: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_4")
    var HOUSE_FLOOR_5: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_5")
    var HOUSE_FLOOR_6: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_6")
    var HOUSE_FLOOR_7: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_7")
    var HOUSE_FLOOR_8: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "house_floor_8")
    var ICE_STONE_BLOCK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ice_stone_block")
    var ICE_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ice_brick")
    var INSIDE_WALL_BOTTOM: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "inside_wall_bottom")
    var INSIDE_WALL_MIDDLE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "inside_wall_middle")
    var INSIDE_WALL_TOP: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "inside_wall_top")
    var INSIDE_WALL: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "inside_wall")
    var INSIDE_WALL_MOLDING: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "inside_wall_molding")
    var MARBLE_BLACK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_black")
    var MARBLE_BLUE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_blue")
    var MARBLE_BROWN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_brown")
    var MARBLE_CYAN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_cyan")
    var MARBLE_GRAY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_gray")
    var MARBLE_GREEN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_green")
    var MARBLE_LIGHT_BLUE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_light_blue")
    var MARBLE_LIME: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_lime")
    var MARBLE_MAGENTA: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_magenta")
    var MARBLE_ORANGE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_orange")
    var MARBLE_PINK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_pink")
    var MARBLE_PURPLE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_purple")
    var MARBLE_RED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_red")
    var MARBLE_SILVER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_silver")
    var MARBLE_WHITE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_white")
    var MARBLE_YELLOW: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "marble_yellow")
    var OUTSIDE_WALL: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "outside_wall")
    var POKE_BRICK_BLACK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_black")
    var POKE_BRICK_BLUE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_blue")
    var POKE_BRICK_BROWN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_brown")
    var POKE_BRICK_CYAN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_cyan")
    var POKE_BRICK_GRAY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_gray")
    var POKE_BRICK_GREEN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_green")
    var POKE_BRICK_LIGHT_BLUE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_light_blue")
    var POKE_BRICK_LIME: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_lime")
    var POKE_BRICK_MAGENTA: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_magenta")
    var POKE_BRICK_ORANGE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_orange")
    var POKE_BRICK_PINK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_pink")
    var POKE_BRICK_PURPLE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_purple")
    var POKE_BRICK_RED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_red")
    var POKE_BRICK_SILVER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_silver")
    var POKE_BRICK_WHITE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_white")
    var POKE_BRICK_YELLOW: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "poke_brick_yellow")
    var POKEGEM_BLOCK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "pokegem_block")
    var POKEGEM_ORE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "pokegem_ore")
    var TEMPLE_BLOCK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "temple_block")
    var TEMPLE_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "temple_brick")
    var CARVED_RAVAGESTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_ravagestone")
    var CHISELED_RED_SANDSTONE_BRICKS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "chiseled_red_sandstone_bricks"
    )
    var CLAYSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "claystone")
    var CLAYSTONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "claystone_bricks")
    var CLAYSTONE_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "claystone_tiles")
    var CRACKED_RED_SANDSTONE_BRICKS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "cracked_red_sandstone_bricks"
    )
    var END_STONE_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "end_stone_diamond_pavers")
    var END_STONE_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "end_stone_tiled")
    var GILDED_RED_SANDSTONE_BRICKS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "gilded_red_sandstone_bricks"
    )
    var NETHER_BRICK_DIAMOND_PAVERS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "nether_brick_diamond_pavers"
    )
    var NETHERRACK_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "netherrack_diamond_pavers")
    var NETHERRACK_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "netherrack_tiled")
    var OBSIDIAN_DIAMOND_PAVER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "obsidian_diamond_paver")
    var OBSIDIAN_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "obsidian_tiled")
    var POLISHED_CLAYSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "polished_claystone")
    var POLISHED_RAVAGESTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "polished_ravagestone")
    var PURPUR_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "purpur_diamond_pavers")
    var QUARTZ_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "quartz_diamond_pavers")
    var RAVAGED_METAL_BLOCK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ravaged_metal_block")
    var RAVAGESTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ravagestone")
    var RAVAGESTONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ravagestone_bricks")
    var RAVAGESTONE_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ravagestone_tiles")
    var RED_SANDSTONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "red_sandstone_bricks")
    var COOL_STONE_GRASS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cool_stone_grass")
    var DRIED_DIRT_GRASS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dried_dirt_grass")
    var GHOST_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ghost_pillar")
    var ICE_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "ice_pillar")
    var MOSSY_BAYSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mossy_baystone")
    var STONY_GRASS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stony_grass")
    var SUNBURNT_SAND: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunburnt_sand")
    var SUNBURNT_SANDSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunburnt_sandstone")
    var SUNBURNT_SANDSTONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunburnt_sandstone_bricks")
    var SUNBURNT_SANDSTONE_POLISHED: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "sunburnt_sandstone_polished"
    )
    var SUNNY_SAND: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunny_sand")
    var SUNNY_SANDSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunny_sandstone")
    var SUNNY_SANDSTONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunny_sandstone_bricks")
    var SUNNY_SANDSTONE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunny_sandstone_polished")
    var SUNNY_SAND_GRASS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "sunny_sand_grass")
    var BAYSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "baystone")
    var BAYSTONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "baystone_bricks")
    var POLISHED_BAYSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "polished_baystone")
    var TILED_BAYSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "tiled_baystone")
    var KYANITE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "kyanite_bricks")
    var KYANITE_CHISELED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "kyanite_chiseled")
    var KYANITE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "kyanite_polished")
    var KYANITE_RAW: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "kyanite_raw")
    var MOSSY_BEACH_STONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mossy_beach_stone")
    var MOSSY_BEACH_STONE_COBBLE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mossy_beach_stone_cobble")
    var BLEACH_STONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bleach_stone")
    var BLEACH_STONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bleach_stone_bricks")
    var BLEACH_STONE_LAYERED_BRICKS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "bleach_stone_layered_bricks"
    )
    var BLEACH_STONE_TILED_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bleach_stone_tiled_bricks")
    var BLEACHED_STONE_CRUMBLED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "bleached_stone_crumbled")
    var CARVED_GLOOMSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_gloomstone")
    var COBBLE_RUINS_1: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cobble_ruins_1")
    var COBBLE_RUINS_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cobble_ruins_2")
    var COBBLE_RUINS_3: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cobble_ruins_3")
    var COBBLE_RUINS_4: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cobble_ruins_4")
    var COOL_STONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cool_stone")
    var COOL_STONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cool_stone_bricks")
    var COOL_STONE_LAYERED_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cool_stone_layered_bricks")
    var COOL_STONE_TILED_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cool_stone_tiled_bricks")
    var GLOOMSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "gloomstone")
    var GLOOMSTONE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "gloomstone_bricks")
    var GLOOMSTONE_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "gloomstone_tiles")
    var LAYERED_STONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "layered_stone")
    var POLISHED_BLEACH_STONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "polished_bleach_stone")
    var POLISHED_COOL_STONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "polished_cool_stone")
    var POLISHED_GLOOMSTONE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "polished_gloomstone")
    var ANDESITE_CHISELED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_chiseled")
    var ANDESITE_COBBLED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_cobbled")
    var ANDESITE_COBBLED_MOSSY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_cobbled_mossy")
    var ANDESITE_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_diamond_pavers")
    var ANDESITE_LARGE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_large_bricks")
    var ANDESITE_LARGE_BRICKS_CRACKED: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "andesite_large_bricks_cracked"
    )
    var ANDESITE_LARGE_BRICKS_MOSSY: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "andesite_large_bricks_mossy"
    )
    var ANDESITE_PANELS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_panels")
    var ANDESITE_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_pavers")
    var ANDESITE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_polished")
    var ANDESITE_SMALL_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_small_brick")
    var ANDESITE_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_tiled")
    var ANDESITE_CARVED_CREEPER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_carved_creeper")
    var ANDESITE_CARVED_DERP: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_carved_derp")
    var ANDESITE_CARVED_VILLAGER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_carved_villager")
    var ANDESITE_CARVED_WITHER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_carved_wither")
    var ANDESITE_CARVED_WRITING: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_carved_writing")
    var ANDESITE_COLUMN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_column")
    var ANDESITE_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_cut")
    var ANDESITE_ENGRAVED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_engraved")
    var ANDESITE_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_pillar")
    var ANDESITE_ROUGH_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "andesite_rough_cut")
    var DARK_PRISMARINE_CARVED_CREEPER: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_carved_creeper"
    )
    var DARK_PRISMARINE_CARVED_DERP: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_carved_derp"
    )
    var DARK_PRISMARINE_CARVED_VILLAGER: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_carved_villager"
    )
    var DARK_PRISMARINE_CARVED_WITHER: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_carved_wither"
    )
    var DARK_PRISMARINE_CARVED_WRITING: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_carved_writing"
    )
    var DARK_PRISMARINE_CHISELED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_chiseled")
    var DARK_PRISMARINE_COBBLED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_cobbled")
    var DARK_PRISMARINE_COBBLED_MOSSY: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_cobbled_mossy"
    )
    var DARK_PRISMARINE_COLUMN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_column")
    var DARK_PRISMARINE_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_cut")
    var DARK_PRISMARINE_DIAMOND_PAVERS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_diamond_pavers"
    )
    var DARK_PRISMARINE_ENGRAVED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_engraved")
    var DARK_PRISMARINE_LARGE_BRICKS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_large_bricks"
    )
    var DARK_PRISMARINE_LARGE_BRICKS_CRACKED: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_large_bricks_cracked"
    )
    var DARK_PRISMARINE_LARGE_BRICKS_MOSSY: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_large_bricks_mossy"
    )
    var DARK_PRISMARINE_PANELS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_panels")
    var DARK_PRISMARINE_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_pavers")
    var DARK_PRISMARINE_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_pillar")
    var DARK_PRISMARINE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_polished")
    var DARK_PRISMARINE_ROUGH_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_rough_cut")
    var DARK_PRISMARINE_SMALL_BRICK: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "dark_prismarine_small_brick"
    )
    var DARK_PRISMARINE_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "dark_prismarine_tiled")
    var DIORITE_CARVED_CREEPER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_carved_creeper")
    var DIORITE_CARVED_DERP: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_carved_derp")
    var DIORITE_CARVED_VILLAGER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_carved_villager")
    var DIORITE_CARVED_WITHER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_carved_wither")
    var DIORITE_CARVED_WRITING: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_carved_writing")
    var DIORITE_CHISELED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_chiseled")
    var DIORITE_COBBLED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_cobbled")
    var DIORITE_COBBLED_MOSSY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_cobbled_mossy")
    var DIORITE_COLUMN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_column")
    var DIORITE_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_cut")
    var DIORITE_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_diamond_pavers")
    var DIORITE_ENGRAVED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_engraved")
    var DIORITE_LARGE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_large_bricks")
    var DIORITE_LARGE_BRICKS_CRACKED: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "diorite_large_bricks_cracked"
    )
    var DIORITE_LARGE_BRICKS_MOSSY: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "diorite_large_bricks_mossy"
    )
    var DIORITE_PANELS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_panels")
    var DIORITE_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_pavers")
    var DIORITE_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_pillar")
    var DIORITE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_polished")
    var DIORITE_ROUGH_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_rough_cut")
    var DIORITE_SMALL_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_small_brick")
    var DIORITE_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "diorite_tiled")
    var GRANITE_CARVED_CREEPER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_carved_creeper")
    var GRANITE_CARVED_DERP: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_carved_derp")
    var GRANITE_CARVED_VILLAGER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_carved_villager")
    var GRANITE_CARVED_WITHER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_carved_wither")
    var GRANITE_CARVED_WRITING: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_carved_writing")
    var GRANITE_CHISELED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_chiseled")
    var GRANITE_COBBLED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_cobbled")
    var GRANITE_COBBLED_MOSSY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_cobbled_mossy")
    var GRANITE_COLUMN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_column")
    var GRANITE_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_cut")
    var GRANITE_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_diamond_pavers")
    var GRANITE_ENGRAVED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_engraved")
    var GRANITE_LARGE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_large_bricks")
    var GRANITE_LARGE_BRICKS_CRACKED: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "granite_large_bricks_cracked"
    )
    var GRANITE_LARGE_BRICKS_MOSSY: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "granite_large_bricks_mossy"
    )
    var GRANITE_PANELS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_panels")
    var GRANITE_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_pavers")
    var GRANITE_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_pillar")
    var GRANITE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_polished")
    var GRANITE_ROUGH_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_rough_cut")
    var GRANITE_SMALL_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_small_brick")
    var GRANITE_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "granite_tiled")
    var PRISMARINE_CARVED_CREEPER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_carved_creeper")
    var PRISMARINE_CARVED_DERP: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_carved_derp")
    var PRISMARINE_CARVED_VILLAGER: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "prismarine_carved_villager"
    )
    var PRISMARINE_CARVED_WITHER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_carved_wither")



    var PRISMARINE_CARVED_WRITING: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_carved_writing")
    var PRISMARINE_CHISELED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_chiseled")
    var PRISMARINE_COBBLED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_cobbled")
    var PRISMARINE_COBBLED_MOSSY: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_cobbled_mossy")
    var PRISMARINE_COLUMN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_column")
    var PRISMARINE_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_cut")
    var PRISMARINE_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_diamond_pavers")
    var PRISMARINE_ENGRAVED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_engraved")
    var PRISMARINE_LARGE_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_large_bricks")
    var PRISMARINE_LARGE_BRICKS_CRACKED: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "prismarine_large_bricks_cracked"
    )
    var PRISMARINE_LARGE_BRICKS_MOSSY: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "prismarine_large_bricks_mossy"
    )
    var PRISMARINE_PANELS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_panels")
    var PRISMARINE_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_pavers")
    var PRISMARINE_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_pillar")
    var PRISMARINE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_polished")
    var PRISMARINE_ROUGH_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_rough_cut")
    var PRISMARINE_SMALL_BRICK: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_small_brick")
    var PRISMARINE_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "prismarine_tiled")
    var STONE_CARVED_CREEPER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_carved_creeper")
    var STONE_CARVED_DERP: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_carved_derp")
    var STONE_CARVED_VILLAGER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_carved_villager")
    var STONE_CARVED_WITHER: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_carved_wither")
    var STONE_CARVED_WRITING: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_carved_writing")
    var STONE_COLUMN: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_column")
    var STONE_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_cut")
    var STONE_DIAMOND_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_diamond_pavers")
    var STONE_ENGRAVED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_engraved")
    var STONE_PANELS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_panels")
    var STONE_PAVERS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_pavers")
    var STONE_PILLAR: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_pillar")
    var STONE_POLISHED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_polished")
    var STONE_ROUGH_CUT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_rough_cut")
    var STONE_TILED: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "stone_tiled")
    var BLACK_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "black_terracotta_bricks")
    var BLACK_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "black_terracotta_pavement")
    var BLACK_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "black_terracotta_roof_tiles"
    )
    var BLACK_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "black_terracotta_tiles")
    var BLUE_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "blue_terracotta_bricks")
    var BLUE_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "blue_terracotta_pavement")
    var BLUE_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "blue_terracotta_roof_tiles"
    )
    var BLUE_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "blue_terracotta_tiles")
    var BROWN_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "brown_terracotta_bricks")
    var BROWN_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "brown_terracotta_pavement")
    var BROWN_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "brown_terracotta_roof_tiles"
    )
    var BROWN_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "brown_terracotta_tiles")
    var CYAN_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cyan_terracotta_bricks")
    var CYAN_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cyan_terracotta_pavement")
    var CYAN_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "cyan_terracotta_roof_tiles"
    )
    var CYAN_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "cyan_terracotta_tiles")
    var GRAY_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "gray_terracotta_bricks")
    var GRAY_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "gray_terracotta_pavement")
    var GRAY_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "gray_terracotta_roof_tiles"
    )
    var GRAY_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "gray_terracotta_tiles")
    var GREEN_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "green_terracotta_bricks")
    var GREEN_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "green_terracotta_pavement")
    var GREEN_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "green_terracotta_roof_tiles"
    )
    var GREEN_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "green_terracotta_tiles")
    var LIGHT_BLUE_TERRACOTTA_BRICKS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_blue_terracotta_bricks"
    )
    var LIGHT_BLUE_TERRACOTTA_PAVEMENT: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_blue_terracotta_pavement"
    )
    var LIGHT_BLUE_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_blue_terracotta_roof_tiles"
    )
    var LIGHT_BLUE_TERRACOTTA_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_blue_terracotta_tiles"
    )
    var LIGHT_GRAY_TERRACOTTA_BRICKS: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_gray_terracotta_bricks"
    )
    var LIGHT_GRAY_TERRACOTTA_PAVEMENT: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_gray_terracotta_pavement"
    )
    var LIGHT_GRAY_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_gray_terracotta_roof_tiles"
    )
    var LIGHT_GRAY_TERRACOTTA_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "light_gray_terracotta_tiles"
    )
    var LIME_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "lime_terracotta_bricks")
    var LIME_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "lime_terracotta_pavement")
    var LIME_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "lime_terracotta_roof_tiles"
    )
    var LIME_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "lime_terracotta_tiles")
    var MAGENTA_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "magenta_terracotta_bricks")
    var MAGENTA_TERRACOTTA_PAVEMENT: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "magenta_terracotta_pavement"
    )
    var MAGENTA_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "magenta_terracotta_roof_tiles"
    )
    var MAGENTA_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "magenta_terracotta_tiles")
    var ORANGE_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "orange_terracotta_bricks")
    var ORANGE_TERRACOTTA_PAVEMENT: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "orange_terracotta_pavement"
    )
    var ORANGE_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "orange_terracotta_roof_tiles"
    )
    var ORANGE_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "orange_terracotta_tiles")
    var PINK_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "pink_terracotta_bricks")
    var PINK_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "pink_terracotta_pavement")
    var PINK_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "pink_terracotta_roof_tiles"
    )
    var PINK_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "pink_terracotta_tiles")
    var PURPLE_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "purple_terracotta_bricks")
    var PURPLE_TERRACOTTA_PAVEMENT: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "purple_terracotta_pavement"
    )
    var PURPLE_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "purple_terracotta_roof_tiles"
    )
    var PURPLE_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "purple_terracotta_tiles")
    var RED_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "red_terracotta_bricks")
    var RED_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "red_terracotta_pavement")
    var RED_TERRACOTTA_ROOF_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "red_terracotta_roof_tiles")
    var RED_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "red_terracotta_tiles")
    var TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "terracotta_bricks")
    var TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "terracotta_pavement")
    var TERRACOTTA_ROOF_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "terracotta_roof_tiles")
    var TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "terracotta_tiles")
    var WHITE_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "white_terracotta_bricks")
    var WHITE_TERRACOTTA_PAVEMENT: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "white_terracotta_pavement")
    var WHITE_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "white_terracotta_roof_tiles"
    )
    var WHITE_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "white_terracotta_tiles")
    var YELLOW_TERRACOTTA_BRICKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "yellow_terracotta_bricks")
    var YELLOW_TERRACOTTA_PAVEMENT: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "yellow_terracotta_pavement"
    )
    var YELLOW_TERRACOTTA_ROOF_TILES: Block = Block(
        QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(),
        "yellow_terracotta_roof_tiles"
    )
    var YELLOW_TERRACOTTA_TILES: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "yellow_terracotta_tiles")
    var ACACIA_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "acacia_braced_planks")
    var ACACIA_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "acacia_supported_planks")
    var BIRCH_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "birch_braced_planks")
    var BIRCH_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "birch_supported_planks")
    var CARVED_ACACIA_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_acacia_planks")
    var CARVED_BIRCH_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_birch_planks")
    var CARVED_CRIMSON_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_crimson_planks")
    var CARVED_DARK_OAK_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_dark_oak_planks")
    var CARVED_JUNGLE_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_jungle_planks")
    var CARVED_OAK_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_oak_planks")
    var CARVED_SPRUCE_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_spruce_planks")
    var CARVED_WARPED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "carved_warped_planks")
    var CRIMSON_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "crimson_braced_planks")
    var CRIMSON_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "crimson_supported_planks")
    var DARKOAK_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "darkoak_braced_planks")
    var DARKOAK_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "darkoak_supported_planks")
    var JUNGLE_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "jungle_braced_planks")
    var JUNGLE_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "jungle_supported_planks")
    var OAK_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "oak_braced_planks")
    var OAK_CRATE_SIDE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "oak_crate_side")
    var OAK_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "oak_supported_planks")
    var SPRUCE_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "spruce_braced_planks")
    var SPRUCE_CRATE_SIDE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "spruce_crate_side")
    var SPRUCE_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "spruce_supported_planks")
    var VERTICAL_ACACIA_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_acacia_planks")
    var VERTICAL_BIRCH_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_birch_planks")
    var VERTICAL_CRIMSON_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_crimson_planks")
    var VERTICAL_DARK_OAK_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_dark_oak_planks")
    var VERTICAL_JUNGLE_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_jungle_planks")
    var VERTICAL_OAK_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_oak_planks")
    var VERTICAL_SPRUCE_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_spruce_planks")
    var VERTICAL_WARPED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "vertical_warped_planks")
    var WARPED_BRACED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "warped_braced_planks")
    var WARPED_SUPPORTED_PLANKS: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "warped_supported_planks")
    var GLOW_ORE: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "glow_ore")
    var MEW_1: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mew_1")
    var MEW_2: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mew_2")
    var MEW_3: Block =
        Block(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool(), "mew_3")

    var CRAMOMATIC: CramomaticBlock = CramomaticBlock(QuiltBlockSettings.of(Material.METAL).hardness(3.6F).requiresTool().dynamicShape().noOcclusion()).setBlockEntity { MythicalBlockEntities.CRAMOMATIC_BLOCK_ENTITY!! } as CramomaticBlock

    var EMPTY_LANDMARK_BLOCK: LandmarkEmptyBlock = LandmarkEmptyBlock(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool().dynamicShape().noOcclusion().isViewBlocking{ _, _, _ -> false}).setBlockEntity { MythicalBlockEntities.EMPTY_LANDMARK!! } as LandmarkEmptyBlock
    var NORMAL_LANDMARK: LandmarkBlock = LandmarkBlock(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool().dynamicShape().noOcclusion()).setBlockEntity { MythicalBlockEntities.NORMAL_LANDMARK!! } as LandmarkBlock
    var ELECTRIC_LANDMARK: LandmarkBlock = LandmarkBlock(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool().dynamicShape().noOcclusion()).setBlockEntity { MythicalBlockEntities.ELECTRIC_LANDMARK!! } as LandmarkBlock
    var DRAGON_LANDMARK: LandmarkBlock = LandmarkBlock(QuiltBlockSettings.of(Material.STONE).hardness(3.6F).requiresTool().dynamicShape().noOcclusion()).setBlockEntity { MythicalBlockEntities.DRAGON_LANDMARK!! } as LandmarkBlock
    fun registerBlocks() {
        for(block in BLOCKS) {
            registerBlock(block.value, block.key)
        }
        registerBlock(CRAMOMATIC, ResourceLocation("mythicalmod", "cramomatic"))
        registerBlock(NORMAL_LANDMARK, ResourceLocation("mythicalmod", "normal_landmark"))
        registerBlock(ELECTRIC_LANDMARK, ResourceLocation("mythicalmod", "electric_landmark"))
        registerBlock(DRAGON_LANDMARK, ResourceLocation("mythicalmod", "dragon_landmark"))
        registerBlock(EMPTY_LANDMARK_BLOCK, ResourceLocation("mythicalmod", "empty_landmark"))
    }

    private fun registerBlock(block: Block, rl: ResourceLocation) {
        Registry.register(Registry.BLOCK, rl, block)
    }
}