package net.bbsmc.server.world.entity.impl;

import net.bbsmc.server.core.BukkitInjector;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.jetbrains.annotations.NotNull;

public class CraftCustomHorse extends CraftAbstractHorse {

    public String entityName;

    public CraftCustomHorse(CraftServer server, AbstractHorse entity) {
        super(server, entity);
        this.entityName = BukkitInjector.entityTypeMap.get(entity.getType());
        if (entityName == null) {
            entityName = entity.getName().getString();
        }
    }

    @Override
    public String toString() {
        return "CraftCustomHorse";
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull EntityType getType() {
        var entityType = EntityType.fromName(this.entityName);
        return entityType != null ? entityType : EntityType.MOD_CUSTOM;
    }

    @Override
    @SuppressWarnings("deprecation")
    public Horse.Variant getVariant() {
        return Horse.Variant.MOD_CUSTOM;
    }
}
