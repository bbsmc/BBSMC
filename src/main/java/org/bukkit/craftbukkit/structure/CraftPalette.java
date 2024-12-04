package org.bukkit.craftbukkit.structure;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.block.CraftBlockStates;
import org.bukkit.structure.Palette;

public class CraftPalette implements Palette {

    private final net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.a palette;

    public CraftPalette(net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.a palette) {
        this.palette = palette;
    }

    @Override
    public List<BlockState> getBlocks() {
        List<BlockState> blocks = new ArrayList<>();
        for (net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.BlockInfo blockInfo : palette.blocks()) {
            blocks.add(CraftBlockStates.getBlockState(blockInfo.pos, blockInfo.state, blockInfo.nbt));
        }
        return blocks;
    }

    @Override
    public int getBlockCount() {
        return palette.blocks().size();
    }
}