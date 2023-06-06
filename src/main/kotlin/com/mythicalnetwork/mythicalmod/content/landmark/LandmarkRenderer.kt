package com.mythicalnetwork.mythicalmod.content.landmark

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
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
}