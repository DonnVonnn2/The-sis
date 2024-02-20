package mersif.cooler.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.RedstoneView;
import net.minecraft.world.World;

public interface RedstoneChargingCallback {

    Event<RedstoneChargingCallback> EVENT = EventFactory.createArrayBacked(RedstoneChargingCallback.class, (listeners) -> (armorstand, redstone, world) -> {
        for(RedstoneChargingCallback listener : listeners) {
            ActionResult result = listener.interact(armorstand, redstone, world);

            if(result != ActionResult.PASS){
                return result;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult interact(ArmorStandEntity armorstand, RedstoneView redstone, World world);
}
