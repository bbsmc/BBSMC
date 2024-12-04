package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.DaylightDetector;

public class CraftDaylightDetector extends CraftBlockEntityState<net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity> implements DaylightDetector {

    public CraftDaylightDetector(World world, net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}