package com.linvy.culinaryhorizons;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

import com.linvy.culinaryhorizons.item.FoodConsumableItem;
import com.linvy.culinaryhorizons.item.PopsicleItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    // Raw Crops
    public static Item CABBAGE;
    public static Item TOMATO;
    public static Item ONION;

    // Basic Foods
    public static Item FRIED_EGG;
    public static Item PIE_CRUST;
    public static Item PUMPKIN_SLICE;
    public static Item CABBAGE_LEAF;
    public static Item MINCED_BEEF;
    public static Item BEEF_PATTY;
    public static Item COOKED_CHICKEN_CUTS;
    public static Item BACON;
    public static Item COOKED_BACON;
    public static Item COD_SLICE;
    public static Item COOKED_COD_SLICE;
    public static Item SALMON_SLICE;
    public static Item COOKED_SALMON_SLICE;
    public static Item MUTTON_CHOPS;
    public static Item COOKED_MUTTON_CHOPS;
    public static Item HAM;
    public static Item SMOKED_HAM;
    public static Item WHEAT_DOUGH;
    public static Item RAW_PASTA;
    public static Item CHICKEN_CUTS;
    public static Item MIXED_SALAD;
    public static Item NETHER_SALAD;

    // Sweets
    public static Item SWEET_BERRY_COOKIE;
    public static Item HONEY_COOKIE;
    public static Item APPLE_PIE_SLICE;
    public static Item SWEET_BERRY_CHEESECAKE_SLICE;
    public static Item CHOCOLATE_PIE_SLICE;
    public static Item CAKE_SLICE;
    public static Item MELON_POPSICLE;
    public static Item FRUIT_SALAD;

    // Handheld Foods
    public static Item BARBECUE_STICK;
    public static Item EGG_SANDWICH;
    public static Item CHICKEN_SANDWICH;
    public static Item HAMBURGER;
    public static Item BACON_SANDWICH;
    public static Item MUTTON_WRAP;
    public static Item DUMPLINGS;
    public static Item STUFFED_POTATO;
    public static Item CABBAGE_ROLLS;
    public static Item SALMON_ROLL;
    public static Item COD_ROLL;
    public static Item KELP_ROLL;
    public static Item KELP_ROLL_SLICE;

    // Soups and Stews
    public static Item COOKED_RICE;
    public static Item BEEF_STEW;
    public static Item CHICKEN_SOUP;
    public static Item BONE_BROTH;
    public static Item VEGETABLE_SOUP;
    public static Item FISH_STEW;
    public static Item FRIED_RICE;
    public static Item PUMPKIN_SOUP;
    public static Item BAKED_COD_STEW;
    public static Item NOODLE_SOUP;

    // Plated Meals
    public static Item BACON_AND_EGGS;
    public static Item RATATOUILLE;
    public static Item STEAK_AND_POTATOES;
    public static Item PASTA_WITH_MEATBALLS;
    public static Item PASTA_WITH_MUTTON_CHOP;
    public static Item MUSHROOM_RICE;
    public static Item ROASTED_MUTTON_CHOPS;
    public static Item VEGETABLE_NOODLES;
    public static Item SQUID_INK_PASTA;
    public static Item GRILLED_SALMON;

    // Feast Portions
    public static Item ROAST_CHICKEN_PORTION;
    public static Item STUFFED_PUMPKIN_PORTION;
    public static Item HONEY_GLAZED_HAM_PORTION;
    public static Item SHEPHERDS_PIE_PORTION;

    public static void init() {

        // Raw Crops
        CABBAGE = new FoodConsumableItem(FoodValues.CABBAGE_NUTRITION, FoodValues.CABBAGE_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cabbage")
            .setTextureName("culinaryhorizons:cabbage");
        GameRegistry.registerItem(CABBAGE, "cabbage");

        TOMATO = new FoodConsumableItem(FoodValues.TOMATO_NUTRITION, FoodValues.TOMATO_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("tomato")
            .setTextureName("culinaryhorizons:tomato");
        GameRegistry.registerItem(TOMATO, "tomato");

        ONION = new FoodConsumableItem(FoodValues.ONION_NUTRITION, FoodValues.ONION_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("onion")
            .setTextureName("culinaryhorizons:onion");
        GameRegistry.registerItem(ONION, "onion");

        // Basic Foods
        FRIED_EGG = new FoodConsumableItem(FoodValues.FRIED_EGG_NUTRITION, FoodValues.FRIED_EGG_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("fried_egg")
            .setTextureName("culinaryhorizons:fried_egg");
        GameRegistry.registerItem(FRIED_EGG, "fried_egg");

        PIE_CRUST = new FoodConsumableItem(FoodValues.PIE_CRUST_NUTRITION, FoodValues.PIE_CRUST_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("pie_crust")
            .setTextureName("culinaryhorizons:pie_crust");
        GameRegistry.registerItem(PIE_CRUST, "pie_crust");

        PUMPKIN_SLICE = new FoodConsumableItem(FoodValues.PUMPKIN_SLICE_NUTRITION, FoodValues.PUMPKIN_SLICE_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("pumpkin_slice")
            .setTextureName("culinaryhorizons:pumpkin_slice");
        GameRegistry.registerItem(PUMPKIN_SLICE, "pumpkin_slice");

        CABBAGE_LEAF = new FoodConsumableItem(FoodValues.CABBAGE_LEAF_NUTRITION, FoodValues.CABBAGE_LEAF_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cabbage_leaf")
            .setTextureName("culinaryhorizons:cabbage_leaf");
        GameRegistry.registerItem(CABBAGE_LEAF, "cabbage_leaf");

        MINCED_BEEF = new FoodConsumableItem(FoodValues.MINCED_BEEF_NUTRITION, FoodValues.MINCED_BEEF_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("minced_beef")
            .setTextureName("culinaryhorizons:minced_beef");
        GameRegistry.registerItem(MINCED_BEEF, "minced_beef");

        BEEF_PATTY = new FoodConsumableItem(FoodValues.BEEF_PATTY_NUTRITION, FoodValues.BEEF_PATTY_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("beef_patty")
            .setTextureName("culinaryhorizons:beef_patty");
        GameRegistry.registerItem(BEEF_PATTY, "beef_patty");

        COOKED_CHICKEN_CUTS = new FoodConsumableItem(
            FoodValues.COOKED_CHICKEN_CUTS_NUTRITION,
            FoodValues.COOKED_CHICKEN_CUTS_SATURATION).setUseDuration(FoodValues.FAST_EAT)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("cooked_chicken_cuts")
                .setTextureName("culinaryhorizons:cooked_chicken_cuts");
        GameRegistry.registerItem(COOKED_CHICKEN_CUTS, "cooked_chicken_cuts");

        BACON = new FoodConsumableItem(FoodValues.BACON_NUTRITION, FoodValues.BACON_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("bacon")
            .setTextureName("culinaryhorizons:bacon");
        GameRegistry.registerItem(BACON, "bacon");

        COOKED_BACON = new FoodConsumableItem(FoodValues.COOKED_BACON_NUTRITION, FoodValues.COOKED_BACON_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cooked_bacon")
            .setTextureName("culinaryhorizons:cooked_bacon");
        GameRegistry.registerItem(COOKED_BACON, "cooked_bacon");

        COD_SLICE = new FoodConsumableItem(FoodValues.COD_SLICE_NUTRITION, FoodValues.COD_SLICE_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cod_slice")
            .setTextureName("culinaryhorizons:cod_slice");
        GameRegistry.registerItem(COD_SLICE, "cod_slice");

        COOKED_COD_SLICE = new FoodConsumableItem(
            FoodValues.COOKED_COD_SLICE_NUTRITION,
            FoodValues.COOKED_COD_SLICE_SATURATION).setUseDuration(FoodValues.FAST_EAT)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("cooked_cod_slice")
                .setTextureName("culinaryhorizons:cooked_cod_slice");
        GameRegistry.registerItem(COOKED_COD_SLICE, "cooked_cod_slice");

        SALMON_SLICE = new FoodConsumableItem(FoodValues.SALMON_SLICE_NUTRITION, FoodValues.SALMON_SLICE_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("salmon_slice")
            .setTextureName("culinaryhorizons:salmon_slice");
        GameRegistry.registerItem(SALMON_SLICE, "salmon_slice");

        COOKED_SALMON_SLICE = new FoodConsumableItem(
            FoodValues.COOKED_SALMON_SLICE_NUTRITION,
            FoodValues.COOKED_SALMON_SLICE_SATURATION).setUseDuration(FoodValues.FAST_EAT)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("cooked_salmon_slice")
                .setTextureName("culinaryhorizons:cooked_salmon_slice");
        GameRegistry.registerItem(COOKED_SALMON_SLICE, "cooked_salmon_slice");

        MUTTON_CHOPS = new FoodConsumableItem(FoodValues.MUTTON_CHOP_NUTRITION, FoodValues.MUTTON_CHOP_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("mutton_chops")
            .setTextureName("culinaryhorizons:mutton_chops");
        GameRegistry.registerItem(MUTTON_CHOPS, "mutton_chops");

        COOKED_MUTTON_CHOPS = new FoodConsumableItem(
            FoodValues.COOKED_MUTTON_CHOP_NUTRITION,
            FoodValues.COOKED_MUTTON_CHOP_SATURATION).setUseDuration(FoodValues.FAST_EAT)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("cooked_mutton_chops")
                .setTextureName("culinaryhorizons:cooked_mutton_chops");
        GameRegistry.registerItem(COOKED_MUTTON_CHOPS, "cooked_mutton_chops");

        HAM = new FoodConsumableItem(FoodValues.HAM_NUTRITION, FoodValues.HAM_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("ham")
            .setTextureName("culinaryhorizons:ham");
        GameRegistry.registerItem(HAM, "ham");

        SMOKED_HAM = new FoodConsumableItem(FoodValues.SMOKED_HAM_NUTRITION, FoodValues.SMOKED_HAM_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("smoked_ham")
            .setTextureName("culinaryhorizons:smoked_ham");
        GameRegistry.registerItem(SMOKED_HAM, "smoked_ham");

        WHEAT_DOUGH = new FoodConsumableItem(FoodValues.WHEAT_DOUGH_NUTRITION, FoodValues.WHEAT_DOUGH_SATURATION)
            .addEffect(Potion.hunger, 600, 0, 0.3F)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("wheat_dough")
            .setTextureName("culinaryhorizons:wheat_dough");
        GameRegistry.registerItem(WHEAT_DOUGH, "wheat_dough");

        RAW_PASTA = new FoodConsumableItem(FoodValues.RAW_PASTA_NUTRITION, FoodValues.RAW_PASTA_SATURATION)
            .addEffect(Potion.hunger, 600, 0, 0.3F)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("raw_pasta")
            .setTextureName("culinaryhorizons:raw_pasta");
        GameRegistry.registerItem(RAW_PASTA, "raw_pasta");

        CHICKEN_CUTS = new FoodConsumableItem(FoodValues.CHICKEN_CUTS_NUTRITION, FoodValues.CHICKEN_CUTS_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .addEffect(Potion.hunger, 600, 0, 0.3F)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("chicken_cuts")
            .setTextureName("culinaryhorizons:chicken_cuts");
        GameRegistry.registerItem(CHICKEN_CUTS, "chicken_cuts");

        MIXED_SALAD = new FoodConsumableItem(FoodValues.MIXED_SALAD_NUTRITION, FoodValues.MIXED_SALAD_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(Potion.regeneration, 100, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("mixed_salad")
            .setTextureName("culinaryhorizons:mixed_salad");
        GameRegistry.registerItem(MIXED_SALAD, "mixed_salad");

        NETHER_SALAD = new FoodConsumableItem(FoodValues.NETHER_SALAD_NUTRITION, FoodValues.NETHER_SALAD_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(Potion.confusion, 240, 0, 0.3F)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("nether_salad")
            .setTextureName("culinaryhorizons:nether_salad");
        GameRegistry.registerItem(NETHER_SALAD, "nether_salad");

        // Sweets
        SWEET_BERRY_COOKIE = new FoodConsumableItem(FoodValues.COOKIES_NUTRITION, FoodValues.COOKIES_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("sweet_berry_cookie")
            .setTextureName("culinaryhorizons:sweet_berry_cookie");
        GameRegistry.registerItem(SWEET_BERRY_COOKIE, "sweet_berry_cookie");

        HONEY_COOKIE = new FoodConsumableItem(FoodValues.COOKIES_NUTRITION, FoodValues.COOKIES_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("honey_cookie")
            .setTextureName("culinaryhorizons:honey_cookie");
        GameRegistry.registerItem(HONEY_COOKIE, "honey_cookie");

        APPLE_PIE_SLICE = new FoodConsumableItem(FoodValues.PIE_SLICE_NUTRITION, FoodValues.PIE_SLICE_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("apple_pie_slice")
            .setTextureName("culinaryhorizons:apple_pie_slice");
        GameRegistry.registerItem(APPLE_PIE_SLICE, "apple_pie_slice");

        SWEET_BERRY_CHEESECAKE_SLICE = new FoodConsumableItem(
            FoodValues.PIE_SLICE_NUTRITION,
            FoodValues.PIE_SLICE_SATURATION).setUseDuration(FoodValues.FAST_EAT)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("sweet_berry_cheesecake_slice")
                .setTextureName("culinaryhorizons:sweet_berry_cheesecake_slice");
        GameRegistry.registerItem(SWEET_BERRY_CHEESECAKE_SLICE, "sweet_berry_cheesecake_slice");

        CHOCOLATE_PIE_SLICE = new FoodConsumableItem(FoodValues.PIE_SLICE_NUTRITION, FoodValues.PIE_SLICE_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .addEffect(Potion.moveSpeed, 600, 0)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("chocolate_pie_slice")
            .setTextureName("culinaryhorizons:chocolate_pie_slice");
        GameRegistry.registerItem(CHOCOLATE_PIE_SLICE, "chocolate_pie_slice");

        CAKE_SLICE = new FoodConsumableItem(FoodValues.CAKE_SLICE_NUTRITION, FoodValues.CAKE_SLICE_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .addEffect(Potion.moveSpeed, 400, 0)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cake_slice")
            .setTextureName("culinaryhorizons:cake_slice");
        GameRegistry.registerItem(CAKE_SLICE, "cake_slice");

        MELON_POPSICLE = new PopsicleItem(FoodValues.POPSICLE_NUTRITION, FoodValues.POPSICLE_SATURATION)
            .setUseDuration(FoodValues.FAST_EAT)
            .setAlwaysEdible(true)
            .addEffect(Potion.regeneration, 100, 0)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("melon_popsicle")
            .setTextureName("culinaryhorizons:melon_popsicle");
        GameRegistry.registerItem(MELON_POPSICLE, "melon_popsicle");

        FRUIT_SALAD = new FoodConsumableItem(FoodValues.FRUIT_SALAD_NUTRITION, FoodValues.FRUIT_SALAD_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(Potion.regeneration, 100, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("fruit_salad")
            .setTextureName("culinaryhorizons:fruit_salad");
        GameRegistry.registerItem(FRUIT_SALAD, "fruit_salad");

        // Handheld Foods
        BARBECUE_STICK = new FoodConsumableItem(
            FoodValues.BARBECUE_STICK_NUTRITION,
            FoodValues.BARBECUE_STICK_SATURATION).setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("barbecue_stick")
                .setTextureName("culinaryhorizons:barbecue_stick");
        GameRegistry.registerItem(BARBECUE_STICK, "barbecue_stick");

        EGG_SANDWICH = new FoodConsumableItem(FoodValues.EGG_SANDWICH_NUTRITION, FoodValues.EGG_SANDWICH_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("egg_sandwich")
            .setTextureName("culinaryhorizons:egg_sandwich");
        GameRegistry.registerItem(EGG_SANDWICH, "egg_sandwich");

        CHICKEN_SANDWICH = new FoodConsumableItem(
            FoodValues.CHICKEN_SANDWICH_NUTRITION,
            FoodValues.CHICKEN_SANDWICH_SATURATION).setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("chicken_sandwich")
                .setTextureName("culinaryhorizons:chicken_sandwich");
        GameRegistry.registerItem(CHICKEN_SANDWICH, "chicken_sandwich");

        HAMBURGER = new FoodConsumableItem(FoodValues.HAMBURGER_NUTRITION, FoodValues.HAMBURGER_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("hamburger")
            .setTextureName("culinaryhorizons:hamburger");
        GameRegistry.registerItem(HAMBURGER, "hamburger");

        BACON_SANDWICH = new FoodConsumableItem(
            FoodValues.BACON_SANDWICH_NUTRITION,
            FoodValues.BACON_SANDWICH_SATURATION).setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("bacon_sandwich")
                .setTextureName("culinaryhorizons:bacon_sandwich");
        GameRegistry.registerItem(BACON_SANDWICH, "bacon_sandwich");

        MUTTON_WRAP = new FoodConsumableItem(FoodValues.MUTTON_WRAP_NUTRITION, FoodValues.MUTTON_WRAP_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("mutton_wrap")
            .setTextureName("culinaryhorizons:mutton_wrap");
        GameRegistry.registerItem(MUTTON_WRAP, "mutton_wrap");

        DUMPLINGS = new FoodConsumableItem(FoodValues.DUMPLINGS_NUTRITION, FoodValues.DUMPLINGS_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("dumplings")
            .setTextureName("culinaryhorizons:dumplings");
        GameRegistry.registerItem(DUMPLINGS, "dumplings");

        STUFFED_POTATO = new FoodConsumableItem(
            FoodValues.STUFFED_POTATO_NUTRITION,
            FoodValues.STUFFED_POTATO_SATURATION).setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("stuffed_potato")
                .setTextureName("culinaryhorizons:stuffed_potato");
        GameRegistry.registerItem(STUFFED_POTATO, "stuffed_potato");

        CABBAGE_ROLLS = new FoodConsumableItem(FoodValues.CABBAGE_ROLLS_NUTRITION, FoodValues.CABBAGE_ROLLS_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cabbage_rolls")
            .setTextureName("culinaryhorizons:cabbage_rolls");
        GameRegistry.registerItem(CABBAGE_ROLLS, "cabbage_rolls");

        SALMON_ROLL = new FoodConsumableItem(FoodValues.SALMON_ROLL_NUTRITION, FoodValues.SALMON_ROLL_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("salmon_roll")
            .setTextureName("culinaryhorizons:salmon_roll");
        GameRegistry.registerItem(SALMON_ROLL, "salmon_roll");

        COD_ROLL = new FoodConsumableItem(FoodValues.COD_ROLL_NUTRITION, FoodValues.COD_ROLL_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cod_roll")
            .setTextureName("culinaryhorizons:cod_roll");
        GameRegistry.registerItem(COD_ROLL, "cod_roll");

        KELP_ROLL = new FoodConsumableItem(FoodValues.KELP_ROLL_NUTRITION, FoodValues.KELP_ROLL_SATURATION)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("kelp_roll")
            .setTextureName("culinaryhorizons:kelp_roll");
        GameRegistry.registerItem(KELP_ROLL, "kelp_roll");

        KELP_ROLL_SLICE = new FoodConsumableItem(
            FoodValues.KELP_ROLL_SLICE_NUTRITION,
            FoodValues.KELP_ROLL_SLICE_SATURATION).setUseDuration(FoodValues.FAST_EAT)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("kelp_roll_slice")
                .setTextureName("culinaryhorizons:kelp_roll_slice");
        GameRegistry.registerItem(KELP_ROLL_SLICE, "kelp_roll_slice");

        // Soups and Stews
        COOKED_RICE = new FoodConsumableItem(FoodValues.COOKED_RICE_NUTRITION, FoodValues.COOKED_RICE_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.BRIEF_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("cooked_rice")
            .setTextureName("culinaryhorizons:cooked_rice");
        GameRegistry.registerItem(COOKED_RICE, "cooked_rice");

        BEEF_STEW = new FoodConsumableItem(FoodValues.BEEF_STEW_NUTRITION, FoodValues.BEEF_STEW_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("beef_stew")
            .setTextureName("culinaryhorizons:beef_stew");
        GameRegistry.registerItem(BEEF_STEW, "beef_stew");

        CHICKEN_SOUP = new FoodConsumableItem(FoodValues.CHICKEN_SOUP_NUTRITION, FoodValues.CHICKEN_SOUP_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("chicken_soup")
            .setTextureName("culinaryhorizons:chicken_soup");
        GameRegistry.registerItem(CHICKEN_SOUP, "chicken_soup");

        BONE_BROTH = new FoodConsumableItem(FoodValues.BONE_BROTH_NUTRITION, FoodValues.BONE_BROTH_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.SHORT_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("bone_broth")
            .setTextureName("culinaryhorizons:bone_broth");
        GameRegistry.registerItem(BONE_BROTH, "bone_broth");

        VEGETABLE_SOUP = new FoodConsumableItem(
            FoodValues.VEGETABLE_SOUP_NUTRITION,
            FoodValues.VEGETABLE_SOUP_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("vegetable_soup")
                .setTextureName("culinaryhorizons:vegetable_soup");
        GameRegistry.registerItem(VEGETABLE_SOUP, "vegetable_soup");

        FISH_STEW = new FoodConsumableItem(FoodValues.FISH_STEW_NUTRITION, FoodValues.FISH_STEW_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.MEDIUM_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("fish_stew")
            .setTextureName("culinaryhorizons:fish_stew");
        GameRegistry.registerItem(FISH_STEW, "fish_stew");

        FRIED_RICE = new FoodConsumableItem(FoodValues.FRIED_RICE_NUTRITION, FoodValues.FRIED_RICE_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("fried_rice")
            .setTextureName("culinaryhorizons:fried_rice");
        GameRegistry.registerItem(FRIED_RICE, "fried_rice");

        PUMPKIN_SOUP = new FoodConsumableItem(FoodValues.PUMPKIN_SOUP_NUTRITION, FoodValues.PUMPKIN_SOUP_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("pumpkin_soup")
            .setTextureName("culinaryhorizons:pumpkin_soup");
        GameRegistry.registerItem(PUMPKIN_SOUP, "pumpkin_soup");

        BAKED_COD_STEW = new FoodConsumableItem(
            FoodValues.BAKED_COD_STEW_NUTRITION,
            FoodValues.BAKED_COD_STEW_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("baked_cod_stew")
                .setTextureName("culinaryhorizons:baked_cod_stew");
        GameRegistry.registerItem(BAKED_COD_STEW, "baked_cod_stew");

        NOODLE_SOUP = new FoodConsumableItem(FoodValues.NOODLE_SOUP_NUTRITION, FoodValues.NOODLE_SOUP_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.COMFORT, FoodValues.LONG_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("noodle_soup")
            .setTextureName("culinaryhorizons:noodle_soup");
        GameRegistry.registerItem(NOODLE_SOUP, "noodle_soup");

        // Plated Meals
        BACON_AND_EGGS = new FoodConsumableItem(
            FoodValues.BACON_AND_EGGS_NUTRITION,
            FoodValues.BACON_AND_EGGS_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.SHORT_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("bacon_and_eggs")
                .setTextureName("culinaryhorizons:bacon_and_eggs");
        GameRegistry.registerItem(BACON_AND_EGGS, "bacon_and_eggs");

        RATATOUILLE = new FoodConsumableItem(FoodValues.RATATOUILLE_NUTRITION, FoodValues.RATATOUILLE_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.NOURISHMENT, FoodValues.SHORT_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("ratatouille")
            .setTextureName("culinaryhorizons:ratatouille");
        GameRegistry.registerItem(RATATOUILLE, "ratatouille");

        STEAK_AND_POTATOES = new FoodConsumableItem(
            FoodValues.STEAK_AND_POTATOES_NUTRITION,
            FoodValues.STEAK_AND_POTATOES_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("steak_and_potatoes")
                .setTextureName("culinaryhorizons:steak_and_potatoes");
        GameRegistry.registerItem(STEAK_AND_POTATOES, "steak_and_potatoes");

        PASTA_WITH_MEATBALLS = new FoodConsumableItem(
            FoodValues.PASTA_WITH_MEATBALLS_NUTRITION,
            FoodValues.PASTA_WITH_MEATBALLS_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("pasta_with_meatballs")
                .setTextureName("culinaryhorizons:pasta_with_meatballs");
        GameRegistry.registerItem(PASTA_WITH_MEATBALLS, "pasta_with_meatballs");

        PASTA_WITH_MUTTON_CHOP = new FoodConsumableItem(
            FoodValues.PASTA_WITH_MUTTON_CHOP_NUTRITION,
            FoodValues.PASTA_WITH_MUTTON_CHOP_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("pasta_with_mutton_chop")
                .setTextureName("culinaryhorizons:pasta_with_mutton_chop");
        GameRegistry.registerItem(PASTA_WITH_MUTTON_CHOP, "pasta_with_mutton_chop");

        MUSHROOM_RICE = new FoodConsumableItem(FoodValues.MUSHROOM_RICE_NUTRITION, FoodValues.MUSHROOM_RICE_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(16)
            .addEffect(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0)
            .setHasFoodEffectTooltip(true)
            .setCreativeTab(ModCreativeTab.INSTANCE)
            .setUnlocalizedName("mushroom_rice")
            .setTextureName("culinaryhorizons:mushroom_rice");
        GameRegistry.registerItem(MUSHROOM_RICE, "mushroom_rice");

        ROASTED_MUTTON_CHOPS = new FoodConsumableItem(
            FoodValues.ROASTED_MUTTON_CHOPS_NUTRITION,
            FoodValues.ROASTED_MUTTON_CHOPS_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("roasted_mutton_chops")
                .setTextureName("culinaryhorizons:roasted_mutton_chops");
        GameRegistry.registerItem(ROASTED_MUTTON_CHOPS, "roasted_mutton_chops");

        VEGETABLE_NOODLES = new FoodConsumableItem(
            FoodValues.VEGETABLE_NOODLES_NUTRITION,
            FoodValues.VEGETABLE_NOODLES_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("vegetable_noodles")
                .setTextureName("culinaryhorizons:vegetable_noodles");
        GameRegistry.registerItem(VEGETABLE_NOODLES, "vegetable_noodles");

        SQUID_INK_PASTA = new FoodConsumableItem(
            FoodValues.SQUID_INK_PASTA_NUTRITION,
            FoodValues.SQUID_INK_PASTA_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("squid_ink_pasta")
                .setTextureName("culinaryhorizons:squid_ink_pasta");
        GameRegistry.registerItem(SQUID_INK_PASTA, "squid_ink_pasta");

        GRILLED_SALMON = new FoodConsumableItem(
            FoodValues.GRILLED_SALMON_NUTRITION,
            FoodValues.GRILLED_SALMON_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("grilled_salmon")
                .setTextureName("culinaryhorizons:grilled_salmon");
        GameRegistry.registerItem(GRILLED_SALMON, "grilled_salmon");

        // Feast Portions
        ROAST_CHICKEN_PORTION = new FoodConsumableItem(
            FoodValues.ROAST_CHICKEN_PORTION_NUTRITION,
            FoodValues.ROAST_CHICKEN_PORTION_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("roast_chicken_portion")
                .setTextureName("culinaryhorizons:roast_chicken_portion");
        GameRegistry.registerItem(ROAST_CHICKEN_PORTION, "roast_chicken_portion");

        STUFFED_PUMPKIN_PORTION = new FoodConsumableItem(
            FoodValues.STUFFED_PUMPKIN_PORTION_NUTRITION,
            FoodValues.STUFFED_PUMPKIN_PORTION_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("stuffed_pumpkin_portion")
                .setTextureName("culinaryhorizons:stuffed_pumpkin_portion");
        GameRegistry.registerItem(STUFFED_PUMPKIN_PORTION, "stuffed_pumpkin_portion");

        HONEY_GLAZED_HAM_PORTION = new FoodConsumableItem(
            FoodValues.HONEY_GLAZED_HAM_PORTION_NUTRITION,
            FoodValues.HONEY_GLAZED_HAM_PORTION_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.LONG_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("honey_glazed_ham_portion")
                .setTextureName("culinaryhorizons:honey_glazed_ham_portion");
        GameRegistry.registerItem(HONEY_GLAZED_HAM_PORTION, "honey_glazed_ham_portion");

        SHEPHERDS_PIE_PORTION = new FoodConsumableItem(
            FoodValues.SHEPHERDS_PIE_PORTION_NUTRITION,
            FoodValues.SHEPHERDS_PIE_PORTION_SATURATION).setContainerItem(Items.bowl)
                .setMaxStackSize(16)
                .addEffect(ModEffects.NOURISHMENT, FoodValues.MEDIUM_DURATION, 0)
                .setHasFoodEffectTooltip(true)
                .setCreativeTab(ModCreativeTab.INSTANCE)
                .setUnlocalizedName("shepherds_pie_portion")
                .setTextureName("culinaryhorizons:shepherds_pie_portion");
        GameRegistry.registerItem(SHEPHERDS_PIE_PORTION, "shepherds_pie_portion");
    }
}
