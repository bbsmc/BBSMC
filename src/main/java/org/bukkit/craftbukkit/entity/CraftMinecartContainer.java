package org.bukkit.craftbukkit.entity;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

public abstract class CraftMinecartContainer extends CraftMinecart implements Lootable {

    public CraftMinecartContainer(CraftServer server, net.minecraft.world.entity.vehicle.AbstractMinecart entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.vehicle.AbstractMinecartContainer getHandle() {
        return (net.minecraft.world.entity.vehicle.AbstractMinecartContainer) entity;
    }

    @Override
    public void setLootTable(LootTable table) {
        setLootTable(table, getSeed());
    }

    @Override
    public LootTable getLootTable() {
        net.minecraft.resources.ResourceLocation nmsTable = getHandle().lootTable;
        if (nmsTable == null) {
            return null; // return empty loot table?
        }

        NamespacedKey key = CraftNamespacedKey.fromMinecraft(nmsTable);
        return Bukkit.getLootTable(key);
    }

    @Override
    public void setSeed(long seed) {
        setLootTable(getLootTable(), seed);
    }

    @Override
    public long getSeed() {
        return getHandle().lootTableSeed;
    }

    private void setLootTable(LootTable table, long seed) {
        net.minecraft.resources.ResourceLocation newKey = (table == null) ? null : CraftNamespacedKey.toMinecraft(table.getKey());
        getHandle().setLootTable(newKey, seed);
    }
}