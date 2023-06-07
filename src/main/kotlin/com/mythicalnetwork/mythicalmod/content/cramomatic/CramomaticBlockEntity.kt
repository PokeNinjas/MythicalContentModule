package com.mythicalnetwork.mythicalmod.content.cramomatic

import com.cobblemon.mod.common.util.giveOrDropItemStack
import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.MythicalBlockEntity
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalPackets
import com.mythicalnetwork.mythicalmod.util.TooltipHelper
import foundry.veil.color.Color
import foundry.veil.color.ColorTheme
import foundry.veil.ui.Tooltippable
import foundry.veil.ui.VeilUIItemTooltipDataHolder
import foundry.veil.ui.anim.TooltipTimeline
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
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
import java.util.UUID
import kotlin.math.cos
import kotlin.math.sin

// TODO: Fix animation. Figure out why adding items makes the first item always air.
class CramomaticBlockEntity(pos: BlockPos, state: BlockState) :
    MythicalBlockEntity(MythicalBlockEntities.CRAMOMATIC_BLOCK_ENTITY!!, pos, state), IAnimatable, Tooltippable {
    private var state = STATE.IDLE
    private var animationFactory: AnimationFactory = GeckoLibUtil.createFactory(this)
    var ticksSinceItemAdded: Int = -1
    private var instance: CramomaticInstance? = null
    private var tooltip: MutableList<Component> = mutableListOf()
    private var progress: Int = 0
    private var theme: ColorTheme = ColorTheme().also {
        it.addColor("background", Color(53, 141, 222).multiply(1.0f, 1.0f, 1.0f, 0.75f))
        it.addColor("bottomBorder", Color(249, 224, 29))
        it.addColor("topBorder", Color(255, 122, 83))
        it.addColor("title", Color(255, 107, 0))
    }

    fun tick(level: Level, pos: BlockPos, state: BlockState, entity: BlockEntity) {
        if (ticksSinceItemAdded < 30 && ticksSinceItemAdded != -1) {
            ticksSinceItemAdded++
        }
        if(ticksSinceItemAdded == 30) {
            ticksSinceItemAdded = -1
            this.state = STATE.IDLE
        }
        if (level.isClientSide) {
            instance?.let { instance ->
                if(instance.getCurrentItems().isNotEmpty()){
                    tooltip[1] = Component.literal("Time until auto-eject: ${TooltipHelper.formatTime((instance.getMaxTime()-instance.getTime()).toLong())}").withStyle { s -> s.withColor(theme.getColor("bottomBorder").rgb) }
                }
            } ?: run {
                tooltip[1] = Component.translatable("block.mythicalmod.cramomatic.empty_tooltip", progress / 20).withStyle { s -> s.withColor(theme.getColor("bottomBorder").rgb) }
            }
            instance?.getCurrentItems()?.let {
                if (it.isNotEmpty()) {
                    if (tooltip.size < 3) {
                        tooltip.add(Component.empty())
                        tooltip.add(Component.empty())
                        tooltip.add(Component.empty())
                        tooltip.add(Component.empty())
                        tooltip.add(Component.literal("[Crouch + Right Click to empty Cramomatic!]")
                            .withStyle { s -> s.withColor(theme.getColor("bottomBorder").rgb) })
                    }
                } else {
                    if (tooltip.size == 7) {
                        tooltip.removeAt(6)
                        tooltip.removeAt(5)
                        tooltip.removeAt(4)
                        tooltip.removeAt(3)
                        tooltip.removeAt(2)
                    }
                }
            }
        }
    }

    init {
        tooltip.add(Component.translatable("block.mythicalmod.cramomatic").withStyle { s -> s.withColor(theme.getColor("title").rgb) })
        tooltip.add(Component.translatable("block.mythicalmod.cramomatic.empty_tooltip", progress / 20).withStyle { s -> s.withColor(theme.getColor("bottomBorder").rgb) })
    }

    override fun registerControllers(animationData: AnimationData) {
        animationData.addAnimationController(AnimationController(this, "controller", 0.0F, this::predicate))
    }

    fun getInstance(): CramomaticInstance? {
        return instance
    }


    private fun <E> predicate(animationEvent: AnimationEvent<E>): PlayState
            where E : IAnimatable, E : BlockEntity {
        val controller = animationEvent.controller
        controller.transitionLengthTicks = 10.0
        controller.easingType = EasingType.EaseInCubic
        when (state) {
            STATE.IDLE -> {
                controller.setAnimation(
                    AnimationBuilder().addAnimation(
                        "idle",
                        ILoopType.EDefaultLoopTypes.LOOP
                    )
                )
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

    override fun onUse(player: Player, hand: InteractionHand): InteractionResult {
        if(ticksSinceItemAdded != -1) {
            return InteractionResult.CONSUME
        }
        if(player.level.isClientSide){
            if(!player.isCrouching){
                if(player.getItemInHand(hand) != ItemStack.EMPTY){
                    ticksSinceItemAdded = 0
                    state = STATE.CONSUMING
                }
            }
        }
        if (!player.level.isClientSide) {
            if (player.isCrouching) {
                MythicalContent.CRAMOMATIC_HANDLER?.let { handler ->
                    handler.getPlayer(player.uuid)?.let { playerHandler ->
                        playerHandler.onComplete {
                            MythicalContent.CRAMOMATIC_HANDLER!!.onComplete(it)
                        }
                        MythicalContent.CRAMOMATIC_HANDLER!!.removePlayer(player.uuid)
                        state = STATE.EJECTING
                        update(player.uuid, playerHandler)
                    }
                }
            } else {
                MythicalContent.CRAMOMATIC_HANDLER?.let { handler ->
                    handler.getPlayer(player.uuid)?.let { playerHandler ->
                        if(playerHandler.getCurrentItems().size == 5){
                            return InteractionResult.PASS
                        }
                        if (!playerHandler.isComplete) {
                            addItem(player.getItemInHand(hand), player)
                            player.getItemInHand(hand).shrink(1)
                            state = STATE.CONSUMING
                        } else {
                            playerHandler.onComplete {
                                MythicalContent.CRAMOMATIC_HANDLER!!.onComplete(it)
                            }
                            MythicalContent.CRAMOMATIC_HANDLER!!.removePlayer(player.uuid)
                            state = STATE.EJECTING
                        }
                        update(player.uuid, playerHandler)
                    } ?: run {
                        handler.addPlayer(player.uuid, CramomaticInstance(this)).let { playerHandler ->
                            if (!playerHandler.isComplete) {
                                addItem(player.getItemInHand(hand), player)
                                player.getItemInHand(hand).shrink(1)
                                state = STATE.CONSUMING
                            } else {
                                playerHandler.onComplete {
                                    MythicalContent.CRAMOMATIC_HANDLER!!.onComplete(it)
                                }
                                MythicalContent.CRAMOMATIC_HANDLER!!.removePlayer(player.uuid)
                                state = STATE.EJECTING
                            }
                            update(player.uuid, playerHandler)
                        }
                    }
                }
            }
        }
        return InteractionResult.SUCCESS
    }

    fun update(player: UUID, instance: CramomaticInstance?, shouldNull: Boolean = false) {
        instance?.let {
            val buf: FriendlyByteBuf = PacketByteBufs.create()
            buf.writeBlockPos(worldPosition)
            buf.writeNbt(it.save())
            val playerEntity: Player? = level?.getPlayerByUUID(player)
            if(shouldNull){
                buf.writeBoolean(true)
            } else {
                buf.writeBoolean(false)
            }
            if (playerEntity != null) {
                ServerPlayNetworking.send(playerEntity as ServerPlayer, MythicalPackets.CRAMOMATIC_S2C_SYNC.identifier, buf)
            }
        }
    }

    // TODO: add max item count of 5
    private fun addItem(stack: ItemStack, player: Player) {
        if (stack.isEmpty) return
        if (!player.level.isClientSide) {
            MythicalContent.CRAMOMATIC_HANDLER?.getPlayer(player.uuid)?.let {
                it.addItem(stack)
                player.displayClientMessage(Component.literal("Added item ${stack.displayName.string} to Cramomatic"), true)
                update(player.uuid, it)
            }
        }
        ticksSinceItemAdded = 0
        state = STATE.CONSUMING
    }

    fun ejectItems(stack: MutableList<ItemStack>, player: Player) {
        if (!player.level.isClientSide) {
            for (item in stack) {
                player.giveOrDropItemStack(item, true)
            }
        }
        state = STATE.EJECTING
    }

    fun setInstance(instance: CramomaticInstance?) {
        this.instance = instance
    }

    enum class STATE {
        IDLE,
        CONSUMING,
        EJECTING
    }

    override fun getTooltip(): MutableList<Component> {
        return tooltip
    }

    override fun isTooltipEnabled(): Boolean {
        return true
    }

    override fun saveTooltipData(): CompoundTag {
        return CompoundTag()
    }

    override fun loadTooltipData(p0: CompoundTag?) {
    }

    override fun setTooltip(p0: MutableList<Component>?) {
        tooltip = p0!!
    }

    override fun addTooltip(p0: Component?) {
        tooltip.add(p0!!)
    }

    override fun addTooltip(p0: MutableList<Component>?) {
        tooltip.addAll(p0!!)
    }

    override fun addTooltip(p0: String?) {
        tooltip.add(Component.literal(p0!!))
    }

    override fun getTheme(): ColorTheme {
        return theme
    }

    override fun setTheme(p0: ColorTheme?) {
        theme = p0!!
    }

    override fun setBackgroundColor(p0: Int) {
        theme.colors.add(0, Color.of(p0))
    }

    override fun setTopBorderColor(p0: Int) {
        theme.colors.add(1, Color.of(p0))
    }

    override fun setBottomBorderColor(p0: Int) {
        theme.colors.add(2, Color.of(p0))
    }

    override fun getWorldspace(): Boolean {
        return true
    }

    override fun getTimeline(): TooltipTimeline {
        return TooltipTimeline(arrayOf(), 1.0f)
    }

    override fun getStack(): ItemStack {
        return ItemStack.EMPTY
    }

    override fun getTooltipWidth(): Int {
        return 0
    }

    override fun getTooltipHeight(): Int {
        return 0

    }

    override fun getTooltipXOffset(): Int {
        return -10
    }

    override fun getTooltipYOffset(): Int {
        return 15
    }

    override fun getItems(): MutableList<VeilUIItemTooltipDataHolder> {
        val itemList: MutableList<VeilUIItemTooltipDataHolder> = mutableListOf()
        instance?.getCurrentItems()?.size?.let {
            var j = 0
            for (i in 0 until it) {
                val item = instance!!.getCurrentItems()[i]
                for(k in 0 until item.count) {
                    itemList.add(VeilUIItemTooltipDataHolder(item, { (i+(k/8f)) * 16f }, { -48f + (k/2f) }))
                    j++
                }
            }
        }
        instance?.getRecipeGuess()?.let { recipe ->
            itemList.add(
                VeilUIItemTooltipDataHolder(
                    recipe.output!![((level?.gameTime ?: 0) / 40 % recipe.output!!.size).toInt()].getItemStack(), {
                        (level?.gameTime?.plus(it)
                            ?.div(10)).let { it1 -> it1?.let { it2 -> sin(it2.toDouble()).toFloat() } }
                    }, {
                        -32f + cos((level?.gameTime?.plus(it)?.div(10))?.toDouble() ?: 0.0).toFloat()
                    })
            )
        }
        return itemList
    }
}