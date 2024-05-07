package mersif.cooler.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import mersif.cooler.block.chargeLevelsItems;
import org.jetbrains.annotations.Nullable;



public class ChargingStation extends Block {
    private static final BooleanProperty POWERED = BooleanProperty.of("powered");


    boolean active;
    boolean loopCheck;

    //to do anything with this you need a block entity type. Have fun ^.^
    public ChargingStation(Settings settings) {
        super(settings);
        active = false;
        //loopCheck = false;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(POWERED);
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {


        if(loopCheck) {
            loopCheck = false;
            return ActionResult.SUCCESS;
        }
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient)
        {

            // give vibe check, it goes 4 times for some reason

            boolean st = state.get(POWERED);
            if(st && !stack.isEmpty())
            {
                new chargeLevelsItems(stack);

            }
            loopCheck = true;


            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient()){
            world.setBlockState(pos, state.with(POWERED,false));
        }
    }



    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {

        if(!world.isClient())
        {
            if(world.isReceivingRedstonePower(pos))
            {
                world.setBlockState(pos, state.with(POWERED, true));
            }
            else{
                world.setBlockState(pos, state.with(POWERED, false));
            }


        }

//super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }
}
