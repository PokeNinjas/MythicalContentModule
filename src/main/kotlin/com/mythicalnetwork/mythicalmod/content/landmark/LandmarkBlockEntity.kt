package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.particle.CobblemonParticles
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
import net.minecraft.core.particles.ParticleTypes
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
import net.minecraft.world.phys.AABB
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
            MultiblockStructure.StructurePiece(1, 0, 1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0, 0, 1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1, 0, 1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1, 0, 0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1, 0, 0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1, 0, -1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0, 0, -1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1, 0, -1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),

            MultiblockStructure.StructurePiece(1, 1, 1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0, 1, 1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1, 1, 1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1, 1, 0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1, 1, 0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(1, 1, -1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0, 1, -1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(-1, 1, -1, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0, 1, 0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0, 2, 0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
            MultiblockStructure.StructurePiece(0, 3, 0, MythicalBlocks.EMPTY_LANDMARK_BLOCK.defaultBlockState()),
        )
    }


    init {
    }

    override fun onUse(player: Player, hand: InteractionHand): InteractionResult {
        state = if (state == 0) {
            1
        } else {
            0
        }
        if (!level!!.isClientSide) {
            isActive = state == 1
            if (isActive) {
                duration = spawnData?.duration!!
                cooldown = spawnData.cooldown
            }
            player.sendSystemMessage(Component.literal("State: $state, isActive: $isActive, duration: $duration, cooldown: $cooldown"))
        }
        return InteractionResult.SUCCESS
    }

    fun tick(level: Level, pos: BlockPos, state: BlockState, entity: BlockEntity) {
        if (level.isClientSide) {
            return
        }
        if (isActive && duration > 0 && isPlayerNearby() && !isOverpopulated()) {
            duration--
            if (delay > 0) {
                delay--
            }
            if (delay == 0) {
                delay = (spawnData?.maxDelay?.let { spawnData.minDelay.rangeTo(it) })?.random() ?: 0
                spawn()
            }
            spawnParticles()
        }
        if (cooldown > 0) {
            cooldown--
        }
    }

    // TODO: Add idle active particles
    private fun spawnParticles() {
        for(i in 0..level!!.random.nextInt(1, MythicalContent.CONFIG.landmarkSpawnRange()/ 1.coerceAtLeast(MythicalContent.CONFIG.landmarkSpawnRange() / 10))) {
            val blocks: MutableIterable<BlockPos>? = getRandomBlocks(worldPosition, 5)
            blocks?.forEach { blockPos ->
                if(level!!.canSeeSky(blockPos)){
                    for(j in 0..level!!.random.nextInt(1, level!!.random.nextIntBetweenInclusive(5, 10))){
                        level!!.addParticle(ParticleTypes.CRIMSON_SPORE, blockPos.x.toDouble() + (level!!.random.nextFloat()/10f), blockPos.y.toDouble(), blockPos.z.toDouble() + (level!!.random.nextFloat()/10f),  ((level!!.random.nextFloat()/10f).toDouble()), 0.25,  ((level!!.random.nextFloat()/10f).toDouble()))
                    }
                }
            }
        }
    }

    private fun isPlayerNearby(): Boolean {
        val players = level!!.getEntitiesOfClass(Player::class.java, AABB(worldPosition).inflate(spawnData?.requiredPlayerRange?.toDouble() ?: 10.0))
        return players.isNotEmpty()
    }

    private fun isOverpopulated(): Boolean {
        val entities = level!!.getEntitiesOfClass(PokemonEntity::class.java, AABB(worldPosition).inflate(MythicalContent.CONFIG.landmarkSpawnRange().toDouble()))
        return entities.size >= (spawnData?.maxNearbyEntities ?: 10)
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
//        controller.transitionLengthTicks = 10.0
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

    override fun saveAdditional(nbt: CompoundTag) {
        super.saveAdditional(nbt)
        nbt.putInt("state", state)
        nbt.putInt("duration", duration)
        nbt.putInt("cooldown", cooldown)
        nbt.putInt("delay", delay)
        nbt.putBoolean("isActive", isActive)
    }

    override fun load(nbt: CompoundTag) {
        super.load(nbt)
        state = nbt.getInt("state")
        duration = nbt.getInt("duration")
        cooldown = nbt.getInt("cooldown")
        delay = nbt.getInt("delay")
        isActive = nbt.getBoolean("isActive")
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
        var blockPos: BlockPos? = null
        for(i in 0..5){
            val blocksToCheck: MutableIterable<BlockPos>? = getRandomBlocks(worldPosition, range)
            blocksToCheck?.forEach { pos ->
                val blocks: MutableIterable<BlockPos>? = BlockPos.withinManhattan(
                    pos,
                    pokemon.getDimensions(Pose.STANDING).width.toInt() + 1,
                    pokemon.getDimensions(Pose.STANDING).height.toInt() + 1,
                    pokemon.getDimensions(Pose.STANDING).width.toInt() + 1
                )
                for (block in blocks!!) {
                    if (level!!.random.nextFloat() < 0.5) {
                        continue
                    }
                    if (!level!!.getBlockState(block).isAir) {
                        continue
                    }
                    if(block == worldPosition || block == worldPosition.north() || block == worldPosition.south() || block == worldPosition.east() || block == worldPosition.west() || block == worldPosition.north().east() || block == worldPosition.north().west() || block == worldPosition.south().east() || block == worldPosition.south().west()) {
                        continue
                    }
                    if(!level!!.getBlockState(block.above()).isAir || !level!!.getBlockState(block.north()).isAir || !level!!.getBlockState(block.south()).isAir || !level!!.getBlockState(block.east()).isAir || !level!!.getBlockState(block.west()).isAir || !level!!.getBlockState(block.north().east()).isAir || !level!!.getBlockState(block.north().west()).isAir || !level!!.getBlockState(block.south().east()).isAir || !level!!.getBlockState(block.south().west()).isAir){
                    }
                    if (!level!!.getBlockState(block.below()).isSolidRender(level!!, block)) {
                        continue
                    }
                    blockPos = block
                    break
                }
            }
            if(blockPos != null){
                break
            }
        }
        return blockPos
    }

    private fun getRandomBlocks(pos: BlockPos, range: Int): MutableIterable<BlockPos>? {
        return BlockPos.randomInCube(level!!.random, 16, pos, range)
    }
}