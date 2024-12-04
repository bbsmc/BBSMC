package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Golem;

public class CraftGolem extends CraftCreature implements Golem {
    public CraftGolem(CraftServer server, net.minecraft.world.entity.animal.AbstractGolem entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.AbstractGolem getHandle() {
        return (net.minecraft.world.entity.animal.AbstractGolem) entity;
    }

    @Override
    public String toString() {
        return "CraftGolem";
    }
}