package com.mythicalnetwork.mythicalmod.content.radar

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.spawning.CobblemonSpawnPools
import com.cobblemon.mod.common.api.spawning.CobblemonWorldSpawnerManager
import com.cobblemon.mod.common.api.spawning.SpawnBucket
import com.cobblemon.mod.common.api.spawning.SpawnCause
import com.cobblemon.mod.common.api.spawning.condition.AreaSpawningCondition
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnDetail
import com.cobblemon.mod.common.api.spawning.spawner.PlayerSpawner
import com.cobblemon.mod.common.api.spawning.spawner.SpawningArea
import com.cobblemon.mod.common.command.argument.SpawnBucketArgumentType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.registry.BiomeIdentifierCondition
import com.cobblemon.mod.common.util.sendParticlesServer
import com.mythicalnetwork.mythicalmod.MythicalContent
import com.mythicalnetwork.mythicalmod.content.landmark.AspectData
import com.mythicalnetwork.mythicalmod.content.landmark.LandmarkBlockEntity
import dev.lightdream.logger.Debugger
import dev.onyxstudios.cca.api.v3.item.CcaNbtType
import dev.onyxstudios.cca.api.v3.item.ItemComponent
import net.fabricmc.fabric.api.util.NbtType
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.util.Mth
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.pathfinder.Path
import net.minecraft.world.phys.Vec3
import org.quiltmc.qkl.library.math.times
import java.util.Date

// TODO: Make it so radar bonuses are only applied if the player actually has the radar in their inventory.
class RadarItemComponentImpl(stack: ItemStack) : RadarItemComponent, ItemComponent(stack) {
    override fun isActive(): Boolean {
        if (!this.hasTag("active")) {
            this.putBoolean("active", false)
        }
        return this.getBoolean("active")
    }

    override fun setActive(active: Boolean) {
        this.putBoolean("active", active)
    }

    override fun isEnabled(): Boolean {
        if (!this.hasTag("enabled")) {
            this.putBoolean("enabled", true)
        }
        return this.getBoolean("enabled")
    }

    override fun setEnabled(enabled: Boolean) {
        this.putBoolean("enabled", enabled)
    }

    override fun getLastTickedTime(): Long {
        if (!this.hasTag("lastTickedTime", CcaNbtType.LONG)) {
            this.putInt("lastTickedTime", 0)
        }
        return this.getLong("lastTickedTime")
    }

    override fun setLastTickedTime(time: Long) {
        this.putLong("lastTickedTime", time)
    }

    override fun getSpecies(): String {
        if (!this.hasTag("species", CcaNbtType.STRING)) {
            this.putString("species", "random")
        }
        if (this.getString("species") == "random") {
            var species = PokemonSpecies.random()
            MythicalContent.sendDebugMessage("Random species: ${species.name}")
            while (MythicalContent.CONFIG.blacklistedSpecies().contains(species.resourceIdentifier.path)) {
                MythicalContent.sendDebugMessage("Random species: ${species.name} is blacklisted")
                species = PokemonSpecies.random()
            }
            this.putString("species", species.name)
        }
        return this.getString("species")
    }

    override fun setSpecies(species: String) {
        this.putString("species", species)
    }

    override fun getAspects(): List<AspectData> {
        if (!this.hasTag("aspects", CcaNbtType.LIST)) {
            this.putList("aspects", ListTag())
        }
        return this.getList("aspects", CcaNbtType.COMPOUND).map { AspectData.fromNbt(it as CompoundTag) }
    }

    override fun setAspects(aspects: List<AspectData>) {
        val aspectList = ListTag()
        aspects.forEach { aspectList.add(it.toNbt()) }
        this.putList("aspects", aspectList)
    }

    override fun getLevelRange(): IntRange {
        if (!this.hasTag("minLevel", CcaNbtType.INT) || !this.hasTag("maxLevel", CcaNbtType.INT)) {
            this.putInt("minLevel", 15)
            this.putInt("maxLevel", 38)
        }
        return this.getInt("minLevel")..this.getInt("maxLevel")
    }

    override fun setLevelRange(levelRange: IntRange) {
        this.putInt("minLevel", levelRange.first)
        this.putInt("maxLevel", levelRange.last)
    }

    override fun getChainLength(): Int {

        Debugger.log("====================================")
        Debugger.log(this.hasTag("chainLength"));
        Debugger.log(this.hasTag("chainLength"));
        Debugger.log(this.hasTag("chainLength"));
        Debugger.log(this.hasTag("chainLength"));
        Debugger.log(this.hasTag("chainLength"));
        Debugger.log(this.hasTag("chainLength"));
        Debugger.log("====================================")

        if (!this.hasTag("chainLength")) {
            this.putInt("chainLength", 0)
        }
        return this.getInt("chainLength")
    }

