package org.bukkit.craftbukkit.entity;

import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;

public class CraftEnderCrystal extends CraftEntity implements EnderCrystal {
    public CraftEnderCrystal(CraftServer server, net.minecraft.world.entity.boss.enderdragon.EndCrystal entity) {
        super(server, entity);
    }

    @Override
    public boolean isShowingBottom() {
        return getHandle().showsBottom();
    }

    @Override
    public void setShowingBottom(boolean showing) {
        getHandle().setShowBottom(showing);
    }

    @Override
    public Location getBeamTarget() {
        net.minecraft.core.BlockPos pos = getHandle().getBeamTarget();
        return pos == null ? null : new Location(getWorld(), pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void setBeamTarget(Location location) {
        if (location == null) {
            getHandle().setBeamTarget((net.minecraft.core.BlockPos) null);
        } else if (location.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot set beam target location to different world");
        } else {
            getHandle().setBeamTarget(new net.minecraft.core.BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        }
    }

    @Override
    public net.minecraft.world.entity.boss.enderdragon.EndCrystal getHandle() {
        return (net.minecraft.world.entity.boss.enderdragon.EndCrystal) entity;
    }

    @Override
    public String toString() {
        return "CraftEnderCrystal";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDER_CRYSTAL;
    }
}