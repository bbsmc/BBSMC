package org.bukkit.craftbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Spellcaster;
import org.bukkit.entity.Spellcaster.Spell;

public class CraftSpellcaster extends CraftIllager implements Spellcaster {

    public CraftSpellcaster(CraftServer server, net.minecraft.world.entity.monster.SpellcasterIllager entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.monster.SpellcasterIllager getHandle() {
        return (net.minecraft.world.entity.monster.SpellcasterIllager) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftSpellcaster";
    }

    @Override
    public Spell getSpell() {
        return toBukkitSpell(getHandle().getCurrentSpell());
    }

    @Override
    public void setSpell(Spell spell) {
        Preconditions.checkArgument(spell != null, "Use Spell.NONE");

        getHandle().setIsCastingSpell(toNMSSpell(spell));
    }

    public static Spell toBukkitSpell(net.minecraft.world.entity.monster.SpellcasterIllager.Spell spell) {
        return Spell.valueOf(spell.name());
    }

    public static net.minecraft.world.entity.monster.SpellcasterIllager.Spell toNMSSpell(Spell spell) {
        return net.minecraft.world.entity.monster.SpellcasterIllager.Spell.byId(spell.ordinal());
    }
}