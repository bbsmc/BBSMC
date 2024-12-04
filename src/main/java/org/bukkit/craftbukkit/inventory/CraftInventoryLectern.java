package org.bukkit.craftbukkit.inventory;

import org.bukkit.block.Lectern;
import org.bukkit.inventory.LecternInventory;

public class CraftInventoryLectern extends CraftInventory implements LecternInventory {

    public net.minecraft.world.MenuProvider tile;

    public CraftInventoryLectern(net.minecraft.world.Container inventory) {
        super(inventory);
        if (inventory instanceof net.minecraft.world.level.block.entity.LecternBlockEntity.LecternInventory) {
            this.tile = ((net.minecraft.world.level.block.entity.LecternBlockEntity.LecternInventory) inventory).getLectern();
        }
    }

    @Override
    public Lectern getHolder() {
        return (Lectern) inventory.getOwner();
    }
}