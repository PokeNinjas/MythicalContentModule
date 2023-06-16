package com.mythicalnetwork.mythicalmod.content.base

import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.LazyLoadedValue
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import java.util.function.Supplier

enum class ArmorMaterials(
    private val internalName: String,
    private val durabilityMultiplier: Int,
    private val slotProtections: IntArray,
    private val enchantmentValue: Int,
    private val sound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    repairIngredientSupplier: Supplier<Ingredient>
) : ArmorMaterial {
    ROCKET_BOOTS(
        "rocketboots",
        5,
        intArrayOf(1, 2, 3, 1),
        15,
        SoundEvents.ARMOR_EQUIP_IRON,
        0.0f,
        0.0f,
        Supplier { Ingredient.EMPTY });

    private val repairIngredient: LazyLoadedValue<Ingredient>

    init {
        repairIngredient = LazyLoadedValue<Ingredient> { repairIngredientSupplier.get() }
    }

    override fun getDurabilityForSlot(slot: EquipmentSlot): Int {
        return HEALTH_PER_SLOT[slot.index] * durabilityMultiplier
    }

    override fun getDefenseForSlot(slot: EquipmentSlot): Int {
        return slotProtections[slot.index]
    }

    override fun getEnchantmentValue(): Int {
        return enchantmentValue
    }

    override fun getEquipSound(): SoundEvent {
        return sound
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get() as Ingredient
    }

    override fun getName(): String {
        return internalName
    }

    override fun getToughness(): Float {
        return toughness
    }

    override fun getKnockbackResistance(): Float {
        return knockbackResistance
    }

    companion object {
        private val HEALTH_PER_SLOT = intArrayOf(13, 15, 16, 11)
    }
}
