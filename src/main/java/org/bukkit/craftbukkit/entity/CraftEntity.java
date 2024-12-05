package org.bukkit.craftbukkit.entity;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.bbsmc.server.world.entity.CraftEntityClassLookup;
import net.bbsmc.server.world.entity.impl.CraftFakePlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraftforge.common.util.FakePlayer;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftSound;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.craftbukkit.util.CraftChatMessage;
import org.bukkit.craftbukkit.util.CraftSpawnCategory;
import org.bukkit.craftbukkit.util.CraftVector;
import org.bukkit.entity.Pose;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

public abstract class CraftEntity implements org.bukkit.entity.Entity {
    private static PermissibleBase perm;
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();

    protected final CraftServer server;
    protected Entity entity;
    private EntityDamageEvent lastDamageEvent;
    private final CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);

    public CraftEntity(final CraftServer server, final Entity entity) {
        this.server = server;
        this.entity = entity;
    }

    public static CraftEntity getEntity(CraftServer server, Entity entity) {
        if (entity instanceof FakePlayer) {
            return new CraftFakePlayer(server, (FakePlayer) entity);
        }
        if (entity instanceof EnderDragonPart enderDragonPart) {
            if (enderDragonPart.parentMob instanceof EnderDragon) {
                return new CraftEnderDragonPart(server, (EnderDragonPart) entity);
            }
            return new CraftComplexPart(server, enderDragonPart);
        }
        return (CraftEntity) CraftEntityClassLookup.getConvert(entity).apply(server, entity);
    }

    @Override
    public Location getLocation() {
        return new Location(getWorld(), entity.getX(), entity.getY(), entity.getZ(), entity.getBukkitYaw(), entity.getXRot());
    }

    @Override
    public Location getLocation(Location loc) {
        if (loc != null) {
            loc.setWorld(getWorld());
            loc.setX(entity.getX());
            loc.setY(entity.getY());
            loc.setZ(entity.getZ());
            loc.setYaw(entity.getBukkitYaw());
            loc.setPitch(entity.getXRot());
        }

        return loc;
    }

    @Override
    public Vector getVelocity() {
        return CraftVector.toBukkit(entity.getDeltaMovement());
    }

    @Override
    public void setVelocity(Vector velocity) {
        Preconditions.checkArgument(velocity != null, "velocity");
        velocity.checkFinite();
        entity.setDeltaMovement(CraftVector.toNMS(velocity));
        entity.hurtMarked = true;
    }

    @Override
    public double getHeight() {
        return getHandle().getBbHeight();
    }

    @Override
    public double getWidth() {
        return getHandle().getBbWidth();
    }

    @Override
    public BoundingBox getBoundingBox() {
        net.minecraft.world.phys.AABB bb = getHandle().getBoundingBox();
        return new BoundingBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ);
    }

    @Override
    public boolean isOnGround() {
        if (entity instanceof net.minecraft.world.entity.projectile.AbstractArrow) {
            return ((net.minecraft.world.entity.projectile.AbstractArrow) entity).inGround;
        }
        return entity.isOnGround();
    }

    @Override
    public boolean isInWater() {
        return entity.isInWater();
    }

    @Override
    public World getWorld() {
        return entity.level.getWorld();
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        NumberConversions.checkFinite(pitch, "pitch not finite");
        NumberConversions.checkFinite(yaw, "yaw not finite");

        yaw = Location.normalizeYaw(yaw);
        pitch = Location.normalizePitch(pitch);

        entity.setYRot(yaw);
        entity.setXRot(pitch);
        entity.yRotO = yaw;
        entity.xRotO = pitch;
        entity.setYHeadRot(yaw);
    }

    @Override
    public boolean teleport(Location location) {
        return teleport(location, TeleportCause.PLUGIN);
    }

    @Override
    public boolean teleport(Location location, TeleportCause cause) {
        Preconditions.checkArgument(location != null, "location cannot be null");
        location.checkFinite();

        if (entity.isVehicle() || entity.isRemoved()) {
            return false;
        }

        // If this entity is riding another entity, we must dismount before teleporting.
        entity.stopRiding();

        // Let the server handle cross world teleports
        if (location.getWorld() != null && !location.getWorld().equals(getWorld())) {
            // Prevent teleportation to an other world during world generation
            Preconditions.checkState(!entity.generation, "Cannot teleport entity to an other world during world generation");
            entity.teleportTo(((CraftWorld) location.getWorld()).getHandle(), new net.minecraft.core.PositionImpl(location.getX(), location.getY(), location.getZ()));
            return true;
        }

        // entity.setLocation() throws no event, and so cannot be cancelled
        entity.absMoveTo(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        // SPIGOT-619: Force sync head rotation also
        entity.setYHeadRot(location.getYaw());

        return true;
    }

    @Override
    public boolean teleport(org.bukkit.entity.Entity destination) {
        return teleport(destination.getLocation());
    }

    @Override
    public boolean teleport(org.bukkit.entity.Entity destination, TeleportCause cause) {
        return teleport(destination.getLocation(), cause);
    }

    @Override
    public List<org.bukkit.entity.Entity> getNearbyEntities(double x, double y, double z) {
        Preconditions.checkState(!entity.generation, "Cannot get nearby entities during world generation");

        List<Entity> notchEntityList = entity.level.getEntities(entity, entity.getBoundingBox().inflate(x, y, z), Predicates.alwaysTrue());
        List<org.bukkit.entity.Entity> bukkitEntityList = new java.util.ArrayList<org.bukkit.entity.Entity>(notchEntityList.size());

        for (Entity e : notchEntityList) {
            bukkitEntityList.add(e.getBukkitEntity());
        }
        return bukkitEntityList;
    }

    @Override
    public int getEntityId() {
        return entity.getId();
    }

    @Override
    public int getFireTicks() {
        return entity.remainingFireTicks;
    }

    @Override
    public int getMaxFireTicks() {
        return entity.getFireImmuneTicks();
    }

    @Override
    public void setFireTicks(int ticks) {
        entity.remainingFireTicks = ticks;
    }

    @Override
    public void setVisualFire(boolean fire) {
        getHandle().hasVisualFire = fire;
    }

    @Override
    public boolean isVisualFire() {
        return getHandle().hasVisualFire;
    }

    @Override
    public int getFreezeTicks() {
        return getHandle().getTicksFrozen();
    }

    @Override
    public int getMaxFreezeTicks() {
        return getHandle().getTicksRequiredToFreeze();
    }

    @Override
    public void setFreezeTicks(int ticks) {
        Preconditions.checkArgument(0 <= ticks, "Ticks cannot be less than 0");

        getHandle().setTicksFrozen(ticks);
    }

    @Override
    public boolean isFrozen() {
        return getHandle().isFullyFrozen();
    }

    @Override
    public void remove() {
        entity.discard();
    }

    @Override
    public boolean isDead() {
        return !entity.isAlive();
    }

    @Override
    public boolean isValid() {
        return entity.isAlive() && entity.valid && entity.isChunkLoaded();
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public boolean isPersistent() {
        return entity.persist;
    }

    @Override
    public void setPersistent(boolean persistent) {
        entity.persist = persistent;
    }

    public Vector getMomentum() {
        return getVelocity();
    }

    public void setMomentum(Vector value) {
        setVelocity(value);
    }

    @Override
    public org.bukkit.entity.Entity getPassenger() {
        return isEmpty() ? null : getHandle().passengers.get(0).getBukkitEntity();
    }

    @Override
    public boolean setPassenger(org.bukkit.entity.Entity passenger) {
        Preconditions.checkArgument(!this.equals(passenger), "Entity cannot ride itself.");
        if (passenger instanceof CraftEntity) {
            eject();
            return ((CraftEntity) passenger).getHandle().startRiding(getHandle());
        } else {
            return false;
        }
    }

    @Override
    public List<org.bukkit.entity.Entity> getPassengers() {
        return Lists.newArrayList(Lists.transform(getHandle().passengers, new Function<Entity, org.bukkit.entity.Entity>() {
            @Override
            public org.bukkit.entity.Entity apply(Entity input) {
                return input.getBukkitEntity();
            }
        }));
    }

    @Override
    public boolean addPassenger(org.bukkit.entity.Entity passenger) {
        Preconditions.checkArgument(passenger != null, "passenger == null");
        Preconditions.checkArgument(!this.equals(passenger), "Entity cannot ride itself.");

        return ((CraftEntity) passenger).getHandle().startRiding(getHandle(), true);
    }

    @Override
    public boolean removePassenger(org.bukkit.entity.Entity passenger) {
        Preconditions.checkArgument(passenger != null, "passenger == null");

        ((CraftEntity) passenger).getHandle().stopRiding();
        return true;
    }

    @Override
    public boolean isEmpty() {
        return !getHandle().isVehicle();
    }

    @Override
    public boolean eject() {
        if (isEmpty()) {
            return false;
        }

        getHandle().ejectPassengers();
        return true;
    }

    @Override
    public float getFallDistance() {
        return getHandle().fallDistance;
    }

    @Override
    public void setFallDistance(float distance) {
        getHandle().fallDistance = distance;
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        lastDamageEvent = event;
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return lastDamageEvent;
    }

    @Override
    public UUID getUniqueId() {
        return getHandle().getUUID();
    }

    @Override
    public int getTicksLived() {
        return getHandle().tickCount;
    }

    @Override
    public void setTicksLived(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Age must be at least 1 tick");
        }
        getHandle().tickCount = value;
    }

    public Entity getHandle() {
        return entity;
    }

    @Override
    public void playEffect(EntityEffect type) {
        Preconditions.checkArgument(type != null, "type");
        Preconditions.checkState(!entity.generation, "Cannot play effect during world generation");

        if (type.getApplicable().isInstance(this)) {
            this.getHandle().level.broadcastEntityEvent(getHandle(), type.getData());
        }
    }

    @Override
    public Sound getSwimSound() {
        return CraftSound.getBukkit(getHandle().getSwimSound0());
    }

    @Override
    public Sound getSwimSplashSound() {
        return CraftSound.getBukkit(getHandle().getSwimSplashSound0());
    }

    @Override
    public Sound getSwimHighSpeedSplashSound() {
        return CraftSound.getBukkit(getHandle().getSwimHighSpeedSplashSound0());
    }

    public void setHandle(final Entity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "CraftEntity{" + "id=" + getEntityId() + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CraftEntity other = (CraftEntity) obj;
        return (this.getEntityId() == other.getEntityId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.getEntityId();
        return hash;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        server.getEntityMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return server.getEntityMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return server.getEntityMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        server.getEntityMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public boolean isInsideVehicle() {
        return getHandle().isPassenger();
    }

    @Override
    public boolean leaveVehicle() {
        if (!isInsideVehicle()) {
            return false;
        }

        getHandle().stopRiding();
        return true;
    }

    @Override
    public org.bukkit.entity.Entity getVehicle() {
        if (!isInsideVehicle()) {
            return null;
        }

        return getHandle().getVehicle().getBukkitEntity();
    }

    @Override
    public void setCustomName(String name) {
        // sane limit for name length
        if (name != null && name.length() > 256) {
            name = name.substring(0, 256);
        }

        getHandle().setCustomName(CraftChatMessage.fromStringOrNull(name));
    }

    @Override
    public String getCustomName() {
        net.minecraft.network.chat.Component name = getHandle().getCustomName();

        if (name == null) {
            return null;
        }

        return CraftChatMessage.fromComponent(name);
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        getHandle().setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return getHandle().isCustomNameVisible();
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendMessage(String... messages) {

    }

    @Override
    public void sendMessage(UUID sender, String message) {
        this.sendMessage(message); // Most entities don't know about senders
    }

    @Override
    public void sendMessage(UUID sender, String... messages) {
        this.sendMessage(messages); // Most entities don't know about senders
    }

    @Override
    public String getName() {
        return CraftChatMessage.fromComponent(getHandle().getName());
    }

    @Override
    public boolean isPermissionSet(String name) {
        return getPermissibleBase().isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return CraftEntity.getPermissibleBase().isPermissionSet(perm);
    }

    @Override
    public boolean hasPermission(String name) {
        return getPermissibleBase().hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return getPermissibleBase().hasPermission(perm);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return getPermissibleBase().addAttachment(plugin, name, value);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return getPermissibleBase().addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return getPermissibleBase().addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return getPermissibleBase().addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(PermissionAttachment attachment) {
        getPermissibleBase().removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        getPermissibleBase().recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return getPermissibleBase().getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return getPermissibleBase().isOp();
    }

    @Override
    public void setOp(boolean value) {
        getPermissibleBase().setOp(value);
    }

    @Override
    public void setGlowing(boolean flag) {
        getHandle().setGlowingTag(flag);
    }

    @Override
    public boolean isGlowing() {
        return getHandle().isCurrentlyGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        getHandle().setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return getHandle().isInvulnerableTo(DamageSource.GENERIC);
    }

    @Override
    public boolean isSilent() {
        return getHandle().isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        getHandle().setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return !getHandle().isNoGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        getHandle().setNoGravity(!gravity);
    }

    @Override
    public int getPortalCooldown() {
        return getHandle().portalCooldown;
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        getHandle().portalCooldown = cooldown;
    }

    @Override
    public Set<String> getScoreboardTags() {
        return getHandle().getTags();
    }

    @Override
    public boolean addScoreboardTag(String tag) {
        return getHandle().addTag(tag);
    }

    @Override
    public boolean removeScoreboardTag(String tag) {
        return getHandle().removeTag(tag);
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return PistonMoveReaction.getById(getHandle().getPistonPushReaction().ordinal());
    }

    @Override
    public BlockFace getFacing() {
        // Use this method over getDirection because it handles boats and minecarts.
        return CraftBlock.notchToBlockFace(getHandle().getMotionDirection());
    }

    @Override
    public CraftPersistentDataContainer getPersistentDataContainer() {
        return persistentDataContainer;
    }

    @Override
    public Pose getPose() {
        return Pose.values()[getHandle().getPose().ordinal()];
    }

    @Override
    public SpawnCategory getSpawnCategory() {
        return CraftSpawnCategory.toBukkit(getHandle().getType().getCategory());
    }

    public void storeBukkitValues(net.minecraft.nbt.CompoundTag c) {
        if (!this.persistentDataContainer.isEmpty()) {
            c.put("BukkitValues", this.persistentDataContainer.toTagCompound());
        }
    }

    public void readBukkitValues(net.minecraft.nbt.CompoundTag c) {
        net.minecraft.nbt.Tag base = c.get("BukkitValues");
        if (base instanceof net.minecraft.nbt.CompoundTag) {
            this.persistentDataContainer.putAll((net.minecraft.nbt.CompoundTag) base);
        }
    }

    protected net.minecraft.nbt.CompoundTag save() {
        net.minecraft.nbt.CompoundTag nbttagcompound = new net.minecraft.nbt.CompoundTag();

        nbttagcompound.putString("id", getHandle().getEncodeId());
        getHandle().saveWithoutId(nbttagcompound);

        return nbttagcompound;
    }

    // re-sends the spawn entity packet to updated values which cannot be updated otherwise
    protected void update() {
        if (!getHandle().isAlive()) {
            return;
        }

        net.minecraft.server.level.ServerLevel world = ((CraftWorld) getWorld()).getHandle();
        net.minecraft.server.level.ChunkTracker.EntityTracker entityTracker = world.getChunkSource().chunkMap.entityMap.get(getEntityId());

        if (entityTracker == null) {
            return;
        }

        entityTracker.broadcast(getHandle().getAddEntityPacket());
    }

    private static PermissibleBase getPermissibleBase() {
        if (perm == null) {
            perm = new PermissibleBase(new ServerOperator() {

                @Override
                public boolean isOp() {
                    return false;
                }

                @Override
                public void setOp(boolean value) {

                }
            });
        }
        return perm;
    }
}