package com.mythicalnetwork.mythicalmod.content.base

import com.mythicalnetwork.mythicalmod.content.base.AbstractPacket
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundSource
import org.quiltmc.qsl.networking.api.PacketSender
import oshi.hardware.SoundCard

class UnvalidatedPlaySoundPacket(id: String) : AbstractPacket(id) {

    // this will be better, this is just to fake it client side.
    override fun handleS2CPacket(
        client: Minecraft,
        handler: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        val sound: ResourceLocation = buf.readResourceLocation()
        val category: SoundSource = buf.readEnum(SoundSource::class.java)
        val x: Double = buf.readDouble()
        val y: Double = buf.readDouble()
        val z: Double = buf.readDouble()
        val volume: Float = buf.readFloat()
        val pitch: Float = buf.readFloat()
        client.execute {
            if(client.soundManager.getSoundEvent(sound) != null) {
                client.level?.playSound(client.player, x, y, z, SoundEvent(sound), category, volume, pitch)
            }
        }
    }


}