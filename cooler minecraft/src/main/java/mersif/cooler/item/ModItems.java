package mersif.cooler.item;

import mersif.cooler.SampleItem;
import mersif.cooler.item.fused.FusedMaterials;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;


public class ModItems {
// will register items.
    public static final Item WERLDSTARR = registerItem("werldstarr", new Item(new FabricItemSettings()));   //just a test
    //Fused weapons
    public static final Item FUSEDIRONSWORD = registerItem("fused_iron_sword", new SwordItem(FusedMaterials.COPPER, 3, -2.4f, new Item.Settings()));
    public static final Item FUSEDIRONSHOVEL = registerItem("fused_iron_shovel", new ShovelItem(FusedMaterials.COPPER, 1, -2.7f, new Item.Settings()));
    public static final Item FUSEDIRONAXE = registerItem("fused_iron_axe", new AxeItem(FusedMaterials.COPPER, 5, -2.9f, new Item.Settings()));
    public static final Item FUSEDIRONPICKAXE = registerItem("fused_iron_pickaxe", new PickaxeItem(FusedMaterials.COPPER, 2, -2.8f, new Item.Settings()));
    public static final Item FUSEDIRONHOE = registerItem("fused_iron_hoe", new HoeItem(FusedMaterials.COPPER, 0, -1.4f, new Item.Settings()));        //she a hoe :0



    private static void addToMisc(FabricItemGroupEntries entries)
    {

        entries.add(WERLDSTARR);
        entries.add(FUSEDIRONSWORD);
        entries.add(FUSEDIRONAXE);
        entries.add(FUSEDIRONHOE);
        entries.add(FUSEDIRONPICKAXE);
        entries.add(FUSEDIRONSHOVEL);
    }

    private static Item registerItem(String name, Item obj) {
        return Registry.register(Registries.ITEM, new Identifier(SampleItem.MOD_ID, name), obj);
    }

    public static void registerItems() {
        SampleItem.LOGGER.info("Registering Mod Items for " + SampleItem.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addToMisc);
    }
}
