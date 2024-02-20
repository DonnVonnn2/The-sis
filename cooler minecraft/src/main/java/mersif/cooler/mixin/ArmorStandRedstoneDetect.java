package mersif.cooler.mixin;

import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.world.RedstoneView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArmorStandEntity.class)
public abstract class ArmorStandRedstoneDetect  {
    RedstoneView redstoneView;

/*
    private Boolean getCharge(RedstoneView red, World wold, Blo){
        boolean charge = false;
        red.isReceivingRedstonePower(this.p)


        return charge;
    }
*/


}
