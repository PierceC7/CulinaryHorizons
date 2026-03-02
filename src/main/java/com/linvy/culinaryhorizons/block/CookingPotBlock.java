package com.linvy.culinaryhorizons.block;

import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;
import com.linvy.culinaryhorizons.CulinaryHorizons;
import com.linvy.culinaryhorizons.GuiHandler;
import com.linvy.culinaryhorizons.TileEntity.CookingPotTileEntity;
import com.linvy.culinaryhorizons.client.particle.ParticleSteam;
import com.linvy.culinaryhorizons.util.HeatSourceRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class CookingPotBlock extends BlockContainer {

    private static final int FACING_MASK = 0x3;
    private static final int SUPPORT_MASK = 0xC;
    private static final int SUPPORT_SHIFT = 2;

    public static final int SUPPORT_NONE = 0;
    public static final int SUPPORT_HANDLE = 1;
    public static final int SUPPORT_TRAY = 2;

    public CookingPotBlock() {
        super(Material.iron);
        this.setHardness(2.0F);
        this.setResistance(6.0F);
        this.setStepSound(soundTypeMetal);
        this.setBlockName("cooking_pot");
        this.setHarvestLevel("pickaxe", 0);
    }

    public static int getFacing(int meta) {
        return meta & FACING_MASK;
    }

    public static int getSupport(int meta) {
        return (meta & SUPPORT_MASK) >> SUPPORT_SHIFT;
    }

    public static int setFacing(int meta, int facing) {
        return (meta & ~FACING_MASK) |  (facing & FACING_MASK);
    }

    public static int setSupport(int meta, int support) {
        return (meta & ~SUPPORT_MASK) | ((support << SUPPORT_SHIFT) & SUPPORT_MASK);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new CookingPotTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int facing = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        int support = getTrayState(world, x, y, z);
        int meta = setFacing(0, facing);
        meta = setSupport(meta, support);

        world.setBlockMetadataWithNotify(x, y, z, meta, 2);

        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("BlockEntityTag")) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof CookingPotTileEntity pot) {
                NBTTagCompound blockEntityTag = stack.getTagCompound().getCompoundTag("BlockEntityTag");

                if (blockEntityTag.hasKey("Meal")) {
                    ItemStack meal = ItemStack.loadItemStackFromNBT(blockEntityTag.getCompoundTag("Meal"));
                    pot.setInventorySlotContents(CookingPotTileEntity.MEAL_DISPLAY_SLOT, meal);
                }

                if (blockEntityTag.hasKey("MealContainer")) {
                    ItemStack container = ItemStack.loadItemStackFromNBT(blockEntityTag.getCompoundTag("MealContainer"));
                    pot.setMealContainer(container);
                }

                pot.markDirty();
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        int meta = world.getBlockMetadata(x, y, z);
        int currentSupport = getSupport(meta);

        if (currentSupport != SUPPORT_HANDLE) {
            int newSupport = getTrayState(world, x, y, z);
            if (newSupport != currentSupport) {
                meta = setSupport(meta, newSupport);
                world.setBlockMetadataWithNotify(x, y, z, meta, 3);
            }
        }
    }

    private int getTrayState(World world, int x, int y, int z) {
        if (HeatSourceRegistry.isTrayHeatSourceBelow(world, x, y, z)) {
            return SUPPORT_TRAY;
        }
        return SUPPORT_NONE;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }

        ItemStack heldStack = player.getCurrentEquippedItem();

        if (heldStack == null && player.isSneaking()) {
            int meta = world.getBlockMetadata(x, y, z);
            int currentSupport = getSupport(meta);

            if (currentSupport == SUPPORT_HANDLE) {
                int newSupport = getTrayState(world, x, y, z);
                meta = setSupport(meta, newSupport);
            } else {
                meta = setSupport(meta, SUPPORT_HANDLE);
            }

            world.setBlockMetadataWithNotify(x, y, z, meta, 3);
            world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.click", 0.7F, 1.0F);
            return true;
        }

        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof CookingPotTileEntity) {
            player.openGui(CulinaryHorizons.instance, GuiHandler.COOKING_POT_GUI, world, x, y, z);
            return true;
        }

        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof CookingPotTileEntity pot) {
            ItemStack drop = new ItemStack(this);
            ItemStack meal = pot.getStackInSlot(CookingPotTileEntity.MEAL_DISPLAY_SLOT);
            ItemStack container = pot.getStackInSlot(CookingPotTileEntity.CONTAINER_SLOT);
            ItemStack mealContainer = pot.getMealContainer();

            if (meal != null || container != null) {
                NBTTagCompound blockEntityTag = new NBTTagCompound();

                if (meal != null) {
                    NBTTagCompound mealTag = new NBTTagCompound();
                    meal.writeToNBT(mealTag);
                    blockEntityTag.setTag("Meal", mealTag);
                }

                if (mealContainer != null) {
                    NBTTagCompound containerTag = new NBTTagCompound();
                    pot.getMealContainer().writeToNBT(containerTag);
                    blockEntityTag.setTag("MealContainer", containerTag);
                }

                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setTag("BlockEntityTag", blockEntityTag);
                drop.setTagCompound(itemTag);
            }

            this.dropBlockAsItem(world, x, y, z, drop);

            for (int i = 0; i < CookingPotTileEntity.INGREDIENT_SLOTS; i++) {
                ItemStack stack = pot.getStackInSlot(i);
                if (stack != null) {
                    this.dropBlockAsItem(world, x, y, z, stack);
                }
            }

            ItemStack containerSlot = pot.getStackInSlot(CookingPotTileEntity.CONTAINER_SLOT);
            if (containerSlot != null) {
                this.dropBlockAsItem(world, x, y, z, containerSlot);
            }

            ItemStack outputSlot = pot.getStackInSlot(CookingPotTileEntity.OUTPUT_SLOT);
            if (outputSlot != null) {
                this.dropBlockAsItem(world, x, y, z, outputSlot);
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<>();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (HeatSourceRegistry.isHeatSourceBelow(world, x, y, z)) {
            TileEntity te = world.getTileEntity(x, y, z);

            if (te instanceof CookingPotTileEntity pot) {
                if (pot.isCooking()) {
                    double xPos = x + 0.5D;
                    double yPos = y + 0.5D;
                    double zPos = z + 0.5D;

                    if (rand.nextFloat() < 0.25F) {
                        double xOffset = (rand.nextDouble() - 0.5D) * 0.3D;
                        double zOffset = (rand.nextDouble() - 0.5D) * 0.3D;
                        ParticleSteam steam = new ParticleSteam(world, xPos + xOffset, yPos, zPos + zOffset, 0.0D, 0.0D, 0.0D);
                        net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(steam);
                    }
                }
            }
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        int support = getSupport(meta);

        if (support == SUPPORT_TRAY) {
            return AxisAlignedBB.getBoundingBox(
                x, y - 1.0, z,
                x + 1, y + 0.625, z + 1
            );
        }

        return AxisAlignedBB.getBoundingBox(
            x + 0.125, y, z + 0.125,
            x + 0.875, y + 0.625, z + 0.875
        );
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        return getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return ModelISBRH.JSON_ISBRH_ID;
    }
}
