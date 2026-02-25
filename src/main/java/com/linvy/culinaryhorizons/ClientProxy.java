package com.linvy.culinaryhorizons;

import static com.linvy.culinaryhorizons.CulinaryHorizons.MODID;

import com.gtnewhorizon.gtnhlib.client.model.loading.ModelRegistry;
import com.linvy.culinaryhorizons.TileEntity.StoveTileEntity;
import com.linvy.culinaryhorizons.client.model.FeastBlockISBRH;

import com.linvy.culinaryhorizons.client.renderer.StoveTESR;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ModelRegistry.registerModid(MODID);
        RenderingRegistry.registerBlockHandler(FeastBlockISBRH.INSTANCE);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ClientRegistry.bindTileEntitySpecialRenderer(StoveTileEntity.class, new StoveTESR());
    }

}
