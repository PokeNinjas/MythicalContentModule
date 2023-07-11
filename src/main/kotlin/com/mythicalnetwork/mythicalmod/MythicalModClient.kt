package com.mythicalnetwork.mythicalmod

import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.moves.Moves
import com.mythicalnetwork.mythicalmod.content.base.AbstractPacket
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticItemRenderer
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticRenderer
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkItemRenderer
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkModel
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkRenderer
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalItems
import com.mythicalnetwork.mythicalmod.registry.MythicalPackets
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.world.entity.LivingEntity
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer

object MythicalModClient : ClientModInitializer {
    override fun onInitializeClient(mod: ModContainer?) {
        BlockEntityRenderers.register(MythicalBlockEntities.CRAMOMATIC_BLOCK_ENTITY!!) { CramomaticRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.NORMAL_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.DRAGON_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.ELECTRIC_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.FIRE_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.WATER_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.GRASS_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.ICE_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.FIGHTING_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.POISON_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.GROUND_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.FLYING_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.PSYCHIC_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.BUG_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.ROCK_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.GHOST_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.DARK_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.STEEL_LANDMARK!!) { LandmarkRenderer() }
        BlockEntityRenderers.register(MythicalBlockEntities.FAIRY_LANDMARK!!) { LandmarkRenderer() }
        AbstractPacket.registerClientReceiver(MythicalPackets.CRAMOMATIC_S2C_SYNC)
        AbstractPacket.registerClientReceiver(MythicalPackets.UNVALIDATED_SOUND)
        AbstractPacket.registerClientReceiver(MythicalPackets.RADAR_BIOME_DATA)
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
        GeoItemRenderer.registerItemRenderer(MythicalItems.ELECTRIC_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.DRAGON_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.NORMAL_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.FIRE_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.WATER_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.GRASS_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.ICE_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.FIGHTING_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.POISON_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.GROUND_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.FLYING_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.PSYCHIC_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.BUG_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.ROCK_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.GHOST_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.DARK_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.STEEL_LANDMARK.asItem(), LandmarkItemRenderer())
        GeoItemRenderer.registerItemRenderer(MythicalItems.FAIRY_LANDMARK.asItem(), LandmarkItemRenderer())
//        GeoArmorRenderer.registerArmorRenderer<LivingEntity>(RocketBootsRenderer(), MythicalItems.ROCKET_BOOTS)

        ColorProviderRegistry.ITEM.register(
            { itemStack, i ->
                if(itemStack.`is`(MythicalItems.TM)){
                    if(itemStack.hasTag()){
                        val template: MoveTemplate? = Moves.getByName(itemStack.tag!!.getString("move"))
                        if(template == null){
                            return@register -1
                        } else {
                            return@register template.elementalType.hue
                        }
                    }
                    return@register -1
                }
                return@register -1
            },
            MythicalItems.TM
        )
    }
}