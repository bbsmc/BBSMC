package net.bbsmc.server.world.entity.impl;

import net.bbsmc.server.core.BukkitInjector;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftAbstractSkeleton;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.jetbrains.annotations.NotNull;

public class CraftCustomAbstractSkeleton extends CraftAbstractSkeleton {

    public String entityName;

    public CraftCustomAbstractSkeleton(CraftServer server, AbstractSkeleton entity) {
        super(server, entity);
        this.entityName = BukkitInjector.entityTypeMap.get(entity.getType());
        if (entityName == null) {
            entityName = entity.getName().getString();
        }
    }

    @Override
    public String toString() {
        return "CraftCustomAbstractSkeleton";
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull EntityType getType() {
        var entityType = EntityType.fromName(this.entityName);
        return entityType != null ? entityType : EntityType.MOD_CUSTOM;
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull Skeleton.SkeletonType getSkeletonType() {
        return Skeleton.SkeletonType.MOD_CUSTOM;
    }
}
