package mersif.cooler.block;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import mersif.cooler.item.fused.FusedMaterials;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

import static mersif.cooler.item.fused.FusedArmorMaterials.COPPER;

public class chargeLevelsItems {

    // typo, items dont get charge levels. despite this error I am not changing the name

    public chargeLevelsItems(ItemStack itemStack){
        // constructor
        if(!itemStack.hasNbt())
        {
            return;
        }

        NbtCompound nbt = itemStack.getNbt();
        Item item = itemStack.getItem();
        String name = item.toString();
        boolean nameCheck = materalCheck(name);
        String itemType = name.substring(name.indexOf("_") +1, name.length());

        if(nameCheck)
        {
            nbt.putBoolean("charged", true);
            itemStack.writeNbt(nbt);
        }
        else
        {return;}
        switch (itemType)   //switch. No buff for helmet :3
        {
            case "sword", "axe":
                damageBuff(itemStack);
                swiftnessBuff(itemStack);
                break;
            case "shovel", "pickaxe", "hoe":
                swiftnessBuff(itemStack);
                break;
            case "boots":
                armorBuff(itemStack);       //speed boost only
                break;
            case "leggings":
                leggingBuff(itemStack);
                break;
            case "chestplate":
                chestplateBuff(itemStack);
                break;
        }


    }

    private boolean materalCheck(String name) {
        String[] materials = {"iron", "fuse", "oxid","gold"};
        // dont feel like making a thing to check for the space, we doin 4
        // ASSUMING it goes by offical item name :/
        name = name.substring(0, 4);


        for(int i = 0; i <= 3; i++)
        {
            String mat = materials[i];
            if(name.equals(mat))
            {
                return true;
            }
        }

        return false;
    }


    public boolean chargeCheck(@NotNull ItemStack item)
    {
        boolean check = false;
        if(!item.hasNbt())
        {
            return check;
        }
        NbtCompound nbt = item.getNbt();
        check = nbt.contains("charged", 1);

        return check;

    }

    private void damageBuff(ItemStack item){
        // if a tool there is a damage increase
        item.addEnchantment(Enchantments.POWER, 2);
    }

    private void swiftnessBuff(ItemStack item){
        //if a tool then swiftness
        //Update: cant get haste, have to increaase speed the old fashioned way
        item.addEnchantment(Enchantments.EFFICIENCY, 2);
    }

    private void armorBuff(ItemStack item){
        // if armor then increase walking speed and defense.
        //I have no fucking clue if this will work
        EntityAttributeModifier attributeModifier = new EntityAttributeModifier("generic.movement_speed", 0.1, EntityAttributeModifier.Operation.ADDITION);
        item.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, attributeModifier, EquipmentSlot.FEET);

    }

    private void leggingBuff(ItemStack item){
        //?????
    }
    private void chestplateBuff(ItemStack item){
        // armor
        EntityAttributeModifier attributeModifier = new EntityAttributeModifier("generic.armor", 8, EntityAttributeModifier.Operation.ADDITION);
        item.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, attributeModifier, EquipmentSlot.CHEST);
    }

}