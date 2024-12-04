package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;

public class CraftEvoker extends CraftSpellcaster implements Evoker {

    public CraftEvoker(CraftServer server, net.minecraft.world.entity.monster.Evoker entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.monster.Evoker getHandle() {
        return (net.minecraft.world.entity.monster.Evoker) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftEvoker";
    }

    @Override
    public EntityType getType() {
        return EntityType.EVOKER;
    }

    @Override
    public Evoker.Spell getCurrentSpell() {
        return Evoker.Spell.values()[getHandle().getCurrentSpell().ordinal()];
    }

    @Override
    public void setCurrentSpell(Evoker.Spell spell) {
        getHandle().setIsCastingSpell(spell == null ? net.minecraft.world.entity.monster.SpellcasterIllager.Spell.NONE : net.minecraft.world.entity.monster.SpellcasterIllager.Spell.byId(spell.ordinal()));
    }
}