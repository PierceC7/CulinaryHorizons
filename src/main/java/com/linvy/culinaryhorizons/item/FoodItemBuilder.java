package com.linvy.culinaryhorizons.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class FoodItemBuilder {

    private final String name;
    private final int healAmount;
    private final float saturation;

    private boolean isWolfFood = false;
    private int eatDuration = 32;
    private ItemStack containerItem = null;
    private boolean alwaysEdible = false;
    private int maxStackSize = 64;
    private List<PotionEffect> effects = new ArrayList<>();
    private boolean showEffectTooltip = true;

    private FoodItemBuilder(String name, int healAmount, float saturation) {
        this.name = name;
        this.healAmount = healAmount;
        this.saturation = saturation;
    }

    public static FoodItemBuilder create(String name, int healAmount, float saturation) {
        return new FoodItemBuilder(name, healAmount, saturation);
    }


    public FoodItemBuilder setWolfFood(boolean isWolfFood) {
        this.isWolfFood = isWolfFood;
        return this;
    }


    public FoodItemBuilder setEatDuration(int ticks) {
        this.eatDuration = ticks;
        return this;
    }

    public FoodItemBuilder setContainer(Item container) {
        this.containerItem = new ItemStack(container);
        return this;
    }

    public FoodItemBuilder setContainer(ItemStack container) {
        this.containerItem = container;
        return this;
    }

    public FoodItemBuilder setAlwaysEdible(boolean alwaysEdible) {
        this.alwaysEdible = alwaysEdible;
        return this;
    }

    public FoodItemBuilder setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        return this;
    }

    public FoodItemBuilder setShowEffectTooltip(boolean show) {
        this.showEffectTooltip = show;
        return this;
    }

    public FoodItemBuilder addEffect(PotionEffect effect) {
        this.effects.add(effect);
        return this;
    }

    public FoodItemBuilder addEffect(int potionId, int duration, int amplifier) {
        this.effects.add(new PotionEffect(potionId, duration, amplifier));
        return this;
    }

    public FoodItemBase build() {
        PotionEffect[] effectArray = effects.toArray(new PotionEffect[0]);
        return new FoodItemBase(name, healAmount, saturation, isWolfFood, eatDuration, containerItem, alwaysEdible, maxStackSize, showEffectTooltip, effectArray);
    }
}
