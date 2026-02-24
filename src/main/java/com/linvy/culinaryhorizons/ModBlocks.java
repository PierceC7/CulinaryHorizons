package com.linvy.culinaryhorizons;

import com.linvy.culinaryhorizons.block.StoveBlock;
import cpw.mods.fml.common.Mod;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.linvy.culinaryhorizons.block.FeastBlock;

import cpw.mods.fml.common.registry.GameRegistry;

import java.lang.reflect.Array;

// Credit to Et Futurum (Requiem)
public enum ModBlocks {

    // spotless:off
    // make sure to leave a trailing comma
    // Feast blocks
    ROAST_CHICKEN_BLOCK(
        new FeastBlock(
        ModItems.ROAST_CHICKEN_PORTION,
        4,
        true,
        new ItemStack(Items.bowl),
        new ItemStack(Items.dye, 1, 15)
        ), "roast_chicken_block"
    ),

    STUFFED_PUMPKIN_BLOCK(
        new FeastBlock(
        ModItems.STUFFED_PUMPKIN_PORTION,
        4,
        false
        ), "stuffed_pumpkin_block"
    ),

    HONEY_GLAZED_HAM_BLOCK(
        new FeastBlock(
            ModItems.HONEY_GLAZED_HAM_PORTION,
            4,
            true,
            new ItemStack(Items.bowl),
            new ItemStack(Items.bone)
        ),"honey_glazed_ham_block"
    ),

    SHEPHERDS_PIE_BLOCK(
        new FeastBlock(
            ModItems.SHEPHERDS_PIE_PORTION,
            4,
            true,
            new ItemStack(Items.bowl)
        ),"shepherds_pie_block"
    ),

    RICE_ROLL_MEDLEY_BLOCK(
      new FeastBlock(new Item[]{ModItems.COD_ROLL,
        ModItems.COD_ROLL, ModItems.SALMON_ROLL,
        ModItems.SALMON_ROLL, ModItems.SALMON_ROLL,
        ModItems.KELP_ROLL_SLICE, ModItems.KELP_ROLL_SLICE,
        ModItems.KELP_ROLL_SLICE}, true, new ItemStack(Items.bowl)
      ), "rice_roll_medley_block"
    ),

    //Functional Blocks

    STOVE (
      new StoveBlock(false),
     "stove_block"
    ),

    STOVE_LIT (
        new StoveBlock(true),
        null,
        "lit_stove_block"
    ),
    ; // leave trailing semicolon
    //spotless:on

    public static final ModBlocks[] VALUES = values();

    public static void init() {
        for (ModBlocks block : VALUES) {
            block.theBlock.setCreativeTab(ModCreativeTab.INSTANCE);
            if (block.getItemBlock() != null || !block.getHasItemBlock()) {
                GameRegistry.registerBlock(block.get(), block.getItemBlock(), block.name);
                // This part is used if the getItemBlock() is not ItemBlock.class, so we register a custom ItemBlock
                // class as the ItemBlock
                // It is also used if the getItemBlock() == null and getHasItemBlock() is false, meaning we WANT to
                // register it as null, making the block have no inventory item.
            } else {
                GameRegistry.registerBlock(block.get(), block.name);
                // Used if getItemBlock() == null but getHasItemBlock() is true, registering it with a default
                // inventory item.
            }
        }
    }

    private final Block theBlock;
    /**
     * null == default ItemBlock
     */
    private final Class<? extends ItemBlock> itemBlock;
    /**
     * Determines if we should register the block with an ItemBlock.
     * Set to false when the constructor that specifies the ItemBlock is specifically set to false.
     */
    private boolean hasItemBlock;
    private final String name;

    ModBlocks(Block block, String name) {
        this(block, null, name);
        hasItemBlock = true;
    }

    ModBlocks(Block block, Class<? extends ItemBlock> iblock, String name) {
        theBlock = block;
        itemBlock = iblock;
        hasItemBlock = iblock != null;
        this.name = name;
        theBlock.setBlockName(this.name);
        theBlock.setBlockTextureName(CulinaryHorizons.MODID + ":" + this.name);
    }

    /**
     * If this is false, the block is initialized without an inventory item, or ItemBlock.
     */
    public boolean getHasItemBlock() {
        return hasItemBlock;
    }

    public Block get() {
        return theBlock;
    }

    public Class<? extends ItemBlock> getItemBlock() {
        return itemBlock;
    }

    public Item getItem() {
        return Item.getItemFromBlock(get());
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
