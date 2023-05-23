package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mythicalnetwork.mythicalmod.content.base.AbstractPacket
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import org.quiltmc.qsl.networking.api.PacketSender

class CramomaticSyncPacket(id: String) : AbstractPacket(id) {

    // this will be better, this is just to fake it client side.
    override fun handleS2CPacket(
        client: Minecraft,
        handler: ClientPacketListener,
        buf: FriendlyByteBuf,
        sender: PacketSender
    ) {
        val pos = buf.readBlockPos()
        val tag = buf.readNbt()
        client.execute {
            val blockEntity = client.level?.getBlockEntity(pos)
            if (blockEntity is CramomaticBlockEntity) {
                if (tag != null) {
                    blockEntity.setInstance(CramomaticInstance.load(tag))
                }
            }
        }
    }


}