package mersif.cooler.block;
import mersif.cooler.SampleItem;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static mersif.cooler.block.ModBlocks.*;

public class CreeperBlockEntity extends BlockEntity {
    public CreeperBlockEntity(BlockPos pos, BlockState state) {
        super(CREEPER_BLOCK_ENTITY, pos, state);
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }


/*
    @Override
    protected void writeNbt(NbtCompound nbt) {

        nbt.putBoolean("ignited", false);
        nbt.putBoolean("charged", false);
        nbt.putShort("fuse", (short) 4);


        super.writeNbt(nbt);

    }
*/
}
