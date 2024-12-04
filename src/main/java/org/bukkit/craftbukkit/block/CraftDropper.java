package org.bukkit.craftbukkit.block;

import com.google.common.base.Preconditions;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dropper;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

public class CraftDropper extends CraftLootable<net.minecraft.world.level.block.entity.DropperBlockEntity> implements Dropper {

    public CraftDropper(World world, net.minecraft.world.level.block.entity.DropperBlockEntity tileEntity) {
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

    @Override
    public void drop() {
        ensureNoWorldGeneration();
        Block block = getBlock();
        if (block.getType() == Material.DROPPER) {
            CraftWorld world = (CraftWorld) this.getWorld();
            net.minecraft.world.level.block.DropperBlock drop = (net.minecraft.world.level.block.DropperBlock) Blocks.DROPPER;

            drop.dispenseFrom(world.getHandle(), this.getPosition());
        }
    }
}