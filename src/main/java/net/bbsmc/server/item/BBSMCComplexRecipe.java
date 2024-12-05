package net.bbsmc.server.item;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.inventory.CraftComplexRecipe;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BBSMCComplexRecipe extends CraftComplexRecipe {
    private final Recipe<?> recipe;

    public BBSMCComplexRecipe(Recipe<?> recipe) {
        super(null);
        this.recipe = recipe;
    }

    @Override
    public @NotNull ItemStack getResult() {
        return CraftItemStack.asCraftMirror(this.recipe.getResultItem());
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(this.recipe.getId());
    }

    @Override
    public void addToCraftingManager() {
        MinecraftServer.getServer().getRecipeManager().addRecipe(this.recipe);
    }
}
