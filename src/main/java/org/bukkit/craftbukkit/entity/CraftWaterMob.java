package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.WaterMob;

public class CraftWaterMob extends CraftCreature implements WaterMob {

    public CraftWaterMob(CraftServer server, net.minecraft.world.entity.animal.WaterAnimal entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.WaterAnimal getHandle() {
        return (net.minecraft.world.entity.animal.WaterAnimal) entity;
    }

    @Override
    public String toString() {
        return "CraftWaterMob";
    }
}