package com.linvy.culinaryhorizons.TileEntity;

import com.linvy.culinaryhorizons.recipe.CookingPotRecipe;
import com.linvy.culinaryhorizons.recipe.CookingPotRecipeRegistry;
import com.linvy.culinaryhorizons.util.HeatSourceRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;
import java.util.Objects;

public class CookingPotTileEntity extends TileEntity implements ISidedInventory {

    public static final int INGREDIENT_SLOTS = 6;
    public static final int MEAL_DISPLAY_SLOT = 6;
    public static final int CONTAINER_SLOT = 7;
    public static final int OUTPUT_SLOT = 8;
    public static final int TOTAL_SLOTS = 9;

    private ItemStack mealContainer;
    private ItemStack[] inventory = new ItemStack[TOTAL_SLOTS];
    private java.util.Map<String, Integer> usedRecipeTracker = new java.util.HashMap<>();
    private String lastRecipeId;

    private int cookTime;
    private int cookTimeTotal;
    private int servingCooldown = 0;

    public CookingPotTileEntity() {
        this.cookTime = 0;
        this.cookTimeTotal = 0;
    }

    public boolean isHeated() {
        if (worldObj == null) return false;
        return HeatSourceRegistry.isHeatSourceBelow(worldObj, xCoord, yCoord, zCoord);
    }

    public boolean isCooking() {
        if (!isHeated()) {
            return false;
        }

        if (!hasInput()) {
            return false;
        }

        CookingPotRecipe recipe = CookingPotRecipeRegistry.findRecipe(getIngredientArray());
        if (recipe == null) {
            return false;
        }

        return canCook(recipe);
    }

