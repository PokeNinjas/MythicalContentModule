package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Matrix4f
import com.mojang.math.Vector3f
import foundry.veil.anim.Keyframe
import foundry.veil.anim.Path
import foundry.veil.math.Easings
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer
import kotlin.math.min

class CramomaticRenderer : GeoBlockRenderer<CramomaticBlockEntity>(CramomaticModel()) {
    val itemPath: Path = Path(listOf(
        Keyframe(Vec3(0.5, 0.75, -0.5), Vec3.ZERO, Vec3.ZERO, 20, Easings.Easing.linear),
        Keyframe(Vec3(0.5, 0.75, -0.5), Vec3.ZERO, Vec3.ZERO, 5, Easings.Easing.easeInQuad),
        Keyframe(Vec3(0.5, 1.1, -0.25), Vec3.ZERO, Vec3.ZERO, 5, Easings.Easing.easeInBounce),
        Keyframe(Vec3(0.5, 1.1, 0.0), Vec3.ZERO, Vec3.ZERO, 5, Easings.Easing.easeInBounce),
        Keyframe(Vec3(0.5, 1.1, 0.25), Vec3.ZERO, Vec3.ZERO, 5, Easings.Easing.easeInBounce),
        Keyframe(Vec3(0.5, 1.1, 0.75), Vec3.ZERO, Vec3.ZERO, 10, Easings.Easing.easeInBounce)
    ), false, true)
    private var oldPos: Vec3 = Vec3(-0.5, 0.75, -0.5)
    companion object {
        const val HALF_SQRT_3: Float = 0.05f
    }

    override fun render(
        tile: CramomaticBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int
    ) {
        poseStack.pushPose()
        super.render(tile, partialTick, poseStack, bufferSource, packedLight)
        poseStack.popPose()
        poseStack.scale(1.5f, 1.5f, 1.5f)
        poseStack.pushPose()
        rotate(poseStack, tile.blockState.getValue(HorizontalDirectionalBlock.FACING))
        if(tile.ticksSinceItemAdded == 0) {
            oldPos = Vec3(-0.5, 0.75, -0.5)
        }
        tile.getInstance()?.let { instance ->
            if(instance.getOutputTicks() > 0){
                oldPos = Vec3(0.5, 1.1, 0.75)
            }
        }
        val ticks = if(tile.ticksSinceItemAdded == -1) 0 else tile.ticksSinceItemAdded
        var pos: Vec3 = itemPath.frameAtProgress(ticks / 20f).position
        var outputTest: Int = 0
        tile.getInstance()?.let {
            outputTest = it.getOutputTicks()
            if(outputTest > 0) {
                pos = Minecraft.getInstance().player?.position() ?: Vec3.ZERO
            }
        }
        oldPos = oldPos.lerp(pos, 0.02)
        poseStack.translate(oldPos.x, oldPos.y, oldPos.z)
        var l = 0.0
        var m = 0.0
        if(tile.ticksSinceItemAdded > 0 || outputTest > 0) {
            l = ((tile.ticksSinceItemAdded + partialTick) / 180.0)
            val le = if(l > 0.8F) (l - 0.8F) / 0.2F else 0.0F
            m = min(le.toDouble(), 1.0)
            val randomSource: RandomSource = RandomSource.create(432L)
            val vertexConsumer: VertexConsumer = bufferSource.getBuffer(RenderType.lightning())
            poseStack.pushPose()
            val e = (l+l*l)/4.0*240.0
            var i = 0
            while(i < e){
                i++
                poseStack.mulPose(Vector3f.XP.rotationDegrees((randomSource.nextFloat() * 360.0f) + partialTick))
                poseStack.mulPose(Vector3f.YP.rotationDegrees((randomSource.nextFloat() * 360.0f) + partialTick))
                poseStack.mulPose(Vector3f.ZP.rotationDegrees((randomSource.nextFloat() * 360.0f) + partialTick))
                poseStack.mulPose(Vector3f.XP.rotationDegrees((randomSource.nextFloat() * 360.0f) + partialTick))
                poseStack.mulPose(Vector3f.YP.rotationDegrees((randomSource.nextFloat() * 360.0f) + partialTick))
                poseStack.mulPose(Vector3f.ZP.rotationDegrees((randomSource.nextFloat() * 360.0f + l * 90.0f).toFloat()))
                val o: Float = (randomSource.nextFloat() * 20.0f + 5.0f + m * 10.0f).toFloat() / 500.0f
                val p: Float = (randomSource.nextFloat() * 2.0f + 1.0f + m * 2.0f).toFloat() / 10.0f
                val matrix4f: Matrix4f = poseStack.last().pose()
                val q: Int = (255.0f * (1.0f - m)).toInt()
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

    private fun rotate(poseStack: PoseStack, facing: Direction){
        when (facing) {
            Direction.SOUTH -> {
                poseStack.translate(1.0, 0.0, 1.0)
                poseStack.mulPose(Vector3f.YP.rotationDegrees(180f))
            }
            Direction.WEST -> {
                poseStack.translate(0.0, 0.0, 1.0)
                poseStack.mulPose(Vector3f.YP.rotationDegrees(90f))
            }
            Direction.NORTH -> poseStack.mulPose(Vector3f.YP.rotationDegrees(0f))
            Direction.EAST -> {
                poseStack.translate(1.0, 0.0,0.0)
                poseStack.mulPose(Vector3f.YP.rotationDegrees(270f))
            }
            Direction.UP -> poseStack.mulPose(Vector3f.XP.rotationDegrees(90f))
            Direction.DOWN -> poseStack.mulPose(Vector3f.XN.rotationDegrees(90f))
        }
    }

    private fun vertex01(vertices: VertexConsumer, matrix: Matrix4f, alpha: Int) {
        vertices.vertex(matrix, 0.0f, 0.0f, 0.0f).color(255, 255, 255, 255).endVertex()
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