    override fun setChainLength(chainLength: Int) {
        this.putInt("chainLength", chainLength)
    }

    override fun getMaxLength(): Int {
        if (!this.hasTag("maxLength", NbtType.INT)) {
            this.putInt("maxLength", MythicalContent.CONFIG.defaultMaxChainLength())
        }
        return this.getInt("maxLength")
    }

    override fun setMaxLength(maxLevel: Int) {
        this.putInt("maxLength", maxLevel)
    }

    override fun getTimerTicks(): Int {
        if (!this.hasTag("timerTicks", CcaNbtType.INT)) {
            this.putInt("timerTicks", 0)
        }
        return this.getInt("timerTicks")
    }

    override fun setTimerTicks(timerTicks: Int) {
        this.putInt("timerTicks", timerTicks)
    }

    override fun tick(player: ServerPlayer) {
        val tag: CompoundTag = CompoundTag()
        this.writeToNbt(tag)
        MythicalContent.sendDebugMessage("Radar tag: $tag")
        if (!isActive()) return
        var isSearchedSpeciesNear: Boolean = false
        if (player.level.gameTime.toInt() % MythicalContent.CONFIG.spawnDelay() == 0) {
            isSearchedSpeciesNear = player.level.getEntitiesOfClass(
                PokemonEntity::class.java,
                player.boundingBox.inflate(MythicalContent.CONFIG.spawnRadius().toDouble())
            ).any {
                it.pokemon.species.name == getSpecies() && it.pokemon.aspects.contains("radar_spawned") && it.tags.contains(
                    player.uuid.toString()
                )
            }
        }
        if (player.level.gameTime - getLastTickedTime() >= MythicalContent.CONFIG.scanDelay()) {
            val spawner: PlayerSpawner = CobblemonWorldSpawnerManager.spawnersForPlayers[player.uuid] ?: return
            val buckets: List<String> = MythicalContent.CONFIG.bucketsToCheck()
            var canSpawn: Boolean = false
            for (buck in buckets) {
                if (canSpawn) {
                    break
                }
                val bucket = SpawnBucket()
                bucket.name = buck
                val cause = SpawnCause(spawner, bucket, player)
                val slice = spawner.prospector.prospect(
                    spawner = spawner,
                    area = SpawningArea(
                        cause = cause,
                        world = player.level,
                        baseX = Mth.ceil(player.x - Cobblemon.config.worldSliceDiameter / 2f),
                        baseY = Mth.ceil(player.y - Cobblemon.config.worldSliceHeight / 2f),
                        baseZ = Mth.ceil(player.z - Cobblemon.config.worldSliceDiameter / 2f),
                        length = Cobblemon.config.worldSliceDiameter,
                        height = Cobblemon.config.worldSliceHeight,
                        width = Cobblemon.config.worldSliceDiameter
                    )
                )
                val contexts = spawner.resolver.resolve(spawner, spawner.contextCalculators, slice)
                val spawnProbabilities = spawner.getSpawningSelector().getProbabilities(spawner, contexts)
                val namedProbabilities = mutableMapOf<String, Float>()
                spawnProbabilities.entries.forEach {
                    val nameText = it.key.getName()
                    val nameString = nameText.string
                    if (!namedProbabilities.containsKey(nameString)) {
                        namedProbabilities[nameString] = it.value
                    } else {
                        namedProbabilities[nameString] = (namedProbabilities[nameString] ?: 0F) + it.value
                    }
                    if(it.key.getName().string == getSpecies()) {
                        if(it.key is PokemonSpawnDetail){
                            (it.key as PokemonSpawnDetail).levelRange?.let { it1 -> setLevelRange(it1) }
                        }
                    }
                }
                MythicalContent.sendDebugMessage("Spawn probabilities for player ${player.name.string}: $namedProbabilities")
                if (namedProbabilities.containsKey(getSpecies())) {
                    MythicalContent.sendDebugMessage("Found spawn for player ${player.name.string}, found ${getSpecies()}. ${canSpawn()}, ${isEnabled()}, $isSearchedSpeciesNear")
                    canSpawn = true
                }
            }
            if (canSpawn) {
                if (!isEnabled()) {
                    player.level.playSound(
                        null,
                        player,
                        CobblemonSounds.PC_ON.get(),
                        SoundSource.PLAYERS,
                        0.5F,
                        1.5f
                    )
                }
                MythicalContent.sendDebugMessage("Enabled spawn anchor for player ${player.name.string}. ${canSpawn()}, ${isEnabled()}, $isSearchedSpeciesNear")
                setEnabled(true)
            } else if (isEnabled() && !isSearchedSpeciesNear) {
                MythicalContent.sendDebugMessage("Disabled spawn anchor for player ${player.name.string}. ${canSpawn()}, ${isEnabled()}, $isSearchedSpeciesNear")
                setEnabled(false)
            }
            setLastTickedTime(player.level.gameTime)
        }
        if ((player.level.gameTime % MythicalContent.CONFIG.spawnDelay()).toInt() == 0) {
            if ((isEnabled() || isSearchedSpeciesNear) && getSpecies() != "") {
                player.displayClientMessage(
                    Component.literal("Searching.." + ".".repeat(((player.level.gameTime - getLastTickedTime()) / 20).toInt())),
                    true
                )
                if (!isSearchedSpeciesNear) player.level.playSound(
                    null,
                    player,
                    SoundEvents.PLAYER_LEVELUP,
                    SoundSource.PLAYERS,
                    0.25F,
                    1.5f + (player.level.random.nextFloat() * 0.5f)
                )
                else player.level.playSound(
                    null,
                    player,
                    SoundEvents.EXPERIENCE_ORB_PICKUP,
                    SoundSource.PLAYERS,
                    0.25F,
                    1.5f + (player.level.random.nextFloat() * 0.5f)
                )
                val level: ServerLevel = player.level as ServerLevel
                if (level.random.nextFloat() < MythicalContent.CONFIG.spawnChance() && !isSearchedSpeciesNear) {
                    val species: Species = PokemonSpecies.getByName(
                        getSpecies().toLowerCase().filter { it.isLetterOrDigit() || it == '_' }) ?: return
                    val pokemon: Pokemon = species.create()
                    pokemon.level = getLevelRange().random()
                    PokemonProperties.parse("radar_spawned").apply(pokemon)
                    val entity: PokemonEntity = PokemonEntity(level, pokemon)
                    val attemptedPos: BlockPos? =
                        LandmarkBlockEntity.checkSpawnConditions(entity, pokemon.form.behaviour.moving.swim.canBreatheUnderwater, pokemon.form.behaviour.moving.fly.canFly, !pokemon.form.behaviour.moving.walk.avoidsLand, 16, player.onPos, level)
                    if (attemptedPos != null) {
                        entity.setPos(attemptedPos.x.toDouble(), attemptedPos.y.toDouble(), attemptedPos.z.toDouble())
                        entity.addTag(player.uuid.toString())
                        level.addFreshEntity(entity)
                        player.level.playSound(
                            null,
                            player,
                            SoundEvents.BOTTLE_FILL_DRAGONBREATH,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.5f + (player.level.random.nextFloat() * 0.5f)
                        )
                        player.level.playSound(
                            null,
                            player,
                            SoundEvents.BEACON_ACTIVATE,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.5f + (player.level.random.nextFloat() * 0.5f)
                        )
                    }
                }
            }
        }
    }

