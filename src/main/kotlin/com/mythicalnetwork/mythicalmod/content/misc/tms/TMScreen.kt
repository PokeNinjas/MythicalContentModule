package com.mythicalnetwork.mythicalmod.content.misc.tms

import ca.landonjw.gooeylibs2.api.UIManager
import ca.landonjw.gooeylibs2.api.button.ButtonAction
import ca.landonjw.gooeylibs2.api.button.ButtonClick
import ca.landonjw.gooeylibs2.api.button.GooeyButton
import ca.landonjw.gooeylibs2.api.data.UpdateEmitter
import ca.landonjw.gooeylibs2.api.page.Page
import ca.landonjw.gooeylibs2.api.template.Template
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate
import com.cobblemon.mod.common.api.moves.Move
import com.cobblemon.mod.common.api.moves.MoveTemplate
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.sendParticlesServer
import net.minecraft.client.particle.BreakingItemParticle
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.phys.Vec3

class TMScreen(var pokemon: Pokemon, var tm: ItemStack, var moves: List<Move>, var tmMove: MoveTemplate) : UpdateEmitter<Page>(), Page{
    private var template: ChestTemplate? = null

    companion object {
        val TYPE_GLASS_MAP: MutableMap<ElementalType, ItemStack> = mutableMapOf(
            ElementalTypes.NORMAL to ItemStack(Items.WHITE_STAINED_GLASS_PANE),
            ElementalTypes.FIRE to ItemStack(Items.ORANGE_STAINED_GLASS_PANE),
            ElementalTypes.WATER to ItemStack(Items.BLUE_STAINED_GLASS_PANE),
            ElementalTypes.GRASS to ItemStack(Items.LIME_STAINED_GLASS_PANE),
            ElementalTypes.ELECTRIC to ItemStack(Items.YELLOW_STAINED_GLASS_PANE),
            ElementalTypes.ICE to ItemStack(Items.LIGHT_BLUE_STAINED_GLASS_PANE),
            ElementalTypes.FIGHTING to ItemStack(Items.RED_STAINED_GLASS_PANE),
            ElementalTypes.POISON to ItemStack(Items.PURPLE_STAINED_GLASS_PANE),
            ElementalTypes.GROUND to ItemStack(Items.BROWN_STAINED_GLASS_PANE),
            ElementalTypes.FLYING to ItemStack(Items.WHITE_STAINED_GLASS_PANE),
            ElementalTypes.PSYCHIC to ItemStack(Items.MAGENTA_STAINED_GLASS_PANE),
            ElementalTypes.BUG to ItemStack(Items.LIME_STAINED_GLASS_PANE),
            ElementalTypes.ROCK to ItemStack(Items.BROWN_STAINED_GLASS_PANE),
            ElementalTypes.GHOST to ItemStack(Items.PURPLE_STAINED_GLASS_PANE),
            ElementalTypes.DRAGON to ItemStack(Items.PURPLE_STAINED_GLASS_PANE),
            ElementalTypes.DARK to ItemStack(Items.BLACK_STAINED_GLASS_PANE),
            ElementalTypes.STEEL to ItemStack(Items.GRAY_STAINED_GLASS_PANE),
            ElementalTypes.FAIRY to ItemStack(Items.PINK_STAINED_GLASS_PANE)
        )
    }
    init {
        template = ChestTemplate.Builder(4).build()
        refresh()
    }

    private fun refresh() {
        val buttons: MutableList<GooeyButton> = mutableListOf()
        for (i in 0..moves.size) {
            if (i >= moves.size)
                break
            val move: Move = moves[i]
            buttons.add(GooeyButton.builder()
                .display(TYPE_GLASS_MAP[move.type]!!)
                .title(move.displayName)
                .lore(listOf(move.description.string))
                .onClick { buttonClick: ButtonAction ->
                    pokemon.exchangeMove(move.template, tmMove)
                    buttonClick.player.level.playSound(null, buttonClick.player.blockPosition(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f, 1f)
                    if(buttonClick.player.getItemInHand(InteractionHand.MAIN_HAND).hasTag()) {
                        val tag = buttonClick.player.getItemInHand(InteractionHand.MAIN_HAND).tag
                        if(tag!!.contains("type")) {
                            if(tag.getString("type").toLowerCase() == "tr") {
                                buttonClick.player.level.playSound(null, buttonClick.player.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1f, 1f)
                                // check if the item in each hand is a TM, and check if the move is the same as the tmMove of this screen using getItemInHand(hand)
                                // if so, set the item in hand to an empty stack
                                if (buttonClick.player.getItemInHand(InteractionHand.MAIN_HAND) == tm)
                                    buttonClick.player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY)
                                if (buttonClick.player.getItemInHand(InteractionHand.OFF_HAND) == tm)
                                    buttonClick.player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY)
                                (buttonClick.player.level as ServerLevel).sendParticlesServer(ItemParticleOption(
                                    ParticleTypes.ITEM,
                                    tm
                                ), buttonClick.player.eyePosition, 10, Vec3(0.5, 0.5, 0.5), 0.1)
                                // play breaking item particle at the player's location
                            }
                        }
                    }
                    buttonClick.player.closeContainer()
                }
                .build())
        }
        template!!.rectangleFromList(1, 4, 2, 2, buttons as List<GooeyButton>)
        template!!.set(0,0,
            GooeyButton.builder()
                .display(PokemonItem.from(pokemon, 1))
                .title(pokemon.displayName)
                .build()
            )
        template!!.set(1, 0,
            GooeyButton.builder()
                .display(tm)
                .title(tmMove.displayName)
                .build()
            )
        template!!.set(3, 0,
            GooeyButton.builder()
                .display(ItemStack(Items.BARRIER))
                .title("Close")
                .onClick { buttonClick: ButtonAction ->
                    buttonClick.player.playSound(SoundEvents.ITEM_PICKUP, 0.5f, 0.5f)
                    buttonClick.player.closeContainer()
                }
                .build()
            )
    }
    override fun getTemplate(): Template {
        return template!!
    }

    override fun getTitle(): Component {
        return pokemon.displayName
    }
}