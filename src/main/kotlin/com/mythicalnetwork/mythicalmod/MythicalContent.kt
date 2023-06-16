package com.mythicalnetwork.mythicalmod

import com.mojang.logging.LogUtils
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticPlayerHandler
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticRecipeJsonListener
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkSpawnDataJsonListener
import com.mythicalnetwork.mythicalmod.content.misc.rocketboots.RocketBootsItem
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalBlocks
import com.mythicalnetwork.mythicalmod.registry.MythicalComponentRegistry
import com.mythicalnetwork.mythicalmod.registry.MythicalItems
import dev.architectury.event.events.common.PlayerEvent
import dev.architectury.event.events.common.TickEvent
import dev.architectury.registry.ReloadListenerRegistry
import eu.pb4.placeholders.api.PlaceholderContext
import eu.pb4.placeholders.api.PlaceholderResult
import eu.pb4.placeholders.api.Placeholders
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents.EquipmentChange
import net.minecraft.Util
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.packs.PackType
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.animal.Cow
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.WitherSkull
import net.minecraft.world.level.Level
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents
import org.slf4j.Logger
import java.util.function.BiFunction


/**
 * With Kotlin, the Entrypoint can be defined in numerous ways. This is showcased on Fabrics' Github:
 * https://github.com/FabricMC/fabric-language-kotlin#entrypoint-samples
 */
class MythicalContent : ModInitializer {
    companion object {
        var SERVER: MinecraftServer? = null
        const val MODID = "mythicalmod"
        val LOGGER: Logger = LogUtils.getLogger()
        var PLACEHOLDER_LIST = mutableMapOf<ResourceLocation, BiFunction<PlaceholderContext, String?, PlaceholderResult>>()
        var CRAMOMATIC_HANDLER: CramomaticPlayerHandler? = null
        var CONFIG: MythicalModConfig = MythicalModConfig.createAndLoad()
        var entity: LivingEntity? = null

        fun asResource(str: String): ResourceLocation {
            return ResourceLocation(MODID, str)
        }
    }
    override fun onInitialize(mod: ModContainer?) {
        setupPlaceholders()
        MythicalBlocks.registerBlocks()
        MythicalItems.registerItems()
        MythicalBlockEntities.init()
        ServerLifecycleEvents.READY.register { server ->
            SERVER = server
            entity = server.getLevel(Level.OVERWORLD)?.let { Cow(EntityType.COW, it) }
        }

        ServerTickEvents.END.register { server ->
            if(CRAMOMATIC_HANDLER == null) {
                CRAMOMATIC_HANDLER = CramomaticPlayerHandler(server.getLevel(Level.OVERWORLD)!!)
                server.getLevel(Level.OVERWORLD)!!.levelData.getComponent(MythicalComponentRegistry.CRAMOMATIC).handler =
                    CRAMOMATIC_HANDLER
            } else {
                if(CRAMOMATIC_HANDLER!!.isEmpty()){
                    CramomaticPlayerHandler.toLoad.forEach { server.playerList.getPlayer(it.key)
                        ?.let { it1 -> CRAMOMATIC_HANDLER?.addPlayer(it1.uuid, it.value) } }
                }
            }
            if(!server.getLevel(Level.OVERWORLD)!!.isClientSide){
                CRAMOMATIC_HANDLER?.tick()
            }
        }

        ServerPlayConnectionEvents.DISCONNECT.register { handler, server ->
            CRAMOMATIC_HANDLER?.pausePlayer(handler.player.uuid)
        }
        ServerPlayConnectionEvents.JOIN.register { handler, sender, server ->
            CRAMOMATIC_HANDLER?.resumePlayer(handler.player.uuid)
        }
        ReloadListenerRegistry.register(PackType.SERVER_DATA, CramomaticRecipeJsonListener.INSTANCE)
        ReloadListenerRegistry.register(PackType.SERVER_DATA, LandmarkSpawnDataJsonListener.INSTANCE)
        ServerEntityEvents.EQUIPMENT_CHANGE.register { entity, slot, from, to ->
            if (entity is Player && !entity.level.isClientSide) {
                if (slot == EquipmentSlot.FEET) {
                    if (to.item is RocketBootsItem && !entity.isCreative) {
                        entity.addTag("rocketboots")
                    } else if ((!entity.isCreative || !entity.isSpectator) && to.item !is RocketBootsItem && entity.abilities.mayfly) {
                        entity.removeTag("rocketboots")
                    }
                }
            }
        }
        TickEvent.PLAYER_POST.register { player ->
            if (player.tags.contains("rocketboots") && !player.level.isClientSide && player.getItemBySlot(EquipmentSlot.FEET).item is RocketBootsItem) {
                player.abilities.mayfly = true
                player.onUpdateAbilities()
                if((player.level.gameTime % 1).toInt() == 0 && player.abilities.flying){

                    player.getItemBySlot(EquipmentSlot.FEET).hurtAndBreak(1, entity!!) { player1 ->
                        player1.broadcastBreakEvent(
                            EquipmentSlot.FEET
                        )
                    }
                }
            } else if ((!player.isCreative && !player.isSpectator) && !player.tags.contains("rocketboots") && player.abilities.mayfly && !player.level.isClientSide) {
                player.abilities.mayfly = false
                player.abilities.flying = false
                player.onUpdateAbilities()
                player.addTag("rocketbootsfalling")
            }
            if(player.tags.contains("rocketbootsfalling") && !player.level.isClientSide){
                if(player.isOnGround){
                    player.removeTag("rocketbootsfalling")
                } else {
                    player.fallDistance = 0f
                }
            }
        }
    }


    private fun setupPlaceholders(){
        Placeholders.register(ResourceLocation("player", "biome")) { ctx, arg ->
            ctx.player?.let { player ->
                PlaceholderResult.value(Component.translatable(Util.makeDescriptionId("biome", player.level.getBiome(player.blockPosition()).unwrapKey().get().location()).toString()))
            } ?: PlaceholderResult.invalid("Player not found")
        }
        PLACEHOLDER_LIST.forEach { (key, value) ->
            Placeholders.register(key) { ctx, arg ->
                value.apply(ctx, arg)
            }
        }
    }

}