package com.linvy.culinaryhorizons.effect;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class ComfortEffect extends Potion {

    public ComfortEffect(int id) {
        super(id, false, 0);
        this.setPotionName("effect.culinaryhorizons.comfort");
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (entity.isPotionActive(Potion.regeneration)) {
            return;
        }

        if (entity instanceof EntityPlayer player) {
            if (player.getFoodStats()
                .getSaturationLevel() > 0.0) {
                return;
            }
        }

        if (entity.getHealth() < entity.getMaxHealth()) {
            entity.heal(1.0F);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 80 == 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(
            new ResourceLocation("culinaryhorizons", "textures/gui/potion_effects/comfort.png")
        );
        return super.getStatusIconIndex();
    }

    @Override
    public boolean hasStatusIcon() {
        return true;
    }
}
