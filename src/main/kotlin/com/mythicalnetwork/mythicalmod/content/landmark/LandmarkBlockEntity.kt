package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.MythicalBlockEntity
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticBlockEntity
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalBlocks
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockCoreEntity
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockStructure
import foundry.veil.color.Color
import foundry.veil.color.ColorTheme
import foundry.veil.ui.Tooltippable
import foundry.veil.ui.VeilUIItemTooltipDataHolder
import foundry.veil.ui.anim.TooltipKeyframe
import foundry.veil.ui.anim.TooltipTimeline
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
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
import java.util.Optional

class LandmarkBlockEntity(type: BlockEntityType<*>, pos: BlockPos, state: BlockState, var pokemonType: ElementalType) :
    MultiblockCoreEntity(type, pos, state, STRUC), IAnimatable, Tooltippable {
    private var animationFactory: AnimationFactory = GeckoLibUtil.createFactory(this)
    private var tooltip: MutableList<Component> = mutableListOf()
    private var state: Int = 0
    private var theme: ColorTheme = ColorTheme().also {
        it.addColor("background", Color(56, 63, 71).multiply(1.0f, 1.0f, 1.0f, 0.75f))
        it.addColor("bottomBorder", Color(18, 19, 20))
        it.addColor("topBorder", Color(121, 121, 121))
        it.addColor("title", Color(174, 182, 198))
    }
    private val spawnData: LandmarkSpawnData? = LandmarkSpawnData.getForType(pokemonType)
    private var delay = 0
    private var isActive = false
    private var duration = 0
    private var cooldown = 0

    companion object {
        val STRUC: MultiblockStructure = MultiblockStructure.of(
            MultiblockStructure.StructurePiece(1,0,1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0,0,1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1,0,1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1,0,0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1,0,0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1,0,-1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0,0,-1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1,0,-1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),

            MultiblockStructure.StructurePiece(1,1,1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0,1,1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1,1,1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1,1,0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1,1,0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1,1,-1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0,1,-1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1,1,-1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0,1,0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0,2,0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0,3,0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
        )
    }


    init {
    }

    override fun onUse(player: Player, hand: InteractionHand): InteractionResult {
        if(!level!!.isClientSide){
            state = if (state == 0) {
                1
            } else {
                0
            }
            isActive = state == 1
            if(isActive) {
                duration = spawnData?.duration!!
                cooldown = spawnData.cooldown
            }
            player.sendSystemMessage(Component.literal("State: $state, isActive: $isActive, duration: $duration, cooldown: $cooldown"))
        }
        return InteractionResult.SUCCESS
    }

    fun tick(level: Level, pos: BlockPos, state: BlockState, entity: BlockEntity) {
        if(level.isClientSide) {
            return
        }
        if (isActive && duration > 0){
            duration--
            if (delay > 0) {
                delay--
            }
            if (delay == 0) {
                delay = (spawnData?.maxDelay?.let { spawnData.minDelay.rangeTo(it) })?.random() ?: 0
                spawn()
            }
        }
        if(cooldown > 0){
            cooldown--
        }
    }

    private fun spawn() {
        val s = spawnData?.species?.random() ?: return
        val speciesString: String = s.species
        val species: Species? = PokemonSpecies.getByName(speciesString)
        if (species == null) MythicalContent.LOGGER.info("Species $speciesString does not exist")
        else {
            val level: Int? = level?.random?.nextInt(s.levelRange.get().first, s.levelRange.get().last)
            val pokemon: Pokemon = level?.let { species.create(it) } ?: species.create()
            s.aspects.forEach { aspect ->
                PokemonProperties.parse(aspect).apply(pokemon)
            }
            spawn(pokemon)
        }
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
            0 -> controller.setAnimation(AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP))
            1 -> controller.setAnimation(
                AnimationBuilder().addAnimation(
                    "active",
                    ILoopType.EDefaultLoopTypes.PLAY_ONCE
                )
            )
        }
        return PlayState.CONTINUE
    }

    override fun getFactory(): AnimationFactory {
        return animationFactory
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
        return
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
        tooltip.add(Component.nullToEmpty(p0))
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
        return TooltipTimeline(arrayOf<TooltipKeyframe>(), 1.0f)
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
        return mutableListOf()
    }

    fun spawn(pokemon: Pokemon) {
        val pokemonEntity: PokemonEntity = PokemonEntity(level!!, pokemon)
        val pos: BlockPos? = checkSpawnConditions(pokemonEntity)
        if (pos != null) {
            pokemonEntity.setPos(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
            level!!.addFreshEntity(pokemonEntity)
            level!!.server!!.playerList.players.forEach { player ->
                player.sendSystemMessage(Component.literal("A wild ${pokemon.species.name} has appeared!"))
            }
        }
    }

    private fun checkSpawnConditions(pokemon: PokemonEntity): BlockPos? {
        // check all positions in the area (from config), if its a valid spawn location for the pokemon
        // check if the block below is solid, if the block is air, and if the blocks insied the pokemon's hitbox are air
        val range: Int = MythicalContent.CONFIG.landmarkSpawnRange()
        val pos: Optional<BlockPos> = BlockPos.findClosestMatch(blockPos, range, range) { pos ->
            val blocks: MutableIterable<BlockPos>? = BlockPos.withinManhattan(
                pos,
                pokemon.getDimensions(Pose.STANDING).width.toInt()+1,
                pokemon.getDimensions(Pose.STANDING).height.toInt()+1,
                pokemon.getDimensions(Pose.STANDING).width.toInt()+1
            )
            for (block in blocks!!) {
                if (level!!.random.nextFloat() < 0.5) {
                    continue
                }
                if (!level!!.getBlockState(block).isAir) {
                    return@findClosestMatch false
                }
                if (level?.let { level!!.getBlockState(block.below()).isValidSpawn(it, pos, pokemon.type) } == true) {
                    return@findClosestMatch true
                }
            }
            return@findClosestMatch true
        }
        return pos.orElse(null)
    }
}