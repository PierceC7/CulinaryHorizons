package com.linvy.culinaryhorizons.TileEntity;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class FeastTileEntity extends TileEntity {

    private int servings;
    private ArrayList<Item> servingItem;

    public FeastTileEntity() {
        this.servings = 0;
        this.servingItem = new ArrayList<>();
    }

    public FeastTileEntity(ArrayList<Item> servingItem, int servings) {
        this.servings = servings;
        this.servingItem = servingItem;

    }

    public int getServings() {
        return this.servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
        this.markDirty();
    }

    public Item getCurrentServingItem() {
        return servingItem.get(this.servings);
    }

    public ArrayList<Item> getServingItem() {
        return servingItem;
    }

    public void setServingItem(ArrayList<Item> servingItem) {
        this.servingItem = servingItem;
        this.markDirty();
    }

    public void setCurrentServingItem(Item servingItem) {
        this.servingItem.set(this.servings, servingItem);
        this.markDirty();
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
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.servings = nbt.getInteger("Servings");
        int size = nbt.getInteger("ServingItemsSize");

        this.servingItem = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            int itemId = nbt.getInteger("ServingItem_" + i);
            if (itemId >= 0) {
                Item item = Item.getItemById(itemId);
                this.servingItem.add(item);
            } else {
                this.servingItem.add(null); // Preserve null entries
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("Servings", this.servings);
        nbt.setInteger("ServingItemsSize", this.servingItem.size());

        for (int i = 0; i < this.servingItem.size(); i++) {
            Item item = this.servingItem.get(i);
            if (item != null) {
                int itemId = Item.getIdFromItem(item);
                nbt.setInteger("ServingItem_" + i, itemId);
            } else {
                nbt.setInteger("ServingItem_" + i, -1); // -1 for null
            }
        }
    }
}
