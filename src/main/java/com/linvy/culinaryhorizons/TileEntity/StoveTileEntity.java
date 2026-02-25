package com.linvy.culinaryhorizons.TileEntity;

import com.linvy.culinaryhorizons.ModBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class StoveTileEntity extends TileEntity implements ISidedInventory {

    public static final int COOKING_SLOTS = 6;
    public static final int COOKING_TIME = 600;

    private ItemStack[] cookingItems = new ItemStack[COOKING_SLOTS];
    private int[] cookingTimes = new int[COOKING_SLOTS];

    public StoveTileEntity() {
        for (int i = 0; i < COOKING_SLOTS; i++) {
            cookingItems[i] = null;
            cookingTimes[i] = 0;
        }
    }

    public boolean isStoveLit() {
        return this.worldObj.getBlock(xCoord, yCoord, zCoord) == ModBlocks.STOVE_LIT.get();
    }

    public boolean addItem(ItemStack stack) {
        if (!canCook(stack)) {
            return false;
        }

        for (int i = 0; i < COOKING_SLOTS; i++) {
            if (cookingItems[i] == null) {
                cookingItems[i] = stack.copy();
                cookingItems[i].stackSize = 1;
                cookingTimes[i] = 0;
                this.markDirty();

                if (!worldObj.isRemote) {
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
                }
                return true;
            }
        }

        return false;
    }

    private boolean canCook(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return false;

        if (stack.getItem() instanceof ItemFood) {
            ItemStack furnaceResult = FurnaceRecipes.smelting().getSmeltingResult(stack);
            return furnaceResult != null && furnaceResult.getItem() instanceof ItemFood;
        }

        return false;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        boolean isLit = isStoveLit();
        boolean didUpdate = false;

        for (int i = 0; i < COOKING_SLOTS; i++) {
            if (cookingItems[i] != null) {
                if (isLit) {
                    cookingTimes[i]++;

                    if (cookingTimes[i] >= COOKING_TIME) {
                        // Cooking complete - pop off item
                        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(cookingItems[i]);
                        if (result != null) {
                            ejectItem(result.copy(), i);
                        }
                        cookingItems[i] = null;
                        cookingTimes[i] = 0;
                        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    }
                    didUpdate = true;
                } else {

                    if (cookingTimes[i] > 0) {
                        cookingTimes[i] = MathHelper.clamp_int(cookingTimes[i] - 2, 0, COOKING_TIME);
                        didUpdate = true;
                    }
                }
            }
        }

        if (didUpdate) {
            this.markDirty();
        }
    }

    private void ejectItem(ItemStack stack, int slot) {

        EntityItem entityItem = new EntityItem(
            worldObj,
            xCoord + 0.5,
            yCoord + 1.25,
            zCoord + 0.5,
            stack
        );

        entityItem.motionX = 0;
        entityItem.motionY = 0;
        entityItem.motionZ = 0;

        worldObj.spawnEntityInWorld(entityItem);
    }

    public ItemStack[] getCookingItems() {
        return cookingItems;
    }

    public int[] getCookingTimes() {
        return cookingTimes;
    }

    public float getCookProgress(int slot) {
        if (slot < 0 || slot >= COOKING_SLOTS) return 0;
        return (float) cookingTimes[slot] / (float) COOKING_TIME;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        //item slot clear to fix render bug, items were rendering forever
        for (int i = 0; i < COOKING_SLOTS; i++) {
            cookingItems[i] = null;
            cookingTimes[i] = 0;
        }

        NBTTagList itemList = nbt.getTagList("CookingItems", 10);
        for (int i = 0; i < itemList.tagCount() && i < COOKING_SLOTS; i++) {
            NBTTagCompound itemTag = itemList.getCompoundTagAt(i);
            int slot = itemTag.getByte("Slot");
            if (slot >= 0 && slot < COOKING_SLOTS) {
                cookingItems[slot] = ItemStack.loadItemStackFromNBT(itemTag);
            }
        }

        int[] times = nbt.getIntArray("CookingTimes");
        if (times.length == COOKING_SLOTS) {
            cookingTimes = times;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < COOKING_SLOTS; i++) {
            if (cookingItems[i] != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                cookingItems[i].writeToNBT(itemTag);
                itemList.appendTag(itemTag);
            }
        }
        nbt.setTag("CookingItems", itemList);
        nbt.setIntArray("CookingTimes", cookingTimes);
    }

    @Override
    public int getSizeInventory() {
        return COOKING_SLOTS;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < 0 || slot >= COOKING_SLOTS) return null;
        return cookingItems[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (cookingItems[slot] != null) {
            ItemStack stack = cookingItems[slot];
            cookingItems[slot] = null;
            cookingTimes[slot] = 0;
            this.markDirty();
            return stack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot >= 0 && slot < COOKING_SLOTS) {
            cookingItems[slot] = stack;
            if (stack != null) {
                cookingTimes[slot] = 0;
            }
            this.markDirty();
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        System.out.println("SERVER: Sending sync - slot 0 item: " + (cookingItems[0] != null ? cookingItems[0].getDisplayName() : "null"));
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        System.out.println("CLIENT: Receiving sync BEFORE - slot 0: " + (cookingItems[0] != null));
        this.readFromNBT(pkt.func_148857_g());
        System.out.println("CLIENT: Receiving sync AFTER - slot 0: " + (cookingItems[0] != null));
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        this.worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
    }

    @Override
    public String getInventoryName() {
        return "Stove";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(net.minecraft.entity.player.EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return canCook(stack);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[]{0, 1, 2, 3};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return side == 1 && isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return false;
    }
}
