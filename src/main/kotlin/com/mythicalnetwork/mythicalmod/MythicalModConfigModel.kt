package com.mythicalnetwork.mythicalmod

import io.wispforest.owo.config.annotation.Config
import io.wispforest.owo.config.annotation.Modmenu
import io.wispforest.owo.config.annotation.SectionHeader

@Modmenu(modId = "mythicalmod")
@Config(name = "mythical-mod", wrapperName = "MythicalModConfig")
class MythicalModConfigModel {
    @SectionHeader("General")
    @JvmField
    var enableDebugPrints: Boolean = false

    @SectionHeader("Landmarks")
    @JvmField
    var comment: String = "Landmark Config"
    @JvmField
    var landmarkSpawnRange: Int = 16
    @JvmField
    var maxPlayerLandmarkCount: Int = 3
    @JvmField
    var perMaxLandmarkTimer: Int = 1200

    @SectionHeader("Radar")
    @JvmField
    var _comment: String = "Radar Config"
    @JvmField
    var defaultMaxChainLength: Int = 200
    @JvmField
    var spawnRadius: Int = 16
    @JvmField
    var spawnDelay: Int = 20
    @JvmField
    var spawnChance: Float = 0.05f
    @JvmField
    var scanDelay: Int = 100
    @JvmField
    var bucketsToCheck: List<String> = listOf("common", "uncommon", "rare", "ultra-rare")
    @JvmField
    var blacklistedSpecies: List<String> = listOf("xerneas", "mewtwo")
    @JvmField
    var labelsToIgnore: List<String> = listOf("legendary", "mythical", "ultra-beast")
    @JvmField
    var ivRangeValues: String = "0-49: 0, 50-99: 1, 100-149: 2, 150-200: 3"
    @JvmField
    var shinyChance: String = "0-49: 1/8192, 50-99: 1/4096, 100-149: 1/2048, 150-200: 1/1024"
    @JvmField
    var hiddenAbilityChance: String = "0-49: 1/512, 50-99: 1/256, 100-149: 1/128, 150-200: 1/64"
}