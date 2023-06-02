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
import kotlin.math.cos
import kotlin.math.sin

// TODO: Fix animation. Figure out why adding items makes the first item always air.
class CramomaticBlockEntity(pos: BlockPos, state: BlockState) :
    MythicalBlockEntity(MythicalBlockEntities.CRAMOMATIC_BLOCK_ENTITY!!, pos, state), IAnimatable, Tooltippable {
    private var state = STATE.CONSUMING
    private var animationFactory: AnimationFactory = GeckoLibUtil.createFactory(this)
    private var ticksSinceItemAdded: Int = -1
    private var instance: CramomaticInstance? = null
    private var tooltip: MutableList<Component> = mutableListOf()
    private var progress: Int = 0
    private var theme: ColorTheme = ColorTheme().also {
        it.addColor("background", Color(53, 141, 222).multiply(1.0f, 1.0f, 1.0f, 0.75f))
        it.addColor("topBorder", Color(249, 224, 29))
        it.addColor("bottomBorder", Color(255, 122, 83))
    }

    init {
        tooltip.add(Component.translatable("block.mythicalmod.cramomatic"))
        tooltip.add(Component.translatable("block.mythicalmod.cramomatic.progress_tooltip", progress / 20))
    }

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
                controller.setAnimation(
                    AnimationBuilder().addAnimation(
                        "idle",
                        ILoopType.EDefaultLoopTypes.LOOP
                    )
                )
            }

            STATE.CONSUMING -> {
                controller.setAnimation(AnimationBuilder().addAnimation("take", ILoopType.EDefaultLoopTypes.LOOP))
            }

            STATE.EJECTING -> {
                controller.setAnimation(AnimationBuilder().addAnimation("give", ILoopType.EDefaultLoopTypes.LOOP))
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
        if (ticksSinceItemAdded < 20 && ticksSinceItemAdded != -1) {
            ticksSinceItemAdded++
        }
        if (level.isClientSide) {
            tooltip[1] = TooltipHelper.makeProgressBar(
                ((instance?.getTime() ?: 0) / (instance?.getMaxTime() ?: 200)).toFloat(),
                theme.getColor("bottomBorder").rgb,
                theme.getColor("bottomBorder").rgb
            )
        }
    }

    override fun onUse(player: Player, hand: InteractionHand): InteractionResult {
        if (!player.level.isClientSide) {
            if (player.isCrouching) {
                MythicalContent.CRAMOMATIC_HANDLER?.let { handler ->
                    handler.getPlayer(player)?.let { playerHandler ->
                        playerHandler.onComplete {
                            MythicalContent.CRAMOMATIC_HANDLER!!.onComplete(it)
                        }
                        MythicalContent.CRAMOMATIC_HANDLER!!.removePlayer(player)
                        state = STATE.EJECTING
                        update(player, playerHandler)
                    }
                }
            }
            MythicalContent.CRAMOMATIC_HANDLER?.let { handler ->
                handler.getPlayer(player)?.let { playerHandler ->
                    MythicalContent.LOGGER.info("Got player handler for player $player")
                    if (!playerHandler.isComplete) {
                        addItem(player.getItemInHand(hand), player)
                        player.getItemInHand(hand).shrink(1)
                        state = STATE.CONSUMING
                    } else {
                        playerHandler.onComplete {
                            MythicalContent.CRAMOMATIC_HANDLER!!.onComplete(it)
                        }
                        MythicalContent.CRAMOMATIC_HANDLER!!.removePlayer(player)
                        state = STATE.EJECTING
                    }
                    update(player, playerHandler)
                } ?: run {
                    handler.addPlayer(player, CramomaticInstance(this)).let { playerHandler ->
                        MythicalContent.LOGGER.info("Created player handler for player $player")
                        if (!playerHandler.isComplete) {
                            addItem(player.getItemInHand(hand), player)
                            player.getItemInHand(hand).shrink(1)
                            state = STATE.CONSUMING
                        } else {
                            playerHandler.onComplete {
                                MythicalContent.CRAMOMATIC_HANDLER!!.onComplete(it)
                            }
                            MythicalContent.CRAMOMATIC_HANDLER!!.removePlayer(player)
                            state = STATE.EJECTING
                        }
                        update(player, playerHandler)
                    }
                }
            }
        }
        return InteractionResult.SUCCESS
    }

    fun update(player: Player, instance: CramomaticInstance?) {
        instance?.let {
            MythicalContent.LOGGER.info("Updating Cramomatic for player $player")
            val buf: FriendlyByteBuf = PacketByteBufs.create()
            buf.writeBlockPos(worldPosition)
            buf.writeNbt(it.save())
            ServerPlayNetworking.send(player as ServerPlayer, MythicalPackets.CRAMOMATIC_S2C_SYNC.identifier, buf)
        }
    }

    private fun addItem(stack: ItemStack, player: Player) {
        if (stack.isEmpty) return
        if (!player.level.isClientSide) {
            MythicalContent.CRAMOMATIC_HANDLER?.getPlayer(player)?.let {
                MythicalContent.LOGGER.info("Adding item ${stack.displayName.string} to Cramomatic, added by player $player")
                it.addItem(stack)
                player.sendSystemMessage(Component.literal("Added item ${stack.displayName.string} to Cramomatic"))
                update(player, it)
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

    fun setInstance(instance: CramomaticInstance) {
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
        return 32
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
            for (i in 0 until it) {
                itemList.add(VeilUIItemTooltipDataHolder(instance!!.getCurrentItems()[i], { i * 16f }, { 0f }))
            }
        }
        instance?.getRecipeGuess()?.let { recipe ->
            itemList.add(
                VeilUIItemTooltipDataHolder(
                    recipe.output!![((level?.gameTime ?: 0) / 40 % recipe.output!!.size).toInt()].getItemStack(), {
                        (level?.gameTime?.plus(it)
                            ?.div(10)).let { it1 -> it1?.let { it2 -> sin(it2.toDouble()).toFloat() } }
                    }, {
                        16f + cos((level?.gameTime?.plus(it)?.div(10))?.toDouble() ?: 0.0).toFloat()
                    })
            )
        }
        return itemList
    }
}