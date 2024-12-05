package net.bbsmc.server.world.entity;

import io.izzel.arclight.api.Unsafe;
import net.bbsmc.server.BBSMcServer;
import net.bbsmc.server.world.entity.impl.CraftCustomAbstractSkeleton;
import net.bbsmc.server.world.entity.impl.CraftCustomChestedHorse;
import net.bbsmc.server.world.entity.impl.CraftCustomEntity;
import net.bbsmc.server.world.entity.impl.CraftCustomHorse;
import net.bbsmc.server.world.entity.impl.CraftCustomMinecart;
import net.bbsmc.server.world.entity.impl.CraftCustomMinecartContainer;
import net.bbsmc.server.world.entity.impl.CraftCustomMob;
import net.bbsmc.server.world.entity.impl.CraftCustomProjectile;
import net.bbsmc.server.world.entity.impl.CraftCustomRaider;
import net.bbsmc.server.world.entity.impl.CraftCustomThrowableProjectile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Marker;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.entity.vehicle.MinecartCommandBlock;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.entity.vehicle.MinecartHopper;
import net.minecraft.world.entity.vehicle.MinecartSpawner;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftEntity;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class CraftEntityClassLookup {
    public static void init() {
        var allEntityClasses = new HashSet<Class<?>>();
        for (var bukkitType : org.bukkit.entity.EntityType.values()) {
            Class<? extends org.bukkit.entity.Entity> entityClass = bukkitType.getEntityClass();
            if (entityClass != null && !allEntityClasses.contains(entityClass)) {
                var next = new LinkedList<Class<?>>();
                next.add(entityClass);
                while (!next.isEmpty()) {
                    Class<?> cl = next.pollFirst();
                    if (!allEntityClasses.contains(cl)) {
                        allEntityClasses.add(cl);
                        for (Class<?> intf : cl.getInterfaces()) {
                            if (org.bukkit.entity.Entity.class.isAssignableFrom(intf)) {
                                next.addLast(intf);
                            }
                        }
                    }
                }
            }
        }
        Set<Class<?>> ignored = Set.of(
                org.bukkit.entity.Explosive.class,
                org.bukkit.entity.Damageable.class,
                org.bukkit.entity.NPC.class,
                org.bukkit.entity.Boss.class,
                org.bukkit.entity.Breedable.class,
                org.bukkit.entity.Steerable.class,
                org.bukkit.entity.ComplexLivingEntity.class,
                org.bukkit.entity.Vehicle.class
        );
        boolean error = false;
        for (Class<?> entityClass : allEntityClasses) {
            if (ignored.contains(entityClass)) continue;
            var optional = NMS_TO_BUKKIT.values().stream().filter(c -> c.bukkitClass == entityClass).findAny();
            if (optional.isEmpty()) {
                error = true;
                BBSMcServer.log.error("{} has no valid entity class mapping", entityClass);
            }
        }
        if (error) {
            throw new RuntimeException("Missing valid entity class mapping");
        }
    }

    private static final Map<Class<?>, EntityClass<?>> nmsClassMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends Entity> BiFunction<CraftServer, T, org.bukkit.entity.Entity> getConvert(T entity) {
        return (BiFunction<CraftServer, T, org.bukkit.entity.Entity>) nmsClassMap.computeIfAbsent(entity.getClass(), k -> getEntityTypeData(k, entity.getType())).convert;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityClass<T> getEntityTypeData(Class<?> type, EntityType<T> entityType) {
        EntityClass<?> entityClass = null;
        for (Class<?> c = type; entityClass == null; c = c.getSuperclass()) {
            entityClass = NMS_TO_BUKKIT.get(c);
        }
        return (EntityClass<T>) Objects.requireNonNull(entityClass, "entityClass");
    }

    private record EntityClass<T extends Entity>(Class<? extends org.bukkit.entity.Entity> bukkitClass,
                                                 Class<? extends CraftEntity> implClass,
                                                 BiFunction<CraftServer, T, org.bukkit.entity.Entity> convert) {
        private EntityClass {
            if (!bukkitClass.isAssignableFrom(implClass)) {
                throw new IllegalArgumentException(bukkitClass + " " + implClass);
            }
        }
    }

    private static final Map<Class<?>, EntityClass<?>> NMS_TO_BUKKIT = new HashMap<>();

    private static <U extends V, V extends Entity> void add(Class<? super U> cl, EntityClass<? super V> entityClass) {
        if (NMS_TO_BUKKIT.put(cl, entityClass) != null) {
            throw new IllegalStateException("Duplicate " + cl + " mapping");
        }
    }

    private static Class<? extends CraftEntity> forName(String name) {
        try {
            return Class.forName(CraftEntity.class.getPackageName() + "." + name).asSubclass(CraftEntity.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> BiFunction<CraftServer, T, org.bukkit.entity.Entity> convert(String name) {
        try {
            Class<? extends CraftEntity> cl = forName(name);
            for (Constructor<?> constructor : cl.getDeclaredConstructors()) {
                if (constructor.getParameterCount() == 2) {
                    var pTypes = constructor.getParameterTypes();
                    if (pTypes[0].equals(CraftServer.class) && Entity.class.isAssignableFrom(pTypes[1])) {
                        constructor.setAccessible(true);
                        var lookup = Unsafe.lookup().in(constructor.getDeclaringClass());
                        return (BiFunction<CraftServer, T, org.bukkit.entity.Entity>) LambdaMetafactory.metafactory(
                                lookup, "apply",
                                MethodType.methodType(BiFunction.class),
                                MethodType.methodType(Object.class, Object.class, Object.class),
                                lookup.unreflectConstructor(constructor),
                                lookup.unreflectConstructor(constructor).type()
                        ).dynamicInvoker().invoke();
                    }
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("convert");
    }

    static {
        // abstract types
        add(Entity.class, new EntityClass<>(org.bukkit.entity.Entity.class, CraftCustomEntity.class, CraftCustomEntity::new));
        add(AbstractSkeleton.class, new EntityClass<>(org.bukkit.entity.AbstractSkeleton.class, CraftCustomAbstractSkeleton.class, CraftCustomAbstractSkeleton::new));
        add(Mob.class, new EntityClass<>(org.bukkit.entity.Mob.class, org.bukkit.craftbukkit.entity.CraftMob.class, CraftCustomMob::new));
        add(AbstractMinecart.class, new EntityClass<>(org.bukkit.entity.Minecart.class, CraftCustomMinecart.class, CraftCustomMinecart::new));
        add(AbstractMinecartContainer.class, new EntityClass<>(org.bukkit.entity.Minecart.class, CraftCustomMinecartContainer.class, CraftCustomMinecartContainer::new));
        add(AbstractHorse.class, new EntityClass<>(org.bukkit.entity.AbstractHorse.class, CraftCustomHorse.class, CraftCustomHorse::new));
        add(AbstractChestedHorse.class, new EntityClass<>(org.bukkit.entity.ChestedHorse.class, CraftCustomChestedHorse.class, CraftCustomChestedHorse::new));
        add(Projectile.class, new EntityClass<>(org.bukkit.entity.Projectile.class, CraftCustomProjectile.class, CraftCustomProjectile::new));
        add(Raider.class, new EntityClass<>(org.bukkit.entity.Raider.class, CraftCustomRaider.class, CraftCustomRaider::new));
        add(LivingEntity.class, new EntityClass<>(org.bukkit.entity.LivingEntity.class, org.bukkit.craftbukkit.entity.CraftLivingEntity.class, org.bukkit.craftbukkit.entity.CraftLivingEntity::new));
        add(Monster.class, new EntityClass<>(org.bukkit.entity.Monster.class, org.bukkit.craftbukkit.entity.CraftMonster.class, org.bukkit.craftbukkit.entity.CraftMonster::new));
        add(PathfinderMob.class, new EntityClass<>(org.bukkit.entity.Creature.class, org.bukkit.craftbukkit.entity.CraftCreature.class, org.bukkit.craftbukkit.entity.CraftCreature::new));
        add(AgeableMob.class, new EntityClass<>(org.bukkit.entity.Ageable.class, org.bukkit.craftbukkit.entity.CraftAgeable.class, org.bukkit.craftbukkit.entity.CraftAgeable::new));
        add(AbstractVillager.class, new EntityClass<>(org.bukkit.entity.AbstractVillager.class, org.bukkit.craftbukkit.entity.CraftAbstractVillager.class, org.bukkit.craftbukkit.entity.CraftAbstractVillager::new));
        add(AbstractArrow.class, new EntityClass<>(org.bukkit.entity.AbstractArrow.class, org.bukkit.craftbukkit.entity.CraftArrow.class, org.bukkit.craftbukkit.entity.CraftArrow::new));
        add(Animal.class, new EntityClass<>(org.bukkit.entity.Animals.class, org.bukkit.craftbukkit.entity.CraftAnimals.class, org.bukkit.craftbukkit.entity.CraftAnimals::new));
        add(Fireball.class, new EntityClass<>(org.bukkit.entity.SizedFireball.class, org.bukkit.craftbukkit.entity.CraftSizedFireball.class, org.bukkit.craftbukkit.entity.CraftSizedFireball::new));
        add(AbstractHurtingProjectile.class, new EntityClass<>(org.bukkit.entity.Fireball.class, org.bukkit.craftbukkit.entity.CraftFireball.class, org.bukkit.craftbukkit.entity.CraftFireball::new));
        add(AbstractIllager.class, new EntityClass<>(org.bukkit.entity.Illager.class, org.bukkit.craftbukkit.entity.CraftIllager.class, org.bukkit.craftbukkit.entity.CraftIllager::new));
        add(ThrowableItemProjectile.class, new EntityClass<>(org.bukkit.entity.ThrowableProjectile.class, CraftCustomThrowableProjectile.class, CraftCustomThrowableProjectile::new));
        add(HangingEntity.class, new EntityClass<>(org.bukkit.entity.Hanging.class, org.bukkit.craftbukkit.entity.CraftHanging.class, org.bukkit.craftbukkit.entity.CraftHanging::new));
        add(SpellcasterIllager.class, new EntityClass<>(org.bukkit.entity.Spellcaster.class, org.bukkit.craftbukkit.entity.CraftSpellcaster.class, org.bukkit.craftbukkit.entity.CraftSpellcaster::new));
        add(AmbientCreature.class, new EntityClass<>(org.bukkit.entity.Ambient.class, org.bukkit.craftbukkit.entity.CraftAmbient.class, org.bukkit.craftbukkit.entity.CraftAmbient::new));
        add(TamableAnimal.class, new EntityClass<>(org.bukkit.entity.Tameable.class, org.bukkit.craftbukkit.entity.CraftTameableAnimal.class, org.bukkit.craftbukkit.entity.CraftTameableAnimal::new));
        add(AbstractPiglin.class, new EntityClass<>(org.bukkit.entity.PiglinAbstract.class, org.bukkit.craftbukkit.entity.CraftPiglinAbstract.class, org.bukkit.craftbukkit.entity.CraftPiglinAbstract::new));
        add(FlyingMob.class, new EntityClass<>(org.bukkit.entity.Flying.class, org.bukkit.craftbukkit.entity.CraftFlying.class, org.bukkit.craftbukkit.entity.CraftFlying::new));
        add(WaterAnimal.class, new EntityClass<>(org.bukkit.entity.WaterMob.class, org.bukkit.craftbukkit.entity.CraftWaterMob.class, org.bukkit.craftbukkit.entity.CraftWaterMob::new));
        add(AbstractGolem.class, new EntityClass<>(org.bukkit.entity.Golem.class, org.bukkit.craftbukkit.entity.CraftGolem.class, org.bukkit.craftbukkit.entity.CraftGolem::new));
        add(Player.class, new EntityClass<>(org.bukkit.entity.HumanEntity.class, org.bukkit.craftbukkit.entity.CraftHumanEntity.class, org.bukkit.craftbukkit.entity.CraftHumanEntity::new));
        add(AbstractFish.class, new EntityClass<>(org.bukkit.entity.Fish.class, org.bukkit.craftbukkit.entity.CraftFish.class, org.bukkit.craftbukkit.entity.CraftFish::new));
        add(EnderDragonPart.class, new EntityClass<>(org.bukkit.entity.EnderDragonPart.class, org.bukkit.craftbukkit.entity.CraftEnderDragonPart.class, org.bukkit.craftbukkit.entity.CraftEnderDragonPart::new));

        // vanilla mob types
        add(ElderGuardian.class, new EntityClass<>(org.bukkit.entity.ElderGuardian.class, org.bukkit.craftbukkit.entity.CraftElderGuardian.class, org.bukkit.craftbukkit.entity.CraftElderGuardian::new));
        add(WitherSkeleton.class, new EntityClass<>(org.bukkit.entity.WitherSkeleton.class, org.bukkit.craftbukkit.entity.CraftWitherSkeleton.class, org.bukkit.craftbukkit.entity.CraftWitherSkeleton::new));
        add(Stray.class, new EntityClass<>(org.bukkit.entity.Stray.class, org.bukkit.craftbukkit.entity.CraftStray.class, org.bukkit.craftbukkit.entity.CraftStray::new));
        add(Husk.class, new EntityClass<>(org.bukkit.entity.Husk.class, org.bukkit.craftbukkit.entity.CraftHusk.class, org.bukkit.craftbukkit.entity.CraftHusk::new));
        add(ZombieVillager.class, new EntityClass<>(org.bukkit.entity.ZombieVillager.class, org.bukkit.craftbukkit.entity.CraftVillagerZombie.class, org.bukkit.craftbukkit.entity.CraftVillagerZombie::new));
        add(SkeletonHorse.class, new EntityClass<>(org.bukkit.entity.SkeletonHorse.class, org.bukkit.craftbukkit.entity.CraftSkeletonHorse.class, org.bukkit.craftbukkit.entity.CraftSkeletonHorse::new));
        add(ZombieHorse.class, new EntityClass<>(org.bukkit.entity.ZombieHorse.class, org.bukkit.craftbukkit.entity.CraftZombieHorse.class, org.bukkit.craftbukkit.entity.CraftZombieHorse::new));
        add(ArmorStand.class, new EntityClass<>(org.bukkit.entity.ArmorStand.class, org.bukkit.craftbukkit.entity.CraftArmorStand.class, org.bukkit.craftbukkit.entity.CraftArmorStand::new));
        add(Donkey.class, new EntityClass<>(org.bukkit.entity.Donkey.class, org.bukkit.craftbukkit.entity.CraftDonkey.class, org.bukkit.craftbukkit.entity.CraftDonkey::new));
        add(Mule.class, new EntityClass<>(org.bukkit.entity.Mule.class, org.bukkit.craftbukkit.entity.CraftMule.class, org.bukkit.craftbukkit.entity.CraftMule::new));
        add(Evoker.class, new EntityClass<>(org.bukkit.entity.Evoker.class, org.bukkit.craftbukkit.entity.CraftEvoker.class, org.bukkit.craftbukkit.entity.CraftEvoker::new));
        add(Vex.class, new EntityClass<>(org.bukkit.entity.Vex.class, org.bukkit.craftbukkit.entity.CraftVex.class, org.bukkit.craftbukkit.entity.CraftVex::new));
        add(Vindicator.class, new EntityClass<>(org.bukkit.entity.Vindicator.class, org.bukkit.craftbukkit.entity.CraftVindicator.class, org.bukkit.craftbukkit.entity.CraftVindicator::new));
        add(Illusioner.class, new EntityClass<>(org.bukkit.entity.Illusioner.class, org.bukkit.craftbukkit.entity.CraftIllusioner.class, org.bukkit.craftbukkit.entity.CraftIllusioner::new));
        add(Creeper.class, new EntityClass<>(org.bukkit.entity.Creeper.class, org.bukkit.craftbukkit.entity.CraftCreeper.class, org.bukkit.craftbukkit.entity.CraftCreeper::new));
        add(Skeleton.class, new EntityClass<>(org.bukkit.entity.Skeleton.class, org.bukkit.craftbukkit.entity.CraftSkeleton.class, org.bukkit.craftbukkit.entity.CraftSkeleton::new));
        add(Spider.class, new EntityClass<>(org.bukkit.entity.Spider.class, org.bukkit.craftbukkit.entity.CraftSpider.class, org.bukkit.craftbukkit.entity.CraftSpider::new));
        add(Giant.class, new EntityClass<>(org.bukkit.entity.Giant.class, org.bukkit.craftbukkit.entity.CraftGiant.class, org.bukkit.craftbukkit.entity.CraftGiant::new));
        add(Zombie.class, new EntityClass<>(org.bukkit.entity.Zombie.class, org.bukkit.craftbukkit.entity.CraftZombie.class, org.bukkit.craftbukkit.entity.CraftZombie::new));
        add(Slime.class, new EntityClass<>(org.bukkit.entity.Slime.class, org.bukkit.craftbukkit.entity.CraftSlime.class, org.bukkit.craftbukkit.entity.CraftSlime::new));
        add(Ghast.class, new EntityClass<>(org.bukkit.entity.Ghast.class, org.bukkit.craftbukkit.entity.CraftGhast.class, org.bukkit.craftbukkit.entity.CraftGhast::new));
        add(ZombifiedPiglin.class, new EntityClass<>(org.bukkit.entity.PigZombie.class, org.bukkit.craftbukkit.entity.CraftPigZombie.class, org.bukkit.craftbukkit.entity.CraftPigZombie::new));
        add(EnderMan.class, new EntityClass<>(org.bukkit.entity.Enderman.class, org.bukkit.craftbukkit.entity.CraftEnderman.class, org.bukkit.craftbukkit.entity.CraftEnderman::new));
        add(CaveSpider.class, new EntityClass<>(org.bukkit.entity.CaveSpider.class, org.bukkit.craftbukkit.entity.CraftCaveSpider.class, org.bukkit.craftbukkit.entity.CraftCaveSpider::new));
        add(Silverfish.class, new EntityClass<>(org.bukkit.entity.Silverfish.class, org.bukkit.craftbukkit.entity.CraftSilverfish.class, org.bukkit.craftbukkit.entity.CraftSilverfish::new));
        add(Blaze.class, new EntityClass<>(org.bukkit.entity.Blaze.class, org.bukkit.craftbukkit.entity.CraftBlaze.class, org.bukkit.craftbukkit.entity.CraftBlaze::new));
        add(MagmaCube.class, new EntityClass<>(org.bukkit.entity.MagmaCube.class, org.bukkit.craftbukkit.entity.CraftMagmaCube.class, org.bukkit.craftbukkit.entity.CraftMagmaCube::new));
        add(WitherBoss.class, new EntityClass<>(org.bukkit.entity.Wither.class, org.bukkit.craftbukkit.entity.CraftWither.class, org.bukkit.craftbukkit.entity.CraftWither::new));
        add(Bat.class, new EntityClass<>(org.bukkit.entity.Bat.class, org.bukkit.craftbukkit.entity.CraftBat.class, org.bukkit.craftbukkit.entity.CraftBat::new));
        add(Witch.class, new EntityClass<>(org.bukkit.entity.Witch.class, org.bukkit.craftbukkit.entity.CraftWitch.class, org.bukkit.craftbukkit.entity.CraftWitch::new));
        add(Endermite.class, new EntityClass<>(org.bukkit.entity.Endermite.class, org.bukkit.craftbukkit.entity.CraftEndermite.class, org.bukkit.craftbukkit.entity.CraftEndermite::new));
        add(Guardian.class, new EntityClass<>(org.bukkit.entity.Guardian.class, org.bukkit.craftbukkit.entity.CraftGuardian.class, org.bukkit.craftbukkit.entity.CraftGuardian::new));
        add(Shulker.class, new EntityClass<>(org.bukkit.entity.Shulker.class, org.bukkit.craftbukkit.entity.CraftShulker.class, org.bukkit.craftbukkit.entity.CraftShulker::new));
        add(Pig.class, new EntityClass<>(org.bukkit.entity.Pig.class, org.bukkit.craftbukkit.entity.CraftPig.class, org.bukkit.craftbukkit.entity.CraftPig::new));
        add(Sheep.class, new EntityClass<>(org.bukkit.entity.Sheep.class, org.bukkit.craftbukkit.entity.CraftSheep.class, org.bukkit.craftbukkit.entity.CraftSheep::new));
        add(Cow.class, new EntityClass<>(org.bukkit.entity.Cow.class, org.bukkit.craftbukkit.entity.CraftCow.class, org.bukkit.craftbukkit.entity.CraftCow::new));
        add(Chicken.class, new EntityClass<>(org.bukkit.entity.Chicken.class, org.bukkit.craftbukkit.entity.CraftChicken.class, org.bukkit.craftbukkit.entity.CraftChicken::new));
        add(Squid.class, new EntityClass<>(org.bukkit.entity.Squid.class, org.bukkit.craftbukkit.entity.CraftSquid.class, org.bukkit.craftbukkit.entity.CraftSquid::new));
        add(Wolf.class, new EntityClass<>(org.bukkit.entity.Wolf.class, org.bukkit.craftbukkit.entity.CraftWolf.class, org.bukkit.craftbukkit.entity.CraftWolf::new));
        add(MushroomCow.class, new EntityClass<>(org.bukkit.entity.MushroomCow.class, org.bukkit.craftbukkit.entity.CraftMushroomCow.class, org.bukkit.craftbukkit.entity.CraftMushroomCow::new));
        add(SnowGolem.class, new EntityClass<>(org.bukkit.entity.Snowman.class, org.bukkit.craftbukkit.entity.CraftSnowman.class, org.bukkit.craftbukkit.entity.CraftSnowman::new));
        add(Ocelot.class, new EntityClass<>(org.bukkit.entity.Ocelot.class, org.bukkit.craftbukkit.entity.CraftOcelot.class, org.bukkit.craftbukkit.entity.CraftOcelot::new));
        add(IronGolem.class, new EntityClass<>(org.bukkit.entity.IronGolem.class, org.bukkit.craftbukkit.entity.CraftIronGolem.class, org.bukkit.craftbukkit.entity.CraftIronGolem::new));
        add(Horse.class, new EntityClass<>(org.bukkit.entity.Horse.class, org.bukkit.craftbukkit.entity.CraftHorse.class, org.bukkit.craftbukkit.entity.CraftHorse::new));
        add(Rabbit.class, new EntityClass<>(org.bukkit.entity.Rabbit.class, org.bukkit.craftbukkit.entity.CraftRabbit.class, org.bukkit.craftbukkit.entity.CraftRabbit::new));
        add(PolarBear.class, new EntityClass<>(org.bukkit.entity.PolarBear.class, org.bukkit.craftbukkit.entity.CraftPolarBear.class, org.bukkit.craftbukkit.entity.CraftPolarBear::new));
        add(Llama.class, new EntityClass<>(org.bukkit.entity.Llama.class, org.bukkit.craftbukkit.entity.CraftLlama.class, org.bukkit.craftbukkit.entity.CraftLlama::new));
        add(Parrot.class, new EntityClass<>(org.bukkit.entity.Parrot.class, org.bukkit.craftbukkit.entity.CraftParrot.class, org.bukkit.craftbukkit.entity.CraftParrot::new));
        add(Villager.class, new EntityClass<>(org.bukkit.entity.Villager.class, org.bukkit.craftbukkit.entity.CraftVillager.class, org.bukkit.craftbukkit.entity.CraftVillager::new));
        add(Turtle.class, new EntityClass<>(org.bukkit.entity.Turtle.class, org.bukkit.craftbukkit.entity.CraftTurtle.class, org.bukkit.craftbukkit.entity.CraftTurtle::new));
        add(Phantom.class, new EntityClass<>(org.bukkit.entity.Phantom.class, org.bukkit.craftbukkit.entity.CraftPhantom.class, org.bukkit.craftbukkit.entity.CraftPhantom::new));
        add(Cod.class, new EntityClass<>(org.bukkit.entity.Cod.class, org.bukkit.craftbukkit.entity.CraftCod.class, org.bukkit.craftbukkit.entity.CraftCod::new));
        add(Salmon.class, new EntityClass<>(org.bukkit.entity.Salmon.class, org.bukkit.craftbukkit.entity.CraftSalmon.class, org.bukkit.craftbukkit.entity.CraftSalmon::new));
        add(Pufferfish.class, new EntityClass<>(org.bukkit.entity.PufferFish.class, org.bukkit.craftbukkit.entity.CraftPufferFish.class, org.bukkit.craftbukkit.entity.CraftPufferFish::new));
        add(TropicalFish.class, new EntityClass<>(org.bukkit.entity.TropicalFish.class, org.bukkit.craftbukkit.entity.CraftTropicalFish.class, org.bukkit.craftbukkit.entity.CraftTropicalFish::new));
        add(Drowned.class, new EntityClass<>(org.bukkit.entity.Drowned.class, org.bukkit.craftbukkit.entity.CraftDrowned.class, org.bukkit.craftbukkit.entity.CraftDrowned::new));
        add(Dolphin.class, new EntityClass<>(org.bukkit.entity.Dolphin.class, org.bukkit.craftbukkit.entity.CraftDolphin.class, org.bukkit.craftbukkit.entity.CraftDolphin::new));
        add(Cat.class, new EntityClass<>(org.bukkit.entity.Cat.class, org.bukkit.craftbukkit.entity.CraftCat.class, org.bukkit.craftbukkit.entity.CraftCat::new));
        add(Panda.class, new EntityClass<>(org.bukkit.entity.Panda.class, org.bukkit.craftbukkit.entity.CraftPanda.class, org.bukkit.craftbukkit.entity.CraftPanda::new));
        add(Pillager.class, new EntityClass<>(org.bukkit.entity.Pillager.class, org.bukkit.craftbukkit.entity.CraftPillager.class, org.bukkit.craftbukkit.entity.CraftPillager::new));
        add(Ravager.class, new EntityClass<>(org.bukkit.entity.Ravager.class, org.bukkit.craftbukkit.entity.CraftRavager.class, org.bukkit.craftbukkit.entity.CraftRavager::new));
        add(TraderLlama.class, new EntityClass<>(org.bukkit.entity.TraderLlama.class, org.bukkit.craftbukkit.entity.CraftTraderLlama.class, org.bukkit.craftbukkit.entity.CraftTraderLlama::new));
        add(WanderingTrader.class, new EntityClass<>(org.bukkit.entity.WanderingTrader.class, org.bukkit.craftbukkit.entity.CraftWanderingTrader.class, org.bukkit.craftbukkit.entity.CraftWanderingTrader::new));
        add(Fox.class, new EntityClass<>(org.bukkit.entity.Fox.class, org.bukkit.craftbukkit.entity.CraftFox.class, org.bukkit.craftbukkit.entity.CraftFox::new));
        add(Bee.class, new EntityClass<>(org.bukkit.entity.Bee.class, org.bukkit.craftbukkit.entity.CraftBee.class, org.bukkit.craftbukkit.entity.CraftBee::new));
        add(Hoglin.class, new EntityClass<>(org.bukkit.entity.Hoglin.class, org.bukkit.craftbukkit.entity.CraftHoglin.class, org.bukkit.craftbukkit.entity.CraftHoglin::new));
        add(Piglin.class, new EntityClass<>(org.bukkit.entity.Piglin.class, org.bukkit.craftbukkit.entity.CraftPiglin.class, org.bukkit.craftbukkit.entity.CraftPiglin::new));
        add(Strider.class, new EntityClass<>(org.bukkit.entity.Strider.class, org.bukkit.craftbukkit.entity.CraftStrider.class, org.bukkit.craftbukkit.entity.CraftStrider::new));
        add(Zoglin.class, new EntityClass<>(org.bukkit.entity.Zoglin.class, org.bukkit.craftbukkit.entity.CraftZoglin.class, org.bukkit.craftbukkit.entity.CraftZoglin::new));
        add(PiglinBrute.class, new EntityClass<>(org.bukkit.entity.PiglinBrute.class, org.bukkit.craftbukkit.entity.CraftPiglinBrute.class, org.bukkit.craftbukkit.entity.CraftPiglinBrute::new));
        add(Axolotl.class, new EntityClass<>(org.bukkit.entity.Axolotl.class, org.bukkit.craftbukkit.entity.CraftAxolotl.class, org.bukkit.craftbukkit.entity.CraftAxolotl::new));
        add(GlowSquid.class, new EntityClass<>(org.bukkit.entity.GlowSquid.class, org.bukkit.craftbukkit.entity.CraftGlowSquid.class, org.bukkit.craftbukkit.entity.CraftGlowSquid::new));
        add(Goat.class, new EntityClass<>(org.bukkit.entity.Goat.class, org.bukkit.craftbukkit.entity.CraftGoat.class, org.bukkit.craftbukkit.entity.CraftGoat::new));
        add(Allay.class, new EntityClass<>(org.bukkit.entity.Allay.class, org.bukkit.craftbukkit.entity.CraftAllay.class, org.bukkit.craftbukkit.entity.CraftAllay::new));
        add(Frog.class, new EntityClass<>(org.bukkit.entity.Frog.class, org.bukkit.craftbukkit.entity.CraftFrog.class, org.bukkit.craftbukkit.entity.CraftFrog::new));
        add(Tadpole.class, new EntityClass<>(org.bukkit.entity.Tadpole.class, org.bukkit.craftbukkit.entity.CraftTadpole.class, org.bukkit.craftbukkit.entity.CraftTadpole::new));
        add(Warden.class, new EntityClass<>(org.bukkit.entity.Warden.class, org.bukkit.craftbukkit.entity.CraftWarden.class, org.bukkit.craftbukkit.entity.CraftWarden::new));
        add(EnderDragon.class, new EntityClass<>(org.bukkit.entity.EnderDragon.class, org.bukkit.craftbukkit.entity.CraftEnderDragon.class, org.bukkit.craftbukkit.entity.CraftEnderDragon::new));
        add(LargeFireball.class, new EntityClass<>(org.bukkit.entity.LargeFireball.class, org.bukkit.craftbukkit.entity.CraftLargeFireball.class, org.bukkit.craftbukkit.entity.CraftLargeFireball::new));
        add(SmallFireball.class, new EntityClass<>(org.bukkit.entity.SmallFireball.class, org.bukkit.craftbukkit.entity.CraftSmallFireball.class, org.bukkit.craftbukkit.entity.CraftSmallFireball::new));
        add(WitherSkull.class, new EntityClass<>(org.bukkit.entity.WitherSkull.class, org.bukkit.craftbukkit.entity.CraftWitherSkull.class, org.bukkit.craftbukkit.entity.CraftWitherSkull::new));
        add(DragonFireball.class, new EntityClass<>(org.bukkit.entity.DragonFireball.class, org.bukkit.craftbukkit.entity.CraftDragonFireball.class, org.bukkit.craftbukkit.entity.CraftDragonFireball::new));
        add(Painting.class, new EntityClass<>(org.bukkit.entity.Painting.class, org.bukkit.craftbukkit.entity.CraftPainting.class, org.bukkit.craftbukkit.entity.CraftPainting::new));
        add(ItemFrame.class, new EntityClass<>(org.bukkit.entity.ItemFrame.class, org.bukkit.craftbukkit.entity.CraftItemFrame.class, org.bukkit.craftbukkit.entity.CraftItemFrame::new));
        add(GlowItemFrame.class, new EntityClass<>(org.bukkit.entity.GlowItemFrame.class, org.bukkit.craftbukkit.entity.CraftGlowItemFrame.class, org.bukkit.craftbukkit.entity.CraftGlowItemFrame::new));
        add(Arrow.class, new EntityClass<>(org.bukkit.entity.Arrow.class, org.bukkit.craftbukkit.entity.CraftTippedArrow.class, org.bukkit.craftbukkit.entity.CraftTippedArrow::new));
        add(ThrownEnderpearl.class, new EntityClass<>(org.bukkit.entity.EnderPearl.class, org.bukkit.craftbukkit.entity.CraftEnderPearl.class, org.bukkit.craftbukkit.entity.CraftEnderPearl::new));
        add(ThrownExperienceBottle.class, new EntityClass<>(org.bukkit.entity.ThrownExpBottle.class, org.bukkit.craftbukkit.entity.CraftThrownExpBottle.class, org.bukkit.craftbukkit.entity.CraftThrownExpBottle::new));
        add(SpectralArrow.class, new EntityClass<>(org.bukkit.entity.SpectralArrow.class, org.bukkit.craftbukkit.entity.CraftSpectralArrow.class, org.bukkit.craftbukkit.entity.CraftSpectralArrow::new));
        add(EndCrystal.class, new EntityClass<>(org.bukkit.entity.EnderCrystal.class, org.bukkit.craftbukkit.entity.CraftEnderCrystal.class, org.bukkit.craftbukkit.entity.CraftEnderCrystal::new));
        add(ThrownTrident.class, new EntityClass<>(org.bukkit.entity.Trident.class, org.bukkit.craftbukkit.entity.CraftTrident.class, org.bukkit.craftbukkit.entity.CraftTrident::new));
        add(LightningBolt.class, new EntityClass<>(org.bukkit.entity.LightningStrike.class, org.bukkit.craftbukkit.entity.CraftLightningStrike.class, org.bukkit.craftbukkit.entity.CraftLightningStrike::new));
        add(ShulkerBullet.class, new EntityClass<>(org.bukkit.entity.ShulkerBullet.class, org.bukkit.craftbukkit.entity.CraftShulkerBullet.class, org.bukkit.craftbukkit.entity.CraftShulkerBullet::new));
        add(Boat.class, new EntityClass<>(org.bukkit.entity.Boat.class, org.bukkit.craftbukkit.entity.CraftBoat.class, org.bukkit.craftbukkit.entity.CraftBoat::new));
        add(LlamaSpit.class, new EntityClass<>(org.bukkit.entity.LlamaSpit.class, org.bukkit.craftbukkit.entity.CraftLlamaSpit.class, org.bukkit.craftbukkit.entity.CraftLlamaSpit::new));
        add(ChestBoat.class, new EntityClass<>(org.bukkit.entity.ChestBoat.class, org.bukkit.craftbukkit.entity.CraftChestBoat.class, org.bukkit.craftbukkit.entity.CraftChestBoat::new));
        add(Marker.class, new EntityClass<>(org.bukkit.entity.Marker.class, org.bukkit.craftbukkit.entity.CraftMarker.class, org.bukkit.craftbukkit.entity.CraftMarker::new));
        add(ItemEntity.class, new EntityClass<>(org.bukkit.entity.Item.class, org.bukkit.craftbukkit.entity.CraftItem.class, convert("CraftItem")));
        add(ExperienceOrb.class, new EntityClass<>(org.bukkit.entity.ExperienceOrb.class, org.bukkit.craftbukkit.entity.CraftExperienceOrb.class, org.bukkit.craftbukkit.entity.CraftExperienceOrb::new));
        add(AreaEffectCloud.class, new EntityClass<>(org.bukkit.entity.AreaEffectCloud.class, org.bukkit.craftbukkit.entity.CraftAreaEffectCloud.class, org.bukkit.craftbukkit.entity.CraftAreaEffectCloud::new));
        add(ThrownEgg.class, new EntityClass<>(org.bukkit.entity.Egg.class, org.bukkit.craftbukkit.entity.CraftEgg.class, org.bukkit.craftbukkit.entity.CraftEgg::new));
        add(LeashFenceKnotEntity.class, new EntityClass<>(org.bukkit.entity.LeashHitch.class, org.bukkit.craftbukkit.entity.CraftLeash.class, org.bukkit.craftbukkit.entity.CraftLeash::new));
        add(Snowball.class, new EntityClass<>(org.bukkit.entity.Snowball.class, org.bukkit.craftbukkit.entity.CraftSnowball.class, org.bukkit.craftbukkit.entity.CraftSnowball::new));
        add(EyeOfEnder.class, new EntityClass<>(org.bukkit.entity.EnderSignal.class, org.bukkit.craftbukkit.entity.CraftEnderSignal.class, org.bukkit.craftbukkit.entity.CraftEnderSignal::new));
        add(ThrownPotion.class, new EntityClass<>(org.bukkit.entity.ThrownPotion.class, org.bukkit.craftbukkit.entity.CraftThrownPotion.class, org.bukkit.craftbukkit.entity.CraftThrownPotion::new));
        add(PrimedTnt.class, new EntityClass<>(org.bukkit.entity.TNTPrimed.class, org.bukkit.craftbukkit.entity.CraftTNTPrimed.class, org.bukkit.craftbukkit.entity.CraftTNTPrimed::new));
        add(FallingBlockEntity.class, new EntityClass<>(org.bukkit.entity.FallingBlock.class, org.bukkit.craftbukkit.entity.CraftFallingBlock.class, org.bukkit.craftbukkit.entity.CraftFallingBlock::new));
        add(FireworkRocketEntity.class, new EntityClass<>(org.bukkit.entity.Firework.class, org.bukkit.craftbukkit.entity.CraftFirework.class, org.bukkit.craftbukkit.entity.CraftFirework::new));
        add(EvokerFangs.class, new EntityClass<>(org.bukkit.entity.EvokerFangs.class, org.bukkit.craftbukkit.entity.CraftEvokerFangs.class, org.bukkit.craftbukkit.entity.CraftEvokerFangs::new));
        add(MinecartCommandBlock.class, new EntityClass<>(org.bukkit.entity.minecart.CommandMinecart.class, org.bukkit.craftbukkit.entity.CraftMinecartCommand.class, org.bukkit.craftbukkit.entity.CraftMinecartCommand::new));
        add(Minecart.class, new EntityClass<>(org.bukkit.entity.minecart.RideableMinecart.class, org.bukkit.craftbukkit.entity.CraftMinecartRideable.class, org.bukkit.craftbukkit.entity.CraftMinecartRideable::new));
        add(MinecartChest.class, new EntityClass<>(org.bukkit.entity.minecart.StorageMinecart.class, org.bukkit.craftbukkit.entity.CraftMinecartChest.class, org.bukkit.craftbukkit.entity.CraftMinecartChest::new));
        add(MinecartFurnace.class, new EntityClass<>(org.bukkit.entity.minecart.PoweredMinecart.class, org.bukkit.craftbukkit.entity.CraftMinecartFurnace.class, org.bukkit.craftbukkit.entity.CraftMinecartFurnace::new));
        add(MinecartTNT.class, new EntityClass<>(org.bukkit.entity.minecart.ExplosiveMinecart.class, org.bukkit.craftbukkit.entity.CraftMinecartTNT.class, convert("CraftMinecartTNT")));
        add(MinecartHopper.class, new EntityClass<>(org.bukkit.entity.minecart.HopperMinecart.class, org.bukkit.craftbukkit.entity.CraftMinecartHopper.class, org.bukkit.craftbukkit.entity.CraftMinecartHopper::new));
        add(MinecartSpawner.class, new EntityClass<>(org.bukkit.entity.minecart.SpawnerMinecart.class, forName("CraftMinecartMobSpawner"), convert("CraftMinecartMobSpawner")));
        add(FishingHook.class, new EntityClass<>(org.bukkit.entity.FishHook.class, org.bukkit.craftbukkit.entity.CraftFishHook.class, org.bukkit.craftbukkit.entity.CraftFishHook::new));
        add(ServerPlayer.class, new EntityClass<>(org.bukkit.entity.Player.class, org.bukkit.craftbukkit.entity.CraftPlayer.class, org.bukkit.craftbukkit.entity.CraftPlayer::new));
    }
}
