package org.bukkit.craftbukkit.inventory;

import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;

public class Craftnet.minecraft.world.inventory.MerchantContainer extends CraftInventory implements MerchantInventory {

    private final net.minecraft.world.item.trading.Merchant merchant;

    public Craftnet.minecraft.world.inventory.MerchantContainer(net.minecraft.world.item.trading.Merchant merchant, net.minecraft.world.inventory.MerchantContainer inventory) {
        super(inventory);
        this.merchant = merchant;
    }

    @Override
    public int getSelectedRecipeIndex() {
        return getInventory().selectionHint;
    }

    @Override
    public MerchantRecipe getSelectedRecipe() {
        net.minecraft.world.item.trading.MerchantOffer nmsRecipe = getInventory().getActiveOffer();
        return (nmsRecipe == null) ? null : nmsRecipe.asBukkit();
    }

    @Override
    public net.minecraft.world.inventory.MerchantContainer getInventory() {
        return (net.minecraft.world.inventory.MerchantContainer) inventory;
    }

    @Override
    public Merchant getMerchant() {
        return merchant.getCraftMerchant();
    }
}