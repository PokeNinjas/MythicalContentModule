package com.mythicalnetwork.mythicalmod

import ca.landonjw.gooeylibs2.api.UIManager
import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.events.entity.PokemonEntityGoalsEvent
import com.cobblemon.mod.common.api.moves.MoveSet
import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.stats.Stats
import com.cobblemon.mod.common.api.spawning.CobblemonSpawnPools
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnDetail
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.registry.BiomeIdentifierCondition
import com.cobblemon.mod.common.registry.BiomeTagCondition
import com.cobblemon.mod.common.util.sendParticlesServer
import com.mojang.logging.LogUtils
import com.mythicalnetwork.mythicalmod.content.alphas.*
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticPlayerHandler
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticRecipeJsonListener
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkSpawnDataJsonListener
import com.mythicalnetwork.mythicalmod.content.misc.rocketboots.RocketBootsItem
import com.mythicalnetwork.mythicalmod.content.misc.tms.TMItem
import com.mythicalnetwork.mythicalmod.content.misc.tms.TMScreen
import com.mythicalnetwork.mythicalmod.content.radar.RadarItem
import com.mythicalnetwork.mythicalmod.content.radar.RadarItemComponent
import com.mythicalnetwork.mythicalmod.registry.*
import com.mythicalnetwork.mythicalmod.registry.MythicalComponentRegistry.RADAR_ITEM
import com.mythicalnetwork.mythicalmod.util.*
import com.pokeninjas.kingdoms.fabric.dto.database.impl.User
import dev.architectury.event.CompoundEventResult
import dev.architectury.event.EventResult
import dev.architectury.event.events.common.EntityEvent
import dev.architectury.event.events.common.EntityEvent.LivingCheckSpawn
import dev.architectury.event.events.common.InteractionEvent
import dev.architectury.event.events.common.PlayerEvent
import dev.architectury.event.events.common.TickEvent
import dev.architectury.registry.ReloadListenerRegistry
import eu.pb4.placeholders.api.PlaceholderContext
import eu.pb4.placeholders.api.PlaceholderResult
import eu.pb4.placeholders.api.Placeholders
import io.wispforest.owo.offline.DataSavedEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents.EquipmentChange
import net.minecraft.Util
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.ClickEvent
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.HoverEvent
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.TargetGoal
import net.minecraft.world.entity.animal.Cow
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Snowball
import net.minecraft.world.entity.projectile.WitherSkull
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.loader.api.QuiltLoader
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.lifecycle.api.event.ServerLifecycleEvents
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents
import org.quiltmc.qsl.lifecycle.api.event.ServerWorldLoadEvents
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents
import org.quiltmc.qsl.networking.api.ServerPlayNetworking
import org.slf4j.Logger
import java.util.*
import java.util.function.BiFunction
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier


/**
 * With Kotlin, the Entrypoint can be defined in numerous ways. This is showcased on Fabrics' Github:
 * https://github.com/FabricMC/fabric-language-kotlin#entrypoint-samples
 */
