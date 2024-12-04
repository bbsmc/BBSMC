package org.bukkit.craftbukkit.block;

import org.bukkit.World;

public class CraftMovingPiston extends CraftBlockEntityState<net.minecraft.world.level.block.piston.PistonMovingBlockEntity> {

    public CraftMovingPiston(World world, net.minecraft.world.level.block.piston.PistonMovingBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}