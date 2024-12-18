package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Hopper;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

public class CraftHopper extends CraftLootable<net.minecraft.world.level.block.entity.HopperBlockEntity> implements Hopper {

    public CraftHopper(World world, net.minecraft.world.level.block.entity.HopperBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventory(this.getSnapshot());
    }

    @Override
    public Inventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventory(this.getTileEntity());
    }
}