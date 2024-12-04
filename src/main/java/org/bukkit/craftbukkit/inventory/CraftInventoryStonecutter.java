package org.bukkit.craftbukkit.inventory;

import org.bukkit.inventory.StonecutterInventory;

public class CraftInventoryStonecutter extends CraftResultInventory implements StonecutterInventory {

    public CraftInventoryStonecutter(net.minecraft.world.Container inventory, net.minecraft.world.Container resultInventory) {
        super(inventory, resultInventory);
    }
}