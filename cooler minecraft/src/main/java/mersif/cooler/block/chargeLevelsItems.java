package mersif.cooler.block;

import com.google.common.collect.Multimap;
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
import net.minecraft.entity.attribute.AttributeModifierCreator;
import net.minecraft.item.*;
import mersif.cooler.item.fused.FusedMaterials;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

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
        //Update: cant get haste, have to increase speed the old fashioned way
        item.addEnchantment(Enchantments.EFFICIENCY, 2);
    }

    private void armorBuff(ItemStack item){
        // if armor then increase walking speed and defense.
        //I have no fucking clue if this will work
           // this is the answer to all of your probelm. Update: it didnt do shit, help


        EntityAttributeModifier attributeModifier = (EntityAttributeModifier) item.getAttributeModifiers(EquipmentSlot.MAINHAND);     // goes through a bunch of other stuff, ignores all below this one for some reason
        ChargedAttributeModifier mod = new ChargedAttributeModifier(attributeModifier.getId(), attributeModifier.getValue(), attributeModifier.getName(), EntityAttributeModifier.Operation.ADDITION);
        attributeModifier = mod.createAttributeModifier(3);
        item.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, attributeModifier, EquipmentSlot.FEET);

        //item.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, attribute, EquipmentSlot.FEET);

    }

    private void leggingBuff(ItemStack item){
        //?????
    }
    private void chestplateBuff(ItemStack item){
        // armor
        EntityAttributeModifier oldAttributes = (EntityAttributeModifier) item.getAttributeModifiers(EquipmentSlot.MAINHAND);
        EntityAttributeModifier attributeModifier = new EntityAttributeModifier("generic.armor", 3 + oldAttributes.getValue(), EntityAttributeModifier.Operation.ADDITION);
        item.addAttributeModifier(EntityAttributes.GENERIC_ARMOR, attributeModifier, EquipmentSlot.CHEST);
    }

    class ChargedAttributeModifier implements AttributeModifierCreator {

        private final UUID uuid;
        private final double basevalue;
        private final EntityAttributeModifier.Operation operation;
        private final String attribute;

        ChargedAttributeModifier(UUID uuid, double val, String att, EntityAttributeModifier.Operation opp ){
            this.uuid = uuid;
            this.basevalue = val;
            this.operation = opp;
            this.attribute = att;

        }

        @Override
        public UUID getUuid() {
            return this.uuid;
        }

        @Override
        public EntityAttributeModifier createAttributeModifier(int amplifier) {
            return new EntityAttributeModifier(this.uuid, attribute, basevalue * (double) (amplifier + 1), this.operation);
        }
    }

}

