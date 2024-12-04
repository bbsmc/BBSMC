package org.bukkit.craftbukkit.inventory;

import org.bukkit.inventory.GrindstoneInventory;

public class CraftInventoryGrindstone extends CraftResultInventory implements GrindstoneInventory {

    public CraftInventoryGrindstone(net.minecraft.world.Container inventory, net.minecraft.world.Container resultInventory) {
        super(inventory, resultInventory);
    }
}