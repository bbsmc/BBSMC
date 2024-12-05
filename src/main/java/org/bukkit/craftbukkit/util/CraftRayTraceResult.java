package org.bukkit.craftbukkit.util;

import net.minecraft.world.phys.HitResult;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.entity.Entity;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public final class CraftRayTraceResult {

    private CraftRayTraceResult() {}

    public static RayTraceResult fromNMS(World world, net.minecraft.world.phys.HitResult nmsHitResult) {
        if (nmsHitResult == null || nmsHitResult.getType() == HitResult.Type.MISS) return null;

        net.minecraft.world.phys.Vec3 nmsHitPos = nmsHitResult.getLocation();
        Vector hitPosition = new Vector(nmsHitPos.x, nmsHitPos.y, nmsHitPos.z);
        BlockFace hitBlockFace = null;

        if (nmsHitResult.getType() == HitResult.Type.ENTITY) {
            Entity hitEntity = ((net.minecraft.world.phys.EntityHitResult) nmsHitResult).getEntity().getBukkitEntity();
            return new RayTraceResult(hitPosition, hitEntity, null);
        }

        Block hitBlock = null;
        net.minecraft.core.BlockPos nmsBlockPos = null;
        if (nmsHitResult.getType() == HitResult.Type.BLOCK) {
            net.minecraft.world.phys.BlockHitResult blockHitResult = (net.minecraft.world.phys.BlockHitResult) nmsHitResult;
            hitBlockFace = CraftBlock.notchToBlockFace(blockHitResult.getDirection());
            nmsBlockPos = blockHitResult.getBlockPos();
        }
        if (nmsBlockPos != null && world != null) {
            hitBlock = world.getBlockAt(nmsBlockPos.getX(), nmsBlockPos.getY(), nmsBlockPos.getZ());
        }
        return new RayTraceResult(hitPosition, hitBlock, hitBlockFace);
    }
}