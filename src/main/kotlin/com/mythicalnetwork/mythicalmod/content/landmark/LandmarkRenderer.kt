package com.mythicalnetwork.mythicalmod.content.landmark

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer

class LandmarkRenderer : GeoBlockRenderer<LandmarkBlockEntity>(LandmarkModel()) {

    override fun render(
        tile: LandmarkBlockEntity?,
        partialTick: Float,
        poseStack: PoseStack?,
        bufferSource: MultiBufferSource?,
        packedLight: Int
    ) {
        super.render(tile, partialTick, poseStack, bufferSource, packedLight)
    }

    override fun shouldRender(blockEntity: BlockEntity, pos: Vec3): Boolean {
        return true
    }

    override fun shouldRenderOffScreen(blockEntity: BlockEntity): Boolean {
        return true
    }
}