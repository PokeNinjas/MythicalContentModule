package com.mythicalnetwork.mythicalmod.registry

import com.mythicalnetwork.mythicalmod.content.base.AbstractPacket
import com.mythicalnetwork.mythicalmod.content.base.UnvalidatedPlaySoundPacket
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticSyncPacket
import com.mythicalnetwork.mythicalmod.content.radar.RadarBiomeDataPacket

object MythicalPackets {
    val CRAMOMATIC_S2C_SYNC: AbstractPacket = CramomaticSyncPacket("cramomatic_s2c_sync")
    val UNVALIDATED_SOUND: AbstractPacket = UnvalidatedPlaySoundPacket("unvalidated_sound")
    val RADAR_BIOME_DATA: AbstractPacket = RadarBiomeDataPacket("radar_biome_data")
}