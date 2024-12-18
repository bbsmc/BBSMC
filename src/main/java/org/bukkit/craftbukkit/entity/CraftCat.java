package org.bukkit.craftbukkit.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.CatVariant;
import org.bukkit.DyeColor;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;
import org.bukkit.entity.EntityType;

public class CraftCat extends CraftTameableAnimal implements Cat {

    public CraftCat(CraftServer server, net.minecraft.world.entity.animal.Cat entity) {
        super(server, entity);
    }

    @Override
    public net.minecraft.world.entity.animal.Cat getHandle() {
        return (net.minecraft.world.entity.animal.Cat) super.getHandle();
    }

    @Override
    public EntityType getType() {
        return EntityType.CAT;
    }

    @Override
    public String toString() {
        return "CraftCat";
    }

    @Override
    public Type getCatType() {
        return Type.values()[net.minecraft.core.Registry.CAT_VARIANT.getId(getHandle().getCatVariant())];
    }

    @Override
    public void setCatType(Type type) {
        Preconditions.checkArgument(type != null, "Cannot have null Type");

        getHandle().setCatVariant(net.minecraft.core.Registry.CAT_VARIANT.byId(type.ordinal()));
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.getByWoolData((byte) getHandle().getCollarColor().getId());
    }

    @Override
    public void setCollarColor(DyeColor color) {
        getHandle().setCollarColor(net.minecraft.world.item.DyeColor.byId(color.getWoolData()));
    }
}