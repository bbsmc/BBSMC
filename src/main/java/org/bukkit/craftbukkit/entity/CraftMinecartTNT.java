package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.ExplosiveMinecart;

public final class CraftMinecartTNT extends CraftMinecart implements ExplosiveMinecart {
    public CraftMinecartTNT(CraftServer server, net.minecraft.world.entity.vehicle.MinecartTNT entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftMinecartTNT";
    }

    @Override
    public EntityType getType() {
        return EntityType.MINECART_TNT;
    }
}