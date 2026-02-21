package com.linvy.culinaryhorizons.effect;

import java.lang.reflect.Field;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;

public class NourishmentEffect extends Potion {

    public NourishmentEffect(int id) {
        super(id, false, 0);
        this.setPotionName("effect.culinaryhorizons.nourishment");
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (!entity.worldObj.isRemote && entity instanceof EntityPlayer player) {
            FoodStats foodStats = player.getFoodStats();
            boolean isPlayerHealingWithHunger = player.worldObj.getGameRules()
                .getGameRuleBooleanValue("naturalRegeneration") && (player.getHealth() < player.getMaxHealth())
                && (foodStats.getFoodLevel() > 18);
            if (!isPlayerHealingWithHunger) {
                try {
                    Field exhaustionField = FoodStats.class.getDeclaredField("foodExhaustionLevel");
                    exhaustionField.setAccessible(true);

                    float exhaustion = exhaustionField.getFloat(player.getFoodStats());
                    float reduction = Math.min(exhaustion, 4.0F);

                    if (exhaustion > 0.0F) {
                        player.getFoodStats()
                            .addExhaustion(-reduction);
                    }
                } catch (Exception e) {
                    System.out.println("reflection failed");
                }
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
