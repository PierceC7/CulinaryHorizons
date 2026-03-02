package com.linvy.culinaryhorizons.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CookingPotRecipe {

    public static final int INPUT_SLOTS = 6;

    private final List<ItemStack> ingredients;
    private final ItemStack result;
    private final ItemStack container;
    private final int cookTime;
    private final float experience;

    public static final int FAST_COOKING = 100;      // 5 seconds
    public static final int NORMAL_COOKING = 200;    // 10 seconds
    public static final int SLOW_COOKING = 400;      // 20 seconds

    public static final float SMALL_EXP = 0.35F;
    public static final float MEDIUM_EXP = 1.0F;
    public static final float LARGE_EXP = 2.0F;

    public CookingPotRecipe(ItemStack result, List<ItemStack> ingredients, ItemStack container, int cookTime, float experience) {
        this.result = result;
        this.ingredients = ingredients;

        if (container != null && container.stackSize > 0) {
            this.container = container;
        } else if (Objects.requireNonNull(result.getItem()).getContainerItem() != null) {
            this.container = new ItemStack(result.getItem().getContainerItem());
        } else {
            this.container = null;
        }

        this.cookTime = cookTime;
        this.experience = experience;
    }

    public boolean matches(ItemStack[] potIngredients) {
        List<ItemStack> inputs = new ArrayList<>();
        int itemCount = 0;

        for (int i = 0; i < INPUT_SLOTS; i++) {
            if (potIngredients[i] != null && potIngredients[i].stackSize > 0) {
                itemCount++;
                inputs.add(potIngredients[i]);
            }
        }

        if (itemCount != this.ingredients.size()) {
            return false;
        }

        return findMatches(inputs, this.ingredients);
    }

    private boolean findMatches(List<ItemStack> inputs, List<ItemStack> recipeIngredients) {
        List<ItemStack> remainingInputs = new ArrayList<>();
        for (ItemStack input : inputs) {
            remainingInputs.add(input.copy());
        }

        for (ItemStack recipeIngredient : recipeIngredients) {
            boolean found = false;

            for (int i = 0; i < remainingInputs.size(); i++) {
                ItemStack input = remainingInputs.get(i);

                if (itemMatches(recipeIngredient, input)) {
                    remainingInputs.remove(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    private boolean itemMatches(ItemStack recipe, ItemStack input) {
        if (recipe == null || input == null) return false;

        if (recipe.getItem() != input.getItem()) {
            return false;
        }

        return recipe.getItemDamage() == 32767 || recipe.getItemDamage() == input.getItemDamage();
    }

    public List<ItemStack> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result.copy();
    }

    public ItemStack getContainer() {
        return container != null ? container.copy() : null;
    }

    public int getCookTime() {
        return cookTime;
    }

    public float getExperience() {
        return experience;
    }
}
