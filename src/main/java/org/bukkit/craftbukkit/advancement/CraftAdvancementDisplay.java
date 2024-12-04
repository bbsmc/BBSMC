package org.bukkit.craftbukkit.advancement;

import org.bukkit.advancement.AdvancementDisplayType;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.util.CraftChatMessage;
import org.bukkit.inventory.ItemStack;

public class Craftnet.minecraft.advancements.DisplayInfo implements org.bukkit.advancement.net.minecraft.advancements.DisplayInfo {

    private final net.minecraft.advancements.DisplayInfo handle;

    public Craftnet.minecraft.advancements.DisplayInfo(net.minecraft.advancements.DisplayInfo handle) {
        this.handle = handle;
    }

    public net.minecraft.advancements.DisplayInfo getHandle() {
        return handle;
    }

    @Override
    public String getTitle() {
        return CraftChatMessage.fromComponent(handle.getTitle());
    }

    @Override
    public String getDescription() {
        return CraftChatMessage.fromComponent(handle.getDescription());
    }

    @Override
    public ItemStack getIcon() {
        return CraftItemStack.asBukkitCopy(handle.getIcon());
    }

    @Override
    public boolean shouldShowToast() {
        return handle.shouldShowToast();
    }

    @Override
    public boolean shouldAnnounceChat() {
        return handle.shouldAnnounceChat();
    }

    @Override
    public boolean isHidden() {
        return handle.isHidden();
    }

    @Override
    public float getX() {
        return handle.getX();
    }

    @Override
    public float getY() {
        return handle.getY();
    }

    @Override
    public net.minecraft.advancements.DisplayInfoType getType() {
        return net.minecraft.advancements.DisplayInfoType.values()[handle.getFrame().ordinal()];
    }
}