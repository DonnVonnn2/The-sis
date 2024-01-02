package mersif.cooler.recipe;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

public class SmithingFusionRecipe implements SmithingRecipe {
    public final Ingredient base;
    public final Ingredient addition;
    public final ItemStack result;

    public SmithingFusionRecipe(Ingredient base, Ingredient addition, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    // could delete this ngl but keep it for now. gonna remove if truly has no purpose

    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        ItemStack itemStack = this.result.copy();
        NbtCompound nbtCompound = inventory.getStack(1).getNbt();
        if (nbtCompound != null) {
            itemStack.setNbt(nbtCompound.copy());
        }
        return itemStack;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.result;
    }

    @Override
    public RecipeType<?> getType(){
        return Type.INSTANCE;
    }

    @Override
    public boolean testTemplate(ItemStack stack) {
        return false;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;

    }



    public static class Type implements RecipeType<SmithingFusionRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "smithing_fusion";
    }

    /*
    make the serializer it's own file. work on trying to get it to read the damn variables because I aint dealin with all dis shit. Play genshin later, I need my Barbara.
     */

    public static class Serializer implements RecipeSerializer<SmithingFusionRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "smithing_fusion";
        //balls

        //be sure to remove the template when you finally get this working

        public final Codec<SmithingFusionRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(  Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
                RecipeCodecs.CRAFTING_RESULT.fieldOf("result").forGetter(recipe -> recipe.result)).apply(instance,SmithingFusionRecipe::new));


        @Override
        public Codec<SmithingFusionRecipe> codec() {
            return CODEC;
        }

        @Override
        public SmithingFusionRecipe read(PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new SmithingFusionRecipe(ingredient, ingredient2, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, SmithingFusionRecipe smithingTransformRecipe) {
            smithingTransformRecipe.base.write(packetByteBuf);
            smithingTransformRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingTransformRecipe.result);
        }


//        @Override
//        public Recipe read(PacketByteBuf buf) {
//            return this.read(buf);
//        }
//       // can't have this one and the other one. hope it works without this.



    }


}
