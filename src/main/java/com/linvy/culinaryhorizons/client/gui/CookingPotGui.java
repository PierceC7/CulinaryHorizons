package com.linvy.culinaryhorizons.client.gui;

import com.linvy.culinaryhorizons.TileEntity.CookingPotTileEntity;
import com.linvy.culinaryhorizons.container.CookingPotContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class CookingPotGui extends GuiContainer {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("culinaryhorizons", "textures/gui/cooking_pot.png");

    private final CookingPotContainer container;

    public CookingPotGui(InventoryPlayer playerInventory, CookingPotTileEntity tileEntity) {
        super(new CookingPotContainer(playerInventory, tileEntity));
        this.container = (CookingPotContainer) this.inventorySlots;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format("container.cooking_pot");
        this.fontRendererObj.drawString(title, 28, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        int progress = container.getCookProgressScaled(24);

        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        if (container.isHeated()) {
            this.drawTexturedModalRect(x + 47, y + 55, 176, 0, 17, 15);
        }

        if (progress > 0) {
            this.drawTexturedModalRect(x + 89, y + 25, 176, 15, progress + 1, 17);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHeatTooltip(mouseX, mouseY);
    }

    private void renderHeatTooltip(int mouseX, int mouseY) {
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        if (this.isMouseOverSlot(x + 47, y + 55, 17, 15, mouseX, mouseY)) {
            String key = container.isHeated() ? "container.cooking_pot.heated" : "container.cooking_pot.not_heated";
            this.drawCreativeTabHoveringText(I18n.format(key), mouseX, mouseY);
        }
    }

    private boolean isMouseOverSlot(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }
}
