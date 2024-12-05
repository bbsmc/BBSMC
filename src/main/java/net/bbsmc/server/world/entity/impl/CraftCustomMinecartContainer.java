package net.bbsmc.server.world.entity.impl;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftMinecartContainer;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class CraftCustomMinecartContainer extends CraftMinecartContainer {
    public CraftCustomMinecartContainer(CraftServer server, AbstractMinecart entity) {
        super(server, entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MINECART;
    }

    @Override
    public String toString() {
        return "CraftCustomMinecartContainer";
    }
}
