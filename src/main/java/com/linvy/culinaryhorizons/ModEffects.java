package com.linvy.culinaryhorizons;

import net.minecraft.potion.Potion;

import com.linvy.culinaryhorizons.effect.ComfortEffect;
import com.linvy.culinaryhorizons.effect.NourishmentEffect;

public class ModEffects {

    public static Potion COMFORT;
    public static Potion NOURISHMENT;

    private static int findFirstEmptyPotionSlot() {
        for (int i = 24; i < Potion.potionTypes.length; i++) {
            if (Potion.potionTypes[i] == null) {
                return i;
            }
        }
        throw new RuntimeException("No potion IDs available");
    }

    public static void init() {
        COMFORT = new ComfortEffect(findFirstEmptyPotionSlot());
        NOURISHMENT = new NourishmentEffect(findFirstEmptyPotionSlot());
    }
}
