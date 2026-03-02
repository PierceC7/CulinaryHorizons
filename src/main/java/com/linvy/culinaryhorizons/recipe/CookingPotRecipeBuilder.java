package com.linvy.culinaryhorizons.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CookingPotRecipeBuilder {

    private ItemStack result;
    private List<ItemStack> ingredients = new ArrayList<>();
    private ItemStack container;
    private int cookTime = CookingPotRecipe.NORMAL_COOKING;
    private float experience = CookingPotRecipe.MEDIUM_EXP;

    public static CookingPotRecipeBuilder create(Item result, int count) {
        CookingPotRecipeBuilder builder = new CookingPotRecipeBuilder();
        builder.result = new ItemStack(result, count);
        return builder;
    }

    public static CookingPotRecipeBuilder create(ItemStack result) {
        CookingPotRecipeBuilder builder = new CookingPotRecipeBuilder();
        builder.result = result;
        return builder;
    }

    public CookingPotRecipeBuilder addIngredient(Item item) {
        ingredients.add(new ItemStack(item));
        return this;
    }

    public CookingPotRecipeBuilder addIngredient(Item item, int count) {
        ingredients.add(new ItemStack(item, count));
        return this;
    }

    public CookingPotRecipeBuilder addIngredient(ItemStack stack) {
        ingredients.add(stack);
        return this;
    }

    public CookingPotRecipeBuilder setContainer(Item container) {
        this.container = new ItemStack(container);
        return this;
    }

    public CookingPotRecipeBuilder setCookTime(int cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public CookingPotRecipeBuilder setExperience(float experience) {
        this.experience = experience;
        return this;
    }

    public CookingPotRecipe build() {
        return new CookingPotRecipe(result, ingredients, container, cookTime, experience);
    }
}
