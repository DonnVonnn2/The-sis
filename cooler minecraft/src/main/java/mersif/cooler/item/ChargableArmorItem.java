package mersif.cooler.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

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


    private static final Map<EquipmentSlot, StatusEffectInstance> CHARGE_LEVEL_ONE =
            (new ImmutableMap.Builder<EquipmentSlot, StatusEffectInstance>()).put
                    (EquipmentSlot.CHEST, new StatusEffectInstance(StatusEffects.RESISTANCE,
                            -1, 0, false, false, true ))
                    .put(EquipmentSlot.HEAD, new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false, true))
                    .put(EquipmentSlot.LEGS, new StatusEffectInstance(StatusEffects.JUMP_BOOST, -1, 0, false, false, true)).
                    put(EquipmentSlot.FEET, new StatusEffectInstance(StatusEffects.SPEED, -1, 0, false, false, true)).build();


    // -1 duration is infinite :3. Let it be KNOWN


    public ChargableArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);

        boots = false;
        chestplate = false;
        leggings = false;
        helmet = false;
        charged = false;
        //absolutely bizzare glitch where if you have armor that isn't charged on
        //everything below it will no longer work.
        //i have no idea what causes this but i do not believe it is my code.

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
            //entity instanceof PlayerEntity player
            PlayerEntity playerEntity = (PlayerEntity) entity;
            charged = chargeCheck(stack);
            if(charged && (headCheck(playerEntity) || chestCheck(playerEntity) || legsCheck(playerEntity) || bootsCheck(playerEntity))){
                evaluateArmorEffects(playerEntity, stack);
                // dont get rid of the bools. it'll ruin everything
                // change this if to see if the bools are false and then if it is false have it remove the status effect :3
            }
            if (playerEntity.isPlayer()){
                armorEffectScrubber(playerEntity);

                //notes

                //managed to fix infinitely getting status effects, now im putting in removal process
                //in a perpetual state of being added. make a thing that doesn't refresh the effects.
            }
        }

        // super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean chargeCheck(@NotNull ItemStack item)
    {
        boolean check = false;
        if(!item.hasNbt())
        {
            return check;
        }
        NbtCompound nbt = item.getNbt();
        check = nbt.getBoolean("charged");

        return check;

    }


    @Override
    public boolean hasGlint(ItemStack stack) {
        if(chargeCheck(stack)){
            return true;
        }

        return false;
    }

    private void evaluateArmorEffects(PlayerEntity player, ItemStack stack) {


//        if(!compound.contains("charged") || !compound.getBoolean("charged")){return;}
//        //it goes over every instance of chargable armor item in inventory

        StatusEffectInstance statusEffect;
        if(chestCheck(player) && player.getEquippedStack(EquipmentSlot.CHEST) == stack){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.CHEST);        // this is for now, tweak it later
            addStatusEffect(player, statusEffect);
        }
        if(headCheck(player) && player.getEquippedStack(EquipmentSlot.HEAD) == stack){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.HEAD);
            addStatusEffect(player, statusEffect);
        }
        if(legsCheck(player) && player.getEquippedStack(EquipmentSlot.LEGS) == stack){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.LEGS);
            addStatusEffect(player, statusEffect);
        }
        if(bootsCheck(player) && player.getEquippedStack(EquipmentSlot.FEET) == stack){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.FEET);
            addStatusEffect(player, statusEffect);
        }

    }
    private void armorEffectScrubber(PlayerEntity player){

        StatusEffectInstance statusEffect;

        if(!chestplate && player.hasStatusEffect(CHARGE_LEVEL_ONE.get(EquipmentSlot.CHEST).getEffectType())){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.CHEST);
            player.removeStatusEffect(statusEffect.getEffectType());
        }
        if(!helmet && player.hasStatusEffect(CHARGE_LEVEL_ONE.get(EquipmentSlot.HEAD).getEffectType())){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.HEAD);
            player.removeStatusEffect(statusEffect.getEffectType());

        }
        if(!leggings && player.hasStatusEffect(CHARGE_LEVEL_ONE.get(EquipmentSlot.LEGS).getEffectType())){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.LEGS);
            player.removeStatusEffect(statusEffect.getEffectType());

        }
        if(!boots && player.hasStatusEffect(CHARGE_LEVEL_ONE.get(EquipmentSlot.FEET).getEffectType())){
            statusEffect = CHARGE_LEVEL_ONE.get(EquipmentSlot.FEET);
            player.removeStatusEffect(statusEffect.getEffectType());

        }

    }

   private void addStatusEffect(PlayerEntity player, StatusEffectInstance effectInstance){
        charged = player.hasStatusEffect(effectInstance.getEffectType());
        StatusEffectInstance instanceCopy = player.getStatusEffect(effectInstance.getEffectType());

        if(instanceCopy == null /*&& !instanceCopy.equals(effectInstance)*/){
            player.addStatusEffect( new StatusEffectInstance(effectInstance));

        }

    }

/*
    private void removeStatusEffect(PlayerEntity player, StatusEffectInstance effectInstance){
        StatusEffectInstance instanceCopy = player.getStatusEffect(effectInstance.getEffectType());

        if(instanceCopy != null */
/*&& !instanceCopy.equals(effectInstance)*//*
){
            player.removeStatusEffect(effectInstance.getEffectType());

        }

    }
*/

}
