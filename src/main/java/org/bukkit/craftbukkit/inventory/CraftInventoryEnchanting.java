package org.bukkit.craftbukkit.inventory;

import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryEnchanting extends CraftInventory implements EnchantingInventory {
    public CraftInventoryEnchanting(net.minecraft.world.Container inventory) {
        super(inventory);
    }

    @Override
    public void setItem(ItemStack item) {
        setItem(0, item);
    }

    @Override
    public ItemStack getItem() {
        return getItem(0);
    }

    @Override
    public void setSecondary(ItemStack item) {
        setItem(1, item);
    }

    @Override
    public ItemStack getSecondary() {
        return getItem(1);
    }
}