package net.bbsmc.server.world.entity.impl;

import net.bbsmc.server.core.BukkitInjector;
import net.minecraft.world.entity.projectile.Projectile;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftProjectile;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class CraftCustomProjectile extends CraftProjectile {
    public String entityName;

    public CraftCustomProjectile(CraftServer server, Projectile entity) {
        super(server, entity);
        this.entityName = BukkitInjector.entityTypeMap.get(entity.getType());
        if (entityName == null) {
            entityName = entity.getName().getString();
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull EntityType getType() {
        var entityType = EntityType.fromName(this.entityName);
        return entityType != null ? entityType : EntityType.MOD_CUSTOM;
    }

    @Override
    public String toString() {
        return "CraftCustomProjectile";
    }
}
