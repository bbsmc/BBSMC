package org.bukkit.craftbukkit.block;

import net.minecraft.util.RandomSource;
import org.bukkit.Material;
import org.bukkit.block.Block;

public final class CapturedBlockState extends CraftBlockState {

    private final boolean treeBlock;

    public CapturedBlockState(Block block, int flag, boolean treeBlock) {
        super(block, flag);

        this.treeBlock = treeBlock;
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        boolean result = super.update(force, applyPhysics);

        // SPIGOT-5537: Horrible hack to manually add bees given net.minecraft.world.level.Level.captureTreeGeneration does not support tiles
        if (this.treeBlock && getType() == Material.BEE_NEST) {
            net.minecraft.world.level.net.minecraft.world.level.LevelGenLevel generatoraccessseed = this.world.getHandle();
            net.minecraft.core.BlockPos blockposition1 = this.getPosition();
            RandomSource random = generatoraccessseed.getRandom();

            // Begin copied block from net.minecraft.world.level.LevelGenFeatureTreeBeehive
            net.minecraft.world.level.block.entity.BlockEntity tileentity = generatoraccessseed.getBlockEntity(blockposition1);

            if (tileentity instanceof Tilenet.minecraft.world.entity.animal.Beehive) {
                Tilenet.minecraft.world.entity.animal.Beehive tileentitybeehive = (Tilenet.minecraft.world.entity.animal.Beehive) tileentity;
                int j = 2 + random.nextInt(2);

                for (int k = 0; k < j; ++k) {
                    net.minecraft.world.entity.animal.Bee entitybee = new net.minecraft.world.entity.animal.Bee(net.minecraft.world.entity.EntityType.BEE, generatoraccessseed.getMinecraftnet.minecraft.world.level.Level());

                    tileentitybeehive.addOccupantWithPresetTicks(entitybee, false, random.nextInt(599));
                }
            }
            // End copied block
        }

        return result;
    }

    public static CapturedBlockState getBlockState(net.minecraft.world.level.Level world, net.minecraft.core.BlockPos pos, int flag) {
        return new CapturedBlockState(world.getnet.minecraft.world.level.Level().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), flag, false);
    }

    public static CapturedBlockState getTreeBlockState(net.minecraft.world.level.Level world, net.minecraft.core.BlockPos pos, int flag) {
        return new CapturedBlockState(world.getnet.minecraft.world.level.Level().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), flag, true);
    }
}