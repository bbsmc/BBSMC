package org.bukkit.craftbukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.Statistic.Type;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.bukkit.entity.EntityType;

public enum CraftStatistic {
    DAMAGE_DEALT(net.minecraft.stats.Stats.DAMAGE_DEALT),
    DAMAGE_TAKEN(net.minecraft.stats.Stats.DAMAGE_TAKEN),
    DEATHS(net.minecraft.stats.Stats.DEATHS),
    MOB_KILLS(net.minecraft.stats.Stats.MOB_KILLS),
    PLAYER_KILLS(net.minecraft.stats.Stats.PLAYER_KILLS),
    FISH_CAUGHT(net.minecraft.stats.Stats.FISH_CAUGHT),
    ANIMALS_BRED(net.minecraft.stats.Stats.ANIMALS_BRED),
    LEAVE_GAME(net.minecraft.stats.Stats.LEAVE_GAME),
    JUMP(net.minecraft.stats.Stats.JUMP),
    DROP_COUNT(net.minecraft.stats.Stats.DROP),
    DROP(new net.minecraft.resources.ResourceLocation("dropped")),
    PICKUP(new net.minecraft.resources.ResourceLocation("picked_up")),
    PLAY_ONE_MINUTE(net.minecraft.stats.Stats.PLAY_TIME),
    TOTAL_WORLD_TIME(net.minecraft.stats.Stats.TOTAL_WORLD_TIME),
    WALK_ONE_CM(net.minecraft.stats.Stats.WALK_ONE_CM),
    WALK_ON_WATER_ONE_CM(net.minecraft.stats.Stats.WALK_ON_WATER_ONE_CM),
    FALL_ONE_CM(net.minecraft.stats.Stats.FALL_ONE_CM),
    SNEAK_TIME(net.minecraft.stats.Stats.CROUCH_TIME),
    CLIMB_ONE_CM(net.minecraft.stats.Stats.CLIMB_ONE_CM),
    FLY_ONE_CM(net.minecraft.stats.Stats.FLY_ONE_CM),
    WALK_UNDER_WATER_ONE_CM(net.minecraft.stats.Stats.WALK_UNDER_WATER_ONE_CM),
    MINECART_ONE_CM(net.minecraft.stats.Stats.MINECART_ONE_CM),
    BOAT_ONE_CM(net.minecraft.stats.Stats.BOAT_ONE_CM),
    PIG_ONE_CM(net.minecraft.stats.Stats.PIG_ONE_CM),
    HORSE_ONE_CM(net.minecraft.stats.Stats.HORSE_ONE_CM),
    SPRINT_ONE_CM(net.minecraft.stats.Stats.SPRINT_ONE_CM),
    CROUCH_ONE_CM(net.minecraft.stats.Stats.CROUCH_ONE_CM),
    AVIATE_ONE_CM(net.minecraft.stats.Stats.AVIATE_ONE_CM),
    MINE_BLOCK(new net.minecraft.resources.ResourceLocation("mined")),
    USE_ITEM(new net.minecraft.resources.ResourceLocation("used")),
    BREAK_ITEM(new net.minecraft.resources.ResourceLocation("broken")),
    CRAFT_ITEM(new net.minecraft.resources.ResourceLocation("crafted")),
    KILL_ENTITY(new net.minecraft.resources.ResourceLocation("killed")),
    ENTITY_KILLED_BY(new net.minecraft.resources.ResourceLocation("killed_by")),
    TIME_SINCE_DEATH(net.minecraft.stats.Stats.TIME_SINCE_DEATH),
    TALKED_TO_VILLAGER(net.minecraft.stats.Stats.TALKED_TO_VILLAGER),
    TRADED_WITH_VILLAGER(net.minecraft.stats.Stats.TRADED_WITH_VILLAGER),
    CAKE_SLICES_EATEN(net.minecraft.stats.Stats.EAT_CAKE_SLICE),
    CAULDRON_FILLED(net.minecraft.stats.Stats.FILL_CAULDRON),
    CAULDRON_USED(net.minecraft.stats.Stats.USE_CAULDRON),
    ARMOR_CLEANED(net.minecraft.stats.Stats.CLEAN_ARMOR),
    BANNER_CLEANED(net.minecraft.stats.Stats.CLEAN_BANNER),
    BREWINGSTAND_INTERACTION(net.minecraft.stats.Stats.INTERACT_WITH_BREWINGSTAND),
    BEACON_INTERACTION(net.minecraft.stats.Stats.INTERACT_WITH_BEACON),
    DROPPER_INSPECTED(net.minecraft.stats.Stats.INSPECT_DROPPER),
    HOPPER_INSPECTED(net.minecraft.stats.Stats.INSPECT_HOPPER),
    DISPENSER_INSPECTED(net.minecraft.stats.Stats.INSPECT_DISPENSER),
    NOTEBLOCK_PLAYED(net.minecraft.stats.Stats.PLAY_NOTEBLOCK),
    NOTEBLOCK_TUNED(net.minecraft.stats.Stats.TUNE_NOTEBLOCK),
    FLOWER_POTTED(net.minecraft.stats.Stats.POT_FLOWER),
    TRAPPED_CHEST_TRIGGERED(net.minecraft.stats.Stats.TRIGGER_TRAPPED_CHEST),
    ENDERCHEST_OPENED(net.minecraft.stats.Stats.OPEN_ENDERCHEST),
    ITEM_ENCHANTED(net.minecraft.stats.Stats.ENCHANT_ITEM),
    RECORD_PLAYED(net.minecraft.stats.Stats.PLAY_RECORD),
    FURNACE_INTERACTION(net.minecraft.stats.Stats.INTERACT_WITH_FURNACE),
    CRAFTING_TABLE_INTERACTION(net.minecraft.stats.Stats.INTERACT_WITH_CRAFTING_TABLE),
    CHEST_OPENED(net.minecraft.stats.Stats.OPEN_CHEST),
    SLEEP_IN_BED(net.minecraft.stats.Stats.SLEEP_IN_BED),
    SHULKER_BOX_OPENED(net.minecraft.stats.Stats.OPEN_SHULKER_BOX),
    TIME_SINCE_REST(net.minecraft.stats.Stats.TIME_SINCE_REST),
    SWIM_ONE_CM(net.minecraft.stats.Stats.SWIM_ONE_CM),
    DAMAGE_DEALT_ABSORBED(net.minecraft.stats.Stats.DAMAGE_DEALT_ABSORBED),
    DAMAGE_DEALT_RESISTED(net.minecraft.stats.Stats.DAMAGE_DEALT_RESISTED),
    DAMAGE_BLOCKED_BY_SHIELD(net.minecraft.stats.Stats.DAMAGE_BLOCKED_BY_SHIELD),
    DAMAGE_ABSORBED(net.minecraft.stats.Stats.DAMAGE_ABSORBED),
    DAMAGE_RESISTED(net.minecraft.stats.Stats.DAMAGE_RESISTED),
    CLEAN_SHULKER_BOX(net.minecraft.stats.Stats.CLEAN_SHULKER_BOX),
    OPEN_BARREL(net.minecraft.stats.Stats.OPEN_BARREL),
    INTERACT_WITH_BLAST_FURNACE(net.minecraft.stats.Stats.INTERACT_WITH_BLAST_FURNACE),
    INTERACT_WITH_SMOKER(net.minecraft.stats.Stats.INTERACT_WITH_SMOKER),
    INTERACT_WITH_LECTERN(net.minecraft.stats.Stats.INTERACT_WITH_LECTERN),
    INTERACT_WITH_CAMPFIRE(net.minecraft.stats.Stats.INTERACT_WITH_CAMPFIRE),
    INTERACT_WITH_CARTOGRAPHY_TABLE(net.minecraft.stats.Stats.INTERACT_WITH_CARTOGRAPHY_TABLE),
    INTERACT_WITH_LOOM(net.minecraft.stats.Stats.INTERACT_WITH_LOOM),
    INTERACT_WITH_STONECUTTER(net.minecraft.stats.Stats.INTERACT_WITH_STONECUTTER),
    BELL_RING(net.minecraft.stats.Stats.BELL_RING),
    RAID_TRIGGER(net.minecraft.stats.Stats.RAID_TRIGGER),
    RAID_WIN(net.minecraft.stats.Stats.RAID_WIN),
    INTERACT_WITH_ANVIL(net.minecraft.stats.Stats.INTERACT_WITH_ANVIL),
    INTERACT_WITH_GRINDSTONE(net.minecraft.stats.Stats.INTERACT_WITH_GRINDSTONE),
    TARGET_HIT(net.minecraft.stats.Stats.TARGET_HIT),
    INTERACT_WITH_SMITHING_TABLE(net.minecraft.stats.Stats.INTERACT_WITH_SMITHING_TABLE),
    STRIDER_ONE_CM(net.minecraft.stats.Stats.STRIDER_ONE_CM);
    private final net.minecraft.resources.ResourceLocation minecraftKey;
    private final org.bukkit.Statistic bukkit;
    private static final BiMap<net.minecraft.resources.ResourceLocation, org.bukkit.Statistic> statistics;

