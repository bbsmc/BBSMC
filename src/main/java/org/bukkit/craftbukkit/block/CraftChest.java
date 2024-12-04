package org.bukkit.craftbukkit.block;

import net.minecraft.world.level.block.Blocks;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryDoubleChest;
import org.bukkit.inventory.Inventory;

public class CraftChest extends CraftLootable<net.minecraft.world.level.block.entity.ChestBlockEntity> implements Chest {

    public CraftChest(World world, net.minecraft.world.level.block.entity.ChestBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventory(this.getSnapshot());
    }

    @Override
    public Inventory getBlockInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventory(this.getTileEntity());
    }

    @Override
    public Inventory getInventory() {
        CraftInventory inventory = (CraftInventory) this.getBlockInventory();
        if (!isPlaced() || isWorldGeneration()) {
            return inventory;
        }

        // The logic here is basically identical to the logic in net.minecraft.world.level.block.ChestBlock.interact
        CraftWorld world = (CraftWorld) this.getWorld();

        net.minecraft.world.level.block.ChestBlock blockChest = (net.minecraft.world.level.block.ChestBlock) (this.getType() == Material.CHEST ? Blocks.CHEST : Blocks.TRAPPED_CHEST);
        net.minecraft.world.MenuProvider nms = blockChest.getMenuProvider(data, world.getHandle(), this.getPosition(), true);

        if (nms instanceof net.minecraft.world.level.block.ChestBlock.DoubleInventory) {
            inventory = new CraftInventoryDoubleChest((net.minecraft.world.level.block.ChestBlock.DoubleInventory) nms);
        }
        return inventory;
    }

    @Override
    public void open() {
        requirePlaced();
        if (!getTileEntity().openersCounter.opened && getWorldHandle() instanceof net.minecraft.world.level.World) {
            net.minecraft.world.level.block.state.BlockState block = getTileEntity().getBlockState();
            int openCount = getTileEntity().openersCounter.getOpenerCount();

            getTileEntity().openersCounter.onAPIOpen((net.minecraft.world.level.World) getWorldHandle(), getPosition(), block);
            getTileEntity().openersCounter.openerAPICountChanged((net.minecraft.world.level.World) getWorldHandle(), getPosition(), block, openCount, openCount + 1);
        }
        getTileEntity().openersCounter.opened = true;
    }

    @Override
    public void close() {
        requirePlaced();
        if (getTileEntity().openersCounter.opened && getWorldHandle() instanceof net.minecraft.world.level.World) {
            net.minecraft.world.level.block.state.BlockState block = getTileEntity().getBlockState();
            int openCount = getTileEntity().openersCounter.getOpenerCount();

            getTileEntity().openersCounter.onAPIClose((net.minecraft.world.level.World) getWorldHandle(), getPosition(), block);
            getTileEntity().openersCounter.openerAPICountChanged((net.minecraft.world.level.World) getWorldHandle(), getPosition(), block, openCount, 0);
        }
        getTileEntity().openersCounter.opened = false;
    }
}