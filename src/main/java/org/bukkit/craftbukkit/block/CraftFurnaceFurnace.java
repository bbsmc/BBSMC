package org.bukkit.craftbukkit.block;

import org.bukkit.World;

public class CraftFurnaceFurnace extends CraftFurnace<net.minecraft.world.level.block.entity.FurnaceBlockEntity> {

    public CraftFurnaceFurnace(World world, net.minecraft.world.level.block.entity.FurnaceBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}