    static {
        ImmutableBiMap.Builder<net.minecraft.resources.ResourceLocation, org.bukkit.Statistic> statisticBuilder = ImmutableBiMap.builder();
        for (CraftStatistic statistic : CraftStatistic.values()) {
            statisticBuilder.put(statistic.minecraftKey, statistic.bukkit);
        }

        statistics = statisticBuilder.build();
    }

    private CraftStatistic(net.minecraft.resources.ResourceLocation minecraftKey) {
        this.minecraftKey = minecraftKey;

        this.bukkit = org.bukkit.Statistic.valueOf(this.name());
        Preconditions.checkState(bukkit != null, "Bukkit statistic %s does not exist", this.name());
    }

    public static org.bukkit.Statistic getBukkitStatistic(net.minecraft.stats.Stat<?> statistic) {
        net.minecraft.core.Registry statRegistry = statistic.getType().getRegistry();
        net.minecraft.resources.ResourceLocation nmsKey = net.minecraft.core.Registry.STAT_TYPE.getKey(statistic.getType());

        if (statRegistry == net.minecraft.core.Registry.CUSTOM_STAT) {
            nmsKey = (net.minecraft.resources.ResourceLocation) statistic.getValue();
        }

        return statistics.get(nmsKey);
    }

    public static net.minecraft.stats.Stat getNMSStatistic(org.bukkit.Statistic bukkit) {
        Preconditions.checkArgument(bukkit.getType() == Statistic.Type.UNTYPED, "This method only accepts untyped statistics");

        net.minecraft.stats.Stat<net.minecraft.resources.ResourceLocation> nms = net.minecraft.stats.Stats.CUSTOM.get(statistics.inverse().get(bukkit));
        Preconditions.checkArgument(nms != null, "NMS Statistic %s does not exist", bukkit);

        return nms;
    }

