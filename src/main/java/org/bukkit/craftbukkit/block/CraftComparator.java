package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Comparator;

public class CraftComparator extends CraftBlockEntityState<net.minecraft.world.level.block.entity.ComparatorBlockEntity> implements Comparator {

    public CraftComparator(World world, net.minecraft.world.level.block.entity.ComparatorBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}