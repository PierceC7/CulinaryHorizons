package com.linvy.culinaryhorizons.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class FoodConsumableItem extends ConsumableItem {

    private int healAmount;
    private float saturationModifier;
    private boolean hasFoodEffectTooltip;
    private boolean alwaysEdible;

    public FoodConsumableItem(int healAmount, float saturationModifier) {
        this.healAmount = healAmount;
        this.saturationModifier = saturationModifier;
        this.hasFoodEffectTooltip = false;
        this.alwaysEdible = false;
    }

    public FoodConsumableItem setHasFoodEffectTooltip(boolean hasFoodEffectTooltip) {
        this.hasFoodEffectTooltip = hasFoodEffectTooltip;
        return this;
    }

    public FoodConsumableItem setAlwaysEdible(boolean alwaysEdible) {
        this.alwaysEdible = alwaysEdible;
        return this;
    }

    @Override
    public FoodConsumableItem setHasCustomTooltip(boolean hasCustomTooltip) {
        super.setHasCustomTooltip(hasCustomTooltip);
        return this;
    }

    @Override
    public FoodConsumableItem setUseDuration(int useDuration) {
        super.setUseDuration(useDuration);
        return this;
    }

    @Override
    public FoodConsumableItem setUseAction(EnumAction useAction) {
        super.setUseAction(useAction);
        return this;
    }

    @Override
    public FoodConsumableItem setContainerItem(Item containerItem) {
        super.setContainerItem(containerItem);
        return this;
    }

    @Override
    public FoodConsumableItem setMaxStackSize(int maxStackSize) {
        super.setMaxStackSize(maxStackSize);
        return this;
    }

    @Override
    public FoodConsumableItem addEffect(Potion potion, int duration, int amplifier, float probability) {
        super.addEffect(potion, duration, amplifier, probability);
        return this;
    }

    @Override
    public FoodConsumableItem addEffect(Potion potion, int duration, int amplifier) {
        super.addEffect(potion, duration, amplifier);
        return this;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        player.getFoodStats()
            .addStats(healAmount, saturationModifier);
        return super.onEaten(stack, world, player);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (alwaysEdible || player.getFoodStats()
            .needFood() || player.capabilities.isCreativeMode) {
            return super.onItemRightClick(stack, world, player);
        }
        return stack;
    }
}
