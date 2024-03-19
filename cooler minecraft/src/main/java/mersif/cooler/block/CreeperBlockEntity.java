package mersif.cooler.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import static mersif.cooler.block.ModBlocks.CREEPER_BLOCK_ENTITY;

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


    @Override
    protected void writeNbt(NbtCompound nbt) {

        nbt.putBoolean("ignited", false);
        nbt.putBoolean("charged", false);
        nbt.putShort("fuse", (short) 4);


        super.writeNbt(nbt);

    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }
}
