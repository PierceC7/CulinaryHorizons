package com.linvy.culinaryhorizons;

import static com.linvy.culinaryhorizons.CulinaryHorizons.MODID;

import com.gtnewhorizon.gtnhlib.client.model.loading.ModelRegistry;
import com.linvy.culinaryhorizons.client.model.FeastBlockISBRH;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ModelRegistry.registerModid(MODID);
        RenderingRegistry.registerBlockHandler(FeastBlockISBRH.INSTANCE);
    }

}
