package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Jigsaw;

public class CraftJigsaw extends CraftBlockEntityState<net.minecraft.world.level.block.entity.JigsawBlockEntity> implements Jigsaw {

    public CraftJigsaw(World world, net.minecraft.world.level.block.entity.JigsawBlockEntity tileEntity) {
        super(world, tileEntity);
    }
}