package mersif.cooler.block;

import com.google.common.collect.Multimap;
import mersif.cooler.item.ChargableArmorItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.InstantStatusEffect;
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
        Item item = itemStack.getItem();
        if(item.getClass() != ChargableArmorItem.class)
        {return;}


        NbtCompound nbt = itemStack.getNbt();
        String name = item.toString();
//        boolean nameCheck = materalCheck(name);
        String itemType = name.substring(name.indexOf("_") + 1, name.length());

        nbt.putBoolean("charged", true);
        itemStack.writeNbt(nbt);




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
        check = nbt.getBoolean("charged");

        return check;

    }


}

