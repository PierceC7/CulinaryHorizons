package com.linvy.culinaryhorizons.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PopsicleItem extends FoodConsumableItem {

    public PopsicleItem(int healAmount, float saturationModifier) {
        super(healAmount, saturationModifier);
    }

    @Override
    public void affectConsumer(ItemStack stack, World world, EntityLivingBase consumer) {
        super.affectConsumer(stack, world, consumer);
        consumer.extinguish();
    }
}
