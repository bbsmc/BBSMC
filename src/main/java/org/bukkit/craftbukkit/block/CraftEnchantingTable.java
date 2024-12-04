package org.bukkit.craftbukkit.block;

import org.bukkit.World;
import org.bukkit.block.EnchantingTable;
import org.bukkit.craftbukkit.util.CraftChatMessage;

public class CraftEnchantingTable extends CraftBlockEntityState<net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity> implements EnchantingTable {

    public CraftEnchantingTable(World world, net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public String getCustomName() {
        net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity enchant = this.getSnapshot();
        return enchant.hasCustomName() ? CraftChatMessage.fromComponent(enchant.getCustomName()) : null;
    }

    @Override
    public void setCustomName(String name) {
        this.getSnapshot().setCustomName(CraftChatMessage.fromStringOrNull(name));
    }

    @Override
    public void applyTo(net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity enchantingTable) {
        super.applyTo(enchantingTable);

        if (!this.getSnapshot().hasCustomName()) {
            enchantingTable.setCustomName(null);
        }
    }
}