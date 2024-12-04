package org.bukkit.craftbukkit.block;

import org.bukkit.DyeColor;
import org.bukkit.World;
import org.bukkit.block.ShulkerBox;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.bukkit.inventory.Inventory;

public class CraftShulkerBox extends CraftLootable<net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity> implements ShulkerBox {

    public CraftShulkerBox(World world, net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public Inventory getSnapshotInventory() {
        return new CraftInventory(this.getSnapshot());
    }

    @Override
    public Inventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventory(this.getTileEntity());
    }

    @Override
    public DyeColor getColor() {
        net.minecraft.world.item.DyeColor color = ((net.minecraft.world.level.block.ShulkerBoxBlock) CraftMagicNumbers.getBlock(this.getType())).color;

        return (color == null) ? null : DyeColor.getByWoolData((byte) color.getId());
    }

    @Override
    public void open() {
        requirePlaced();
        if (!getTileEntity().opened && getWorldHandle() instanceof net.minecraft.world.level.Level) {
            net.minecraft.world.level.Level world = getTileEntity().getLevel();
            world.blockEvent(getPosition(), getTileEntity().getBlockState().getBlock(), 1, 1);
            world.playSound(null, getPosition(), net.minecraft.sounds.SoundEvents.SHULKER_BOX_OPEN, net.minecraft.sounds.SoundSource.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
        }
        getTileEntity().opened = true;
    }

    @Override
    public void close() {
        requirePlaced();
        if (getTileEntity().opened && getWorldHandle() instanceof net.minecraft.world.level.Level) {
            net.minecraft.world.level.Level world = getTileEntity().getLevel();
            world.blockEvent(getPosition(), getTileEntity().getBlockState().getBlock(), 1, 0);
            world.playSound(null, getPosition(), net.minecraft.sounds.SoundEvents.SHULKER_BOX_OPEN, net.minecraft.sounds.SoundSource.BLOCKS, 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
        }
        getTileEntity().opened = false;
    }
}