package org.bukkit.craftbukkit.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.logging.Level;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftServer;

public class BukkitCommandWrapper implements com.mojang.brigadier.Command<net.minecraft.commands.CommandSourceStack>, Predicate<net.minecraft.commands.CommandSourceStack>, SuggestionProvider<net.minecraft.commands.CommandSourceStack> {

    private final CraftServer server;
    private final Command command;

    public BukkitCommandWrapper(CraftServer server, Command command) {
        this.server = server;
        this.command = command;
    }

    public LiteralCommandNode<net.minecraft.commands.CommandSourceStack> register(CommandDispatcher<net.minecraft.commands.CommandSourceStack> dispatcher, String label) {
        return dispatcher.register(
                LiteralArgumentBuilder.<net.minecraft.commands.CommandSourceStack>literal(label).requires(this).executes(this)
                .then(RequiredArgumentBuilder.<net.minecraft.commands.CommandSourceStack, String>argument("args", StringArgumentType.greedyString()).suggests(this).executes(this))
        );
    }

    @Override
    public boolean test(net.minecraft.commands.CommandSourceStack wrapper) {
        return command.testPermissionSilent(wrapper.getBukkitSender());
    }

    @Override
    public int run(CommandContext<net.minecraft.commands.CommandSourceStack> context) throws CommandSyntaxException {
        CommandSender sender = context.getSource().getBukkitSender();

        try {
            return server.dispatchCommand(sender, context.getInput()) ? 1 : 0;
        } catch (CommandException ex) {
            sender.sendMessage(org.bukkit.ChatColor.RED + "An internal error occurred while attempting to perform this command");
            server.getLogger().log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<net.minecraft.commands.CommandSourceStack> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        List<String> results = server.tabComplete(context.getSource().getBukkitSender(), builder.getInput(), context.getSource().getLevel(), context.getSource().getPosition(), true);

        // Defaults to sub nodes, but we have just one giant args node, so offset accordingly
        builder = builder.createOffset(builder.getInput().lastIndexOf(' ') + 1);

        for (String s : results) {
            builder.suggest(s);
        }

        return builder.buildFuture();
    }
}