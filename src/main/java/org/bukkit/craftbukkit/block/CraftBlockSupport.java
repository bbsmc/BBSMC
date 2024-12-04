package org.bukkit.craftbukkit.block;

import org.bukkit.block.BlockSupport;

public final class CraftBlockSupport {

    private CraftBlockSupport() {
    }

    public static BlockSupport toBukkit(net.minecraft.world.level.block.SupportType support) {
        return switch (support) {
            case FULL -> BlockSupport.FULL;
            case CENTER -> BlockSupport.CENTER;
            case RIGID -> BlockSupport.RIGID;
            default -> throw new IllegalArgumentException("Unsupported net.minecraft.world.level.block.SupportType type: " + support + ". This is a bug.");
        };
    }

    public static net.minecraft.world.level.block.SupportType toNMS(BlockSupport support) {
        return switch (support) {
            case FULL -> net.minecraft.world.level.block.SupportType.FULL;
            case CENTER -> net.minecraft.world.level.block.SupportType.CENTER;
            case RIGID -> net.minecraft.world.level.block.SupportType.RIGID;
            default -> throw new IllegalArgumentException("Unsupported BlockSupport type: " + support + ". This is a bug.");
        };
    }
}