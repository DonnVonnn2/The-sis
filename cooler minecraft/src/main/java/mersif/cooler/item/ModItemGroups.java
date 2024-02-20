package mersif.cooler.item;

import mersif.cooler.SampleItem;
import mersif.cooler.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemGroup;

import static mersif.cooler.item.ModItems.*;

public class ModItemGroups {
    public static final ItemGroup GittsAndShiggles = Registry.register(Registries.ITEM_GROUP, new Identifier(SampleItem.MOD_ID, "gittsandshiggles"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.gittsandshiggles")).icon(() -> new ItemStack(WERLDSTARR)).entries(((displayContext, entries) -> {
                entries.add(ModBlocks.Creeper_Block);
                entries.add(WERLDSTARR);
                entries.add(ModBlocks.DEEZ);

    })).build());

    public static void registerItemGroups(){
        SampleItem.LOGGER.info("Registering Item Groups!");
    }
}
