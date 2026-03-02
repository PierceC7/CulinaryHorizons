package com.linvy.culinaryhorizons.recipe;

import com.linvy.culinaryhorizons.ModItems;
import com.linvy.culinaryhorizons.item.ConsumableItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CookingPotRecipeRegistry {

    private static final List<CookingPotRecipe> RECIPES = new ArrayList<>();

    public static void registerRecipe(CookingPotRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static void registerRecipe(List<ItemStack> ingredients, ItemStack result, ItemStack container, int cookTime, float experience) {
        RECIPES.add(new CookingPotRecipe(result, ingredients, container, cookTime, experience));
    }

    public static CookingPotRecipe findRecipe(ItemStack[] ingredients) {
        for (CookingPotRecipe recipe : RECIPES) {
            if (recipe.matches(ingredients)) {
                return recipe;
            }
        }
        return null;
    }

    public static List<CookingPotRecipe> getAllRecipes() {
        return new ArrayList<>(RECIPES);
    }

    public static void init() {
        CookingPotRecipeRegistry.registerRecipe(
            CookingPotRecipeBuilder.create(Items.mushroom_stew, 1)
                .addIngredient(Item.getItemFromBlock(Blocks.brown_mushroom))
                .addIngredient(Item.getItemFromBlock(Blocks.red_mushroom))
                .setContainer(Items.bowl)
                .setCookTime(CookingPotRecipe.NORMAL_COOKING)
                .setExperience(CookingPotRecipe.MEDIUM_EXP)
                .build()
        );

        CookingPotRecipeRegistry.registerRecipe(
            CookingPotRecipeBuilder.create(ModItems.BEEF_STEW, 1) // Placeholder
                .addIngredient(Items.beef)
                .addIngredient(Items.carrot)
                .addIngredient(Items.potato)
                .setContainer(Items.bowl)
                .setCookTime(CookingPotRecipe.NORMAL_COOKING)
                .setExperience(CookingPotRecipe.MEDIUM_EXP)
                .build()
        );



        // TODO: Add actual recipes here
    }
}
