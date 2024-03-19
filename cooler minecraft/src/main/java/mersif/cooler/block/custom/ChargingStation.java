package mersif.cooler.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import mersif.cooler.block.chargeLevelsItems;

import java.util.Collection;

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
                player.sendMessage(Text.literal("0u0"));
                chargeLevelsItems chargeLevelsItems = new chargeLevelsItems(stack);


            }
            else {
                player.sendMessage(Text.literal("Itemless behavoir"));
            }
            loopCheck = true;


            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {


        if(world.isReceivingRedstonePower(pos))
        {
            world.setBlockState(pos, state.cycle(POWERED));
        }
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }
}
