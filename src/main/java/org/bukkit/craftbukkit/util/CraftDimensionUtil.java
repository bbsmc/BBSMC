package org.bukkit.craftbukkit.util;

import net.minecraft.resources.ResourceKey;

public class CraftDimensionUtil {

    private CraftDimensionUtil() {
    }

    public static ResourceKey<net.minecraft.world.level.Level> getMainDimensionKey(net.minecraft.world.level.Level world) {
        ResourceKey<net.minecraft.world.level.LevelDimension> typeKey = world.getTypeKey();
        if (typeKey == net.minecraft.world.level.LevelDimension.OVERWORLD) {
            return net.minecraft.world.level.Level.OVERWORLD;
        } else if (typeKey == net.minecraft.world.level.LevelDimension.NETHER) {
            return net.minecraft.world.level.Level.NETHER;
        } else if (typeKey == net.minecraft.world.level.LevelDimension.END) {
            return net.minecraft.world.level.Level.END;
        }

        return world.dimension();
    }
}