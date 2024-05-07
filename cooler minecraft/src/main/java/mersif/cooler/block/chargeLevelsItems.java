package mersif.cooler.block;


import mersif.cooler.item.ChargableArmorItem;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;



public class chargeLevelsItems {

    // typo, items dont get charge levels. despite this error I am not changing the name

    public chargeLevelsItems(ItemStack itemStack){
        // constructor
        if(!itemStack.hasNbt())
        {
            return;
        }
        Item item = itemStack.getItem();
        if(item.getClass() == ChargableArmorItem.class)
        {
            NbtCompound nbt = itemStack.getNbt();

            nbt.putBoolean("charged", true);
            itemStack.writeNbt(nbt);
        }






    }



}

