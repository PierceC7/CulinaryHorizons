package com.linvy.culinaryhorizons.item;

import com.linvy.culinaryhorizons.CulinaryHorizons;
import com.linvy.culinaryhorizons.ModCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class FoodItemBase extends ItemFood {

    private final int eatDuration;
    private final ItemStack containerItem;
    private final PotionEffect[] effects;
    private final boolean showEffectTooltip;
    private final IFoodEatenCallback onEatenCallback;

    protected FoodItemBase(String name, int healAmount, float saturation, boolean isWolfFood, int eatDuration, ItemStack containerItem, boolean alwaysEdible, int maxStackSize, boolean showEffectTooltip, IFoodEatenCallback onEatenCallback, PotionEffect[] effects) {
        super(healAmount, saturation, isWolfFood);
        this.setUnlocalizedName(name);
        this.eatDuration = eatDuration;
        this.containerItem = containerItem;
        this.effects = effects;
        this.showEffectTooltip = showEffectTooltip;
        this.onEatenCallback = onEatenCallback;

        if (alwaysEdible) {
            this.setAlwaysEdible();
        }

        this.setMaxStackSize(maxStackSize);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return eatDuration;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (effects != null) {
                for (PotionEffect effect : effects) {
                player.addPotionEffect(new PotionEffect(effect));
                }
            }
            if (onEatenCallback != null) {
                onEatenCallback.onEaten(stack, world, player);
            }            
        }
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onEaten(stack, world, player);

        // Return container item (bowl, bottle, etc.)
        if (containerItem != null) {
            if (!player.inventory.addItemStackToInventory(containerItem.copy())) {
                player.dropPlayerItemWithRandomChoice(containerItem.copy(), false);
            }
        }

        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);

        // Show potion effects if enabled
        if (showEffectTooltip && effects != null && effects.length > 0) {
            for (PotionEffect effect : effects) {
                Potion potion = Potion.potionTypes[effect.getPotionID()];
                String effectName = StatCollector.translateToLocal(potion.getName());

                // Add amplifier (I, II, III, etc.)
                if (effect.getAmplifier() > 0) {
                    effectName = effectName + " " + StatCollector.translateToLocal(
                        "potion.potency." + effect.getAmplifier());
                }

                // Add duration
                int duration = effect.getDuration();
                int minutes = duration / 1200;
                int seconds = (duration % 1200) / 20;

                String durationText = String.format("(%d:%02d)", minutes, seconds);

                tooltip.add(EnumChatFormatting.BLUE + effectName + " " +
                    EnumChatFormatting.GRAY + durationText);
            }
        }
    }
}
