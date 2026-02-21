package com.linvy.culinaryhorizons.client.model;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;
import com.gtnewhorizon.gtnhlib.client.model.baked.BakedModel;
import com.gtnewhorizon.gtnhlib.client.model.loading.ModelRegistry;
import com.gtnewhorizon.gtnhlib.client.model.state.BlockState;
import com.linvy.culinaryhorizons.block.FeastTileEntity;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class FeastBlockISBRH extends ModelISBRH {

    public static final FeastBlockISBRH INSTANCE = new FeastBlockISBRH();

    public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public int getRenderId() {
        return RENDER_ID;
    }

    @Override
    public BakedModel getModel(IBlockAccess world, Block block, int meta, int x, int y, int z) {
        int facing = meta & 3;
        int servings = 4; // default

        if (world != null) {
            TileEntity te = world.getTileEntity(x, y, z);

            if (te instanceof FeastTileEntity) {
                servings = ((FeastTileEntity) te).getServings();
            }
        }

        int feastMeta = servings * 4 + facing;

        BlockState state = new BlockState(block, feastMeta);
        return ModelRegistry.getBakedModel(state);
    }
}
