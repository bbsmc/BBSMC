package net.bbsmc.server.world.entity.impl;

import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftThrowableProjectile;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class CraftCustomThrowableProjectile extends CraftThrowableProjectile {
    public CraftCustomThrowableProjectile(CraftServer server, ThrowableItemProjectile entity) {
        super(server, entity);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.MOD_CUSTOM;
    }

    @Override
    public String toString() {
        return "CraftCustomThrowableProjectile";
    }
}
