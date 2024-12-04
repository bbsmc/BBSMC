package org.bukkit.craftbukkit.inventory;

import net.minecraft.server.MinecraftServer;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.inventory.ItemStack;

public class CraftComplexRecipe implements CraftRecipe, ComplexRecipe {

    private final net.minecraft.world.item.crafting.CustomRecipe recipe;

    public CraftComplexRecipe(net.minecraft.world.item.crafting.CustomRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public ItemStack getResult() {
        return CraftItemStack.asCraftMirror(recipe.getResultItem());
    }

    @Override
    public NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(recipe.getId());
    }

    @Override
    public void addToCraftingManager() {
        MinecraftServer.getServer().getRecipeManager().addRecipe(recipe);
    }
}