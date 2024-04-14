package mersif.cooler.item;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.SetMultimap;
import mersif.cooler.item.fused.FusedArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Set;

public class ChargableArmorItem extends ArmorItem {

    //chargable armor item
    //

    // cool, now that im done copying from that one dude, try to make it possible for this one to work with the other fils :)

    // these booleans will be to check if a certain piece of armor is being worn
    private boolean boots;
    private boolean chestplate;
    private boolean leggings;
    private boolean helmet;
    public boolean charged;


    private static final Map<EquipmentSlot, StatusEffectInstance> CHESTPLATE_CHARGED_BUFF =
            (new ImmutableMap.Builder<EquipmentSlot, StatusEffectInstance>()).put
                    (EquipmentSlot.CHEST, new StatusEffectInstance(StatusEffects.HASTE,
                            -1, 1, false, false, false )).build();

    // -1 duration is infinite :3. Let it be KNOWN


    public ChargableArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);

        boots = false;
        chestplate = false;
        leggings = false;
        helmet = false;
        charged = false;

    }


    private boolean bootsCheck(PlayerEntity player){
        ItemStack shoes = player.getInventory().getArmorStack(0);
        boots = !shoes.isEmpty();
        return boots;
    }

    private boolean legsCheck(PlayerEntity player){
        ItemStack pants = player.getInventory().getArmorStack(1);
        leggings = !pants.isEmpty();
        return leggings;
    }
    private boolean chestCheck(PlayerEntity player){
        ItemStack shirt = player.getInventory().getArmorStack(2);
        chestplate = !shirt.isEmpty();
        return chestplate;
    }

    private boolean headCheck(PlayerEntity player){
        ItemStack hat = player.getInventory().getArmorStack(3);
        helmet = !hat.isEmpty();
        return helmet;
    }






    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if(!world.isClient)
        {
            if(entity instanceof PlayerEntity player && (headCheck(player) || chestCheck(player) || legsCheck(player) || bootsCheck(player))){
                evaluateArmorEffects(player);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {

        ArmorMaterial armorMaterial;    // READ THIS!!! have map work based on equipment slot instead of armor material
        StatusEffectInstance statusEffect;
         //Set<Map.Entry<ArmorMaterial, StatusEffectInstance>> entry =  CHESTPLATE_CHARGED_BUFF.entrySet();
         // cannot cast map entry onto set :)
            // the boolean check is redundant since its a public variable
        if(chestplate){
            statusEffect = CHESTPLATE_CHARGED_BUFF.get(EquipmentSlot.CHEST);        // this is for now, tweak it later

            addStatusEffect(player, statusEffect);
        }
        if(helmet){
            player.sendMessage(Text.literal("Not implemented yet, use chestplate"));
        }

/*
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : CHESTPLATE_CHARGED_BUFF.entrySet()) {

            this works for some reason. I D K W H Y
        }
*/
    }

    private void addStatusEffect(PlayerEntity player, StatusEffectInstance effectInstance){
        charged = player.hasStatusEffect(effectInstance.getEffectType());

        if(!charged){
            player.addStatusEffect( new StatusEffectInstance(effectInstance));
        }
        else{
            player.removeStatusEffect(effectInstance.getEffectType());
        }

    }

}
