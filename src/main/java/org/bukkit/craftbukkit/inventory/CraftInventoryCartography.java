package org.bukkit.craftbukkit.inventory;

import org.bukkit.inventory.CartographyInventory;

public class CraftInventoryCartography extends CraftResultInventory implements CartographyInventory {

    public CraftInventoryCartography(net.minecraft.world.Container inventory, net.minecraft.world.Container resultInventory) {
        super(inventory, resultInventory);
    }
}