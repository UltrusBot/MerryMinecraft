package io.github.ultrusbot.merryminecraft.item;

import io.github.ultrusbot.merryminecraft.MerryMinecraft;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;


public enum MerryToolMaterials implements ToolMaterial {
    RED_CANDY_CANE(131, 6, 1.0F, 1, 10, Ingredient.ofItems(MerryMinecraft.RED_CANDY_CANE));
    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int miningLevel;
    private final int enchantability;
    private final Ingredient ingredient;

    MerryToolMaterials(int durability, float miningSpeed, float attackDamage, int miningLevel, int enchantability, Ingredient ingredient) {
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.miningLevel = miningLevel;
        this.enchantability = enchantability;
        this.ingredient = ingredient;
    }
    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return ingredient;
    }
}
