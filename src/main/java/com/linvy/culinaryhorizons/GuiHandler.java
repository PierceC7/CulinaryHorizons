package com.linvy.culinaryhorizons;

import com.linvy.culinaryhorizons.TileEntity.CookingPotTileEntity;
import com.linvy.culinaryhorizons.client.gui.CookingPotGui;
import com.linvy.culinaryhorizons.container.CookingPotContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int COOKING_POT_GUI = 0;
    // Add more GUI IDs here as you add more GUIs

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (ID == COOKING_POT_GUI) {
            if (te instanceof CookingPotTileEntity) {
                return new CookingPotContainer(player.inventory, (CookingPotTileEntity) te);
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (ID == COOKING_POT_GUI) {
            if (te instanceof CookingPotTileEntity) {
                return new CookingPotGui(player.inventory, (CookingPotTileEntity) te);
            }
        }

        return null;
    }
}
