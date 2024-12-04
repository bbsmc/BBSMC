package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.BlastFurnace;

public class CraftBlastFurnace extends CraftFurnace<net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity> implements BlastFurnace {

    public CraftBlastFurnace(World world, net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}