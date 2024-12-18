package org.bukkit.craftbukkit.projectiles;

import net.minecraft.util.RandomSource;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.potion.CraftPotionUtil;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LingeringPotion;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.TippedArrow;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.projectiles.BlockProjectileSource;
import org.bukkit.util.Vector;

public class CraftBlockProjectileSource implements BlockProjectileSource {
    private final net.minecraft.world.level.block.entity.DispenserBlockEntity dispenserBlock;

    public CraftBlockProjectileSource(net.minecraft.world.level.block.entity.DispenserBlockEntity dispenserBlock) {
        this.dispenserBlock = dispenserBlock;
    }

    @Override
    public Block getBlock() {
        return dispenserBlock.getLevel().getWorld().getBlockAt(dispenserBlock.getBlockPos().getX(), dispenserBlock.getBlockPos().getY(), dispenserBlock.getBlockPos().getZ());
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return launchProjectile(projectile, null);
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        Validate.isTrue(getBlock().getType() == Material.DISPENSER, "Block is no longer dispenser");
        // Copied from net.minecraft.world.level.block.DispenserBlock.dispense()
        net.minecraft.core.BlockSourceImpl isourceblock = new net.minecraft.core.BlockSourceImpl((net.minecraft.server.level.ServerLevel) dispenserBlock.getLevel(), dispenserBlock.getBlockPos());
        // Copied from DispenseBehaviorProjectile
        net.minecraft.core.Position iposition = net.minecraft.world.level.block.DispenserBlock.getDispensePosition(isourceblock);
        net.minecraft.core.Direction enumdirection = (net.minecraft.core.Direction) isourceblock.getBlockState().getValue(net.minecraft.world.level.block.DispenserBlock.FACING);
        net.minecraft.world.level.Level world = dispenserBlock.getLevel();
        net.minecraft.world.entity.Entity launch = null;

        if (Snowball.class.isAssignableFrom(projectile)) {
            launch = new net.minecraft.world.entity.projectile.Snowball(world, iposition.x(), iposition.y(), iposition.z());
        } else if (Egg.class.isAssignableFrom(projectile)) {
            launch = new net.minecraft.world.entity.projectile.ThrownEgg(world, iposition.x(), iposition.y(), iposition.z());
        } else if (EnderPearl.class.isAssignableFrom(projectile)) {
            launch = new net.minecraft.world.entity.projectile.ThrownEnderpearl(world, null);
            launch.setPos(iposition.x(), iposition.y(), iposition.z());
        } else if (ThrownExpBottle.class.isAssignableFrom(projectile)) {
            launch = new net.minecraft.world.entity.projectile.ThrownExperienceBottle(world, iposition.x(), iposition.y(), iposition.z());
        } else if (ThrownPotion.class.isAssignableFrom(projectile)) {
            if (LingeringPotion.class.isAssignableFrom(projectile)) {
                launch = new net.minecraft.world.entity.projectile.ThrownPotion(world, iposition.x(), iposition.y(), iposition.z());
                ((net.minecraft.world.entity.projectile.ThrownPotion) launch).setItem(CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.LINGERING_POTION, 1)));
            } else {
                launch = new net.minecraft.world.entity.projectile.ThrownPotion(world, iposition.x(), iposition.y(), iposition.z());
                ((net.minecraft.world.entity.projectile.ThrownPotion) launch).setItem(CraftItemStack.asNMSCopy(new ItemStack(org.bukkit.Material.SPLASH_POTION, 1)));
            }
        } else if (AbstractArrow.class.isAssignableFrom(projectile)) {
            if (TippedArrow.class.isAssignableFrom(projectile)) {
                launch = new net.minecraft.world.entity.projectile.Arrow(world, iposition.x(), iposition.y(), iposition.z());
                ((net.minecraft.world.entity.projectile.Arrow) launch).setPotionType(CraftPotionUtil.fromBukkit(new PotionData(PotionType.WATER, false, false)));
            } else if (SpectralArrow.class.isAssignableFrom(projectile)) {
                launch = new net.minecraft.world.entity.projectile.SpectralArrow(world, iposition.x(), iposition.y(), iposition.z());
            } else {
                launch = new net.minecraft.world.entity.projectile.Arrow(world, iposition.x(), iposition.y(), iposition.z());
            }
            ((net.minecraft.world.entity.projectile.AbstractArrow) launch).pickup = net.minecraft.world.entity.projectile.AbstractArrow.Pickup.ALLOWED;
            ((net.minecraft.world.entity.projectile.AbstractArrow) launch).projectileSource = this;
        } else if (Fireball.class.isAssignableFrom(projectile)) {
            double d0 = iposition.x() + (double) ((float) enumdirection.getStepX() * 0.3F);
            double d1 = iposition.y() + (double) ((float) enumdirection.getStepY() * 0.3F);
            double d2 = iposition.z() + (double) ((float) enumdirection.getStepZ() * 0.3F);
            RandomSource random = world.random;
            double d3 = random.nextGaussian() * 0.05D + (double) enumdirection.getStepX();
            double d4 = random.nextGaussian() * 0.05D + (double) enumdirection.getStepY();
            double d5 = random.nextGaussian() * 0.05D + (double) enumdirection.getStepZ();

            if (SmallFireball.class.isAssignableFrom(projectile)) {
                launch = new net.minecraft.world.entity.projectile.SmallFireball(world, null, d0, d1, d2);
            } else if (WitherSkull.class.isAssignableFrom(projectile)) {
                launch = net.minecraft.world.entity.EntityType.WITHER_SKULL.create(world);
                launch.setPos(d0, d1, d2);
                double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);

                ((net.minecraft.world.entity.projectile.AbstractHurtingProjectile) launch).xPower = d3 / d6 * 0.1D;
                ((net.minecraft.world.entity.projectile.AbstractHurtingProjectile) launch).yPower = d4 / d6 * 0.1D;
                ((net.minecraft.world.entity.projectile.AbstractHurtingProjectile) launch).zPower = d5 / d6 * 0.1D;
            } else {
                launch = net.minecraft.world.entity.EntityType.FIREBALL.create(world);
                launch.setPos(d0, d1, d2);
                double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);

                ((net.minecraft.world.entity.projectile.AbstractHurtingProjectile) launch).xPower = d3 / d6 * 0.1D;
                ((net.minecraft.world.entity.projectile.AbstractHurtingProjectile) launch).yPower = d4 / d6 * 0.1D;
                ((net.minecraft.world.entity.projectile.AbstractHurtingProjectile) launch).zPower = d5 / d6 * 0.1D;
            }

            ((net.minecraft.world.entity.projectile.AbstractHurtingProjectile) launch).projectileSource = this;
        }

        Validate.notNull(launch, "Projectile not supported");

        if (launch instanceof net.minecraft.world.entity.projectile.Projectile) {
            if (launch instanceof net.minecraft.world.entity.projectile.ThrowableProjectile) {
                ((net.minecraft.world.entity.projectile.ThrowableProjectile) launch).projectileSource = this;
            }
            // Values from DispenseBehaviorProjectile
            float a = 6.0F;
            float b = 1.1F;
            if (launch instanceof net.minecraft.world.entity.projectile.ThrownPotion || launch instanceof ThrownExpBottle) {
                // Values from respective DispenseBehavior classes
                a *= 0.5F;
                b *= 1.25F;
            }
            // Copied from DispenseBehaviorProjectile
            ((net.minecraft.world.entity.projectile.Projectile) launch).shoot((double) enumdirection.getStepX(), (double) ((float) enumdirection.getStepY() + 0.1F), (double) enumdirection.getStepZ(), b, a);
        }

        if (velocity != null) {
            ((T) launch.getBukkitEntity()).setVelocity(velocity);
        }

        world.addFreshEntity(launch);
        return (T) launch.getBukkitEntity();
    }
}