package mersif.cooler.charged;

import net.minecraft.item.*;
import net.minecraft.util.Arm;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ChargedArmorEntity extends ChargedEntity{

    private ArmorItem armorItem;
    private ItemStack stack;


    public ChargedArmorEntity(@NotNull ItemStack itemStack) {
        super(itemStack);
        armorItem = (ArmorItem) itemStack.getItem();
        stack = itemStack;
        isCharged(itemStack);
        doCharge();



    }

    private void doCharge(){
        // gives armor buffs
        ArmorMaterial materials;


        materials = armorItem.getMaterial();



    }


}
