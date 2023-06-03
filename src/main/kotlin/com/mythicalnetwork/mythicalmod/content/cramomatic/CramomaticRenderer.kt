package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Matrix4f
import com.mojang.math.Vector3f
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EnderDragonRenderer
import net.minecraft.util.RandomSource
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer
import kotlin.math.min
import kotlin.math.sqrt

class CramomaticRenderer : GeoBlockRenderer<CramomaticBlockEntity>(CramomaticModel()) {
    companion object {
        val HALF_SQRT_3: Float = (sqrt(3.0) / 3.0).toFloat()
    }

    override fun render(
        tile: CramomaticBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        super.render(tile, partialTick, poseStack, bufferSource, packedLight)
        poseStack.pushPose()
        poseStack.translate(-0.5, 1.5, -0.5)
        var l = 0.0
        var m = 0.0
        if(tile.ticksSinceItemAdded > 0){
            l = ((tile.ticksSinceItemAdded + partialTick).toFloat() / 200.0)
            val le = if(l > 0.8F) (l - 0.8F) / 0.2F else 0.0F
            m = min(le.toDouble(), 1.0)
            val randomSource: RandomSource = RandomSource.create(432L)
            val vertexConsumer: VertexConsumer = bufferSource.getBuffer(RenderType.lightning())
            poseStack.pushPose()
            poseStack.translate(0.0,-1.0,-2.0)
            val e = (l+l*l)/2.0F * 240.0F
            var i = 0
            while(i < e){
                i++
                poseStack.mulPose(Vector3f.XP.rotationDegrees(randomSource.nextFloat() * 360.0f))
                poseStack.mulPose(Vector3f.YP.rotationDegrees(randomSource.nextFloat() * 360.0f))
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(randomSource.nextFloat() * 360.0f))
                poseStack.mulPose(Vector3f.XP.rotationDegrees(randomSource.nextFloat() * 360.0f))
                poseStack.mulPose(Vector3f.YP.rotationDegrees(randomSource.nextFloat() * 360.0f))
                poseStack.mulPose(Vector3f.ZP.rotationDegrees((randomSource.nextFloat() * 360.0f + l * 90.0f).toFloat()))
                val o: Float = (randomSource.nextFloat() * 20.0f + 5.0f + m * 10.0f).toFloat() / 250.0f
                val p: Float = (randomSource.nextFloat() * 2.0f + 1.0f + m * 2.0f).toFloat() / 5.0f
                val matrix4f: Matrix4f = poseStack.last().pose()
                val q: Int = (255.0f * (1.0f - m)).toInt() / 5
                vertex01(vertexConsumer, matrix4f, q)
                vertex2(vertexConsumer, matrix4f, o, p)
                vertex3(vertexConsumer, matrix4f, o, p)
                vertex01(vertexConsumer, matrix4f, q)
                vertex3(vertexConsumer, matrix4f, o, p)
                vertex4(vertexConsumer, matrix4f, o, p)
                vertex01(vertexConsumer, matrix4f, q)
                vertex4(vertexConsumer, matrix4f, o, p)
                vertex2(vertexConsumer, matrix4f, o, p)
            }
            poseStack.popPose()
        }

        poseStack.popPose()
    }

    private fun vertex01(vertices: VertexConsumer, matrix: Matrix4f, alpha: Int) {
        vertices.vertex(matrix, 0.0f, 0.0f, 0.0f).color(255, 255, 255, alpha).endVertex()
    }

    private fun vertex2(vertices: VertexConsumer, matrix: Matrix4f, y: Float, x: Float) {
        vertices.vertex(matrix, -HALF_SQRT_3 * x, y, -0.5f * x).color(255, 255, 255, 0).endVertex()
    }

    private fun vertex3(vertices: VertexConsumer, matrix: Matrix4f, y: Float, x: Float) {
        vertices.vertex(matrix, HALF_SQRT_3 * x, y, -0.5f * x).color(255, 255, 255, 0).endVertex()
    }

    private fun vertex4(vertices: VertexConsumer, matrix: Matrix4f, y: Float, z: Float) {
        vertices.vertex(matrix, 0.0f, y, 1.0f * z).color(255, 255, 255, 0).endVertex()
    }
}