package com.linvy.culinaryhorizons.container;

import com.linvy.culinaryhorizons.TileEntity.CookingPotTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CookingPotResultSlot extends Slot {

    private final CookingPotTileEntity tileEntity;
    private final EntityPlayer player;
    private int removeCount;

    public CookingPotResultSlot(EntityPlayer player, CookingPotTileEntity tileEntity, int index, int x, int y) {
        super(tileEntity, index, x, y);
        this.tileEntity = tileEntity;
        this.player = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.removeCount += Math.min(amount, this.getStack().stackSize);
        }
        return super.decrStackSize(amount);
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
        this.onCrafting(stack);
        super.onPickupFromSlot(player, stack);
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.onCrafting(stack);
    }

    @Override
    protected void onCrafting(ItemStack stack) {
        if (this.removeCount > 0) {
            stack.onCrafting(this.player.worldObj, this.player, this.removeCount);
        }

        if (!this.player.worldObj.isRemote) {
            tileEntity.awardUsedRecipes(this.player);
        }

        this.removeCount = 0;
    }
}
