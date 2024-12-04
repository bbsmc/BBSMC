package net.bbsmc.server.world;

import org.bukkit.generator.BiomeProvider;

public class LevelSetter {
    private static final ThreadLocal<LevelSetter> threadLocal = new ThreadLocal<>();
    public static LevelSetter get() {
        LevelSetter currentThreadObject = threadLocal.get();
        if (currentThreadObject == null) {
            currentThreadObject = new LevelSetter();
            threadLocal.set(currentThreadObject);
        }
        return currentThreadObject;
    }

    private org.bukkit.generator.ChunkGenerator generator;
    private org.bukkit.World.Environment environment;
    private BiomeProvider biomeProvider;

    public void setLevel(org.bukkit.generator.ChunkGenerator gen, org.bukkit.World.Environment env, BiomeProvider biome) {
        generator = gen;
        environment = env;
        biomeProvider = biome;
    }

    public org.bukkit.generator.ChunkGenerator getChunkGenerator() {
        return generator;
    }

    public org.bukkit.World.Environment getEnvironment() {
        return environment;
    }

    public BiomeProvider getBiomeProvider() {
        return biomeProvider;
    }

    public void reset() {
        generator = null;
        environment = null;
        biomeProvider = null;
    }
}
