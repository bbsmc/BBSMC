package net.bbsmc.server.world.entity.impl;

import net.bbsmc.server.core.BukkitInjector;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class CraftCustomEntity extends CraftEntity {
    private String entityName;

    public CraftCustomEntity(CraftServer server, Entity entity) {
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
        return entityType != null ? entityType : EntityType.UNKNOWN;
    }

    @Override
    public String toString() {
        return "CraftCustomEntity";
    }
}
