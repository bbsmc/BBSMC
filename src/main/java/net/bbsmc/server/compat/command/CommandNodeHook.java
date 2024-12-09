package net.bbsmc.server.compat.command;

import com.mojang.brigadier.tree.CommandNode;
import io.izzel.arclight.api.Unsafe;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;

import java.util.Map;

public class CommandNodeHook {

    private static final long CHILDREN, LITERALS, ARGUMENTS, CURRENT;
    private static final Object CURRENT_BASE;

    static {
        try {
            CHILDREN = Unsafe.objectFieldOffset(CommandNode.class.getDeclaredField("children"));
            LITERALS = Unsafe.objectFieldOffset(CommandNode.class.getDeclaredField("literals"));
            ARGUMENTS = Unsafe.objectFieldOffset(CommandNode.class.getDeclaredField("arguments"));
            CURRENT_BASE = Unsafe.staticFieldBase(CommandNode.class.getDeclaredField("CURRENT_COMMAND"));
            CURRENT = Unsafe.staticFieldOffset(CommandNode.class.getDeclaredField("CURRENT_COMMAND"));
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    @SuppressWarnings("unchecked")
    public static void removeCommand(CommandNode<?> node, String command) {
        ((Map<String, ?>) Unsafe.getObject(node, CHILDREN)).remove(command);
        ((Map<String, ?>) Unsafe.getObject(node, LITERALS)).remove(command);
        ((Map<String, ?>) Unsafe.getObject(node, ARGUMENTS)).remove(command);
    }

    public static CommandNode<?> getCurrent() {
        return (CommandNode<?>) Unsafe.getObjectVolatile(CURRENT_BASE, CURRENT);
    }

    public static <S> boolean canUse(CommandNode<S> node, S source) {
        if (source instanceof CommandSourceStack s) {
            try {
                s.setCurrentCommand(node);
                return node.canUse(source);
            } finally {
                s.setCurrentCommand(null);
            }
        } else {
            return node.canUse(source);
        }
    }
}