    public static net.minecraft.stats.Stat getMaterialStatistic(org.bukkit.Statistic stat, Material material) {
        try {
            if (stat == Statistic.MINE_BLOCK) {
                return net.minecraft.stats.Stats.BLOCK_MINED.get(CraftMagicNumbers.getBlock(material));
            }
            if (stat == Statistic.CRAFT_ITEM) {
                return net.minecraft.stats.Stats.ITEM_CRAFTED.get(CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.USE_ITEM) {
                return net.minecraft.stats.Stats.ITEM_USED.get(CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.BREAK_ITEM) {
                return net.minecraft.stats.Stats.ITEM_BROKEN.get(CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.PICKUP) {
                return net.minecraft.stats.Stats.ITEM_PICKED_UP.get(CraftMagicNumbers.getItem(material));
            }
            if (stat == Statistic.DROP) {
                return net.minecraft.stats.Stats.ITEM_DROPPED.get(CraftMagicNumbers.getItem(material));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }

    public static net.minecraft.stats.Stat getEntityStatistic(org.bukkit.Statistic stat, EntityType entity) {
        if (entity.getName() != null) {
            net.minecraft.world.entity.EntityType<?> nmsEntity = net.minecraft.core.Registry.ENTITY_TYPE.get(new net.minecraft.resources.ResourceLocation(entity.getName()));

            if (stat == org.bukkit.Statistic.KILL_ENTITY) {
                return net.minecraft.stats.Stats.ENTITY_KILLED.get(nmsEntity);
            }
            if (stat == org.bukkit.Statistic.ENTITY_KILLED_BY) {
                return net.minecraft.stats.Stats.ENTITY_KILLED_BY.get(nmsEntity);
            }
        }
        return null;
    }

    public static EntityType getEntityTypeFromStatistic(net.minecraft.stats.Stat<net.minecraft.world.entity.EntityType<?>> statistic) {
        net.minecraft.resources.ResourceLocation name = net.minecraft.world.entity.EntityType.getKey(statistic.getValue());
        return EntityType.fromName(name.getPath());
    }

    public static Material getMaterialFromStatistic(net.minecraft.stats.Stat<?> statistic) {
        if (statistic.getValue() instanceof Item) {
            return CraftMagicNumbers.getMaterial((Item) statistic.getValue());
        }
        if (statistic.getValue() instanceof Block) {
            return CraftMagicNumbers.getMaterial((Block) statistic.getValue());
        }
        return null;
    }

    public static void incrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic) {
        incrementStatistic(manager, statistic, 1);
    }

    public static void decrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic) {
        decrementStatistic(manager, statistic, 1);
    }

    public static int getStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic) {
        Validate.notNull(statistic, "Statistic cannot be null");
        Validate.isTrue(statistic.getType() == Type.UNTYPED, "Must supply additional paramater for this statistic");
        return manager.getValue(CraftStatistic.getNMSStatistic(statistic));
    }

    public static void incrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, int amount) {
        Validate.isTrue(amount > 0, "Amount must be greater than 0");
        setStatistic(manager, statistic, getStatistic(manager, statistic) + amount);
    }

    public static void decrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, int amount) {
        Validate.isTrue(amount > 0, "Amount must be greater than 0");
        setStatistic(manager, statistic, getStatistic(manager, statistic) - amount);
    }

