package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Conduit;

public class CraftConduit extends CraftBlockEntityState<net.minecraft.world.level.block.entity.ConduitBlockEntity> implements Conduit {

    public CraftConduit(World world, net.minecraft.world.level.block.entity.ConduitBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}