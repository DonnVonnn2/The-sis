package mersif.cooler.event;

import mersif.cooler.item.fused.FusedArmorMaterials;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.ServerMetadata;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.RedstoneView;
import net.minecraft.world.World;

public class ArmorChargingHandler implements RedstoneChargingCallback{

    @Override
    public ActionResult interact(ArmorStandEntity armorstand, RedstoneView redstone, World world) {

        if(redstone.isReceivingRedstonePower(armorstand.getBlockPos()))
        {
            //https://www.youtube.com/watch?v=IMl8ydhX5TY

            PlayerEntity p  = null;
            p.sendMessage(Text.of("test complate"));


        }

        return ActionResult.PASS;
    }
}


// to get it to work be sure you have all of the right block states implemented https://www.youtube.com/watch?v=UvD2gAgXV1o
//you should have all block changing happenings on the server