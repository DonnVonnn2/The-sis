package mersif.cooler.charged;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.NotNull;

public abstract class ChargedEntity {

    /*
    ChargedEnity is an abstract classed used to define what a charged entity is while leaving room for specifics
    This file has the very basics, which includes the charged nbt tag, levels, a counter until no longer charged,
    and something that allows it to come unchaged while submerged in water.

    The main focus of this is to get the charged mechanics sorted out.
     */
    public enum LEVELS {
        QUARTER,
        HALF,
        FULL
    };

    public ChargedEntity(@NotNull ItemStack itemStack) {


        NbtCompound nbt = itemStack.getNbt();
        nbt.putBoolean("charged", true);

        itemStack.writeNbt(nbt);

    }

    public boolean isCharged(ItemStack item) {
        NbtCompound nbt1;
        nbt1 = item.getNbt();
        if((!nbt1.contains("charged")) && nbt1.getBoolean("charged") == true)
        {
            return true;
        }

        return false;
    }

    protected void removeCharged(ItemStack item) {
        NbtCompound nbt = item.getNbt();

        if(!nbt.contains("charged")){
            return;
        }
        else if(nbt.getBoolean("charged") == true)
        {
            nbt.remove("charged");
        }
    }

    private void chargeCounter() {
        //gotta plan this one
    }








}
