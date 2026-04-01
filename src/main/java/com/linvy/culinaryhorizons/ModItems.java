package com.linvy.culinaryhorizons;

import com.linvy.culinaryhorizons.item.FoodItemBase;
import com.linvy.culinaryhorizons.item.FoodItemBuilder;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;

// Credit to Et Futurum (Requiem)
public enum ModItems {

    //Raw Crops
    CABBAGE(
        FoodItemBuilder.create("cabbage", FoodValues.CABBAGE_NUTRITION, FoodValues.CABBAGE_SATURATION)
            .build(),
        "cabbage"
    ),
    TOMATO(
        FoodItemBuilder.create("tomato", FoodValues.TOMATO_NUTRITION, FoodValues.TOMATO_SATURATION)
            .build(),
        "tomato"
    ),
    ONION(
        FoodItemBuilder.create("onion", FoodValues.ONION_NUTRITION, FoodValues.ONION_SATURATION)
            .build(),
        "onion"
    ),

    // Basic Foods
    FRIED_EGG(
        FoodItemBuilder.create("fried_egg", FoodValues.FRIED_EGG_NUTRITION, FoodValues.FRIED_EGG_SATURATION)
            .build(),
        "fried_egg"
    ),
    PIE_CRUST(
        FoodItemBuilder.create("pie_crust", FoodValues.PIE_CRUST_NUTRITION, FoodValues.PIE_CRUST_SATURATION)
            .build(),
        "pie_crust"
    ),
    PUMPKIN_SLICE(
        FoodItemBuilder.create("pumpkin_slice", FoodValues.PUMPKIN_SLICE_NUTRITION, FoodValues.PUMPKIN_SLICE_SATURATION)
            .build(),
        "pumpkin_slice"
    ),
    CABBAGE_LEAF(
        FoodItemBuilder.create("cabbage_leaf", FoodValues.CABBAGE_LEAF_NUTRITION, FoodValues.CABBAGE_LEAF_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cabbage_leaf"
    ),
    MINCED_BEEF(
        FoodItemBuilder.create("minced_beef", FoodValues.MINCED_BEEF_NUTRITION, FoodValues.MINCED_BEEF_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "minced_beef"
    ),
    BEEF_PATTY(
        FoodItemBuilder.create("beef_patty", FoodValues.BEEF_PATTY_NUTRITION, FoodValues.BEEF_PATTY_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "beef_patty"
    ),

    //Handheld Foods
    SALMON_ROLL(
        FoodItemBuilder.create("salmon_roll", FoodValues.SALMON_ROLL_NUTRITION, FoodValues.SALMON_ROLL_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "salmon_roll"
    ),
    COD_ROLL(
        FoodItemBuilder.create("cod_roll", FoodValues.COD_ROLL_NUTRITION, FoodValues.COD_ROLL_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cod_roll"
    ),
    KELP_ROLL(
        FoodItemBuilder.create("kelp_roll", FoodValues.KELP_ROLL_NUTRITION, FoodValues.KELP_ROLL_SATURATION)
            .build(),
        "kelp_roll"
    ),
    KELP_ROLL_SLICE(
        FoodItemBuilder.create("kelp_roll_slice", FoodValues.KELP_ROLL_SLICE_NUTRITION, FoodValues.KELP_ROLL_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "kelp_roll_slice"
    ),

    // Soups and Stews
    COOKED_RICE(
        FoodItemBuilder.create("cooked_rice", FoodValues.COOKED_RICE_NUTRITION, FoodValues.COOKED_RICE_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.BRIEF_DURATION, 0)
            .build(),
        "cooked_rice"
    ),
    BEEF_STEW(
        FoodItemBuilder.create("beef_stew", FoodValues.BEEF_STEW_NUTRITION, FoodValues.BEEF_STEW_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .build(),
        "beef_stew"
    ),

    // Feast Portions
    ROAST_CHICKEN_PORTION(
        FoodItemBuilder.create("roast_chicken_portion", FoodValues.ROAST_CHICKEN_PORTION_NUTRITION, FoodValues.ROAST_CHICKEN_PORTION_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .build(),
        "roast_chicken_portion"
    ),
    STUFFED_PUMPKIN_PORTION(
        FoodItemBuilder.create("stuffed_pumpkin_portion", FoodValues.STUFFED_PUMPKIN_PORTION_NUTRITION, FoodValues.STUFFED_PUMPKIN_PORTION_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .build(),
        "stuffed_pumpkin_portion"
    ),
    HONEY_GLAZED_HAM_PORTION(
        FoodItemBuilder.create("honey_glazed_ham_portion", FoodValues.HONEY_GLAZED_HAM_PORTION_NUTRITION, FoodValues.HONEY_GLAZED_HAM_PORTION_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .build(),
        "honey_glazed_ham_portion"
    ),
    SHEPHERDS_PIE_PORTION(
        FoodItemBuilder.create("shepherds_pie_portion", FoodValues.SHEPHERDS_PIE_PORTION_NUTRITION, FoodValues.SHEPHERDS_PIE_PORTION_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .build(),
        "shepherds_pie_portion"
    ),
    ;

    public static final ModItems[] VALUES = values();

    private final Item theItem;
    private final String name;

    public static void init() {
        for (ModItems item : VALUES) {
            item.theItem.setCreativeTab(ModCreativeTab.INSTANCE);
            item.theItem.setUnlocalizedName(item.name);
            item.theItem.setTextureName(CulinaryHorizons.MODID + ":" + item.name);
            GameRegistry.registerItem(item.get(), item.name);
        }
    }

    ModItems(Item item, String name) {
        this.theItem = item;
        this.name = name;
    }

    public Item get() {
        return theItem;
    }

    public ItemStack newItemStack() {
        return newItemStack(1);
    }

    public ItemStack newItemStack(int count) {
        return newItemStack(count, 0);
    }

    public ItemStack newItemStack(int count, int meta) {
        return new ItemStack(this.get(), count, meta);
    }
}
