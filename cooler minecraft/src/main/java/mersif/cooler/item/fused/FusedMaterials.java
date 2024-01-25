package mersif.cooler.item.fused;

import mersif.cooler.SampleItem;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Items;
import  net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ToolMaterial;   //not working for some reason
import net.minecraft.util.Lazy;



import java.util.function.Supplier;


public enum FusedMaterials implements ToolMaterial{
    COPPER(MiningLevels.IRON, 301, 6.5f, 2.7f, 3, () -> Ingredient.ofItems(Items.COPPER_INGOT));

    private final int itemDurability;
    private final int miningLevel;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairItem;


    FusedMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairItem = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairItem.get();
    }




}
