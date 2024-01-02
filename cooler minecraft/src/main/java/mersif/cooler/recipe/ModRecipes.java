package mersif.cooler.recipe;

import mersif.cooler.SampleItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(SampleItem.MOD_ID, SmithingFusionRecipe.Serializer.ID),
                SmithingFusionRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(SampleItem.MOD_ID, SmithingFusionRecipe.Type.ID),
                SmithingFusionRecipe.Type.INSTANCE);

    }
}
