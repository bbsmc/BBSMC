package org.bukkit.craftbukkit.entity;

import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.SizedFireball;
import org.bukkit.inventory.ItemStack;

public class CraftSizedFireball extends CraftFireball implements SizedFireball {

    public CraftSizedFireball(CraftServer server, net.minecraft.world.entity.projectile.Fireball entity) {
        super(server, entity);
    }

    @Override
    public ItemStack getDisplayItem() {
        if (getHandle().getItemRaw().isEmpty()) {
            return new ItemStack(Material.FIRE_CHARGE);
        } else {
            return CraftItemStack.asBukkitCopy(getHandle().getItemRaw());
        }
    }

    @Override
    public void setDisplayItem(ItemStack item) {
        getHandle().setItem(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public net.minecraft.world.entity.projectile.Fireball getHandle() {
        return (net.minecraft.world.entity.projectile.Fireball) entity;
    }
}