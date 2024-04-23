package mersif.cooler.item;

import mersif.cooler.SampleItem;
import mersif.cooler.block.ModBlocks;
import mersif.cooler.item.fused.FusedMaterials;
import mersif.cooler.item.fused.FusedArmorMaterials;
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
    public static final Item FUSEDIRONSWORD = registerItem("fused_sword", new SwordItem(FusedMaterials.COPPER, 3, -2.4f, new Item.Settings()));
    public static final Item FUSEDIRONSHOVEL = registerItem("fused_shovel", new ShovelItem(FusedMaterials.COPPER, 1, -2.7f, new Item.Settings()));
    public static final Item FUSEDIRONAXE = registerItem("fused_axe", new AxeItem(FusedMaterials.COPPER, 5, -2.9f, new Item.Settings()));
    public static final Item FUSEDIRONPICKAXE = registerItem("fused_pickaxe", new PickaxeItem(FusedMaterials.COPPER, 2, -2.8f, new Item.Settings()));
    public static final Item FUSEDIRONHOE = registerItem("fused_hoe", new HoeItem(FusedMaterials.COPPER, 0, -1.4f, new Item.Settings()));        //she a hoe :0

    //fused armor

    public static final Item FUSEDIRONHELMET = registerItem("fused_helmet", new ChargableArmorItem(FusedArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item FUSEDIRONCHESTPLATE = registerItem("fused_chestplate", new ChargableArmorItem(FusedArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Settings()));

    public static final Item FUSEDIRONLEGGINGS = registerItem("fused_leggings", new ChargableArmorItem(FusedArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item FUSEDIRONBOOTS = registerItem("fused_boots", new ChargableArmorItem(FusedArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Settings()));

    //oxidised armor
    public static final Item OXIDIZEDHELMET = registerItem("oxidized_helmet", new ChargableArmorItem(FusedArmorMaterials.OXIDIZED, ArmorItem.Type.HELMET, new Item.Settings()));

    public static final Item OXIDIZEDCHESTPLATE = registerItem("oxidized_chestplate", new ChargableArmorItem(FusedArmorMaterials.OXIDIZED, ArmorItem.Type.CHESTPLATE, new Item.Settings()));

    public static final Item OXIDIZEDLEGGINGS = registerItem("oxidized_leggings", new ChargableArmorItem(FusedArmorMaterials.OXIDIZED, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item OXIDIZEDBOOTS = registerItem("oxidized_boots", new ChargableArmorItem(FusedArmorMaterials.OXIDIZED, ArmorItem.Type.BOOTS, new Item.Settings()));

    //oxidized weapons

    public static final Item OXIDIZEDSWORD = registerItem("oxidized_sword", new SwordItem(FusedMaterials.OXIDIZED, 3, -2.4f, new Item.Settings()));
    public static final Item OXIDIZEDAXE = registerItem("oxidized_axe", new AxeItem(FusedMaterials.OXIDIZED, 5, -2.9f, new Item.Settings()));
    public static final Item OXIDIZEDSHOVEL =registerItem("oxidized_shovel", new ShovelItem(FusedMaterials.OXIDIZED, 1, -2.7f, new Item.Settings()));
    public static final Item OXIDIZEDHOE = registerItem("oxidized_hoe", new HoeItem(FusedMaterials.OXIDIZED, 0, -1.4f, new Item.Settings()));
    public static final Item OXDIZEDPICKAXE = registerItem("oxidized_pickaxe", new PickaxeItem(FusedMaterials.OXIDIZED, 2, -2.8f, new Item.Settings()));

    private static void addToMisc(FabricItemGroupEntries entries)
    {


        //marked unstable. IDK waht to do about that
        entries.add(FUSEDIRONSWORD);
        entries.add(FUSEDIRONAXE);
        entries.add(FUSEDIRONHOE);
        entries.add(FUSEDIRONPICKAXE);
        entries.add(FUSEDIRONSHOVEL);
        entries.add(FUSEDIRONHELMET);
        entries.add(FUSEDIRONCHESTPLATE);
        entries.add(FUSEDIRONLEGGINGS);
        entries.add(FUSEDIRONBOOTS);

        entries.add(ModBlocks.CHARGING_STATION);

        entries.add(OXIDIZEDHELMET);
        entries.add(OXIDIZEDCHESTPLATE);
        entries.add(OXIDIZEDLEGGINGS);
        entries.add(OXIDIZEDBOOTS);
        entries.add(OXDIZEDPICKAXE);
        entries.add(OXIDIZEDAXE);
        entries.add(OXIDIZEDHOE);
        entries.add(OXIDIZEDSHOVEL);
        entries.add(OXIDIZEDSWORD);
    }

    private static Item registerItem(String name, Item obj) {
        return Registry.register(Registries.ITEM, new Identifier(SampleItem.MOD_ID, name), obj);
    }

    public static void registerItems() {
        SampleItem.LOGGER.info("Registering Mod Items for " + SampleItem.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addToMisc);
    }


}
