package org.bukkit.craftbukkit.generator;

import com.google.common.base.Preconditions;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Climate;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

public class Customnet.minecraft.world.level.biome.BiomeSource extends net.minecraft.world.level.biome.BiomeSource {

    private final WorldInfo worldInfo;
    private final BiomeProvider biomeProvider;
    private final net.minecraft.core.Registry<net.minecraft.world.level.biome.Biome> registry;

    private static List<Holder<net.minecraft.world.level.biome.Biome>> biomeListTonet.minecraft.world.level.biome.BiomeList(List<Biome> biomes, net.minecraft.core.Registry<net.minecraft.world.level.biome.Biome> registry) {
        List<Holder<net.minecraft.world.level.biome.Biome>> biomeBases = new ArrayList<>();

        for (Biome biome : biomes) {
            Preconditions.checkArgument(biome != Biome.CUSTOM, "Cannot use the biome %s", biome);
            biomeBases.add(CraftBlock.biomeTonet.minecraft.world.level.biome.Biome(registry, biome));
        }

        return biomeBases;
    }

    public Customnet.minecraft.world.level.biome.BiomeSource(WorldInfo worldInfo, BiomeProvider biomeProvider, net.minecraft.core.Registry<net.minecraft.world.level.biome.Biome> registry) {
        super(biomeListTonet.minecraft.world.level.biome.BiomeList(biomeProvider.getBiomes(worldInfo), registry));

        this.worldInfo = worldInfo;
        this.biomeProvider = biomeProvider;
        this.registry = registry;
    }

    @Override
    protected Codec<? extends net.minecraft.world.level.biome.BiomeSource> codec() {
        throw new UnsupportedOperationException("Cannot serialize Customnet.minecraft.world.level.biome.BiomeSource");
    }

    @Override
    public Holder<net.minecraft.world.level.biome.Biome> getNoiseBiome(int x, int y, int z, Climate.Sampler sampler) {
        Biome biome = biomeProvider.getBiome(worldInfo, x << 2, y << 2, z << 2);
        Preconditions.checkArgument(biome != Biome.CUSTOM, "Cannot set the biome to %s", biome);

        return CraftBlock.biomeTonet.minecraft.world.level.biome.Biome(registry, biome);
    }
}