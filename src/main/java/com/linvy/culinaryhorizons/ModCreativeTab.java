package com.linvy.culinaryhorizons;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModCreativeTab extends CreativeTabs {

    public static final ModCreativeTab INSTANCE = new ModCreativeTab();

    public ModCreativeTab() {
        super("culinaryhorizons");
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.CHICKEN_SANDWICH;
    }
}
