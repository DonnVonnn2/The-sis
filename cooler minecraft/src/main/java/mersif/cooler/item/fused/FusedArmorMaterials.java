package mersif.cooler.item.fused;

import mersif.cooler.SampleItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum FusedArmorMaterials implements ArmorMaterial {
    COPPER("copper", 20,  new int[] {2,5 ,6, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.1f,  0f),
    OXIDIZED("oxidized", 10, new int[] {2, 5, 6, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.1f, 0f)
    ;

    // it didnt work because you  FORGOT THE COMMA

    private final String Name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;

    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;

    private final int chargeLevel;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    FusedArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int chargeLevel, SoundEvent equipSound, float toughness, float knockbackResistance) {
        this.Name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.chargeLevel = chargeLevel;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return SampleItem.MOD_ID + ":" + this.Name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public int getChargeLevel(){return this.chargeLevel;}
}
