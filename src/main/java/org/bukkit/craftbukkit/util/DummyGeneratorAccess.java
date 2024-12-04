package org.bukkit.craftbukkit.util;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.LevelTickAccess;

public class DummyGeneratorAccess implements net.minecraft.world.level.WorldGenLevel {

    public static final net.minecraft.world.level.WorldGenLevel INSTANCE = new DummyGeneratorAccess();

    protected DummyGeneratorAccess() {
    }

    @Override
    public long getSeed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.server.level.ServerLevel getLevel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long nextSubTickCount() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LevelTickAccess<Block> getBlockTicks() {
        return net.minecraft.world.ticks.BlackholeTickAccess.emptyLevelList();
    }

    @Override
    public void scheduleTick(net.minecraft.core.BlockPos blockposition, Block block, int i) {
        // Used by BlockComposter
    }

    @Override
    public LevelTickAccess<net.minecraft.world.level.material.FluidStateType> getnet.minecraft.world.level.material.FluidStateTicks() {
        return net.minecraft.world.ticks.BlackholeTickAccess.emptyLevelList();
    }

    @Override
    public net.minecraft.world.level.storage.LevelData getLevelData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.world.DifficultyInstance getCurrentDifficultyAt(net.minecraft.core.BlockPos blockposition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MinecraftServer getServer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.world.level.chunk.ChunkSource getChunkSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RandomSource getRandom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void playSound(net.minecraft.world.entity.player.Player entityhuman, net.minecraft.core.BlockPos blockposition, net.minecraft.sounds.SoundEvent soundeffect, net.minecraft.sounds.SoundSource soundcategory, float f, float f1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addParticle(net.minecraft.core.particles.ParticleOptions particleparam, double d0, double d1, double d2, double d3, double d4, double d5) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void levelEvent(net.minecraft.world.entity.player.Player entityhuman, int i, net.minecraft.core.BlockPos blockposition, int j) {
        // Used by PowderSnowBlock.removenet.minecraft.world.level.material.FluidState
    }

    @Override
    public void gameEvent(GameEvent gameevent, net.minecraft.world.phys.Vec3 vec3d, GameEvent.a gameevent_a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.core.RegistryAccess registryAccess() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Entity> getEntities(Entity entity, net.minecraft.world.phys.AABB aabb, Predicate<? super Entity> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T extends Entity> List<T> getEntities(EntityTypeTest<Entity, T> ett, net.minecraft.world.phys.AABB aabb, Predicate<? super T> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<? extends net.minecraft.world.entity.player.Player> players() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.world.level.chunk.ChunkAccess getChunk(int i, int i1, ChunkStatus cs, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getHeight(net.minecraft.world.level.levelgen.Heightmap.Type type, int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSkyDarken() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BiomeManager getBiomeManager() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Holder<net.minecraft.world.level.biome.Biome> getUncachedNoiseBiome(int i, int i1, int i2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isClientSide() {
        return false;
    }

    @Override
    public int getSeaLevel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.world.level.dimension.DimensionType dimensionType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getShade(net.minecraft.core.Direction ed, boolean bln) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.world.level.lighting.LevelLightEngine getnet.minecraft.world.level.lighting.LevelLightEngine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.world.level.block.entity.BlockEntity getBlockEntity(net.minecraft.core.BlockPos blockposition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public net.minecraft.world.level.block.state.BlockState getBlockState(net.minecraft.core.BlockPos blockposition) {
        return Blocks.AIR.defaultBlockState(); // SPIGOT-6515
    }

    @Override
    public net.minecraft.world.level.material.FluidState getnet.minecraft.world.level.material.FluidStateState(net.minecraft.core.BlockPos blockposition) {
        return net.minecraft.world.level.material.FluidStateTypes.EMPTY.defaultnet.minecraft.world.level.material.FluidStateState(); // SPIGOT-6634
    }

    @Override
    public WorldBorder getWorldBorder() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isStateAtPosition(net.minecraft.core.BlockPos bp, Predicate<net.minecraft.world.level.block.state.BlockState> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isnet.minecraft.world.level.material.FluidStateAtPosition(net.minecraft.core.BlockPos bp, Predicate<net.minecraft.world.level.material.FluidState> prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setBlock(net.minecraft.core.BlockPos blockposition, net.minecraft.world.level.block.state.BlockState iblockdata, int i, int j) {
        return false;
    }

    @Override
    public boolean removeBlock(net.minecraft.core.BlockPos blockposition, boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean destroyBlock(net.minecraft.core.BlockPos blockposition, boolean flag, Entity entity, int i) {
        return false; // SPIGOT-6515
    }
}