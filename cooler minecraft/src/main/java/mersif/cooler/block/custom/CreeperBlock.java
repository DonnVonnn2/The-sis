package mersif.cooler.block.custom;

import mersif.cooler.block.CreeperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class CreeperBlock extends Block implements BlockEntityProvider{
    private int chargeLevel = 0;

    public CreeperBlock(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        CreeperBlockEntity creeperBlock;

        if(!world.isClient)
        {
            player.sendMessage(Text.literal("SUGOMA"));

        }

        return super.onUse(state, world, pos, player, hand, hit);
    }



    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {

/*
        if(world.isReceivingRedstonePower(sourcePos))
        {
            first argument for create explosion must be an entity, cannot be anything else :(
            world.createExplosion(, 2, 2, 2, 3.4f, World.ExplosionSourceType.valueOf(String.valueOf(World.ExplosionSourceType.TNT)));
        }
*/

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CreeperBlockEntity(pos, state);
    }
}
