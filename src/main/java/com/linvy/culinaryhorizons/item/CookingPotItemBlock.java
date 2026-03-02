package com.linvy.culinaryhorizons.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class CookingPotItemBlock extends ItemBlock {

    public CookingPotItemBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag")) {
            NBTTagCompound blockEntityTag = stack.getTagCompound().getCompoundTag("BlockEntityTag");

            if (blockEntityTag.hasKey("Meal")) {
                ItemStack meal = ItemStack.loadItemStackFromNBT(blockEntityTag.getCompoundTag("Meal"));
                if (meal != null) {
                    tooltip.add(EnumChatFormatting.GRAY + "Contains: " +
                        EnumChatFormatting.YELLOW + meal.getDisplayName() +
                        EnumChatFormatting.GRAY + " x" + meal.stackSize);
                }
            }
        }
    }
}