    override fun canSpawn(): Boolean {
        if (!this.hasTag("canSpawn")) {
            this.putBoolean("canSpawn", false)
        }
        return this.getBoolean("canSpawn")
    }

    override fun setCanSpawn(canSpawn: Boolean) {
        this.putBoolean("canSpawn", canSpawn)
    }

    override fun applyChainModifiers(level: ServerLevel, pokemon: Pokemon) {
        val ranges = MythicalContent.formatIvRangeValues()
        for (range in ranges.keys) {
            if (getChainLength() in range) {
                Cobblemon.statProvider.createEmptyIVs(ranges[range]!!).forEach { iv ->
                    pokemon.ivs[iv.key] = iv.value
                }
            }
        }
        val shinyRanges = MythicalContent.formatShinyChance()
        for (range in shinyRanges.keys) {
            if (getChainLength() in range) {
                if (level.random.nextFloat() < shinyRanges[range]!!) {
                    PokemonProperties.parse("shiny=yes").apply(pokemon)
                }
            }
        }
        val haRanges = MythicalContent.formatHiddenAbilityChance()
        for(range in haRanges.keys){
            if(getChainLength() in range){
                if(level.random.nextFloat() < haRanges[range]!!){
                    PokemonProperties.parse("hiddenability").apply(pokemon)
                }
            }
        }
    }
}

private fun Pokemon.hasLabels(labels: List<String>): Boolean {
    var hasLabels = false
    for (label in labels) {
        if (this.hasLabels(label)) {
            hasLabels = true
        }
    }
    return hasLabels
}
