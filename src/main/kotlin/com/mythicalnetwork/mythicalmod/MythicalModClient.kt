package com.mythicalnetwork.mythicalmod

import com.mythicalnetwork.mythicalmod.content.base.AbstractPacket
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticRenderer
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalPackets
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer

object MythicalModClient : ClientModInitializer {
    override fun onInitializeClient(mod: ModContainer?) {
        BlockEntityRenderers.register(MythicalBlockEntities.CRAMOMATIC_BLOCK_ENTITY!!) { CramomaticRenderer() }
        AbstractPacket.registerClientReceiver(MythicalPackets.CRAMOMATIC_S2C_SYNC)
    }
}