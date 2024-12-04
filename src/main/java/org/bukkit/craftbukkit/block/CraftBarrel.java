package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Barrel;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.inventory.Inventory;

public class CraftBarrel extends CraftLootable<net.minecraft.world.level.block.entity.BarrelBlockEntity> implements Barrel {

    public CraftBarrel(World world, net.minecraft.world.level.block.entity.BarrelBlockEntity tileEntity) {
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
    public void open() {
        requirePlaced();
        if (!getTileEntity().openersCounter.opened) {
            net.minecraft.world.level.block.state.BlockState blockData = getTileEntity().getBlockState();
            boolean open = blockData.getValue(net.minecraft.world.level.block.BarrelBlock.OPEN);

            if (!open) {
                getTileEntity().updateBlockState(blockData, true);
                if (getWorldHandle() instanceof net.minecraft.world.level.World) {
                    getTileEntity().playSound(blockData, net.minecraft.sounds.SoundEvents.BARREL_OPEN);
                }
            }
        }
        getTileEntity().openersCounter.opened = true;
    }

    @Override
    public void close() {
        requirePlaced();
        if (getTileEntity().openersCounter.opened) {
            net.minecraft.world.level.block.state.BlockState blockData = getTileEntity().getBlockState();
            getTileEntity().updateBlockState(blockData, false);
            if (getWorldHandle() instanceof net.minecraft.world.level.World) {
                getTileEntity().playSound(blockData, net.minecraft.sounds.SoundEvents.BARREL_CLOSE);
            }
        }
        getTileEntity().openersCounter.opened = false;
    }
}