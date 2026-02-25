package com.linvy.culinaryhorizons.block;

import com.linvy.culinaryhorizons.ModBlocks;
import com.linvy.culinaryhorizons.ModCreativeTab;
import com.linvy.culinaryhorizons.TileEntity.StoveTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class StoveBlock extends BlockContainer {

    private final boolean isLit;

    @SideOnly(Side.CLIENT)
    private IIcon topTextureLit;
    @SideOnly(Side.CLIENT)
    private IIcon topTextureUnlit;
    @SideOnly(Side.CLIENT)
    private IIcon frontTextureLit;
    @SideOnly(Side.CLIENT)
    private IIcon frontTextureUnlit;
    @SideOnly(Side.CLIENT)
    private IIcon sideTexture;
    @SideOnly(Side.CLIENT)
    private IIcon bottomTexture;
    @SideOnly(Side.CLIENT)
    private IIcon bottomTexture_90;
    @SideOnly(Side.CLIENT)
    private IIcon bottomTexture_180;
    @SideOnly(Side.CLIENT)
    private IIcon bottomTexture_270;

    public StoveBlock(boolean isLit) {
        super(Material.rock);
        this.isLit = isLit;
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypePiston);
        this.setHarvestLevel("pickaxe", 0);

        if (isLit) {
            this.setLightLevel(0.875F);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new StoveTileEntity();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int facing = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, facing, 2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
                                    int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }

        ItemStack heldStack = player.getCurrentEquippedItem();
        if (heldStack != null) {
            int meta = world.getBlockMetadata(x, y, z);

            if (!isLit && heldStack.getItem() == Items.flint_and_steel) {
                world.setBlock(x, y, z, ModBlocks.STOVE_LIT.get(), meta, 3);
                world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "fire.ignite", 1.0F,
                    world.rand.nextFloat() * 0.4F + 0.8F);
                heldStack.damageItem(1, player);
                return true;
            }

            if (isLit && heldStack.getItem() instanceof net.minecraft.item.ItemSpade) {
                world.setBlock(x, y, z, ModBlocks.STOVE.get(), meta, 3);
                world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.fizz", 0.5F, 2.6F);
                heldStack.damageItem(1, player);
                return true;
            }

            if (isLit && heldStack.getItem() == Items.water_bucket) {
                world.setBlock(x, y, z, ModBlocks.STOVE.get(), meta, 3);
                world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "random.fizz", 0.5F, 2.6F);
                if (!player.capabilities.isCreativeMode) {
                    player.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
                }
                return true;
            }

            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof StoveTileEntity stove) {
                if (stove.addItem(heldStack)) {
                    if (!player.capabilities.isCreativeMode) {
                        heldStack.stackSize--;
                        if (heldStack.stackSize <= 0) {
                            player.setCurrentItemOrArmor(0, null);
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof StoveTileEntity stove) {
            for (int i = 0; i < StoveTileEntity.COOKING_SLOTS; i++) {
                ItemStack stack = stove.getStackInSlot(i);
                if (stack != null) {
                    this.dropBlockAsItem(world, x, y, z, stack);
                }
            }
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if (isLit && !entity.isImmuneToFire()) {
            entity.attackEntityFrom(net.minecraft.util.DamageSource.inFire, 1.0F);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (isLit) {
            int meta = world.getBlockMetadata(x, y, z);
            int facing = meta & 3;
            double xPos = x + 0.5D;
            double yPos = y + 0.0D;
            double zPos = z + 0.5D;

            if (rand.nextInt(10) == 0) {
                world.playSound(xPos, yPos, zPos, "fire.fire",
                    1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
            }

            // Smoke and flame particles
            double horizontalOffset = rand.nextDouble() * 0.6D - 0.3D;
            double xOffset, zOffset;

            zOffset = switch (facing) {
                case 0 -> {
                    xOffset = horizontalOffset;
                    yield 0.52D;
                }
                case 1 -> {
                    xOffset = -0.52D;
                    yield horizontalOffset;
                }
                case 2 -> {
                    xOffset = horizontalOffset;
                    yield -0.52D;
                }
                case 3 -> {
                    xOffset = 0.52D;
                    yield horizontalOffset;
                }
                default -> {
                    xOffset = 0;
                    yield 0;
                }
            };

            double yOffset = rand.nextDouble() * 6.0D / 16.0D;
            world.spawnParticle("smoke", xPos + xOffset, yPos + yOffset, zPos + zOffset, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", xPos + xOffset, yPos + yOffset, zPos + zOffset, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.sideTexture = register.registerIcon("culinaryhorizons:stove_side");
        this.topTextureLit = register.registerIcon("culinaryhorizons:stove_top_on");
        this.topTextureUnlit = register.registerIcon("culinaryhorizons:stove_top");
        this.frontTextureLit = register.registerIcon("culinaryhorizons:stove_front_on");
        this.frontTextureUnlit = register.registerIcon("culinaryhorizons:stove_front");
        this.bottomTexture = register.registerIcon("culinaryhorizons:stove_bottom");
        this.bottomTexture_90 = register.registerIcon("culinaryhorizons:stove_bottom_90");
        this.bottomTexture_180 = register.registerIcon("culinaryhorizons:stove_bottom_180");
        this.bottomTexture_270 = register.registerIcon("culinaryhorizons:stove_bottom_270");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int facing = meta & 3;

        // Top
        if (side == 1) {
            return isLit ? topTextureLit : topTextureUnlit;
        }

        // Bottom
        if (side == 0) {
            switch(facing) {
                case 0: return bottomTexture;
                case 1: return bottomTexture_90;
                case 2: return bottomTexture_180;
                case 3: return bottomTexture_270;
            }
        }

        // Side 2 = north, 3 = south, 4 = west, 5 = east
        if ((facing == 0 && side == 3) || // South
            (facing == 1 && side == 4) || // West
            (facing == 2 && side == 2) || // North
            (facing == 3 && side == 5)) { // East
            return isLit ? frontTextureLit : frontTextureUnlit;
        }

        return sideTexture;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return isLit ? 14 : 0;
    }

    @Override
    public int getRenderType() {
        return 0; // Standard rendering for now
    }
}
