package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;

public class CraftBlaze extends CraftMonster implements Blaze {
    public CraftBlaze(CraftServer server, net.minecraft.world.entity.monster.Blaze entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.monster.Blaze getHandle() {
        return (net.minecraft.world.entity.monster.Blaze) entity;
    }

    @Override
    public String toString() {
        return "CraftBlaze";
    }

    @Override
    public EntityType getType() {
        return EntityType.BLAZE;
    }
}