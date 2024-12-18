package org.bukkit.craftbukkit.inventory;

import org.bukkit.Location;
import org.bukkit.block.DoubleChest;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryDoubleChest extends CraftInventory implements DoubleChestInventory {
    public net.minecraft.world.MenuProvider tile;
    private final CraftInventory left;
    private final CraftInventory right;

    public CraftInventoryDoubleChest(net.minecraft.world.level.block.ChestBlock.DoubleInventory block) {
        super(block.inventorylargechest);
        this.tile = block;
        this.left = new CraftInventory(block.inventorylargechest.container1);
        this.right = new CraftInventory(block.inventorylargechest.container2);
    }

    public CraftInventoryDoubleChest(net.minecraft.world.CompoundContainer largeChest) {
        super(largeChest);
        if (largeChest.container1 instanceof net.minecraft.world.CompoundContainer) {
            left = new CraftInventoryDoubleChest((net.minecraft.world.CompoundContainer) largeChest.container1);
        } else {
            left = new CraftInventory(largeChest.container1);
        }
        if (largeChest.container2 instanceof net.minecraft.world.CompoundContainer) {
            right = new CraftInventoryDoubleChest((net.minecraft.world.CompoundContainer) largeChest.container2);
        } else {
            right = new CraftInventory(largeChest.container2);
        }
    }

    @Override
    public Inventory getLeftSide() {
        return left;
    }

    @Override
    public Inventory getRightSide() {
        return right;
    }

    @Override
    public void setContents(ItemStack[] items) {
        if (getInventory().getContainerSize() < items.length) {
            throw new IllegalArgumentException("Invalid inventory size; expected " + getInventory().getContainerSize() + " or less");
        }
        ItemStack[] leftItems = new ItemStack[left.getSize()], rightItems = new ItemStack[right.getSize()];
        System.arraycopy(items, 0, leftItems, 0, Math.min(left.getSize(), items.length));
        left.setContents(leftItems);
        if (items.length >= left.getSize()) {
            System.arraycopy(items, left.getSize(), rightItems, 0, Math.min(right.getSize(), items.length - left.getSize()));
            right.setContents(rightItems);
        }
    }

    @Override
    public DoubleChest getHolder() {
        return new DoubleChest(this);
    }

    @Override
    public Location getLocation() {
        return getLeftSide().getLocation().add(getRightSide().getLocation()).multiply(0.5);
    }
}