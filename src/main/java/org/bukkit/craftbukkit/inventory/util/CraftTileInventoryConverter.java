package org.bukkit.craftbukkit.inventory.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryBrewer;
import org.bukkit.craftbukkit.inventory.CraftInventoryFurnace;
import org.bukkit.craftbukkit.util.CraftChatMessage;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class CraftTileInventoryConverter implements CraftInventoryCreator.InventoryConverter {

    public abstract net.minecraft.world.Container getTileEntity();

    @Override
    public Inventory createInventory(InventoryHolder holder, InventoryType type) {
        return getInventory(getTileEntity());
    }

    @Override
    public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
        net.minecraft.world.Container te = getTileEntity();
        if (te instanceof net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity) {
            ((net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity) te).setCustomName(CraftChatMessage.fromStringOrNull(title));
        }

        return getInventory(te);
    }

    public Inventory getInventory(net.minecraft.world.Container tileEntity) {
        return new CraftInventory(tileEntity);
    }

    public static class Furnace extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity furnace = new net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntityFurnace(net.minecraft.core.BlockPos.ZERO, Blocks.FURNACE.defaultBlockState()); // TODO: customize this if required
            return furnace;
        }

        @Override
        public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
            net.minecraft.world.Container tileEntity = getTileEntity();
            ((net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity) tileEntity).setCustomName(CraftChatMessage.fromStringOrNull(title));
            return getInventory(tileEntity);
        }

        @Override
        public Inventory getInventory(net.minecraft.world.Container tileEntity) {
            return new CraftInventoryFurnace((net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity) tileEntity);
        }
    }

    public static class BrewingStand extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            return new net.minecraft.world.level.block.entity.BrewingStandBlockEntity(net.minecraft.core.BlockPos.ZERO, Blocks.BREWING_STAND.defaultBlockState());
        }

        @Override
        public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
            // BrewingStand does not extend net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
            net.minecraft.world.Container tileEntity = getTileEntity();
            if (tileEntity instanceof net.minecraft.world.level.block.entity.BrewingStandBlockEntity) {
                ((net.minecraft.world.level.block.entity.BrewingStandBlockEntity) tileEntity).setCustomName(CraftChatMessage.fromStringOrNull(title));
            }
            return getInventory(tileEntity);
        }

        @Override
        public Inventory getInventory(net.minecraft.world.Container tileEntity) {
            return new CraftInventoryBrewer(tileEntity);
        }
    }

    public static class Dispenser extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            return new net.minecraft.world.level.block.entity.DispenserBlockEntity(net.minecraft.core.BlockPos.ZERO, Blocks.DISPENSER.defaultBlockState());
        }
    }

    public static class Dropper extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            return new net.minecraft.world.level.block.entity.DropperBlockEntity(net.minecraft.core.BlockPos.ZERO, Blocks.DROPPER.defaultBlockState());
        }
    }

    public static class Hopper extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            return new net.minecraft.world.level.block.entity.HopperBlockEntity(net.minecraft.core.BlockPos.ZERO, Blocks.HOPPER.defaultBlockState());
        }
    }

    public static class BlastFurnace extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            return new net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity(net.minecraft.core.BlockPos.ZERO, Blocks.BLAST_FURNACE.defaultBlockState());
        }
    }

    public static class Lectern extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            return new net.minecraft.world.level.block.entity.LecternBlockEntity(net.minecraft.core.BlockPos.ZERO, Blocks.LECTERN.defaultBlockState()).bookAccess;
        }
    }

    public static class Smoker extends CraftTileInventoryConverter {

        @Override
        public net.minecraft.world.Container getTileEntity() {
            return new net.minecraft.world.level.block.entity.SmokerBlockEntity(net.minecraft.core.BlockPos.ZERO, Blocks.SMOKER.defaultBlockState());
        }
    }
}