package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;

public class CraftBlockEntityState<T extends net.minecraft.world.level.block.entity.BlockEntity> extends CraftBlockState implements TileState {

    private final T tileEntity;
    private final T snapshot;

    public CraftBlockEntityState(World world, T tileEntity) {
        super(world, tileEntity.getBlockPos(), tileEntity.getBlockState());

        this.tileEntity = tileEntity;

        // copy tile entity data:
        this.snapshot = this.createSnapshot(tileEntity);
        this.load(snapshot);
    }

    public void refreshSnapshot() {
        this.load(tileEntity);
    }

    private T createSnapshot(T tileEntity) {
        if (tileEntity == null) {
            return null;
        }

        net.minecraft.nbt.CompoundTag nbtTagCompound = tileEntity.saveWithFullMetadata();
        T snapshot = (T) net.minecraft.world.level.block.entity.BlockEntity.loadStatic(getPosition(), getHandle(), nbtTagCompound);

        return snapshot;
    }

    // copies the net.minecraft.world.level.block.entity.BlockEntity-specific data, retains the position
    private void copyData(T from, T to) {
        net.minecraft.nbt.CompoundTag nbtTagCompound = from.saveWithFullMetadata();
        to.load(nbtTagCompound);
    }

    // gets the wrapped net.minecraft.world.level.block.entity.BlockEntity
    protected T getTileEntity() {
        return tileEntity;
    }

    // gets the cloned net.minecraft.world.level.block.entity.BlockEntity which is used to store the captured data
    protected T getSnapshot() {
        return snapshot;
    }

    // gets the current net.minecraft.world.level.block.entity.BlockEntity from the world at this position
    protected net.minecraft.world.level.block.entity.BlockEntity getTileEntityFromWorld() {
        requirePlaced();

        return getWorldHandle().getBlockEntity(this.getPosition());
    }

    // gets the NBT data of the net.minecraft.world.level.block.entity.BlockEntity represented by this block state
    public net.minecraft.nbt.CompoundTag getSnapshotNBT() {
        // update snapshot
        applyTo(snapshot);

        return snapshot.saveWithFullMetadata();
    }

    // copies the data of the given tile entity to this block state
    protected void load(T tileEntity) {
        if (tileEntity != null && tileEntity != snapshot) {
            copyData(tileEntity, snapshot);
        }
    }

    // applies the net.minecraft.world.level.block.entity.BlockEntity data of this block state to the given net.minecraft.world.level.block.entity.BlockEntity
    protected void applyTo(T tileEntity) {
        if (tileEntity != null && tileEntity != snapshot) {
            copyData(snapshot, tileEntity);
        }
    }

    protected boolean isApplicable(net.minecraft.world.level.block.entity.BlockEntity tileEntity) {
        return tileEntity != null && this.tileEntity.getClass() == tileEntity.getClass();
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        boolean result = super.update(force, applyPhysics);

        if (result && this.isPlaced()) {
            net.minecraft.world.level.block.entity.BlockEntity tile = getTileEntityFromWorld();

            if (isApplicable(tile)) {
                applyTo((T) tile);
                tile.setChanged();
            }
        }

        return result;
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return this.getSnapshot().persistentDataContainer;
    }
}