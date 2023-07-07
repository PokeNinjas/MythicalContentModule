package com.mythicalnetwork.mythicalmod.content.landmark

import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.types.ElementalType
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.particle.CobblemonParticles
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.util.sendParticlesServer
import com.mojang.math.Vector3f
import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.base.MythicalBlockEntity
import com.mythicalnetwork.mythicalmod.content.cramomatic.CramomaticBlockEntity
import com.mythicalnetwork.mythicalmod.registry.MythicalBlockEntities
import com.mythicalnetwork.mythicalmod.registry.MythicalBlocks
import com.mythicalnetwork.mythicalmod.registry.MythicalComponentRegistry
import com.mythicalnetwork.mythicalmod.registry.MythicalPackets
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockCoreEntity
import com.mythicalnetwork.mythicalmod.systems.multiblock.MultiblockStructure
import com.mythicalnetwork.mythicalmod.util.BlockHelper
import com.mythicalnetwork.mythicalmod.util.TooltipHelper
import foundry.veil.color.Color
import foundry.veil.color.ColorTheme
import foundry.veil.ui.Tooltippable
import foundry.veil.ui.VeilUIItemTooltipDataHolder
import foundry.veil.ui.anim.TooltipKeyframe
import foundry.veil.ui.anim.TooltipTimeline
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.BlockTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3
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
import java.util.Optional
import java.util.UUID

