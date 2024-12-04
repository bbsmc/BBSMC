package org.bukkit.craftbukkit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.block.CraftBlockState;

public class BlockStateListPopulator extends Dummynet.minecraft.world.level.LevelAccessor {
    private final net.minecraft.world.level.LevelAccessor world;
    private final Map<net.minecraft.core.BlockPos, net.minecraft.world.level.block.state.BlockState> dataMap = new HashMap<>();
    private final Map<net.minecraft.core.BlockPos, net.minecraft.world.level.block.entity.BlockEntity> entityMap = new HashMap<>();
    private final LinkedHashMap<net.minecraft.core.BlockPos, CraftBlockState> list;

    public BlockStateListPopulator(net.minecraft.world.level.LevelAccessor world) {
        this(world, new LinkedHashMap<>());
    }

    private BlockStateListPopulator(net.minecraft.world.level.LevelAccessor world, LinkedHashMap<net.minecraft.core.BlockPos, CraftBlockState> list) {
        this.world = world;
        this.list = list;
    }

    @Override
    public net.minecraft.world.level.block.state.BlockState getBlockState(net.minecraft.core.BlockPos bp) {
        net.minecraft.world.level.block.state.BlockState blockData = dataMap.get(bp);
        return (blockData != null) ? blockData : world.getBlockState(bp);
    }

    @Override
    public net.minecraft.world.level.material.FluidState getnet.minecraft.world.level.material.FluidStateState(net.minecraft.core.BlockPos bp) {
        net.minecraft.world.level.block.state.BlockState blockData = dataMap.get(bp);
        return (blockData != null) ? blockData.getnet.minecraft.world.level.material.FluidStateState() : world.getnet.minecraft.world.level.material.FluidStateState(bp);
    }

    @Override
    public net.minecraft.world.level.block.entity.BlockEntity getBlockEntity(net.minecraft.core.BlockPos blockposition) {
        // The contains is important to check for null values
        if (entityMap.containsKey(blockposition)) {
            return entityMap.get(blockposition);
        }

        return world.getBlockEntity(blockposition);
    }

    @Override
    public boolean setBlock(net.minecraft.core.BlockPos position, net.minecraft.world.level.block.state.BlockState data, int flag) {
        position = position.immutable();
        // remove first to keep insertion order
        list.remove(position);

        dataMap.put(position, data);
        if (data.hasBlockEntity()) {
            entityMap.put(position, ((net.minecraft.world.level.block.EntityBlock) data.getBlock()).newBlockEntity(position, data));
        } else {
            entityMap.put(position, null);
        }

        // use 'this' to ensure that the block state is the correct TileState
        CraftBlockState state = (CraftBlockState) CraftBlock.at(this, position).getState();
        state.setFlag(flag);
        // set world handle to ensure that updated calls are done to the world and not to this populator
        state.setWorldHandle(world);
        list.put(position, state);
        return true;
    }

    @Override
    public net.minecraft.server.level.ServerLevel getMinecraftWorld() {
        return world.getMinecraftWorld();
    }

    public void refreshTiles() {
        for (CraftBlockState state : list.values()) {
            if (state instanceof CraftBlockEntityState) {
                ((CraftBlockEntityState<?>) state).refreshSnapshot();
            }
        }
    }

    public void updateList() {
        for (BlockState state : list.values()) {
            state.update(true);
        }
    }

    public Set<net.minecraft.core.BlockPos> getBlocks() {
        return list.keySet();
    }

    public List<CraftBlockState> getList() {
        return new ArrayList<>(list.values());
    }

    public net.minecraft.world.level.LevelAccessor getWorld() {
        return world;
    }

    // For tree generation
    @Override
    public int getMinBuildHeight() {
        return getWorld().getMinBuildHeight();
    }

    @Override
    public int getHeight() {
        return getWorld().getHeight();
    }

    @Override
    public boolean isStateAtPosition(net.minecraft.core.BlockPos blockposition, Predicate<net.minecraft.world.level.block.state.BlockState> predicate) {
        return predicate.test(getBlockState(blockposition));
    }

    @Override
    public net.minecraft.world.level.dimension.DimensionType dimensionType() {
        return world.dimensionType();
    }
}