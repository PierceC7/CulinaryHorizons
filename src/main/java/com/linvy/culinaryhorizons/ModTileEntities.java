package com.linvy.culinaryhorizons;

import com.linvy.culinaryhorizons.TileEntity.FeastTileEntity;
import com.linvy.culinaryhorizons.TileEntity.StoveTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(FeastTileEntity.class, "culinaryhorizons:FeastTileEntity");
        GameRegistry.registerTileEntity(StoveTileEntity.class, "culinaryhorizons:StoveTileEntity");
    }

}
