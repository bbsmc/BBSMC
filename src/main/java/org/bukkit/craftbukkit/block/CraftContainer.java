package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.Container;
import org.bukkit.craftbukkit.util.CraftChatMessage;

public abstract class CraftContainer<T extends net.minecraft.world.level.block.entity.BaseContainerBlockEntity> extends CraftBlockEntityState<T> implements Container {

    public CraftContainer(World world, T tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public boolean isLocked() {
        return !this.getSnapshot().lockKey.key.isEmpty();
    }

    @Override
    public String getLock() {
        return this.getSnapshot().lockKey.key;
    }

    @Override
    public void setLock(String key) {
        this.getSnapshot().lockKey = (key == null) ? net.minecraft.world.LockCode.NO_LOCK : new net.minecraft.world.LockCode(key);
    }

    @Override
    public String getCustomName() {
        T container = this.getSnapshot();
        return ((T) container).getName() != null ? CraftChatMessage.fromComponent(container.getCustomName()) : null;
    }

    @Override
    public void setCustomName(String name) {
        this.getSnapshot().setCustomName(CraftChatMessage.fromStringOrNull(name));
    }

    @Override
    public void applyTo(T container) {
        super.applyTo(container);

        if (this.getSnapshot().getName() == null) {
            container.setCustomName(null);
        }
    }
}