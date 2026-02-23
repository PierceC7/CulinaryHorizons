package com.linvy.culinaryhorizons.block;

import java.util.ArrayList;
import java.util.Arrays;

import com.linvy.culinaryhorizons.TileEntity.FeastTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.linvy.culinaryhorizons.client.model.FeastBlockISBRH;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FeastBlock extends Block implements ITileEntityProvider {

    private final ArrayList<Item> servingItem;
    private final boolean hasLeftovers;
    private final ItemStack[] drops;
    private final int servings;

    public FeastBlock(Item[] servingItem, boolean hasLeftovers, ItemStack... drops) {
        super(Material.cake);
        this.servingItem = new ArrayList<Item>(Arrays.asList(servingItem));
        this.hasLeftovers = hasLeftovers;
        this.drops = drops;
        this.servings = servingItem.length;
    }

    public FeastBlock(Item servingItem, int servings, boolean hasLeftovers, ItemStack... drops) {
        super(Material.cake);
        this.servingItem = new ArrayList<>();
        int i;
        for (i = 0; i < servings; i++) {
            this.servingItem.add(servingItem);
        }
        this.hasLeftovers = hasLeftovers;
        this.drops = drops;
        this.servings = servings;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new FeastTileEntity();
    }

    @Override
    public int getRenderType() {
        return FeastBlockISBRH.INSTANCE.getRenderId();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> droppedItems = new ArrayList<>();
        for (ItemStack drop : drops) {
            if (drop != null) {
                droppedItems.add(drop.copy());
            }
        }
        return droppedItems;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int facing = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, facing, 2);
        FeastTileEntity te = (FeastTileEntity) world.getTileEntity(x, y, z);
        te.setServingItem(this.servingItem);
        te.setServings(this.servings);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {

        int metadata = world.getBlockMetadata(x, y, z);
        int servings = 0;
        boolean needsContainer = false;

        if (world.isRemote) {
            return true;
        }

        TileEntity te = world.getTileEntity(x, y, z);
        ItemStack heldItem = player.getCurrentEquippedItem();
        ItemStack serving = null;
        if (te instanceof FeastTileEntity) {
            servings = ((FeastTileEntity) te).getServings();
            if (servings > 0 && !(servings == 1 && !this.hasLeftovers)) {
                ((FeastTileEntity) te).setServings(--servings);
            } else {
                world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "dig.wood", 0.8F, 0.8F);
                world.func_147480_a(x, y, z, true);
                if(this.hasLeftovers){
                    return true;
                }
            }
            serving = new ItemStack(((FeastTileEntity) te).getCurrentServingItem());

        }

        if (serving == null) {
            return false;
        }

        if (serving.getItem() == null) {
            return false;
        }

        if (serving.getItem()
            .hasContainerItem(serving)) {
            ItemStack containerStack = serving.getItem()
                .getContainerItem(serving);
            if (containerStack != null && containerStack.getItem() != null) {
                needsContainer = true;
                Item requiredContainer = containerStack.getItem();

                if (heldItem == null || heldItem.getItem() != requiredContainer) {
                    return false;
                }
            }
        }

        if (needsContainer) {
            heldItem.stackSize--;
            if (heldItem.stackSize <= 0) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
            }
        }

        if (!player.inventory.addItemStackToInventory(serving)) {
            player.dropPlayerItemWithRandomChoice(serving, false);
        }

        player.inventoryContainer.detectAndSendChanges();
        world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.pop", 1.0F, 1.0F);
        world.markBlockForUpdate(x, y, z);
        world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public static class FeastItemBlock extends ItemBlock {

        private IIcon itemIcon;
        private static final String MOD_ID = "culinaryhorizons";

        public FeastItemBlock(Block block) {
            super(block);
            setMaxStackSize(1);
        }

        @SideOnly(Side.CLIENT)
        @Override
        public IIcon getIconFromDamage(int damage) {
            return this.itemIcon;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public void registerIcons(IIconRegister register) {
            String blockName = this.getUnlocalizedName()
                .substring(5); // Remove "tile." prefix
            this.itemIcon = register.registerIcon(MOD_ID + ":" + blockName);
        }

        @SideOnly(Side.CLIENT)
        @Override
        public int getSpriteNumber() {
            return 1;
        }
    }
}
