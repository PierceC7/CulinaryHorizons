package com.linvy.culinaryhorizons.util;

import com.linvy.culinaryhorizons.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import java.util.HashSet;
import java.util.Set;

public class HeatSourceRegistry {

    private static final Set<Block> HEAT_SOURCES = new HashSet<>();
    private static final Set<Block> TRAY_HEAT_SOURCES = new HashSet<>();
    private static final Set<Block> HEAT_CONDUCTORS = new HashSet<>();

    public static void registerHeatSource(Block block) {
        HEAT_SOURCES.add(block);
    }

    public static void registerTrayHeatSource(Block block) {
        TRAY_HEAT_SOURCES.add(block);
        HEAT_SOURCES.add(block);
    }

    public static boolean isHeatSource(Block block) {
        return HEAT_SOURCES.contains(block);
    }

    public static boolean isTrayHeatSource(Block block) {
        return TRAY_HEAT_SOURCES.contains(block);
    }

    public static void registerHeatConductor(Block block) {
        HEAT_CONDUCTORS.add(block);
    }

    public static boolean isHeatConductor(Block block) {
        return HEAT_CONDUCTORS.contains(block);
    }

    public static boolean isHeatSource(World world, int x, int y, int z) {
        return isHeatSource(world.getBlock(x, y, z));
    }

    public static boolean isTrayHeatSource(World world, int x, int y, int z) {
        return isTrayHeatSource(world.getBlock(x, y, z));
    }

    public static boolean isHeatConductor(World world, int x, int y, int z) {
        return isHeatConductor(world.getBlock(x, y, z));
    }

    public static boolean isHeatSourceBelow(World world, int x, int y, int z) {
        Block blockBelow = world.getBlock(x, y - 1, z);
        if (isHeatSource(blockBelow)) {
            return true;
        }

        if (isHeatConductor(blockBelow)) {
            Block blockTwoBelow = world.getBlock(x, y - 2, z);
            return isHeatSource(blockTwoBelow);
        }

        return false;
    }

    public static boolean isTrayHeatSourceBelow(World world, int x, int y, int z) {
        return isTrayHeatSource(world, x, y - 1, z);
    }

    public static void init() {
        registerTrayHeatSource(Blocks.fire);
        registerTrayHeatSource(Blocks.lava);
        registerTrayHeatSource(Blocks.flowing_lava);

        registerHeatSource(Blocks.lit_furnace);
        registerHeatSource(ModBlocks.STOVE_LIT.get());

        registerHeatConductor(Blocks.hopper);
    }
}
