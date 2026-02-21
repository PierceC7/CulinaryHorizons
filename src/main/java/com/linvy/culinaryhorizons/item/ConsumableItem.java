package com.linvy.culinaryhorizons.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.github.bsideup.jabel.Desugar;

public class ConsumableItem extends Item {

    private boolean hasCustomTooltip;
    private int useDuration;
    private EnumAction useAction;
    private Item containerItem;
    private List<EffectEntry> effects = new ArrayList<>();

    @Desugar
    private record EffectEntry(Potion potion, int duration, int amplifier, float probability) {}

    public ConsumableItem() {
        this.hasCustomTooltip = false;
        this.useDuration = 32;
        this.useAction = EnumAction.eat;
        this.containerItem = null;
    }

    public ConsumableItem setHasCustomTooltip(boolean hasCustomTooltip) {
        this.hasCustomTooltip = hasCustomTooltip;
        return this;
    }

    public ConsumableItem setUseDuration(int useDuration) {
        this.useDuration = useDuration;
        return this;
    }

    public ConsumableItem setUseAction(EnumAction useAction) {
        this.useAction = useAction;
        return this;
    }

    public ConsumableItem setContainerItem(Item containerItem) {
        this.containerItem = containerItem;
        return this;
    }

    public ConsumableItem addEffect(Potion potion, int duration, int amplifier, float probability) {
        effects.add(new EffectEntry(potion, duration, amplifier, probability));
        return this;
    }

    public ConsumableItem addEffect(Potion potion, int duration, int amplifier) {
        return addEffect(potion, duration, amplifier, 1.0F);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return this.useDuration;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return this.useAction;
    }

    @Override
    public boolean hasContainerItem() {
        return containerItem != null;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return containerItem != null ? new ItemStack(containerItem) : null;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            this.affectConsumer(stack, world, player);
        }

        ItemStack containerStack = this.getContainerItem(stack);
        player.addStat(StatList.objectUseStats[Item.getIdFromItem(this)], 1);
        if (--stack.stackSize <= 0) {
            return containerStack;
        } else {
            if (containerStack != null) {
                if (!player.inventory.addItemStackToInventory(containerStack)) {
                    player.dropPlayerItemWithRandomChoice(containerStack, false);
                }
            }
            return stack;
        }
    }

    public void affectConsumer(ItemStack stack, World world, EntityLivingBase consumer) {
        for (EffectEntry effect : effects) {
            if (world.rand.nextFloat() < effect.probability) {
                consumer.addPotionEffect(new PotionEffect(effect.potion.id, effect.duration, effect.amplifier));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
        if (this.hasCustomTooltip) {
            list.add(StatCollector.translateToLocal("tooltip." + this.getUnlocalizedName()));
        }
    }
}
