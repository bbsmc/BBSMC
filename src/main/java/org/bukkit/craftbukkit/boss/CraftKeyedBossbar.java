package org.bukkit.craftbukkit.boss;

import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;

public class CraftKeyedBossbar extends CraftBossBar implements KeyedBossBar {

    public CraftKeyedBossbar(net.minecraft.server.bossevents.CustomBossEvent bossBattleCustom) {
        super(bossBattleCustom);
    }

    @Override
    public NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(getHandle().getTextId());
    }

    @Override
    public net.minecraft.server.bossevents.CustomBossEvent getHandle() {
        return (net.minecraft.server.bossevents.CustomBossEvent) super.getHandle();
    }
}