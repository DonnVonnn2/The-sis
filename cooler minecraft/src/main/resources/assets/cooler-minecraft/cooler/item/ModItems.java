package mersif.cooler.item;

import mersif.cooler.SampleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;


public class ModItems {
// will register items.
	
	public static final Item WERLDSTARR = registerItem("werldstarr", new Item(new FabricItemSettings()));
	
	private static void addToMisc(FabricItemGroupEntries entries)
	{
		entries.add(WERLDSTARR);
	}
	
	private static Item registerItem(String name, Item obj) {
		return Registry.register(Registries.ITEM, new Identifier(SampleItem.MOD_ID, name), obj);
	}
	
	public static void registerItems() {
		SampleItem.LOGGER.info("Registering Mod Items for " + SampleItem.MOD_ID);
		
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addToMisc);
	}
}