class LandmarkBlockEntity(type: BlockEntityType<*>, pos: BlockPos, state: BlockState, var pokemonType: ElementalType) :
    MultiblockCoreEntity(type, pos, state, STRUC), IAnimatable, Tooltippable {
    private var animationFactory: AnimationFactory = GeckoLibUtil.createFactory(this)
    private var tooltip: MutableList<Component> = mutableListOf()
    private var state: Int = 0
    private var theme: ColorTheme = ColorTheme().also { theme ->
        theme.addColor("background", Color(56, 63, 71).multiply(1.0f, 1.0f, 1.0f, 0.75f))
        theme.addColor("bottomBorder", Color(pokemonType.hue, false).lightenCopy(0.25f))
        theme.addColor("topBorder", Color(pokemonType.hue, false))
        theme.addColor("title", Color(174, 182, 198))
    }
    private val spawnData: LandmarkSpawnData? = LandmarkSpawnData.getForType(pokemonType)
    private var delay = 0
    private var isActive = false
    private var duration = 0
    private var cooldown = 0
    private var owner: UUID? = null

    init {
        tooltip.add(
            Component.literal(LANDMARK_TYPES[pokemonType] + " Landmark")
                .withStyle { s -> s.withColor(pokemonType.hue) })
        tooltip.add(Component.literal(""))
        tooltip.add(Component.literal(""))
    }

    companion object {
        val LANDMARK_TYPES: Map<ElementalType, String> = mutableMapOf(
            ElementalTypes.ELECTRIC to "Zeraora",
            ElementalTypes.DRAGON to "Rayquaza",
            ElementalTypes.NORMAL to "Arceus",
            ElementalTypes.FIRE to "Moltres",
            ElementalTypes.WATER to "Keldeo",
            ElementalTypes.GRASS to "Celebi",
            ElementalTypes.ICE to "Glastrier",
            ElementalTypes.FIGHTING to "Kubfu",
            ElementalTypes.POISON to "Poipole",
            ElementalTypes.GROUND to "Groudon",
            ElementalTypes.FLYING to "Ho-Oh",
            ElementalTypes.PSYCHIC to "Mewtwo",
            ElementalTypes.BUG to "Buzzwole",
            ElementalTypes.ROCK to "Diancie",
            ElementalTypes.GHOST to "Lunala",
            ElementalTypes.DARK to "Darkrai",
            ElementalTypes.STEEL to "Magearna",
            ElementalTypes.FAIRY to "Xerneas"
        )
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

        fun checkSpawnConditions(
            pokemon: PokemonEntity,
            canSwim: Boolean,
            canFly: Boolean,
            canWalk: Boolean,
            range: Int = MythicalContent.CONFIG.landmarkSpawnRange(),
            worldPosition: BlockPos,
            level: Level
        ): BlockPos? {
            // check all positions in the area (from config), if its a valid spawn location for the pokemon
            // check if the block below is solid, if the block is air, and if the blocks insied the pokemon's hitbox are air
            var blockPos: BlockPos? = null
            var blockList: MutableList<BlockPos> = mutableListOf()
            for (j in 0..5) {
                for (i in 0..5) {
                    val blocksToCheck: MutableIterable<BlockPos>? = getRandomBlocks(worldPosition, range, level)
                    blocksToCheck?.forEach { pos ->
                        val blocks: MutableIterable<BlockPos>? = BlockPos.withinManhattan(
                            pos,
                            pokemon.getDimensions(Pose.STANDING).width.toInt() + 1,
                            pokemon.getDimensions(Pose.STANDING).height.toInt() + 1,
                            pokemon.getDimensions(Pose.STANDING).width.toInt() + 1
                        )
                        for (block in blocks!!) {
                            pokemon.setPos(block.x.toDouble(), block.y.toDouble(), block.z.toDouble())
                            if (level.random.nextFloat() < 0.5) {
                                continue
                            }
                            if (!level.getBlockState(block).isAir && !canSwim) {
                                continue
                            }
                            if(pokemon.isInWall){
                                continue
                            }
                            if (block == worldPosition || block == worldPosition.north() || block == worldPosition.south() || block == worldPosition.east() || block == worldPosition.west() || block == worldPosition.north()
                                    .east() || block == worldPosition.north().west() || block == worldPosition.south()
                                    .east() || block == worldPosition.south().west()
                            ) {
                                continue
                            }
                            if (!canFly && canWalk && !canSwim) {
                                if (!level.getBlockState(block.above()).isAir || !level.getBlockState(block.north()).isAir || !level.getBlockState(
                                        block.south()
                                    ).isAir || !level.getBlockState(block.east()).isAir || !level.getBlockState(
                                        block.west()
                                    ).isAir || !level.getBlockState(
                                        block.north().east()
                                    ).isAir || !level.getBlockState(
                                        block.north().west()
                                    ).isAir || !level.getBlockState(
                                        block.south().east()
                                    ).isAir || !level.getBlockState(block.south().west()).isAir
                                ) {
                                    continue
                                }
                            }
                            if (canWalk && !canSwim && !canFly) {
                                if (!level.getBlockState(block.below()).isSolidRender(level, block) && !level.getBlockState(block).`is`(Blocks.WATER)) {
                                    continue
                                }
                            }
                            if (canSwim && !canWalk) {
                                if (!level.getBlockState(block.below()).`is`(Blocks.WATER) && !level.getBlockState(block).`is`(Blocks.WATER) && !level.getBlockState(block.above()).`is`(Blocks.WATER)
                                    && !level.getBlockState(block.above(2)).`is`(Blocks.WATER)
                                    && !level.getBlockState(block.above(3)).`is`(Blocks.WATER)) {
                                    continue
                                }
                                if(!pokemon.isInWater){
                                    continue
                                }
                            }
                            if(!canFly) {
                                if(!level.getBlockState(block.below()).isSolidRender(level, block.below()) && !(canSwim && level.getBlockState(block.below()).`is`(Blocks.WATER))){
                                    continue
                                }
                            }
                            blockPos = block
                            blockList.add(block)
                            break
                        }
                    }
                    if (blockPos != null) {
                        break
                    }
                }
            }
            if (blockList.isEmpty()) {
                return null
            }
            return blockList.random()
        }

        fun getRandomBlocks(pos: BlockPos, range: Int, level: Level): MutableIterable<BlockPos>? {
            return BlockPos.randomInCube(level!!.random, 16, pos, range)
        }
    }


    init {
    }

    override fun onUse(player: Player, hand: InteractionHand): InteractionResult {
        if (owner != player.uuid && !level!!.isClientSide) {
            player.sendSystemMessage(Component.literal("You do not own this landmark!"))
            return InteractionResult.CONSUME
        }
        state = if (state == 0) {
            1
        } else {
            0
        }
        if (!level!!.isClientSide) {
            player.getComponent(MythicalComponentRegistry.LANDMARK_PLAYER_TRACKER).let { tracker ->
                if (tracker.getActiveCount() < MythicalContent.CONFIG.maxPlayerLandmarkCount()) {
                    tracker.addActiveCount(1)
                    tracker.setCooldown(tracker.getCooldown() + MythicalContent.CONFIG.perMaxLandmarkTimer())
                } else {
                    player.sendSystemMessage(Component.literal("You have reached the maximum amount of active landmarks!"))
                    return InteractionResult.CONSUME
                }
            }
            isActive = state == 1
            if (isActive) {
                sendPacket()
                duration = spawnData?.duration!!
            } else {
                cooldown = spawnData?.cooldown!!
            }
            player.sendSystemMessage(Component.literal("State: $state, isActive: $isActive, duration: $duration, cooldown: $cooldown"))
        }
        return InteractionResult.SUCCESS
    }

    private fun sendPacket() {
        val buf: FriendlyByteBuf = PacketByteBufs.create()
        val sound: ResourceLocation =
            ResourceLocation("cobblemon", "pokemon.${LANDMARK_TYPES[pokemonType]?.lowercase()}.ambient")
        buf.writeResourceLocation(sound)
        buf.writeEnum(SoundSource.BLOCKS)
        buf.writeDouble(worldPosition.x.toDouble())
        buf.writeDouble(worldPosition.y.toDouble())
        buf.writeDouble(worldPosition.z.toDouble())
        buf.writeFloat(1.0f)
        buf.writeFloat(1.0f)
        var entities: List<ServerPlayer> =
            level!!.getEntities(null, AABB(worldPosition).inflate(16.0))!!.filterIsInstance<ServerPlayer>()
        ServerPlayNetworking.send(entities, MythicalPackets.UNVALIDATED_SOUND.identifier, buf)
    }

    fun tick(level: Level, pos: BlockPos, state: BlockState, entity: BlockEntity) {
        if (level.isClientSide) {
            val isActive: String = if (isActive) TooltipHelper.formatTime(duration.toLong()) else "Inactive"
            val component: MutableComponent =
                if (cooldown == 0) Component.literal("Remaining time: $isActive") else Component.literal(
                    "Cooldown: ${
                        TooltipHelper.formatTime(cooldown.toLong())
                    }"
                )
            tooltip[1] = component
            if (isOverpopulated()) {
                tooltip[2] = Component.literal("Too many nearby Pokemon")
            } else {
                tooltip[2] = Component.literal("Nearby Pokemon: ${getNearbyPokemonCount()}")
            }
            return
        }
        if (isActive && duration > 0) {
            duration--
            if (delay > 0) {
                delay--
            }
            if (delay == 0 && isPlayerNearby() && !isOverpopulated()) {
                delay = (spawnData?.maxDelay?.let { spawnData.minDelay.rangeTo(it) })?.random() ?: 0
                spawn()
            }
            spawnParticles(0.25)
        }
        if (duration == 0 && isActive) {
            isActive = false
            cooldown = spawnData?.cooldown ?: 0
        }
        if (cooldown > 0) {
            cooldown--
        }
        BlockHelper.updateAndNotifyState(level, pos)
    }

    private fun getNearbyPokemonCount(): String {
        val entities = level!!.getEntitiesOfClass(
            PokemonEntity::class.java,
            AABB(worldPosition).inflate(MythicalContent.CONFIG.landmarkSpawnRange().toDouble() * 2.0)
        )
        return entities.count().toString()
    }

    // TODO: Add idle active particles
    private fun spawnParticles(chance: Double) {
        var particleType: ParticleType<DustParticleOptions>? = ParticleTypes.DUST
        var particlePos: Vec3 = Vec3.atCenterOf(worldPosition)
        var particleSpeed: Vec3 = Vec3(0.0, 0.0, 0.0)
        var particleCount: Int = 0
        val color: Color = Color(pokemonType.hue)
        val blocks: MutableIterable<BlockPos>? =
            getRandomBlocks(worldPosition, MythicalContent.CONFIG.landmarkSpawnRange())
        blocks?.forEach { blockPos ->
            if (level!!.canSeeSky(blockPos)) {
                if (level!!.random.nextFloat() > chance) return
                particlePos = Vec3.atCenterOf(blockPos).add(
                    ((level!!.random.nextFloat() / 10f).toDouble()),
                    -0.5,
                    ((level!!.random.nextFloat() / 10f).toDouble())
                )
                particleSpeed = Vec3(
                    (level!!.random.nextFloat()).toDouble(),
                    (level!!.random.nextFloat()).toDouble(),
                    (level!!.random.nextFloat()).toDouble()
                )
                particleCount = 1
                (level!! as ServerLevel).sendParticles(
                    DustParticleOptions(
                        Vector3f(color.red, color.green, color.blue),
                        1.0F
                    ),
                    particlePos.x,
                    particlePos.y,
                    particlePos.z,
                    particleCount,
                    particleSpeed.x,
                    particleSpeed.y,
                    particleSpeed.z,
                    0.25
                )
            }
        }
    }

    private fun isPlayerNearby(): Boolean {
        val players = level!!.getEntitiesOfClass(
            Player::class.java,
            AABB(worldPosition).inflate(spawnData?.requiredPlayerRange?.toDouble() ?: 10.0)
        )
        return players.isNotEmpty()
    }

    private fun isOverpopulated(): Boolean {
        val entities = level!!.getEntitiesOfClass(
            PokemonEntity::class.java,
            AABB(worldPosition).inflate(MythicalContent.CONFIG.landmarkSpawnRange().toDouble() * 2.0)
        )
        return entities.size >= (spawnData?.maxNearbyEntities ?: 10)
    }

    private fun spawn() {
        val s = spawnData?.getRandomWithWeight(level!!) ?: return
        val speciesString: String = s.species
        val species: Species? = PokemonSpecies.getByName(speciesString)
        if (species == null) MythicalContent.sendDebugMessage("Species $speciesString does not exist")
        else {
            val level: Int? = level?.random?.nextInt(s.levelRange.get().first, s.levelRange.get().last)
            val pokemon: Pokemon = level?.let { species.create(it) } ?: species.create()
            s.aspects.forEach { aspect ->
                if (this.level!!.random.nextFloat() < aspect.chance) {
                    PokemonProperties.parse(aspect.aspect).apply(pokemon)
                }
            }
            spawn(pokemon, s.canSwim.orElse(false), s.canFly.orElse(false), s.canWalk.orElse(false))
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

    fun setOwner(player: UUID) {
        this.owner = player
    }

    override fun saveAdditional(nbt: CompoundTag) {
        super.saveAdditional(nbt)
        nbt.putInt("state", state)
        nbt.putInt("duration", duration)
        nbt.putInt("cooldown", cooldown)
        nbt.putInt("delay", delay)
        nbt.putBoolean("isActive", isActive)
        owner?.let { nbt.putUUID("owner", it) }
    }

    override fun load(nbt: CompoundTag) {
        super.load(nbt)
        state = nbt.getInt("state")
        duration = nbt.getInt("duration")
        cooldown = nbt.getInt("cooldown")
        delay = nbt.getInt("delay")
        isActive = nbt.getBoolean("isActive")
        if (nbt.contains("owner")) owner = nbt.getUUID("owner")
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
        return -30
    }

    override fun getItems(): MutableList<VeilUIItemTooltipDataHolder> {
        return mutableListOf()
    }

    fun spawn(pokemon: Pokemon, canSwim: Boolean, canFly: Boolean, canWalk: Boolean) {
        val pokemonEntity: PokemonEntity = PokemonEntity(level!!, pokemon)
        val pos: BlockPos? = checkSpawnConditions(pokemonEntity, pokemon.form.behaviour.moving.swim.canBreatheUnderwater, pokemon.form.behaviour.moving.fly.canFly, !pokemon.form.behaviour.moving.walk.avoidsLand)
        val color: Color = Color(pokemonType.hue)
        if (pos != null) {
            pokemonEntity.setPos(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
            var particleType: ParticleOptions = ParticleTypes.CRIMSON_SPORE
            var particlePos: Vec3 = Vec3.atCenterOf(pos).add(0.0, -0.5, 0.0)
            var particleSpeed: Vec3 =
                Vec3((level!!.random.nextFloat() / 10f).toDouble(), 0.25, (level!!.random.nextFloat() / 10f).toDouble())
            var particleCount: Int = 25
            var aabbVec: Vec3 =
                Vec3(pokemonEntity.boundingBox.xsize, pokemonEntity.boundingBox.ysize, pokemonEntity.boundingBox.zsize)
            (level!! as ServerLevel).sendParticles(
                DustParticleOptions(
                    Vector3f(color.red, color.green, color.blue),
                    1.0F
                ), particlePos.x, particlePos.y, particlePos.z, particleCount, aabbVec.x, aabbVec.y, aabbVec.z, 0.25
            )
            (level!! as ServerLevel).sendParticles(
                DustParticleOptions(Vector3f(color.red, color.green, color.blue), 1.0F),
                worldPosition.x.toDouble(),
                worldPosition.y.toDouble(),
                worldPosition.z.toDouble(), particleCount * 2, 1.5, 5.0, 1.5, 0.25
            )
            (level!! as ServerLevel).playSound(
                null,
                pos.x.toDouble(), pos.y.toDouble(),
                pos.z.toDouble(), SoundEvents.ALLAY_HURT, SoundSource.BLOCKS, 0.75f, 2.0f
            )
            (level!! as ServerLevel).playSound(
                null,
                worldPosition.x.toDouble(), worldPosition.y.toDouble(),
                worldPosition.z.toDouble(), SoundEvents.ALLAY_ITEM_GIVEN, SoundSource.BLOCKS, 1.0f, 0.75f
            )
            level!!.addFreshEntity(pokemonEntity)
            level!!.getEntities(
                null,
                AABB(worldPosition).inflate(MythicalContent.CONFIG.landmarkSpawnRange() * 3.0)
            ) { entity -> entity is Player }.forEach { player ->
                player.sendSystemMessage(Component.literal("A wild ${pokemon.species.name} has appeared!"))
            }
        }
    }

    fun checkSpawnConditions(
        pokemon: PokemonEntity,
        canSwim: Boolean,
        canFly: Boolean,
        canWalk: Boolean,
        range: Int = MythicalContent.CONFIG.landmarkSpawnRange()
    ): BlockPos? {
        // check all positions in the area (from config), if its a valid spawn location for the pokemon
        // check if the block below is solid, if the block is air, and if the blocks insied the pokemon's hitbox are air
        var blockPos: BlockPos? = null
        var blockList: MutableList<BlockPos> = mutableListOf()
        for (j in 0..5) {
            for (i in 0..5) {
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
                        if (block == worldPosition || block == worldPosition.north() || block == worldPosition.south() || block == worldPosition.east() || block == worldPosition.west() || block == worldPosition.north()
                                .east() || block == worldPosition.north().west() || block == worldPosition.south()
                                .east() || block == worldPosition.south().west()
                        ) {
                            continue
                        }
                        if (!canFly) {
                            if (!level!!.getBlockState(block.above()).isAir || !level!!.getBlockState(block.north()).isAir || !level!!.getBlockState(
                                    block.south()
                                ).isAir || !level!!.getBlockState(block.east()).isAir || !level!!.getBlockState(block.west()).isAir || !level!!.getBlockState(
                                    block.north().east()
                                ).isAir || !level!!.getBlockState(block.north().west()).isAir || !level!!.getBlockState(
                                    block.south().east()
                                ).isAir || !level!!.getBlockState(block.south().west()).isAir
                            ) {
                                continue
                            }
                        }
                        if (canWalk && !canSwim && !canFly) {
                            if (!level!!.getBlockState(block.below()).isSolidRender(level!!, block)) {
                                continue
                            }
                        }
                        if (canSwim && !canWalk) {
                            if (!level!!.getBlockState(block.below()).`is`(Blocks.WATER)) {
                                continue
                            }
                        }
                        blockPos = block
                        blockList.add(block)
                        break
                    }
                }
                if (blockPos != null) {
                    break
                }
            }
        }
        if (blockList.isEmpty()) {
            return null
        }
        return blockList.random()
    }

    private fun getRandomBlocks(pos: BlockPos, range: Int): MutableIterable<BlockPos>? {
        return BlockPos.randomInCube(level!!.random, 16, pos, range)
    }
}
