package org.bukkit.craftbukkit.inventory;

import net.minecraft.server.MinecraftServer;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;
import java.util.Map;

public class RecipeIterator implements Iterator<Recipe> {
    private final Iterator<Map.Entry<net.minecraft.world.item.crafting.RecipeType<?>, Map<net.minecraft.resources.ResourceLocation, net.minecraft.world.item.crafting.Recipe<?>>>> recipes;
    private Iterator<net.minecraft.world.item.crafting.Recipe<?>> current;

    public RecipeIterator() {
        this.recipes = MinecraftServer.getServer().getRecipeManager().recipes.entrySet().iterator();
    }

    @Override
    public boolean hasNext() {
        if (current != null && current.hasNext()) {
            return true;
        }
        if (recipes.hasNext()) {
            current = recipes.next().getValue().values().iterator();
            return hasNext();
        }
        return false;
    }

    @Override
    public Recipe next() {
        if (current == null || !current.hasNext()) {
            current = recipes.next().getValue().values().iterator();
            return next();
        }
        net.minecraft.world.item.crafting.Recipe<?> recipe = current.next();
        try {
            return recipe.toBukkitRecipe();
        } catch (Throwable e) {
            throw new RuntimeException("Error converting recipe " + recipe.getId(), e);
        }
    }

    @Override
    public void remove() {
        if (current == null) {
            throw new IllegalStateException("next() not yet called");
        }

        current.remove();
    }
}