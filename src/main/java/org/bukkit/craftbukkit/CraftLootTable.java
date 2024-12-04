package org.bukkit.craftbukkit;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootTable;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.util.RandomSourceWrapper;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;

public class CraftLootTable implements org.bukkit.loot.LootTable {

    private final LootTable handle;
    private final NamespacedKey key;

    public CraftLootTable(NamespacedKey key, LootTable handle) {
        this.handle = handle;
        this.key = key;
    }

    public LootTable getHandle() {
        return handle;
    }

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        Preconditions.checkArgument(context != null, "LootContext cannot be null");
        net.minecraft.world.level.storage.loot.LootContext nmsContext = convertContext(context, random);
        List<net.minecraft.world.item.ItemStack> nmsItems = handle.getRandomItems(nmsContext);
        Collection<ItemStack> bukkit = new ArrayList<>(nmsItems.size());

        for (net.minecraft.world.item.ItemStack item : nmsItems) {
            if (item.isEmpty()) {
                continue;
            }
            bukkit.add(CraftItemStack.asBukkitCopy(item));
        }

        return bukkit;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {
        Preconditions.checkArgument(inventory != null, "Inventory cannot be null");
        Preconditions.checkArgument(context != null, "LootContext cannot be null");
        net.minecraft.world.level.storage.loot.LootContext nmsContext = convertContext(context, random);
        CraftInventory craftInventory = (CraftInventory) inventory;
        net.minecraft.world.Container handle = craftInventory.getInventory();

        // TODO: When events are added, call event here w/ custom reason?
        getHandle().fillInventory(handle, nmsContext, true);
    }

    @Override
    public NamespacedKey getKey() {
        return key;
    }

    private net.minecraft.world.level.storage.loot.LootContext convertContext(LootContext context, Random random) {
        Preconditions.checkArgument(context != null, "LootContext cannot be null");
        Location loc = context.getLocation();
        Preconditions.checkArgument(loc.getWorld() != null, "LootContext.getLocation#getWorld cannot be null");
        net.minecraft.server.level.ServerLevel handle = ((CraftWorld) loc.getWorld()).getHandle();

        net.minecraft.world.level.storage.loot.LootContext.Builder builder = new net.minecraft.world.level.storage.loot.LootContext.Builder(handle);
        if (random != null) {
            builder = builder.withRandom(new RandomSourceWrapper(random));
        }
        setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.ORIGIN, new net.minecraft.world.phys.Vec3(loc.getX(), loc.getY(), loc.getZ()));
        if (getHandle() != LootTable.EMPTY) {
            // builder.luck(context.getLuck());

            if (context.getLootedEntity() != null) {
                Entity nmsLootedEntity = ((CraftEntity) context.getLootedEntity()).getHandle();
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.THIS_ENTITY, nmsLootedEntity);
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.DAMAGE_SOURCE, DamageSource.GENERIC);
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.ORIGIN, nmsLootedEntity.position());
            }

            if (context.getKiller() != null) {
                net.minecraft.world.entity.player.Player nmsKiller = ((CraftHumanEntity) context.getKiller()).getHandle();
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.KILLER_ENTITY, nmsKiller);
                // If there is a player killer, damage source should reflect that in case loot tables use that information
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.DAMAGE_SOURCE, DamageSource.playerAttack(nmsKiller));
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.LAST_DAMAGE_PLAYER, nmsKiller); // SPIGOT-5603 - Set minecraft:killed_by_player
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.TOOL, nmsKiller.getUseItem()); // SPIGOT-6925 - Set minecraft:match_tool
            }

            // SPIGOT-5603 - Use LootContext#lootingModifier
            if (context.getLootingModifier() != LootContext.DEFAULT_LOOT_MODIFIER) {
                setMaybe(builder, net.minecraft.world.level.storage.loot.parameters.LootContextParams.LOOTING_MOD, context.getLootingModifier());
            }
        }

        // SPIGOT-5603 - Avoid IllegalArgumentException in net.minecraft.world.level.storage.loot.LootContext#build()
        net.minecraft.world.level.storage.loot.parameters.LootContextParamSet.Builder nmsBuilder = new net.minecraft.world.level.storage.loot.parameters.LootContextParamSet.Builder();
        for (net.minecraft.world.level.storage.loot.parameters.LootContextParam<?> param : getHandle().getParamSet().getRequired()) {
            nmsBuilder.required(param);
        }
        for (net.minecraft.world.level.storage.loot.parameters.LootContextParam<?> param : getHandle().getParamSet().getAllowed()) {
            if (!getHandle().getParamSet().getRequired().contains(param)) {
                nmsBuilder.optional(param);
            }
        }
        nmsBuilder.optional(net.minecraft.world.level.storage.loot.parameters.LootContextParams.LOOTING_MOD);

        return builder.create(nmsBuilder.build());
    }

    private <T> void setMaybe(net.minecraft.world.level.storage.loot.LootContext.Builder builder, net.minecraft.world.level.storage.loot.parameters.LootContextParam<T> param, T value) {
        if (getHandle().getParamSet().getRequired().contains(param) || getHandle().getParamSet().getAllowed().contains(param)) {
            builder.withParameter(param, value);
        }
    }

    public static LootContext convertContext(net.minecraft.world.level.storage.loot.LootContext info) {
        net.minecraft.world.phys.Vec3 position = info.getParamOrNull(net.minecraft.world.level.storage.loot.parameters.LootContextParams.ORIGIN);
        if (position == null) {
            position = info.getParamOrNull(net.minecraft.world.level.storage.loot.parameters.LootContextParams.THIS_ENTITY).position(); // Every vanilla context has origin or this_entity, see net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
        }
        Location location = new Location(info.getLevel().getWorld(), position.x(), position.y(), position.z());
        LootContext.Builder contextBuilder = new LootContext.Builder(location);

        if (info.hasParam(net.minecraft.world.level.storage.loot.parameters.LootContextParams.KILLER_ENTITY)) {
            CraftEntity killer = info.getParamOrNull(net.minecraft.world.level.storage.loot.parameters.LootContextParams.KILLER_ENTITY).getBukkitEntity();
            if (killer instanceof CraftHumanEntity) {
                contextBuilder.killer((CraftHumanEntity) killer);
            }
        }

        if (info.hasParam(net.minecraft.world.level.storage.loot.parameters.LootContextParams.THIS_ENTITY)) {
            contextBuilder.lootedEntity(info.getParamOrNull(net.minecraft.world.level.storage.loot.parameters.LootContextParams.THIS_ENTITY).getBukkitEntity());
        }

        if (info.hasParam(net.minecraft.world.level.storage.loot.parameters.LootContextParams.LOOTING_MOD)) {
            contextBuilder.lootingModifier(info.getParamOrNull(net.minecraft.world.level.storage.loot.parameters.LootContextParams.LOOTING_MOD));
        }

        contextBuilder.luck(info.getLuck());
        return contextBuilder.build();
    }

    @Override
    public String toString() {
        return getKey().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof org.bukkit.loot.LootTable)) {
            return false;
        }

        org.bukkit.loot.LootTable table = (org.bukkit.loot.LootTable) obj;
        return table.getKey().equals(this.getKey());
    }
}