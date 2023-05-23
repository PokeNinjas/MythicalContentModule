package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.cobblemon.mod.common.util.giveOrDropItemStack
import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.MythicalBlockEntity
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalPackets
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.ServerPlayNetworking
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.easing.EasingType
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil

// TODO: Fix animation. Figure out why adding items makes the first item always air.
class CramomaticBlockEntity(pos: BlockPos, state: BlockState) : MythicalBlockEntity(MythicalBlockEntities.CRAMOMATIC_BLOCK_ENTITY!!, pos, state), IAnimatable {
    private var state = STATE.CONSUMING
    private var animationFactory: AnimationFactory = GeckoLibUtil.createFactory(this)
    private var ticksSinceItemAdded: Int = -1
    private var instance: CramomaticInstance? = null

    override fun registerControllers(animationData: AnimationData) {
        animationData.addAnimationController(AnimationController(this, "controller", 0.0F, this::predicate))
    }

    private fun <E> predicate(animationEvent: AnimationEvent<E>): PlayState
    where E : IAnimatable, E : BlockEntity {
        val controller = animationEvent.controller
        controller.transitionLengthTicks = 10.0
        controller.easingType = EasingType.EaseInCubic
        when (state) {
            STATE.IDLE -> {
                controller.setAnimation(AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME))
            }
            STATE.CONSUMING -> {
                controller.setAnimation(AnimationBuilder().addAnimation("take", ILoopType.EDefaultLoopTypes.PLAY_ONCE))
            }
            STATE.EJECTING -> {
                controller.setAnimation(AnimationBuilder().addAnimation("give", ILoopType.EDefaultLoopTypes.PLAY_ONCE))
            }
        }
        return PlayState.CONTINUE
    }



    override fun saveAdditional(nbt: CompoundTag) {
        super.saveAdditional(nbt)
    }

    override fun getFactory(): AnimationFactory {
        return animationFactory
    }

    fun tick(level: Level, pos: BlockPos, state: BlockState, entity: BlockEntity) {
        if(ticksSinceItemAdded < 20 && ticksSinceItemAdded != -1) {
            ticksSinceItemAdded++
        }
    }

    override fun onUse(player: Player, hand: InteractionHand): InteractionResult{
        if(!player.level.isClientSide){
            MythicalContent.CRAMOMATIC_HANDLER?.let { handler ->
                player.sendSystemMessage(Component.literal("CramomaticHandler is not null"))
                handler.getPlayer(player)?.let { playerHandler ->
                    player.sendSystemMessage(Component.literal("PlayerHandler is not null"))
                    if(playerHandler.output == null){
                        player.sendSystemMessage(Component.literal("PlayerHandler output is null"))
                        addItem(player.getItemInHand(hand), player)
                        player.getItemInHand(hand).shrink(1)
                    } else {
                        player.sendSystemMessage(Component.literal("PlayerHandler output is not null"))
                        playerHandler.output?.let { ejectItem(it, player) }
                    }
                    update(player, playerHandler)
                } ?: run {
                    handler.addPlayer(player, CramomaticInstance(this)).let { playerHandler ->
                        player.sendSystemMessage(Component.literal("PlayerHandler is null"))
                        if(playerHandler.output == null){
                            addItem(player.getItemInHand(hand), player)
                            player.getItemInHand(hand).shrink(1)
                        } else {
                            playerHandler.output?.let { ejectItem(it, player) }
                        }
                        update(player, playerHandler)
                    }
                }
            }
        }
        return InteractionResult.SUCCESS
    }

    private fun update(player: Player, instance: CramomaticInstance?){
        instance?.let {
            val buf: FriendlyByteBuf = PacketByteBufs.create()
            buf.writeBlockPos(worldPosition)
            buf.writeNbt(it.save())
            ServerPlayNetworking.send(player as ServerPlayer, MythicalPackets.CRAMOMATIC_S2C_SYNC.identifier, buf)
        }

    }

    private fun addItem(stack: ItemStack, player: Player) {
        if(stack.isEmpty) return
        if(!player.level.isClientSide){
            MythicalContent.CRAMOMATIC_HANDLER?.getPlayer(player)?.let{
                it.addItem(stack)
                player.sendSystemMessage(Component.literal("Added item ${stack.displayName.string} to Cramomatic"))
            }
        }
        ticksSinceItemAdded = 0
        state = STATE.CONSUMING
    }

    private fun ejectItem(stack: ItemStack, player: Player) {
        if(!player.level.isClientSide){
            player.giveOrDropItemStack(stack, true)
        }
        state = STATE.EJECTING
    }

    fun setInstance(instance: CramomaticInstance) {
        this.instance = instance
    }

    enum class STATE {
        IDLE,
        CONSUMING,
        EJECTING
    }
}