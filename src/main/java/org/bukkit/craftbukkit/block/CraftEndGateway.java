package org.bukkit.craftbukkit.block;

import java.util.Objects;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.EndGateway;

public class CraftEndGateway extends CraftBlockEntityState<net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity> implements EndGateway {

    public CraftEndGateway(World world, net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Location getExitLocation() {
        net.minecraft.core.BlockPos pos = this.getSnapshot().exitPortal;
        return pos == null ? null : new Location(this.isPlaced() ? this.getWorld() : null, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void setExitLocation(Location location) {
        if (location == null) {
            this.getSnapshot().exitPortal = null;
        } else if (!Objects.equals(location.getWorld(), this.isPlaced() ? this.getWorld() : null)) {
            throw new IllegalArgumentException("Cannot set exit location to different world");
        } else {
            this.getSnapshot().exitPortal = new net.minecraft.core.BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        }
    }

    @Override
    public boolean isExactTeleport() {
        return this.getSnapshot().exactTeleport;
    }

    @Override
    public void setExactTeleport(boolean exact) {
        this.getSnapshot().exactTeleport = exact;
    }

    @Override
    public long getAge() {
        return this.getSnapshot().age;
    }

    @Override
    public void setAge(long age) {
        this.getSnapshot().age = age;
    }

    @Override
    public void applyTo(net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity endGateway) {
        super.applyTo(endGateway);

        if (this.getSnapshot().exitPortal == null) {
            endGateway.exitPortal = null;
        }
    }
}