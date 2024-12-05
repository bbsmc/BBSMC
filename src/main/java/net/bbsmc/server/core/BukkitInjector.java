package net.bbsmc.server.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import net.bbsmc.server.BBSMcServer;
import net.bbsmc.server.utils.EnumHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.dimension.LevelStem;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class BukkitInjector {
    public static BiMap<ResourceKey<LevelStem>, World.Environment> environments =
            HashBiMap.create(ImmutableMap.<ResourceKey<LevelStem>, World.Environment>builder()
            .put(LevelStem.OVERWORLD, World.Environment.NORMAL)
            .put(LevelStem.NETHER, World.Environment.NETHER)
            .put(LevelStem.END, World.Environment.THE_END)
            .build());

    public static Map<EntityType<?>, String> entityTypeMap = new HashMap<>();

    public static void register() {

    }

    public static void registerEnvironment(Registry<LevelStem> registry) {
        int i = World.Environment.values().length;
        List<World.Environment> worldTypes = Lists.newArrayList();
        for (var entry : registry.entrySet()) {
            ResourceKey<LevelStem> resourceKey = entry.getKey();
            // Skip minecraft
            if (Objects.equals(resourceKey.location().getNamespace(), NamespacedKey.MINECRAFT)) {
                continue;
            }
            World.Environment environment = environments.get(resourceKey);
            if (environment == null) {
                String name = standardize(resourceKey.location());
                environment = EnumHelper.makeEnum(World.Environment.class, name, i++, ImmutableList.of(Integer.TYPE), List.of(i - 1));
                worldTypes.add(environment);
                environments.put(resourceKey, environment);
                BBSMcServer.log.info("向 Bukkit 注册新的世界维度 {}", environment);
            }
        }
        EnumHelper.addEnums(World.Environment.class, worldTypes);
    }

    @Contract("null -> fail")
    public static String standardize(ResourceLocation location) {
        Preconditions.checkNotNull(location, "location");
        return (location.getNamespace().equals(NamespacedKey.MINECRAFT) ? location.getPath() : location.toString())
                .replace(':', '_')
                .replaceAll("\\s+", "_")
                .replaceAll("\\W", "")
                .toUpperCase(Locale.ENGLISH);
    }
}