class MythicalContent : ModInitializer {
    companion object {
        var SERVER: MinecraftServer? = null
        const val MODID = "mythicalmod"
        val LOGGER: Logger = LogUtils.getLogger()
        var PLACEHOLDER_LIST =
            mutableMapOf<ResourceLocation, BiFunction<PlaceholderContext, String?, PlaceholderResult>>()
        var CRAMOMATIC_HANDLER: CramomaticPlayerHandler? = null
        var CONFIG: MythicalModConfig = MythicalModConfig.createAndLoad()
        var entity: LivingEntity? = null

        fun asResource(str: String): ResourceLocation {
            return ResourceLocation(MODID, str)
        }

        val ALPHA_GOALS: Map<String, Function<PokemonEntityGoalsEvent, Goal>> = mapOf(
            "melee" to Function { event -> PokemonMeleeAttackGoal(event.entity) },
            "ranged" to Function { event ->
                PokemonRangedAttackGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    15
                )
            },
            "beam" to Function { event ->
                PokemonBreathAttackGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    20,
                    10.0F,
                    60,
                    4800
                )
            },
            "fireball" to Function { event ->
                PokemonFireballGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    12f,
                    120
                )
            },
            "lightning_bolt" to Function { event ->
                PokemonLightningBoltGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    16f,
                    120
                )
            },
            "invisibility" to Function { event ->
                PokemonInvisGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    240
                )
            },
            "teleport" to Function { event ->
                PokemonTeleportGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    240
                )
            },
            "mega_kick" to Function { event ->
                PokemonMegaKickGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    90
                )
            },
            "dream_eater" to Function { event ->
                PokemonDreamEaterGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    12.0f,
                    480
                )
            },
            "ball" to Function { event ->
                PokemonBallAttackGoal(
                    event.entity,
                    event.entity.getAttributeValue(Attributes.MOVEMENT_SPEED),
                    120
                )
            },
        )
        fun sendDebugMessage(string: String){
            if(CONFIG.enableDebugPrints()){
                LOGGER.info(string)
            }
        }
        val SPECIES_BIOME_INFO: MultiMap<String, String> = MultiMap()
        fun getSpawnBiomes() {
            SPECIES_BIOME_INFO.clear()
            CobblemonSpawnPools.WORLD_SPAWN_POOL.details.forEach { detail ->
                if (detail is PokemonSpawnDetail) {
                    detail.conditions.forEach { condition ->
                        condition.biomes?.forEach { biome ->
                            if (biome is BiomeIdentifierCondition) {
                                val species = detail.pokemon.species
                                if(species != null){
                                    sendDebugMessage("Adding ${biome.identifier} to ${species}")
                                    SPECIES_BIOME_INFO.put(species, "biome."+biome.identifier.toLanguageKey())
                                }
                            } else if(biome is BiomeTagCondition){
                                val species = detail.pokemon.species
                                if(species != null){
                                    sendDebugMessage("Adding ${biome.tag.location} to ${species}")
                                    SPECIES_BIOME_INFO.put(species, "biome."+biome.tag.location.toLanguageKey())
                                }
                            }
                        }
                    }

                }
            }
        }
        fun formatIvRangeValues(): Map<IntRange, Int> {
            val ivs: String = CONFIG.ivRangeValues()
            val ivSubString = ivs.split(",")
            val ivMap: MutableMap<IntRange, Int> = mutableMapOf()
            for (iv in ivSubString) {
                iv.replace(" ", "")
                val ivSplit = iv.split(":")
                val ivRange = ivSplit[0].replace(" ", "").split("-")
                val ivStart = ivRange[0].replace(" ", "").toInt()
                val ivEnd = ivRange[1].replace(" ", "").toInt()
                val ivValue = ivSplit[1].replace(" ", "").toInt()
                ivMap[ivStart..ivEnd] = ivValue
            }
            return ivMap
        }

        fun formatHiddenAbilityChance(): Map<IntRange, Float> {
            val hiddenAbilityChance: String = CONFIG.hiddenAbilityChance()
            val hiddenAbilitySubString = hiddenAbilityChance.split(",")
            val hiddenAbilityMap: MutableMap<IntRange, Float> = mutableMapOf()
            for (hiddenAbility in hiddenAbilitySubString) {
                hiddenAbility.replace(" ", "")
                val hiddenAbilitySplit = hiddenAbility.split(":")
                val hiddenAbilityRange = hiddenAbilitySplit[0].replace(" ", "").split("-")
                val hiddenAbilityStart = hiddenAbilityRange[0].replace(" ", "").toInt()
                val hiddenAbilityEnd = hiddenAbilityRange[1].replace(" ", "").toInt()
                val hiddenAbilityValue = 1f / hiddenAbilitySplit[1].replace(" ", "").replace("1/", "").toFloat()
                hiddenAbilityMap[hiddenAbilityStart..hiddenAbilityEnd] = hiddenAbilityValue
            }
            return hiddenAbilityMap
        }

        fun formatShinyChance(): Map<IntRange, Float> {
            val shinyChance: String = CONFIG.shinyChance()
            val shinySubString = shinyChance.split(",")
            val shinyMap: MutableMap<IntRange, Float> = mutableMapOf()
            for (shiny in shinySubString) {
                shiny.replace(" ", "")
                val shinySplit = shiny.split(":")
                val shinyRange = shinySplit[0].replace(" ", "").split("-")
                val shinyStart = shinyRange[0].replace(" ", "").toInt()
                val shinyEnd = shinyRange[1].replace(" ", "").toInt()
                val shinyValue = 1f / shinySplit[1].replace(" ", "").replace("1/", "").toFloat()
                shinyMap[shinyStart..shinyEnd] = shinyValue
            }
            return shinyMap
        }

        fun getBiomesForSpecies(species: String): List<String> {
            return SPECIES_BIOME_INFO.get(species)?.toCollection(mutableListOf()) ?: mutableListOf()
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
            getSpawnBiomes()
            server.playerList.players.forEach { player ->
                val tag: CompoundTag = CompoundTag()
                for((key, value) in SPECIES_BIOME_INFO.interator()){
                    val list = ListTag()
                    if (value != null) {
                        for(biome in value){
                            list.add(StringTag.valueOf(biome))
                        }
                    }
                    tag.put(key, list)
                }
                val buf: FriendlyByteBuf = PacketByteBufs.create()
                buf.writeNbt(tag)
                ServerPlayNetworking.send(player, MythicalPackets.RADAR_BIOME_DATA.identifier, buf)
            }
        }

        InteractionEvent.INTERACT_ENTITY.register { player, entity, hand ->
            if(player.level.isClientSide) return@register EventResult.pass()
            if(entity is PokemonEntity && entity.pokemon.getOwnerPlayer() == player) {
                val pokemon: Pokemon = entity.pokemon
                val tmMove: String = if(player.getItemInHand(hand).item is TMItem) {
                    if(player.getItemInHand(hand).tag != null) {
                        player.getItemInHand(hand).tag!!.getString("move")
                    } else {
                        return@register EventResult.pass()
                    }
                } else {
                    return@register EventResult.pass()
                }
                val move: MoveTemplate = pokemon.form.moves.tmMoves.first { it.name == tmMove } ?: return@register EventResult.pass()
                if(pokemon.allAccessibleMoves.contains(move)) return@register EventResult.pass()
                UIManager.openUIForcefully(player as ServerPlayer, TMScreen(pokemon, player.getItemInHand(hand), pokemon.moveSet.getMoves(), move))
            }
            return@register EventResult.pass()
        }

        ServerTickEvents.END.register { server ->
            if (CRAMOMATIC_HANDLER == null) {
                CRAMOMATIC_HANDLER = CramomaticPlayerHandler(server.getLevel(Level.OVERWORLD)!!)
                server.getLevel(Level.OVERWORLD)!!.levelData.getComponent(MythicalComponentRegistry.CRAMOMATIC).handler =
                    CRAMOMATIC_HANDLER
            } else {
                if (CRAMOMATIC_HANDLER!!.isEmpty()) {
                    CramomaticPlayerHandler.toLoad.forEach {
                        server.playerList.getPlayer(it.key)
                            ?.let { it1 -> CRAMOMATIC_HANDLER?.addPlayer(it1.uuid, it.value) }
                    }
                }
            }
            if (!server.getLevel(Level.OVERWORLD)!!.isClientSide) {
                CRAMOMATIC_HANDLER?.tick()
            }

            if ((server.overworld().gameTime % 216000).toInt() == 0) {
                getSpawnBiomes()
                val tag: CompoundTag = CompoundTag()
                for((key, value) in SPECIES_BIOME_INFO.interator()){
                    val list = ListTag()
                    if (value != null) {
                        for(biome in value){
                            list.add(StringTag.valueOf(biome))
                        }
                    }
                    tag.put(key, list)
                }
                val buf: FriendlyByteBuf = PacketByteBufs.create()
                buf.writeNbt(tag)
                server.playerList.players.forEach { player ->
                    ServerPlayNetworking.send(player, MythicalPackets.RADAR_BIOME_DATA.identifier, buf)
                }
            }
        }

        ServerPlayConnectionEvents.DISCONNECT.register { handler, server ->
            CRAMOMATIC_HANDLER?.pausePlayer(handler.player.uuid)
            handler.player.level.getEntitiesOfClass(PokemonEntity::class.java, handler.player.boundingBox.inflate(64.0)) { entity ->
                entity.tags.contains(
                    handler.player.uuid.toString()
                )
            }.forEach { pokemon ->
                pokemon.discard()
            }
            handler.player.inventory.items.filter { it -> it.`is`(MythicalItems.RADAR) }.forEach { itemStack ->
                val component: RadarItemComponent = RADAR_ITEM.get(itemStack)
                component.setActive(false)
                sendDebugMessage("Chain length for player ${handler.player.name} for species ${component.getSpecies()}: "+component.getChainLength().toString())
            }
        }
        ServerPlayConnectionEvents.JOIN.register { handler, sender, server ->
            CRAMOMATIC_HANDLER?.resumePlayer(handler.player.uuid)
            val tag: CompoundTag = CompoundTag()
            for((key, value) in SPECIES_BIOME_INFO.interator()){
                val list = ListTag()
                if (value != null) {
                    for(biome in value){
                        list.add(StringTag.valueOf(biome))
                    }
                }
                tag.put(key, list)
            }
            val buf: FriendlyByteBuf = PacketByteBufs.create()
            buf.writeNbt(tag)
            sender.sendPacket(MythicalPackets.RADAR_BIOME_DATA.identifier, buf)
            handler.player.inventory.items.filter { it -> it.`is`(MythicalItems.RADAR) }.forEach { itemStack ->
                val component: RadarItemComponent = RADAR_ITEM.get(itemStack)
                component.setActive(false)
                sendDebugMessage("Chain length for player ${handler.player.name} for species ${component.getSpecies()}: "+component.getChainLength().toString())
            }
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
            if (player.tags.contains("rocketboots") && !player.level.isClientSide && player.getItemBySlot(EquipmentSlot.FEET).item is RocketBootsItem && kingdomsCheck(
                    player as ServerPlayer
                )
            ) {
                player.abilities.mayfly = true
                player.onUpdateAbilities()
                if ((player.level.gameTime % 20).toInt() == 0 && player.abilities.flying) {
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
            if (player.tags.contains("rocketbootsfalling") && !player.level.isClientSide) {
                if (player.isOnGround) {
                    player.removeTag("rocketbootsfalling")
                } else {
                    player.fallDistance = 0f
                }
            }
            if(!player.level.isClientSide && player.level.gameTime % 20 == 0L){
                Cobblemon.storage.getParty(player as ServerPlayer).filter { it.aspects.contains("radar_spawned") }.forEach { PokemonProperties.parse("radar_spawned=false").apply(it) }
                Cobblemon.storage.getPC(player.uuid).filter { it.aspects.contains("radar_spawned") }.forEach { PokemonProperties.parse("radar_spawned=false").apply(it) }
            }
        }

        EntityEvent.ADD.register { entity, level ->
            if(level.isClientSide) return@register EventResult.pass()
            if (entity is PokemonEntity){
                if(entity.owner != null && entity.pokemon.aspects.contains("radar_spawned")){
                    PokemonProperties.parse("radar_spawned=false").apply(entity)
                }
            }
            return@register EventResult.pass()
        }

        CobblemonEvents.CAPTURE_CONDITIONS.subscribe { event ->
            AlphaHelper.alphaTest(event)
            RadarItem.testRadarSpawnedPokemon(event)
        }
        InteractionEvent.RIGHT_CLICK_ITEM.register { player, hand ->
            if(player.level.isClientSide) return@register CompoundEventResult.pass()
            val item = player.getItemInHand(hand)
            if(item.`is`(Items.GLOW_INK_SAC)){
                getSpawnBiomes()
                val tag: CompoundTag = CompoundTag()
                for((key, value) in SPECIES_BIOME_INFO.interator()){
                    val list = ListTag()
                    if (value != null) {
                        for(biome in value){
                            list.add(StringTag.valueOf(biome))
                        }
                    }
                    tag.put(key, list)
                }
                val buf: FriendlyByteBuf = PacketByteBufs.create()
                buf.writeNbt(tag)
                player.level.server!!.playerList.players.forEach { player ->
                    ServerPlayNetworking.send(player, MythicalPackets.RADAR_BIOME_DATA.identifier, buf)
                }
            }
            return@register CompoundEventResult.pass()
        }
        CobblemonEvents.POKEMON_CAPTURED.subscribe { event ->
            val hasRadar: Boolean = event.player.getSpeciesRadar(event.pokemon.species) != ItemStack.EMPTY
            var hasPlayedParticles = false
            event.player.getAllNonMatchingRadars(event.pokemon.species.name).forEach { radar ->
                val component: RadarItemComponent = RADAR_ITEM.get(radar)
                if (!component.isActive()) return@forEach
                radar.hurtAndBreak(radar.maxDamage, event.player) { player ->
                    player.broadcastBreakEvent(EquipmentSlot.MAINHAND)
                }
                if(!event.player.level.isClientSide){
                    event.player.level.getEntitiesOfClass(PokemonEntity::class.java, event.player.boundingBox.inflate(64.0)) { entity ->
                        entity.tags.contains(
                            event.player.uuid.toString()
                        ) && entity.pokemon.species.name == component.getSpecies()
                    }.forEach { pokemon ->
                        pokemon.discard()
                    }
                    if(!hasPlayedParticles) {
                        val serverLevel: ServerLevel = event.player.level as ServerLevel
                        serverLevel.sendParticlesServer(
                            ItemParticleOption(
                                ParticleTypes.ITEM,
                                MythicalItems.RADAR.defaultInstance
                            ), event.player.eyePosition, 20, Vec3(0.5, 0.0, 0.5), 0.05
                        )
                        serverLevel.playSound(
                            null,
                            event.player,
                            SoundEvents.ITEM_BREAK,
                            SoundSource.PLAYERS,
                            1.0f,
                            1.0f
                        )
                        event.player.sendSystemMessage(Component.literal("Your radar broke!"))
                        hasPlayedParticles = true
                    }
                }
            }
            if (hasRadar) {
                val radar: ItemStack = event.player.getSpeciesRadar(event.pokemon.species)
                if (radar == ItemStack.EMPTY) return@subscribe
                val component: RadarItemComponent = RADAR_ITEM.get(radar)
                if (!component.isActive()) return@subscribe
                component.setChainLength(component.getChainLength() + 1)
                if (component.getChainLength() >= component.getMaxLength()) {
                    radar.hurtAndBreak(radar.maxDamage, event.player) { player ->
                        player.broadcastBreakEvent(EquipmentSlot.MAINHAND)
                    }
                    if (!event.player.level.isClientSide) {
                        event.player.level.getEntitiesOfClass(PokemonEntity::class.java, event.player.boundingBox.inflate(64.0)) { entity ->
                            entity.tags.contains(
                                event.player.uuid.toString()
                            ) && entity.pokemon.species.name == component.getSpecies()
                        }.forEach { pokemon ->
                            pokemon.discard()
                        }
                        val serverLevel: ServerLevel = event.player.level as ServerLevel
                        serverLevel.sendParticlesServer(
                            ItemParticleOption(
                                ParticleTypes.ITEM,
                                MythicalItems.RADAR.defaultInstance
                            ), event.player.eyePosition, 20, Vec3(0.5, 0.0, 0.5), 0.05
                        )
                        serverLevel.playSound(
                            null,
                            event.player,
                            SoundEvents.ITEM_BREAK,
                            SoundSource.PLAYERS,
                            1.0f,
                            1.0f
                        )
                        event.player.sendSystemMessage(Component.literal("Your radar broke!"))
                    }
                }
            }
            if (event.pokemon.aspects.contains("radar_spawned")) {
                if (event.player.level is ServerLevel) {
                    PokemonProperties.parse("radar_spawned=false").apply(event.pokemon)
                    val radar: ItemStack = event.player.getSpeciesRadar(event.pokemon.species)
                    if (radar == ItemStack.EMPTY) return@subscribe
                    val component: RadarItemComponent = RADAR_ITEM.get(radar)
                    if (!component.isActive()) return@subscribe
                    component.applyChainModifiers(event.player.level as ServerLevel, event.pokemon)
                }
            }
        }

        CobblemonEvents.BATTLE_VICTORY.subscribe { event ->
            if (event.battle.isPvW) {
                if (event.battle.actors.any { a -> a is PlayerBattleActor }) {
                    val player: PlayerBattleActor =
                        event.battle.actors.first { a -> a is PlayerBattleActor } as PlayerBattleActor
                    var hasPlayedParticle: Boolean = false
                    player.entity!!.getAllNonMatchingRadars(event.battle.actors.first { p -> p !is PlayerBattleActor }.pokemonList.first().originalPokemon.species.name)
                        .filter { RADAR_ITEM.get(it).isActive() }.also {
                            if (it.isNotEmpty()) {
                                if (!player.entity!!.level.isClientSide && !hasPlayedParticle) {
                                    val serverLevel: ServerLevel = player.entity!!.level as ServerLevel
                                    serverLevel.sendParticlesServer(
                                        ItemParticleOption(
                                            ParticleTypes.ITEM,
                                            MythicalItems.RADAR.defaultInstance
                                        ), player.entity!!.eyePosition, 20, Vec3(0.5, 0.0, 0.5), 0.05
                                    )
                                    serverLevel.playSound(
                                        null,
                                        player.entity!!,
                                        SoundEvents.ITEM_BREAK,
                                        SoundSource.PLAYERS,
                                        1.0f,
                                        1.0f
                                    )
                                    hasPlayedParticle = true
                                }
                            }
                        }.forEach { radar ->
                            val component: RadarItemComponent = RADAR_ITEM.get(radar)
                            if (!component.isActive()) return@forEach
                            radar.hurtAndBreak(radar.maxDamage, player.entity!!) { player1 ->
                                player1.broadcastBreakEvent(EquipmentSlot.MAINHAND)
                                player1.sendSystemMessage(Component.literal("Your radar broke!"))
                            }
                            player.entity!!.level.getEntitiesOfClass(PokemonEntity::class.java, player.entity!!.boundingBox.inflate(64.0)) { entity ->
                                entity.tags.contains(
                                    player.entity!!.uuid.toString()
                                ) && entity.pokemon.species.name == component.getSpecies()
                            }.forEach { pokemon ->
                                pokemon.discard()
                            }
                        }
                }
            }
        }

        EntityEvent.ADD.register { entity, level ->
            if (level.isClientSide) return@register EventResult.pass()
            if (entity is PokemonEntity) {
                if (entity.pokemon.aspects.contains("alpha") && !entity.pokemon.aspects.contains("alpha_defeated")) {
                    sendDebugMessage("Alpha pokemon found, max health is ${entity.health}")
                    entity.getAttribute(Attributes.MAX_HEALTH)?.addPermanentModifier(
                        AttributeModifier(
                            "alpha",
                            entity.pokemon.getStat(Stats.HP).toDouble() * 10, AttributeModifier.Operation.ADDITION
                        )
                    )
                    entity.health = entity.pokemon.getStat(Stats.HP).toFloat() * 10
                    sendDebugMessage("Alpha pokemon edited, max health is ${entity.health}")
                    return@register EventResult.pass()
                }
            }
            EventResult.pass()
        }

        EntityEvent.LIVING_HURT.register { entity, source, amount ->
            if (source.entity is Snowball) {
                if ((source.entity as Snowball).tags.any { tag -> tag.contains("pokemonid=") }) {
                    val id: Int = (source.entity as Snowball).tags.first { tag -> tag.contains("pokemonid=") }
                        .split("=")[1].toInt()
                    val pokemonEntity: PokemonEntity? = entity.level.getEntity(id) as PokemonEntity?
                    if (pokemonEntity != null) {
                        if (pokemonEntity.target != null) {
                            pokemonEntity.doHurtTarget(pokemonEntity.target!!)
                        }
                    }
                }
            }
            EventResult.pass()
        }


        CobblemonEvents.POKEMON_ENTITY_GOALS.subscribe { event ->
            if (event.entity.pokemon.aspects.contains("alpha")) {
                event.entity.getAttribute(Attributes.MAX_HEALTH)?.addPermanentModifier(
                    AttributeModifier(
                        "alpha",
                        event.entity.pokemon.getStat(Stats.HP).toDouble() * 2, AttributeModifier.Operation.ADDITION
                    )
                )
                event.entity.health = event.entity.pokemon.getStat(Stats.HP).toFloat() * 2
                event.goalSelector.addGoal(0, NearestAttackableTargetGoal(event.entity, ServerPlayer::class.java, true))
                event.goalSelector.addGoal(0, HurtByTargetGoal(event.entity))
                println(ALPHA_GOALS.keys.toString())
                ALPHA_GOALS.forEach { (name, goal) ->
                    println("Checking for $name")
                    if (event.entity.pokemon.hasLabels(name)) {
                        event.goalSelector.addGoal(2, goal.apply(event))
                    }
                }
            }
        }
    }


    private fun setupPlaceholders() {
        Placeholders.register(ResourceLocation("player", "biome")) { ctx, arg ->
            ctx.player?.let { player ->
                PlaceholderResult.value(
                    Component.translatable(
                        Util.makeDescriptionId(
                            "biome",
                            player.level.getBiome(player.blockPosition()).unwrapKey().get().location()
                        ).toString()
                    )
                )
            } ?: PlaceholderResult.invalid("Player not found")
        }
        PLACEHOLDER_LIST.forEach { (key, value) ->
            Placeholders.register(key) { ctx, arg ->
                value.apply(ctx, arg)
            }
        }
    }

    private fun kingdomsCheck(player: ServerPlayer): Boolean {
        if (QuiltLoader.isModLoaded("kingdoms")) {
            if (KingdomsHelper.isInKingdom(player)) {
                return true
            } else {
                return false
            }
        } else {
            return true
        }
    }

}