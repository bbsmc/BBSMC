package org.bukkit.craftbukkit.block;

import org.bukkit.Bukkit;
import org.bukkit.Nameable;
import org.bukkit.World;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

public abstract class CraftLootable<T extends net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity> extends CraftContainer<T> implements Nameable, Lootable {

    public CraftLootable(World world, T tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public void applyTo(T lootable) {
        super.applyTo(lootable);

        if (this.getSnapshot().lootTable == null) {
            lootable.setLootTable((net.minecraft.resources.ResourceLocation) null, 0L);
        }
    }

    @Override
    public LootTable getLootTable() {
        if (getSnapshot().lootTable == null) {
            return null;
        }

        net.minecraft.resources.ResourceLocation key = getSnapshot().lootTable;
        return Bukkit.getLootTable(CraftNamespacedKey.fromMinecraft(key));
    }

    @Override
    public void setLootTable(LootTable table) {
        setLootTable(table, getSeed());
    }

    @Override
    public long getSeed() {
        return getSnapshot().lootTableSeed;
    }

    @Override
    public void setSeed(long seed) {
        setLootTable(getLootTable(), seed);
    }

    private void setLootTable(LootTable table, long seed) {
        net.minecraft.resources.ResourceLocation key = (table == null) ? null : CraftNamespacedKey.toMinecraft(table.getKey());
        getSnapshot().setLootTable(key, seed);
    }
}