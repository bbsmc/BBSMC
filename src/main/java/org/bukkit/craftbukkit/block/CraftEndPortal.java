package org.bukkit.craftbukkit.block;

import org.bukkit.World;

public class CraftEndPortal extends CraftBlockEntityState<net.minecraft.world.level.block.entity.TheEndPortalBlockEntity> {

    public CraftEndPortal(World world, net.minecraft.world.level.block.entity.TheEndPortalBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}