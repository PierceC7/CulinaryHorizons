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
    CHICKEN_CUTS(
        FoodItemBuilder.create("chicken_cuts", FoodValues.CHICKEN_CUTS_NUTRITION, FoodValues.CHICKEN_CUTS_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .addEffect(Potion.hunger, 600, 0, 0.3F)
            .build(),
        "chicken_cuts"
    ),
    COOKED_CHICKEN_CUTS(
        FoodItemBuilder.create("cooked_chicken_cuts", FoodValues.COOKED_CHICKEN_CUTS_NUTRITION, FoodValues.COOKED_CHICKEN_CUTS_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cooked_chicken_cuts"
    ),
    BACON(
        FoodItemBuilder.create("bacon", FoodValues.BACON_NUTRITION, FoodValues.BACON_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "bacon"
    ),
    COOKED_BACON(
        FoodItemBuilder.create("cooked_bacon", FoodValues.COOKED_BACON_NUTRITION, FoodValues.COOKED_BACON_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cooked_bacon"
    ),
    COD_SLICE(
        FoodItemBuilder.create("cod_slice", FoodValues.COD_SLICE_NUTRITION, FoodValues.COD_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cod_slice"
    ),
    COOKED_COD_SLICE(
        FoodItemBuilder.create("cooked_cod_slice", FoodValues.COOKED_COD_SLICE_NUTRITION, FoodValues.COOKED_COD_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cooked_cod_slice"
    ),
    SALMON_SLICE(
        FoodItemBuilder.create("salmon_slice", FoodValues.SALMON_SLICE_NUTRITION, FoodValues.SALMON_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "salmon_slice"
    ),
    COOKED_SALMON_SLICE(
        FoodItemBuilder.create("cooked_salmon_slice", FoodValues.COOKED_SALMON_SLICE_NUTRITION, FoodValues.COOKED_SALMON_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cooked_salmon_slice"
    ),
    MUTTON_CHOPS(
        FoodItemBuilder.create("mutton_chops", FoodValues.MUTTON_CHOP_NUTRITION, FoodValues.MUTTON_CHOP_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "mutton_chops"
    ),
    COOKED_MUTTON_CHOPS(
        FoodItemBuilder.create("cooked_mutton_chops", FoodValues.COOKED_MUTTON_CHOP_NUTRITION, FoodValues.COOKED_MUTTON_CHOP_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "cooked_mutton_chops"
    ),
    HAM(
        FoodItemBuilder.create("ham", FoodValues.HAM_NUTRITION, FoodValues.HAM_SATURATION)
            .build(),
        "ham"
    ),
    SMOKED_HAM(
        FoodItemBuilder.create("smoked_ham", FoodValues.SMOKED_HAM_NUTRITION, FoodValues.SMOKED_HAM_SATURATION)
            .build(),
        "smoked_ham"
    ),
    WHEAT_DOUGH(
        FoodItemBuilder.create("wheat_dough", FoodValues.WHEAT_DOUGH_NUTRITION, FoodValues.WHEAT_DOUGH_SATURATION)
            .addEffect(Potion.hunger, 600, 0, 0.3F)
            .build(),
        "wheat_dough"
    ),
    RAW_PASTA(
        FoodItemBuilder.create("raw_pasta", FoodValues.RAW_PASTA_NUTRITION, FoodValues.RAW_PASTA_SATURATION)
            .addEffect(Potion.hunger, 600, 0, 0.3F)
            .build(),
        "raw_pasta"
    ),
    MIXED_SALAD(
        FoodItemBuilder.create("mixed_salad", FoodValues.MIXED_SALAD_NUTRITION, FoodValues.MIXED_SALAD_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(Potion.regeneration, 100, 0)
            .setShowEffectTooltip(true)
            .build(),
        "mixed_salad"
    ),
    NETHER_SALAD(
        FoodItemBuilder.create("nether_salad", FoodValues.NETHER_SALAD_NUTRITION, FoodValues.NETHER_SALAD_SATURATION)
            .setContainerItem(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(Potion.confusion, 240, 0, 0.3F)
            .build(),
        "nether_salad"
    ),

    //Sweets
    SWEET_BERRY_COOKIE(
        FoodItemBuilder.create("sweet_berry_cookie", FoodValues.COOKIES_NUTRITION, FoodValues.COOKIES_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "sweet_berry_cookie"
    ),
    HONEY_COOKIE(
        FoodItemBuilder.create("honey_cookie", FoodValues.COOKIES_NUTRITION, FoodValues.COOKIES_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "honey_cookie"
    ),
    APPLE_PIE_SLICE(
        FoodItemBuilder.create("apple_pie_slice", FoodValues.PIE_SLICE_NUTRITION, FoodValues.PIE_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "apple_pie_slice"
    ),
    SWEET_BERRY_CHEESECAKE_SLICE(
        FoodItemBuilder.create("sweet_berry_cheesecake_slice", FoodValues.PIE_SLICE_NUTRITION, FoodValues.PIE_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .build(),
        "sweet_berry_cheesecake_slice"
    ),
    CHOCOLATE_PIE_SLICE(
        FoodItemBuilder.create("chocolate_pie_slice", FoodValues.PIE_SLICE_NUTRITION, FoodValues.PIE_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .addEffect(Potion.moveSpeed, 600, 0)
            .build(),
        "chocolate_pie_slice"
    ),
    CAKE_SLICE(
        FoodItemBuilder.create("cake_slice", FoodValues.CAKE_SLICE_NUTRITION, FoodValues.CAKE_SLICE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .addEffect(Potion.moveSpeed, 400, 0)
            .build(),
        "cake_slice"
    ),
    MELON_POPSICLE(
        FoodItemBuilder.create("melon_popsicle", FoodValues.POPSICLE_NUTRITION, FoodValues.POPSICLE_SATURATION)
            .setEatDuration(FoodValues.FAST_EAT)
            .alwaysEdible(true)
            .addEffect(Potion.regeneration, 100, 0)
            .onEaten(new IFoodEatenCallback() {
                @Override
                public void onEaten(ItemStack stack, World world, EntityPlayer player) {
                    if (player.isBurning()) {
                        player.extinguish();
                    }
                }
            })
            .build(),
        "melon_popsicle"
    ),
    FRUIT_SALAD(
        FoodItemBuilder.create("fruit_salad", FoodValues.FRUIT_SALAD_NUTRITION, FoodValues.FRUIT_SALAD_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(Potion.regeneration, 100, 0)
            .setShowEffectTooltip(true)
            .build(),
        "fruit_salad"
    ),

    //Handheld Foods
    BARBECUE_STICK(
        FoodItemBuilder.create("barbecue_stick", FoodValues.BARBECUE_STICK_NUTRITION, FoodValues.BARBECUE_STICK_SATURATION)
            .build(),
        "barbecue_stick"
    ),
    EGG_SANDWICH(
        FoodItemBuilder.create("egg_sandwich", FoodValues.EGG_SANDWICH_NUTRITION, FoodValues.EGG_SANDWICH_SATURATION)
            .build(),
    ),
    CHICKEN_SANDWICH(
        FoodItemBuilder.create("chicken_sandwich", FoodValues.CHICKEN_SANDWICH_NUTRITION, FoodValues.CHICKEN_SANDWICH_SATURATION)
            .build(),
        "chicken_sandwich"
    ),
    HAMBURGER(
        FoodItemBuilder.create("hamburger", FoodValues.HAMBURGER_NUTRITION, FoodValues.HAMBURGER_SATURATION)
            .build(),
        "hamburger"
    ),
    BACON_SANDWITCH(
        FoodItemBuilder.create("bacon_sandwich", FoodValues.BACON_SANDWICH_NUTRITION, FoodValues.BACON_SANDWICH_SATURATION)
            .build(),
        "bacon_sandwich"
    ),
    MUTTON_WRAP(
        FoodItemBuilder.create("mutton_wrap", FoodValues.MUTTON_WRAP_NUTRITION, FoodValues.MUTTON_WRAP_SATURATION)
            .build(),
        "mutton_wrap"
    ),
    DUMPLINGS(
        FoodItemBuilder.create("dumplings", FoodValues.DUMPLINGS_NUTRITION, FoodValues.DUMPLINGS_SATURATION)
            .build(),
        "dumplings"
    ),
    STUFFED_POTATO(
        FoodItemBuilder.create("stuffed_potato", FoodValues.STUFFED_POTATO_NUTRITION, FoodValues.STUFFED_POTATO_SATURATION)
            .build(),
        "stuffed_potato"
    ),
    CABBAGE_ROLLS(
        FoodItemBuilder.create("cabbage_rolls", FoodValues.CABBAGE_ROLLS_NUTRITION, FoodValues.CABBAGE_ROLLS_SATURATION)
            .build(),
        "cabbage_rolls"
    ),
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
            .setShowEffectTooltip(true)
            .build(),
        "cooked_rice"
    ),
    BEEF_STEW(
        FoodItemBuilder.create("beef_stew", FoodValues.BEEF_STEW_NUTRITION, FoodValues.BEEF_STEW_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "beef_stew"
    ),
    CHICKEN_SOUP(
        FoodItemBuilder.create("chicken_soup", FoodValues.CHICKEN_SOUP_NUTRITION, FoodValues.CHICKEN_SOUP_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "chicken_soup"
    ),
    BONE_BROTH(
        FoodItemBuilder.create("bone_broth", FoodValues.BONE_BROTH_NUTRITION, FoodValues.BONE_BROTH_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.SHORT_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "bone_broth"
    ),
    VEGETABLE_SOUP(
        FoodItemBuilder.create("vegetable_soup", FoodValues.VEGETABLE_SOUP_NUTRITION, FoodValues.VEGETABLE_SOUP_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "vegetable_soup"
    ),
    FISH_STEW(
        FoodItemBuilder.create("fish_stew", FoodValues.FISH_STEW_NUTRITION, FoodValues.FISH_STEW_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "fish_stew"
    ),
    FRIED_RICE(
        FoodItemBuilder.create("fried_rice", FoodValues.FRIED_RICE_NUTRITION, FoodValues.FRIED_RICE_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "fried_rice"
    ),
    PUMPKIN_SOUP(
        FoodItemBuilder.create("pumpkin_soup", FoodValues.PUMPKIN_SOUP_NUTRITION, FoodValues.PUMPKIN_SOUP_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "pumpkin_soup"
    ),
    BAKED_COD_STEW(
        FoodItemBuilder.create("baked_cod_stew", FoodValues.BAKED_COD_STEW_NUTRITION, FoodValues.BAKED_COD_STEW_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "baked_cod_stew"
    ),
    NOODLE_SOUP(
        FoodItemBuilder.create("noodle_soup", FoodValues.NOODLE_SOUP_NUTRITION, FoodValues.NOODLE_SOUP_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.COMFORT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "noodle_soup"
    ),

    // Plated Meals
    BACON_AND_EGGS(
        FoodItemBuilder.create("bacon_and_eggs", FoodValues.BACON_AND_EGGS_NUTRITION, FoodValues.BACON_AND_EGGS_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.SHORT_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "bacon_and_eggs"
    ),
    RATATOUILLE(
        FoodItemBuilder.create("ratatouille", FoodValues.RATATOUILLE_NUTRITION, FoodValues.RATATOUILLE_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.SHORT_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "ratatouille"
    ),
    STEAK_AND_POTATOES(
        FoodItemBuilder.create("steak_and_potatoes", FoodValues.STEAK_AND_POTATOES_NUTRITION, FoodValues.STEAK_AND_POTATOES_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "steak_and_potatoes"
    ),
    PASTA_WITH_MEATBALLS(
        FoodItemBuilder.create("pasta_with_meatballs", FoodValues.PASTA_WITH_MEATBALLS_NUTRITION, FoodValues.PASTA_WITH_MEATBALLS_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "pasta_with_meatballs"
    ),
    PASTA_WITH_MUTTON_CHOP(
        FoodItemBuilder.create("pasta_with_mutton_chop", FoodValues.PASTA_WITH_MUTTON_CHOP_NUTRITION, FoodValues.PASTA_WITH_MUTTON_CHOP_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "pasta_with_mutton_chop"
    ),
    MUSHROOM_RICE(
        FoodItemBuilder.create("mushroom_rice", FoodValues.MUSHROOM_RICE_NUTRITION, FoodValues.MUSHROOM_RICE_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "mushroom_rice"
    ),
    ROASTED_MUTTON_CHOPS(
        FoodItemBuilder.create("roasted_mutton_chops", FoodValues.ROASTED_MUTTON_CHOPS_NUTRITION, FoodValues.ROASTED_MUTTON_CHOPS_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "roasted_mutton_chops"
    ),
    VEGETABLE_NOODLES(
        FoodItemBuilder.create("vegetable_noodles", FoodValues.VEGETABLE_NOODLES_NUTRITION, FoodValues.VEGETABLE_NOODLES_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "vegetable_noodles"
    ),
    SQUID_INK_PASTA(
        FoodItemBuilder.create("squid_ink_pasta", FoodValues.SQUID_INK_PASTA_NUTRITION, FoodValues.SQUID_INK_PASTA_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.LONG_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "squid_ink_pasta"
    ),
    GRILLED_SALMON(
        FoodItemBuilder.create("grilled_salmon", FoodValues.GRILLED_SALMON_NUTRITION, FoodValues.GRILLED_SALMON_SATURATION)
            .setContainer(Items.bowl)
            .setMaxStackSize(FoodValues.SMALL_STACK)
            .addEffect(ModEffects.NOURISHMENT.getId(), FoodValues.MEDIUM_DURATION, 0)
            .setShowEffectTooltip(true)
            .build(),
        "grilled_salmon"
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
