package net.bbsmc.server.world.entity.impl;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public class CraftFakePlayer extends CraftPlayer {
    public CraftFakePlayer(CraftServer server, ServerPlayer entity) {
        super(server, entity);
    }

    @Override
    public boolean isOp() {
        GameProfile profile = this.getHandle().getGameProfile();
        return profile.getId() != null && super.isOp();
    }
}
