package com.mythicalnetwork.mythicalmod.content.radar

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.events.pokemon.PokeballCaptureConditionsEvent
import com.cobblemon.mod.common.api.spawning.CobblemonWorldSpawnerManager
import com.cobblemon.mod.common.api.spawning.SpawnBucket
import com.cobblemon.mod.common.api.spawning.SpawnCause
import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawner
import com.cobblemon.mod.common.api.spawning.spawner.SpawningArea
import com.cobblemon.mod.common.api.text.red
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.google.gson.JsonElement
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.registry.MythicalComponentRegistry.RADAR_ITEM
import com.mythicalnetwork.mythicalmod.registry.MythicalGroups
import com.mythicalnetwork.mythicalmod.util.ClientUtils
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import java.util.*

class RadarItem(properties: Properties) : Item(properties.tab(MythicalGroups.MYTHICAL_ITEMS).durability(100)) {


    override fun inventoryTick(stack: ItemStack, world: Level, entity: Entity, slot: Int, selected: Boolean) {
        super.inventoryTick(stack, world, entity, slot, selected)
        if (world.isClientSide) {
            return
        }
        if (entity !is ServerPlayer) {
            return
        }
        val component: RadarItemComponent = RADAR_ITEM.get(stack)
        component.tick(entity)
    }

    override fun isFoil(stack: ItemStack): Boolean {
        return RADAR_ITEM.get(stack).isActive()
    }

    override fun use(world: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        if (world.isClientSide) {
            return super.use(world, player, hand)
        }
        val component: RadarItemComponent = RADAR_ITEM.get(player.getItemInHand(hand))
        component.setActive(!component.isActive())
        if (component.isActive()) {
            player.playSound(SoundEvents.ALLAY_HURT, 1.0f, 0.75f)
        }
        return super.use(world, player, hand)
    }

    override fun appendHoverText(
        stack: ItemStack,
        world: Level?,
        tooltip: MutableList<Component>,
        context: TooltipFlag
    ) {
        val component: RadarItemComponent = RADAR_ITEM.get(stack)
        tooltip.add(
            Component.literal("Approximate biomes: ")
                .withStyle { s -> s.withColor(ChatFormatting.GOLD) })
        val biomes: MutableList<String> = MythicalContent.getBiomesForSpecies(component.getSpecies().toLowerCase().filter { c -> c.isLetter() }).toMutableList()
        var counter = 0
        val toRemove: MutableList<String> = ArrayList()
        for (biome in biomes) {
            if(biome in toRemove) continue
            if (counter >= 3) {
                break
            }
            counter++
            tooltip.add(
                Component.literal(" - ").append(Component.translatable(biome))
                    .withStyle { s -> s.withColor(ChatFormatting.GRAY) })
            toRemove.add(biome)
        }
        biomes.removeAll(toRemove)

        if (component.isEnabled() && component.isActive()) {
            tooltip.add(
                Component.literal("Current chain length: ${component.getChainLength()}")
                    .withStyle { s -> s.withColor(ChatFormatting.GOLD) })
            tooltip.add(
                Component.literal("Last scanned ${(world!!.gameTime - component.getLastTickedTime()) / 20} seconds ago")
                    .withStyle { s -> s.withColor(ChatFormatting.GOLD) })
        } else {
            tooltip.add(
                Component.literal("Current chain length: ${component.getChainLength()}")
                    .withStyle { s -> s.withColor(ChatFormatting.GOLD) })
            if (component.isActive()) {
                tooltip.add(
                    Component.literal("Trail lost.").withStyle { s -> s.withColor(ChatFormatting.GOLD) })
            } else {
                tooltip.add(
                    Component.literal("Inactive.").withStyle { s -> s.withColor(ChatFormatting.GOLD) })
            }
        }
        super.appendHoverText(stack, world, tooltip, context)
    }

    init {
        MythicalContent.LOGGER.info("RadarItem init")
    }

    override fun getName(stack: ItemStack): Component {
        return (super.getName(stack) as MutableComponent).append(
            Component.literal(
                " - ${
                    RADAR_ITEM.get(stack).getSpecies().capitalize()
                }"
            ).withStyle { s -> s.withColor(ChatFormatting.GOLD) })
    }

    companion object {
        fun testRadarSpawnedPokemon(event: PokeballCaptureConditionsEvent) {
            val pokemon = event.pokemonEntity
            val player: Entity? = event.pokeBallEntity.owner
            if (pokemon.pokemon.aspects.contains("radar_spawned")) {
                if (player != null) {
                    if (!pokemon.tags.contains(player.uuid.toString())) {
                        event.setFailMessage(
                            Component.literal("This Pokémon seems to not be affected by the Poké Ball!").red()
                        )
                        event.cancel()
                    }
                } else {
                    event.setFailMessage(Component.empty())
                    event.cancel()
                }
            }
        }

        fun getUUIDFromTags(pokemonEntity: PokemonEntity): UUID? {
            for (tag in pokemonEntity.tags) {
                if (tag.contains("-")) {
                    return UUID.fromString(tag)
                }
            }
            return null
        }
    }

    override fun allowNbtUpdateAnimation(
        player: Player?,
        hand: InteractionHand?,
        oldStack: ItemStack?,
        newStack: ItemStack?
    ): Boolean {
        return false
    }
}