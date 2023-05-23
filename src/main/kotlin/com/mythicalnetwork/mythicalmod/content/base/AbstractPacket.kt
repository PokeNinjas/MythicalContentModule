package com.mythicalnetwork.mythicalmod.content.base

import dev.architectury.networking.NetworkManager
import net.fabricmc.api.Environment
import net.fabricmc.fabric.mixin.event.interaction.ServerPlayNetworkHandlerMixin
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientPacketListener
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import org.quiltmc.loader.api.minecraft.ClientOnly
import org.quiltmc.qkl.library.brigadier.argument.identifier
import org.quiltmc.qsl.networking.api.PacketSender
import org.quiltmc.qsl.networking.api.ServerPlayNetworking
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking

abstract class AbstractPacket(identifier: String) {
    val identifier: ResourceLocation = ResourceLocation("mythicalmod", identifier)

    companion object {
        fun registerServerReceiver(packet: AbstractPacket) {
            val rl: ResourceLocation = packet.identifier
            ServerPlayNetworking.registerGlobalReceiver(rl, packet::handleC2SPacket)
        }

        @ClientOnly
        fun registerClientReceiver(packet: AbstractPacket) {
            val rl: ResourceLocation = packet.identifier
            ClientPlayNetworking.registerGlobalReceiver(rl, packet::handleS2CPacket)
        }
    }

    open fun handleC2SPacket(server: MinecraftServer, player: ServerPlayer, handler: ServerGamePacketListenerImpl, buf: FriendlyByteBuf, sender: PacketSender){

    }

    open fun handleS2CPacket(client: Minecraft, handler: ClientPacketListener, buf: FriendlyByteBuf, sender: PacketSender){

    }
}