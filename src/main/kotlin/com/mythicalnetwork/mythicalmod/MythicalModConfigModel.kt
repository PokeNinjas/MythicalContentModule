package com.mythicalnetwork.mythicalmod

import io.wispforest.owo.config.annotation.Config
import io.wispforest.owo.config.annotation.Modmenu

@Modmenu(modId = "mythicalmod")
@Config(name = "mythical-mod", wrapperName = "MythicalModConfig")
class MythicalModConfigModel {

    @JvmField
    var landmarkSpawnRange: Int = 16
    @JvmField
    var maxPlayerLandmarkCount: Int = 3
    @JvmField
    var perMaxLandmarkTimer: Int = 1200
}