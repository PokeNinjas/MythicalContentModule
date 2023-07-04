package com.mythicalnetwork.mythicalmod

import io.wispforest.owo.config.annotation.Config
import io.wispforest.owo.config.annotation.Modmenu
import io.wispforest.owo.config.annotation.SectionHeader

@Modmenu(modId = "mythicalmod")
@Config(name = "mythical-mod", wrapperName = "MythicalModConfig")
class MythicalModConfigModel {

    @JvmField
    var landmarkSpawnRange: Int = 16
    @JvmField
    var maxPlayerLandmarkCount: Int = 3
    @JvmField
    var perMaxLandmarkTimer: Int = 1200

    @SectionHeader("Radar")
    @JvmField
    var defaultMaxChainLength: Int = 100
    @JvmField
    var spawnRadius: Int = 16
    @JvmField
    var spawnDelay: Int = 20
    @JvmField
    var scanDelay: Int = 100
    @JvmField
    var bucketsToCheck: List<String> = listOf("common", "uncommon", "rare", "ultra-rare")
}