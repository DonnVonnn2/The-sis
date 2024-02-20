package mersif.cooler.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChargingStation extends Block {


    boolean active;
    boolean loopCheck;

    //to do anything with this you need a block entity type. Have fun ^.^
    public ChargingStation(Settings settings) {
        super(settings);
        active = false;
        //loopCheck = false;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        byte hatin = 1;

        if(loopCheck) {
            loopCheck = false;
            return ActionResult.SUCCESS;
        }

        if (!world.isClient)
        {

            // give vibe check, it goes 4 times for some reason

            if(active && hatin == 1)
            {
                player.sendMessage(Text.literal("Aw, don't hate me because I'm beautiful nigga,\nmaybe if you got diamond armor Creepers wouldn\'t kill you bitch ass all the time.\nBetter yet maybe you\'d actually win a game of sky games cuz you suck ass\nN I G G A!"));
                loopCheck = true;
            }
            else {
                player.sendMessage(Text.literal("Man, fuck you. I'll see you at work."));
                loopCheck = true;
            }


            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {



        if(world.isReceivingRedstonePower(pos))
        {
            active = true;
        }
        else {active = false;}
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }
}
