package net.bbsmc.server.world.entity.impl;

import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftChestedHorse;
import org.bukkit.entity.Horse;
import org.jetbrains.annotations.NotNull;

public class CraftCustomChestedHorse extends CraftChestedHorse {
    public CraftCustomChestedHorse(CraftServer server, AbstractChestedHorse entity) {
        super(server, entity);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull Horse.Variant getVariant() {
        return Horse.Variant.MOD_CUSTOM;
    }

    @Override
    public String toString() {
        return "CraftCustomChestedHorse";
    }
}
