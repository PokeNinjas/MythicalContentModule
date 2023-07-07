package com.mythicalnetwork.mythicalmod.content.radar

import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.AbstractPacket
import net.fabricmc.fabric.api.util.NbtType
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.nbt.TagTypes
import net.minecraft.network.FriendlyByteBuf
import org.quiltmc.qsl.networking.api.PacketSender

class RadarBiomeDataPacket(id: String) : AbstractPacket(id) {
    override fun handleS2CPacket(
        client: Minecraft,
        handler: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        val tag: CompoundTag = buf.readNbt()!!
        MythicalContent.LOGGER.info("Received radar biome data packet.")
        client.execute {
            for(key in tag.allKeys){
                val list = tag.getList(key, Tag.TAG_STRING.toInt())
                for(i in 0 until list.size){
                    MythicalContent.LOGGER.info("$key : ${list.getString(i)}")
                    MythicalContent.SPECIES_BIOME_INFO.put(key, list.getString(i))
                }
            }
        }
    }
}