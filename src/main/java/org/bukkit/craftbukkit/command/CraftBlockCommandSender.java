package org.bukkit.craftbukkit.command;

import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.util.CraftChatMessage;

/**
 * Represents input from a command block
 */
public class CraftBlockCommandSender extends ServerCommandSender implements BlockCommandSender {
    private final net.minecraft.commands.CommandSourceStack block;
    private final net.minecraft.world.level.block.entity.BlockEntity tile;

    public CraftBlockCommandSender(net.minecraft.commands.CommandSourceStack commandBlockListenerAbstract, net.minecraft.world.level.block.entity.BlockEntity tile) {
        super();
        this.block = commandBlockListenerAbstract;
        this.tile = tile;
    }

    @Override
    public Block getBlock() {
        return CraftBlock.at(tile.getLevel(), tile.getBlockPos());
    }

    @Override
    public void sendMessage(String message) {
        for (net.minecraft.network.chat.Component component : CraftChatMessage.fromString(message)) {
            block.source.sendSystemMessage(component);
        }
    }

    @Override
    public void sendMessage(String... messages) {
        for (String message : messages) {
            sendMessage(message);
        }
    }

    @Override
    public String getName() {
        return block.getTextName();
    }

    @Override
    public boolean isOp() {
        return true;
    }

    @Override
    public void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot change operator status of a block");
    }

    public net.minecraft.commands.CommandSourceStack getWrapper() {
        return block;
    }
}