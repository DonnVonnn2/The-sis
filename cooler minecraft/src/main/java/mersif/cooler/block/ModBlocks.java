package mersif.cooler.block;

import mersif.cooler.SampleItem;
import mersif.cooler.block.custom.ChargingStation;
import mersif.cooler.block.custom.CreeperBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //w


    //blocks
    public static final Block Creeper_Block = registerBlock("creeper_block", new CreeperBlock(FabricBlockSettings.copyOf(Blocks.DIRT)));
    public static final Block DEEZ = registerBlock("deez", new Block(FabricBlockSettings.copyOf(Blocks.NETHERRACK)));

    //used to charged metallic items. made with redstone copper and gold. puts a charge on weapons/tools and armor from copper to gold. Best used on Copper and Iron.
    public static final Block CHARGING_STATION = registerBlock("charging_station", new ChargingStation(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));

    //block entities
    public static final BlockEntityType<CreeperBlockEntity> CREEPER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(SampleItem.MOD_ID, "creeper_block"), FabricBlockEntityTypeBuilder.create(CreeperBlockEntity::new, Creeper_Block).build());
    public static final BlockEntityType<ChargingStationEntity> CHARGING_STATION_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(SampleItem.MOD_ID, "charging_station"), FabricBlockEntityTypeBuilder.create(ChargingStationEntity::new, CHARGING_STATION).build());


    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(SampleItem.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(SampleItem.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBLocks(){
        SampleItem.LOGGER.info("Registering blocks!");
    }
}