    public static void setStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, int newValue) {
        Validate.notNull(statistic, "Statistic cannot be null");
        Validate.isTrue(statistic.getType() == Type.UNTYPED, "Must supply additional paramater for this statistic");
        Validate.isTrue(newValue >= 0, "Value must be greater than or equal to 0");
        net.minecraft.stats.Stat nmsStatistic = CraftStatistic.getNMSStatistic(statistic);
        manager.setValue(null, nmsStatistic, newValue);;
    }

    public static void incrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, Material material) {
        incrementStatistic(manager, statistic, material, 1);
    }

    public static void decrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, Material material) {
        decrementStatistic(manager, statistic, material, 1);
    }

    public static int getStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, Material material) {
        Validate.notNull(statistic, "Statistic cannot be null");
        Validate.notNull(material, "Material cannot be null");
        Validate.isTrue(statistic.getType() == Type.BLOCK || statistic.getType() == Type.ITEM, "This statistic does not take a Material parameter");
        net.minecraft.stats.Stat nmsStatistic = CraftStatistic.getMaterialStatistic(statistic, material);
        Validate.notNull(nmsStatistic, "The supplied Material does not have a corresponding statistic");
        return manager.getValue(nmsStatistic);
    }

    public static void incrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, Material material, int amount) {
        Validate.isTrue(amount > 0, "Amount must be greater than 0");
        setStatistic(manager, statistic, material, getStatistic(manager, statistic, material) + amount);
    }

    public static void decrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, Material material, int amount) {
        Validate.isTrue(amount > 0, "Amount must be greater than 0");
        setStatistic(manager, statistic, material, getStatistic(manager, statistic, material) - amount);
    }

    public static void setStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, Material material, int newValue) {
        Validate.notNull(statistic, "Statistic cannot be null");
        Validate.notNull(material, "Material cannot be null");
        Validate.isTrue(newValue >= 0, "Value must be greater than or equal to 0");
        Validate.isTrue(statistic.getType() == Type.BLOCK || statistic.getType() == Type.ITEM, "This statistic does not take a Material parameter");
        net.minecraft.stats.Stat nmsStatistic = CraftStatistic.getMaterialStatistic(statistic, material);
        Validate.notNull(nmsStatistic, "The supplied Material does not have a corresponding statistic");
        manager.setValue(null, nmsStatistic, newValue);
    }

    public static void incrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, EntityType entityType) {
        incrementStatistic(manager, statistic, entityType, 1);
    }

    public static void decrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, EntityType entityType) {
        decrementStatistic(manager, statistic, entityType, 1);
    }

    public static int getStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, EntityType entityType) {
        Validate.notNull(statistic, "Statistic cannot be null");
        Validate.notNull(entityType, "EntityType cannot be null");
        Validate.isTrue(statistic.getType() == Type.ENTITY, "This statistic does not take an EntityType parameter");
        net.minecraft.stats.Stat nmsStatistic = CraftStatistic.getEntityStatistic(statistic, entityType);
        Validate.notNull(nmsStatistic, "The supplied EntityType does not have a corresponding statistic");
        return manager.getValue(nmsStatistic);
    }

    public static void incrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, EntityType entityType, int amount) {
        Validate.isTrue(amount > 0, "Amount must be greater than 0");
        setStatistic(manager, statistic, entityType, getStatistic(manager, statistic, entityType) + amount);
    }

    public static void decrementStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, EntityType entityType, int amount) {
        Validate.isTrue(amount > 0, "Amount must be greater than 0");
        setStatistic(manager, statistic, entityType, getStatistic(manager, statistic, entityType) - amount);
    }

    public static void setStatistic(net.minecraft.stats.ServerStatsCounter manager, Statistic statistic, EntityType entityType, int newValue) {
        Validate.notNull(statistic, "Statistic cannot be null");
        Validate.notNull(entityType, "EntityType cannot be null");
        Validate.isTrue(newValue >= 0, "Value must be greater than or equal to 0");
        Validate.isTrue(statistic.getType() == Type.ENTITY, "This statistic does not take an EntityType parameter");
        net.minecraft.stats.Stat nmsStatistic = CraftStatistic.getEntityStatistic(statistic, entityType);
        Validate.notNull(nmsStatistic, "The supplied EntityType does not have a corresponding statistic");
        manager.setValue(null, nmsStatistic, newValue);
    }
}