    private boolean hasInput() {
        for (int i = 0; i < INGREDIENT_SLOTS; i++) {
            if (inventory[i] != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        boolean isHeated = isHeated();
        boolean didUpdate = false;

        if (isHeated && hasInput()) {
            CookingPotRecipe recipe = CookingPotRecipeRegistry.findRecipe(getIngredientArray());

            if (recipe != null && canCook(recipe)) {
                cookTime++;
                cookTimeTotal = recipe.getCookTime();
                didUpdate = true;

                if (cookTime >= cookTimeTotal) {
                    processCooking(recipe);
                    cookTime = 0;
                }
            } else {
                if (cookTime > 0) {
                    cookTime = Math.max(cookTime - 2, 0);
                    didUpdate = true;
                }
            }
        } else if (cookTime > 0) {
            cookTime = Math.max(cookTime - 2, 0);
            didUpdate = true;
        }

        if (servingCooldown > 0) {
            servingCooldown--;
        } else if (canServe()) {
            serveMeal();
            servingCooldown = 20; // Wait 1 second between servings
            didUpdate = true;
        }

        if (didUpdate) {
            this.markDirty();
        }
    }

    private void serveMeal() {
        ItemStack meal = inventory[MEAL_DISPLAY_SLOT];
        ItemStack container = inventory[CONTAINER_SLOT];
        ItemStack output = inventory[OUTPUT_SLOT];

        if (output == null) {
            ItemStack serving = meal.copy();
            serving.stackSize = 1;
            inventory[OUTPUT_SLOT] = serving;
        } else {
            output.stackSize++;
        }

        meal.stackSize--;
        if (meal.stackSize <= 0) {
            inventory[MEAL_DISPLAY_SLOT] = null;
            mealContainer = null;
        }

        container.stackSize--;
        if (container.stackSize <= 0) {
            inventory[CONTAINER_SLOT] = null;
        }

        this.markDirty();
        if (!worldObj.isRemote) {
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    private boolean canServe() {
        ItemStack meal = inventory[MEAL_DISPLAY_SLOT];
        if (meal == null || meal.stackSize <= 0) {
            return false;
        }

        ItemStack container = inventory[CONTAINER_SLOT];
        if (container == null || container.stackSize <= 0) {
            return false;
        }

        if (mealContainer != null && container.getItem() != mealContainer.getItem()) {
            return false;
        }

        ItemStack output = inventory[OUTPUT_SLOT];
        if (output == null) {
            return true;
        }

        if (!output.isItemEqual(meal)) {
            return false;
        }

        if (!ItemStack.areItemStackTagsEqual(output, meal)) {
            return false;
        }

        return output.stackSize < output.getMaxStackSize();
    }

    private ItemStack[] getIngredientArray() {
        ItemStack[] ingredients = new ItemStack[INGREDIENT_SLOTS];
        System.arraycopy(inventory, 0, ingredients, 0, INGREDIENT_SLOTS);
        return ingredients;
    }

    private boolean canCook(CookingPotRecipe recipe) {
        ItemStack result = recipe.getResult();
        ItemStack currentMeal = inventory[MEAL_DISPLAY_SLOT];

        if (currentMeal == null) {
            return true;
        }

        if (!Objects.equals(currentMeal.getItem(), result.getItem())) {
            return false;
        }

        if (currentMeal.getItemDamage() != result.getItemDamage()) {
            return false;
        }

        int newSize = currentMeal.stackSize + result.stackSize;
        return newSize <= 64;

    }

    private void processCooking(CookingPotRecipe recipe) {
        for (ItemStack ingredient : recipe.getIngredients()) {
            consumeIngredient(ingredient);
        }

        ItemStack result = recipe.getResult();
        ItemStack currentMeal = inventory[MEAL_DISPLAY_SLOT];

        if (currentMeal == null) {
            inventory[MEAL_DISPLAY_SLOT] = result;
            mealContainer = recipe.getContainer();
        } else {
            currentMeal.stackSize += result.stackSize;
        }

        trackRecipeUsed(recipe, result.stackSize);

        this.markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    private void consumeIngredient(ItemStack ingredient) {
        for (int i = 0; i < INGREDIENT_SLOTS; i++) {
            if (inventory[i] != null && ingredient.getItem() == inventory[i].getItem()) {
                if (ingredient.getItemDamage() == 32767 ||
                    ingredient.getItemDamage() == inventory[i].getItemDamage()) {

                    inventory[i].stackSize--;
                    if (inventory[i].stackSize <= 0) {
                        inventory[i] = null;
                    }
                    return;
                }
            }
        }
    }

    /**
     * Award XP to player for recipes they've used
     */
    public void awardUsedRecipes(EntityPlayer player) {
        if (worldObj == null || worldObj.isRemote) return;

        for (java.util.Map.Entry<String, Integer> entry : usedRecipeTracker.entrySet()) {
            String recipeId = entry.getKey();
            int craftedAmount = entry.getValue();

            // TODO: Get the recipe's XP value
            // For now, give a small amount of XP
            float experience = 0.35F; // Default cooking XP

            int totalXP = net.minecraft.util.MathHelper.floor_float((float) craftedAmount * experience);
            float fraction = (float) craftedAmount * experience - totalXP;

            if (fraction > 0.0F && Math.random() < (double) fraction) {
                totalXP++;
            }

            // Spawn XP orbs at the pot location
            while (totalXP > 0) {
                int orbValue = net.minecraft.entity.item.EntityXPOrb.getXPSplit(totalXP);
                totalXP -= orbValue;
                worldObj.spawnEntityInWorld(new net.minecraft.entity.item.EntityXPOrb(
                    worldObj, xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, orbValue));
            }
        }

        usedRecipeTracker.clear();
    }

    /**
     * Track that a recipe was used
     */
    public void trackRecipeUsed(CookingPotRecipe recipe, int amount) {
        // TODO: Once we have recipe IDs, use them instead of a generic key
        String recipeId = "cooking_pot_recipe"; // Placeholder

        if (usedRecipeTracker.containsKey(recipeId)) {
            usedRecipeTracker.put(recipeId, usedRecipeTracker.get(recipeId) + amount);
        } else {
            usedRecipeTracker.put(recipeId, amount);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        Arrays.fill(inventory, null);

        NBTTagList itemList = nbt.getTagList("Inventory", 10);
        for (int i = 0; i < itemList.tagCount(); i++) {
            NBTTagCompound itemTag = itemList.getCompoundTagAt(i);
            int slot = itemTag.getByte("Slot");

            if (slot >= 0 && slot < TOTAL_SLOTS) {
                if (itemTag.getBoolean("Empty")) {
                    inventory[slot] = null;
                } else {
                    inventory[slot] = ItemStack.loadItemStackFromNBT(itemTag);
                }
            }
        }

        this.cookTime = nbt.getInteger("CookTime");
        this.cookTimeTotal = nbt.getInteger("CookTimeTotal");
        this.servingCooldown = nbt.getInteger("ServingCooldown");

        if (nbt.hasKey("MealContainer")) {
            mealContainer = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("MealContainer"));
        } else {
            mealContainer = null;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < TOTAL_SLOTS; i++) {
            NBTTagCompound itemTag = new NBTTagCompound();
            itemTag.setByte("Slot", (byte) i);

            if (inventory[i] != null) {
                inventory[i].writeToNBT(itemTag);
            } else {
                itemTag.setBoolean("Empty", true);
            }

            itemList.appendTag(itemTag);
        }

        nbt.setTag("Inventory", itemList);
        nbt.setInteger("CookTime", this.cookTime);
        nbt.setInteger("CookTimeTotal", this.cookTimeTotal);
        nbt.setInteger("ServingCooldown", servingCooldown);

        if (mealContainer != null) {
            NBTTagCompound containerTag = new NBTTagCompound();
            mealContainer.writeToNBT(containerTag);
            nbt.setTag("MealContainer", containerTag);
        }
    }

    public void setMealContainer(ItemStack container) {
        this.mealContainer = container;
    }

    public ItemStack getMealContainer() {
        return mealContainer;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
        if (this.worldObj != null) {
            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    @Override
    public int getSizeInventory() {
        return TOTAL_SLOTS;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < 0 || slot >= TOTAL_SLOTS) return null;
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (inventory[slot] != null) {
            ItemStack stack;

            if (inventory[slot].stackSize <= amount) {
                stack = inventory[slot];
                inventory[slot] = null;
                this.markDirty();
                return stack;
            } else {
                stack = inventory[slot].splitStack(amount);
                if (inventory[slot].stackSize == 0) {
                    inventory[slot] = null;
                }
                this.markDirty();
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot >= 0 && slot < TOTAL_SLOTS) {
            inventory[slot] = stack;

            if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                stack.stackSize = getInventoryStackLimit();
            }

            this.markDirty();
        }
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public void setCookTimeTotal(int cookTimeTotal) {
        this.cookTimeTotal = cookTimeTotal;
    }

    @Override
    public String getInventoryName() {
        return "container.cooking_pot";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
            player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64.0;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot >= 0 && slot < INGREDIENT_SLOTS) {
            return true;
        }

        // TODO: Check if item is a valid container
        return slot == CONTAINER_SLOT;
    }


    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == 1) {
            return new int[]{0, 1, 2, 3, 4, 5, CONTAINER_SLOT};
        }

        return new int[]{OUTPUT_SLOT};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        if (side != 1) return false;
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == OUTPUT_SLOT;
    }

    public int getCookTime() {
        return cookTime;
    }

    public int getCookTimeTotal() {
        return cookTimeTotal;
    }

    public float getCookProgress() {
        if (cookTimeTotal == 0) return 0;
        return (float) cookTime / (float) cookTimeTotal;
    }
}
