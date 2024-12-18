package org.bukkit.craftbukkit.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Jukebox;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;

public class CraftJukebox extends CraftBlockEntityState<net.minecraft.world.level.block.entity.JukeboxBlockEntity> implements Jukebox {

    public CraftJukebox(World world, net.minecraft.world.level.block.entity.JukeboxBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        boolean result = super.update(force, applyPhysics);

        if (result && this.isPlaced() && this.getType() == Material.JUKEBOX) {
            CraftWorld world = (CraftWorld) this.getWorld();
            Material record = this.getPlaying();
            if (record == Material.AIR) {
                getWorldHandle().setBlock(this.getPosition(), Blocks.JUKEBOX.defaultBlockState().setValue(net.minecraft.world.level.block.JukeboxBlock.HAS_RECORD, false), 3);
            } else {
                getWorldHandle().setBlock(this.getPosition(), Blocks.JUKEBOX.defaultBlockState().setValue(net.minecraft.world.level.block.JukeboxBlock.HAS_RECORD, true), 3);
            }
            world.playEffect(this.getLocation(), Effect.RECORD_PLAY, record);
        }

        return result;
    }

    @Override
    public Material getPlaying() {
        return getRecord().getType();
    }

    @Override
    public void setPlaying(Material record) {
        if (record == null || CraftMagicNumbers.getItem(record) == null) {
            record = Material.AIR;
        }

        setRecord(new org.bukkit.inventory.ItemStack(record));
    }

    @Override
    public org.bukkit.inventory.ItemStack getRecord() {
        ItemStack record = this.getSnapshot().getRecord();
        return CraftItemStack.asBukkitCopy(record);
    }

    @Override
    public void setRecord(org.bukkit.inventory.ItemStack record) {
        ItemStack nms = CraftItemStack.asNMSCopy(record);
        this.getSnapshot().setRecord(nms);
        if (nms.isEmpty()) {
            this.data = this.data.setValue(net.minecraft.world.level.block.JukeboxBlock.HAS_RECORD, false);
        } else {
            this.data = this.data.setValue(net.minecraft.world.level.block.JukeboxBlock.HAS_RECORD, true);
        }
    }

    @Override
    public boolean isPlaying() {
        return getHandle().getValue(net.minecraft.world.level.block.JukeboxBlock.HAS_RECORD);
    }

    @Override
    public void stopPlaying() {
        getWorld().playEffect(getLocation(), Effect.RECORD_PLAY, Material.AIR);
    }

    @Override
    public boolean eject() {
        ensureNoWorldGeneration();

        net.minecraft.world.level.block.entity.BlockEntity tileEntity = this.getTileEntityFromWorld();
        if (!(tileEntity instanceof net.minecraft.world.level.block.entity.JukeboxBlockEntity jukebox)) return false;

        boolean result = !jukebox.getRecord().isEmpty();
        CraftWorld world = (CraftWorld) this.getWorld();
        ((net.minecraft.world.level.block.JukeboxBlock) Blocks.JUKEBOX).dropRecording(world.getHandle(), getPosition());
        return result;
    }
}