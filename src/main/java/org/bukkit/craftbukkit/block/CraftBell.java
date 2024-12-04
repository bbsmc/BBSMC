package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Bell;

public class CraftBell extends CraftBlockEntityState<net.minecraft.world.level.block.entity.BellBlockEntity> implements Bell {

    public CraftBell(World world, net.minecraft.world.level.block.entity.BellBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}