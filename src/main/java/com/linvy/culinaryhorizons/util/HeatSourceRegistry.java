package com.linvy.culinaryhorizons.util;

import com.linvy.culinaryhorizons.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import java.util.HashSet;
import java.util.Set;

public class HeatSourceRegistry {

    private static final Set<Block> HEAT_SOURCES = new HashSet<>();

    public static void registerHeatSource(Block block) {
        HEAT_SOURCES.add(block);
    }

    public static boolean isHeatSource(Block block) {
        return HEAT_SOURCES.contains(block);
    }

    public static boolean isHeatSource(World world, int x, int y, int z) {
        return isHeatSource(world.getBlock(x, y, z));
    }

    public static boolean isHeatSourceBelow(World world, int x, int y, int z) {
        return isHeatSource(world, x, y - 1, z);
    }

    public static void init() {
        registerHeatSource(Blocks.fire);
        registerHeatSource(Blocks.lava);
        registerHeatSource(Blocks.flowing_lava);
        registerHeatSource(Blocks.lit_furnace);
        registerHeatSource(ModBlocks.STOVE_LIT.get());
    }
}
