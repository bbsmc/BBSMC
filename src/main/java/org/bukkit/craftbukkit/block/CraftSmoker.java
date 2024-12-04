package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Smoker;

public class CraftSmoker extends CraftFurnace<net.minecraft.world.level.block.entity.SmokerBlockEntity> implements Smoker {

    public CraftSmoker(World world, net.minecraft.world.level.block.entity.SmokerBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}