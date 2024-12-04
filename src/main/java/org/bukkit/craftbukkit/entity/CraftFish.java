package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Fish;

public class CraftFish extends CraftWaterMob implements Fish {

    public CraftFish(CraftServer server, net.minecraft.world.entity.animal.AbstractFish entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.AbstractFish getHandle() {
        return (net.minecraft.world.entity.animal.AbstractFish) entity;
    }

    @Override
    public String toString() {
        return "CraftFish";
    }
}