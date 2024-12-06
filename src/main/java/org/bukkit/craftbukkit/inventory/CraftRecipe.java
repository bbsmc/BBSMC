package org.bukkit.craftbukkit.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.item.crafting.Ingredient;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;

public interface CraftRecipe extends Recipe {

    void addToCraftingManager();

    default net.minecraft.world.item.crafting.Ingredient toNMS(RecipeChoice bukkit, boolean requireNotEmpty) {
        net.minecraft.world.item.crafting.Ingredient stack;

        if (bukkit == null) {
            stack = net.minecraft.world.item.crafting.Ingredient.EMPTY;
        } else if (bukkit instanceof RecipeChoice.MaterialChoice) {
            stack = new net.minecraft.world.item.crafting.Ingredient(((RecipeChoice.MaterialChoice) bukkit).getChoices().stream().map((mat) -> new Ingredient.ItemValue(CraftItemStack.asNMSCopy(new ItemStack(mat)))));
        } else if (bukkit instanceof RecipeChoice.ExactChoice) {
            stack = new net.minecraft.world.item.crafting.Ingredient(((RecipeChoice.ExactChoice) bukkit).getChoices().stream().map((mat) -> new Ingredient.ItemValue(CraftItemStack.asNMSCopy(mat))));
            stack.exact = true;
        } else {
            throw new IllegalArgumentException("Unknown recipe stack instance " + bukkit);
        }

        stack.dissolve();
        if (requireNotEmpty && stack.itemStacks.length == 0) {
            throw new IllegalArgumentException("Recipe requires at least one non-air choice!");
        }

        return stack;
    }

    public static RecipeChoice toBukkit(net.minecraft.world.item.crafting.Ingredient list) {
        list.dissolve();

        if (list.itemStacks.length == 0) {
            return null;
        }

        if (list.exact) {
            List<org.bukkit.inventory.ItemStack> choices = new ArrayList<>(list.itemStacks.length);
            for (net.minecraft.world.item.ItemStack i : list.itemStacks) {
                choices.add(CraftItemStack.asBukkitCopy(i));
            }

            return new RecipeChoice.ExactChoice(choices);
        } else {

            List<org.bukkit.Material> choices = new ArrayList<>(list.itemStacks.length);
            for (net.minecraft.world.item.ItemStack i : list.itemStacks) {
                choices.add(CraftMagicNumbers.getMaterial(i.getItem()));
            }

            return new RecipeChoice.MaterialChoice(choices);
        }
    }
}