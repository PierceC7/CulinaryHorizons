package com.linvy.culinaryhorizons.container;

import com.linvy.culinaryhorizons.TileEntity.CookingPotTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CookingPotContainer extends Container {

    private final CookingPotTileEntity tileEntity;

    private int lastCookTime;
    private int lastCookTimeTotal;

    public CookingPotContainer(InventoryPlayer playerInventory, CookingPotTileEntity tileEntity) {
        this.tileEntity = tileEntity;

        // Ingredient slots (6 slots in 2x3 grid) - matching Farmer's Delight layout
        int inputStartX = 30;
        int inputStartY = 17;
        int slotSize = 18;

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                this.addSlotToContainer(new Slot(tileEntity, (row * 3) + col, inputStartX + (col * slotSize), inputStartY + (row * slotSize)));
            }
        }

        this.addSlotToContainer(new MealDisplaySlot(tileEntity, CookingPotTileEntity.MEAL_DISPLAY_SLOT, 124, 26));
        this.addSlotToContainer(new ContainerSlot(tileEntity, CookingPotTileEntity.CONTAINER_SLOT, 92, 55));
        this.addSlotToContainer(new CookingPotResultSlot(playerInventory.player, tileEntity, CookingPotTileEntity.OUTPUT_SLOT, 124, 55));

        int startPlayerInvY = 84;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlotToContainer(new Slot(playerInventory, 9 + (row * 9) + col,
                    8 + (col * slotSize), startPlayerInvY + (row * slotSize)));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlotToContainer(new Slot(playerInventory, col, 8 + (col * slotSize), 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
        crafter.sendProgressBarUpdate(this, 0, tileEntity.getCookTime());
        crafter.sendProgressBarUpdate(this, 1, tileEntity.getCookTimeTotal());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (ICrafting crafter : this.crafters) {
            if (this.lastCookTime != tileEntity.getCookTime()) {
                crafter.sendProgressBarUpdate(this, 0, tileEntity.getCookTime());
            }

            if (this.lastCookTimeTotal != tileEntity.getCookTimeTotal()) {
                crafter.sendProgressBarUpdate(this, 1, tileEntity.getCookTimeTotal());
            }
        }

        this.lastCookTime = tileEntity.getCookTime();
        this.lastCookTimeTotal = tileEntity.getCookTimeTotal();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        if (id == 0) {
            tileEntity.setCookTime(value);
        }
        if (id == 1) {
            tileEntity.setCookTimeTotal(value);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack originalStack = null;
        Slot slot = this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            originalStack = stackInSlot.copy();

            int indexMealDisplay = 6;
            int indexContainerInput = 7;
            int indexOutput = 8;
            int startPlayerInv = 9;
            int endPlayerInv = 45;

            if (slotIndex == indexOutput) {
                if (!this.mergeItemStack(stackInSlot, startPlayerInv, endPlayerInv, true)) {
                    return null;
                }
                slot.onSlotChange(stackInSlot, originalStack);
            }

            else if (slotIndex >= startPlayerInv) {
                boolean moved = false;
                boolean isValidContainer = isValidServingContainer(stackInSlot);

                if (isValidContainer) {
                    moved = this.mergeItemStack(stackInSlot, indexContainerInput, indexContainerInput + 1, false);
                }

                if (!moved) {
                    moved = this.mergeItemStack(stackInSlot, 0, indexMealDisplay, false);
                }

                if (!moved) {
                    if (slotIndex < 36) {
                        moved = this.mergeItemStack(stackInSlot, 36, endPlayerInv, false);
                    } else {
                        moved = this.mergeItemStack(stackInSlot, startPlayerInv, 36, false);
                    }
                }

                if (!moved) {
                    return null;
                }
            }

            else if (slotIndex != indexMealDisplay) {
                if (!this.mergeItemStack(stackInSlot, startPlayerInv, endPlayerInv, false)) {
                    return null;
                }
            }

            else {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (stackInSlot.stackSize == originalStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stackInSlot);
        }

        return originalStack;
    }

    private boolean isValidServingContainer(ItemStack stack) {
        if (stack == null) return false;

        // TODO: change check from specific recipe container to a list of valid containers. Possibly done, testing, remove to do if works
        ItemStack requiredContainer = tileEntity.getMealContainer();
        if (requiredContainer != null) {
            return stack.getItem() == requiredContainer.getItem();
        }

        return stack.getItem() == net.minecraft.init.Items.bowl ||
            stack.getItem() == net.minecraft.init.Items.glass_bottle;
    }

    public int getCookProgressScaled(int pixels) {
        int cookTime = tileEntity.getCookTime();
        int cookTimeTotal = tileEntity.getCookTimeTotal();
        return cookTimeTotal != 0 && cookTime != 0 ? cookTime * pixels / cookTimeTotal : 0;
    }

    public boolean isHeated() {
        return tileEntity.isHeated();
    }

    private static class MealDisplaySlot extends Slot {
        public MealDisplaySlot(CookingPotTileEntity te, int index, int x, int y) {
            super(te, index, x, y);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return false;
        }

        @Override
        public boolean canTakeStack(EntityPlayer player) {
            return false;
        }
    }

    private static class ContainerSlot extends Slot {
        public ContainerSlot(CookingPotTileEntity te, int index, int x, int y) {
            super(te, index, x, y);
        }
    }
}
