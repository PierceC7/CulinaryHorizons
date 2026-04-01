package com.linvy.culinaryhorizons.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IFoodEatenCallback {
    void onEaten(ItemStack stack, World world, EntityPlayer player);
}