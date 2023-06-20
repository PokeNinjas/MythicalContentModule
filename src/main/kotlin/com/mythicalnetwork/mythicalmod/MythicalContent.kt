package com.mythicalnetwork.mythicalmod

import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.pokemon.stats.Stats
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.logging.LogUtils
import com.mythicalnetwork.mythicalmod.content.alphas.*
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticPlayerHandler
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticRecipeJsonListener
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkSpawnDataJsonListener
import com.mythicalnetwork.mythicalmod.content.misc.rocketboots.RocketBootsItem
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalBlocks
import com.mythicalnetwork.mythicalmod.registry.MythicalComponentRegistry
import com.mythicalnetwork.mythicalmod.registry.MythicalItems
import dev.architectury.event.EventResult
import dev.architectury.event.events.common.EntityEvent
import dev.architectury.event.events.common.EntityEvent.LivingCheckSpawn
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
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.TargetGoal
import net.minecraft.world.entity.animal.Cow
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Snowball
import net.minecraft.world.entity.projectile.WitherSkull
import net.minecraft.world.level.Level
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents
import org.slf4j.Logger
import java.util.*
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

        CobblemonEvents.CAPTURE_CONDITIONS.subscribe { event ->
            AlphaHelper.alphaTest(event)
        }

        EntityEvent.ADD.register { entity, level ->
            if(level.isClientSide) return@register EventResult.pass()
            if(entity is PokemonEntity){
                if(entity.pokemon.aspects.contains("alpha") && !entity.pokemon.aspects.contains("alpha_defeated")){
                    LOGGER.info("Alpha pokemon found, max health is ${entity.health}")
                    entity.getAttribute(Attributes.MAX_HEALTH)?.addPermanentModifier(AttributeModifier("alpha",
                        entity.pokemon.getStat(Stats.HP).toDouble() * 10, AttributeModifier.Operation.ADDITION))
                    entity.health = entity.pokemon.getStat(Stats.HP).toFloat() * 10
                    LOGGER.info("Alpha pokemon edited, max health is ${entity.health}")
                    return@register EventResult.pass()
                }
            }
            EventResult.pass()
        }

        EntityEvent.LIVING_HURT.register { entity, source, amount ->
            if(source.entity is Snowball) {
                if((source.entity as Snowball).tags.any { tag -> tag.contains("pokemonid=") }){
                    val id: Int = (source.entity as Snowball).tags.first { tag -> tag.contains("pokemonid=") }.split("=")[1].toInt()
                    val pokemonEntity: PokemonEntity? = entity.level.getEntity(id) as PokemonEntity?
                    if(pokemonEntity != null) {
                        if(pokemonEntity.target != null) {
                            pokemonEntity.doHurtTarget(pokemonEntity.target!!)
                        }
                    }
                }
            }
            EventResult.pass()
        }

        CobblemonEvents.POKEMON_ENTITY_GOALS.subscribe { event ->
            if(event.entity.pokemon.aspects.contains("alpha")){
                event.entity.getAttribute(Attributes.MAX_HEALTH)?.addPermanentModifier(AttributeModifier("alpha",
                    event.entity.pokemon.getStat(Stats.HP).toDouble() * 2, AttributeModifier.Operation.ADDITION))
                event.entity.health = event.entity.pokemon.getStat(Stats.HP).toFloat() * 2
                event.goalSelector.addGoal(0, NearestAttackableTargetGoal(event.entity, ServerPlayer::class.java, true))
//                event.goalSelector.addGoal(0, HurtByTargetGoal(event.entity))
                if(event.entity.pokemon.hasLabels("melee")) {
                    LOGGER.info("Adding melee attack goal")
                    event.goalSelector.addGoal(2, PokemonMeleeAttackGoal(event.entity))
                }
                if(event.entity.pokemon.hasLabels("ranged")) {
                    LOGGER.info("Adding ranged attack goal")
                    event.goalSelector.addGoal(2, PokemonRangedAttackGoal(event.entity, event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED), 15))
                }
                if(event.entity.pokemon.hasLabels("beam")){
                    LOGGER.info("Adding beam attack goal")
                    event.goalSelector.addGoal(1, PokemonBreathAttackGoal(event.entity, event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED), 20, 10.0F, 60, 4800))
                }
                if(event.entity.pokemon.hasLabels("fireball")){
                    LOGGER.info("Adding fireball attack goal")
                    event.goalSelector.addGoal(1, PokemonFireballGoal(event.entity, event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED), 12f, 120))
                }
                if(event.entity.pokemon.hasLabels("lightning_bolt")){
                    LOGGER.info("Adding lightning bolt attack goal")
                    event.goalSelector.addGoal(1, PokemonLightningBoltGoal(event.entity, event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED), 16f, 120))
                }
                if(event.entity.pokemon.hasLabels("invisibility")){
                    LOGGER.info("Adding invisibility goal")
                    event.goalSelector.addGoal(1, PokemonInvisGoal(event.entity, event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED), 240))
                }
                if(event.entity.pokemon.hasLabels("teleportation")){
                    LOGGER.info("Adding teleportation goal")
                    event.goalSelector.addGoal(1, PokemonTeleportGoal(event.entity, event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED), 240))
                }
                if(event.entity.pokemon.hasLabels("kick")){
                    LOGGER.info("Adding kick goal")
                    event.goalSelector.addGoal(1, PokemonMegaKickGoal(event.entity, event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED), 90))
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