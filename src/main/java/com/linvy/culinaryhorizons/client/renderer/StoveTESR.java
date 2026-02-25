package com.linvy.culinaryhorizons.client.renderer;

import com.linvy.culinaryhorizons.TileEntity.StoveTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class StoveTESR extends TileEntitySpecialRenderer {

    private static final RenderItem itemRenderer = new RenderItem();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {

        if (!(te instanceof StoveTileEntity stove)) return;

        ItemStack[] items = stove.getCookingItems();
        int[] cookTimes = stove.getCookingTimes();

        // Get stove facing for rotation
        int meta = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
        int facing = meta & 3;

        /*for (int i = 0; i < StoveTileEntity.COOKING_SLOTS; i++) {
            if (items[i] != null && cookTimes[i] >= 0 && cookTimes[i] < StoveTileEntity.COOKING_TIME && stove.isStoveLit()) {
                if (Math.random() < 0.3) {
                    double[] pos = getSlotPosition(i, facing);

                    double randomX = Math.random() * 0.15;
                    double randomZ = (Math.random() - 0.5) * 0.15;

                    // Spawn particle in WORLD coordinates
                    double particleX = te.xCoord + 0.5 + pos[0] + randomX;
                    double particleY = te.yCoord + 1.05;
                    double particleZ = te.zCoord + 0.5 + pos[1] + randomZ;

                    stove.getWorldObj().spawnParticle("smoke", particleX, particleY, particleZ, 0.0, 0.02, 0.0);
                }
            }
        }*/

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.0, z + 0.5); // Center on top of block

        for (int i = 0; i < StoveTileEntity.COOKING_SLOTS; i++) {
            if (items[i] != null) {
                boolean isCooking = items[i] != null && cookTimes[i] >= 0 && cookTimes[i] < StoveTileEntity.COOKING_TIME && stove.isStoveLit();
                renderItemInSlot(items[i], i, facing, stove, isCooking, te.xCoord, te.yCoord, te.zCoord);
            }
        }

        GL11.glPopMatrix();
    }

    private void renderItemInSlot(ItemStack stack, int slot, int facing, StoveTileEntity stove, boolean isCooking, int worldX, int worldY, int worldZ) {
        GL11.glPushMatrix();

        double[] pos = getSlotPosition(slot, facing);

        GL11.glTranslated(pos[0], 0.01, pos[1]);

        // Spawn smoke at this exact position if cooking
        if (isCooking && Math.random() < 0.3) {
            // Calculate world position from current render position
            double randomX = (Math.random() - 0.5) * 0.15;
            double randomZ = (Math.random() - 0.5) * 0.15;

            double particleX = worldX + 0.5 + pos[2] + randomX;
            double particleY = worldY + 1.05;
            double particleZ = worldZ + 0.5 + pos[3] + randomZ;

            stove.getWorldObj().spawnParticle("smoke", particleX, particleY, particleZ, 0.0, 0.02, 0.0);
        }

        float rotation = switch (facing) {
            case 0 -> 180F;
            case 1 -> 90F;
            case 2 -> 0F;
            case 3 -> 270F;
            default -> 0;
        };
        GL11.glRotatef(rotation, 0, 1, 0);

        GL11.glRotatef(90F, 1, 0, 0);

        float scale = 0.75F;
        GL11.glScalef(scale, scale, scale);

        EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0, 0, 0, stack);
        entityItem.hoverStart = 0.0F;

        RenderManager renderManager = RenderManager.instance;
        renderManager.renderEntityWithPosYaw(entityItem, 0, 0, 0, 0, 0);

        GL11.glPopMatrix();
    }

    private double[] getSlotPosition(int slot, int facing) {

        double xOffset;
        // Z offset: 2 rows (front, back)
        double zOffset = (slot >= 3) ? -0.35 : 0.0;
        double rotatedX;
        double rotatedZ;
        double smokeX;
        double smokeZ;

        // 3x2 grid (3 columns, 2 rows)
        // Row 1 (front): [0] [1] [2]
        // Row 2 (back):  [3] [4] [5]
        int column = slot % 3;
        if (column == 0) {
            xOffset = 0.30; // Left
        } else if (column == 1) {
            xOffset = 0.0;   // Center
        } else {
            xOffset = -0.30;  // Right
        }
        rotatedZ = switch (facing) {
            case 1 -> {
                rotatedX = zOffset;
                smokeX = zOffset + 0.125;
                smokeZ = -xOffset;
                yield -xOffset;
            }
            case 2 -> {
                rotatedX = xOffset;
                smokeX = xOffset;
                smokeZ = zOffset + 0.125;
                yield zOffset;
            }
            case 3 -> {
                rotatedX = -zOffset;
                smokeX = -zOffset - 0.125;
                smokeZ = xOffset;
                yield xOffset;
            }
            default -> {
                rotatedX = -xOffset;
                smokeX = -xOffset;
                smokeZ = -zOffset - 0.125;
                yield -zOffset;
            }
        };

        return new double[]{rotatedX, rotatedZ, smokeX, smokeZ};
    }
}
