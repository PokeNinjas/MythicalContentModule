package com.mythicalnetwork.mythicalmod

import com.mythicalnetwork.mythicalmod.content.base.AbstractPacket
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticItemRenderer
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticRenderer
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkModel
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkRenderer
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalItems
import com.mythicalnetwork.mythicalmod.registry.MythicalPackets
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.item.ItemProperties
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer

object MythicalModClient : ClientModInitializer {
    override fun onInitializeClient(mod: ModContainer?) {
        BlockEntityRenderers.register(MythicalBlockEntities.CRAMOMATIC_BLOCK_ENTITY!!) { CramomaticRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.NORMAL_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.DRAGON_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.ELECTRIC_LANDMARK!!) { LandmarkRenderer() }
        AbstractPacket.registerClientReceiver(MythicalPackets.CRAMOMATIC_S2C_SYNC)
        ItemProperties.register(MythicalItems.PROGRESS_BAR.asItem(), MythicalContent.asResource("progress")) { stack, level, entity, seed ->
            if(stack.item.equals(MythicalItems.PROGRESS_BAR.asItem())){
                if(stack.hasTag()){
                    return@register stack.tag!!.getFloat("progress") as Float
                } else {
                    return@register 0.0f
                }
            } else {
                return@register 0.0f
            }
        }

        GeoItemRenderer.registerItemRenderer(MythicalItems.CRAMOMATIC.asItem(), CramomaticItemRenderer())
    }
}