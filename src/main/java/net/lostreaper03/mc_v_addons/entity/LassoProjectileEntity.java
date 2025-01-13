package net.lostreaper03.mc_v_addons.entity;

import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MCVAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LassoProjectileEntity extends AbstractArrow {
    private Vec3 startPosition;
    private int lifespan = 200;
    private static Entity hitEntity;

    public LassoProjectileEntity(EntityType<? extends AbstractArrow> type, Level level) {
       super(type, level);
       MinecraftForge.EVENT_BUS.register(this);
    }

    public LassoProjectileEntity(Level level, LivingEntity shooter) {
        super(ModEntities.LASSO_PROJECTILE.get(), level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
        this.setBoundingBox(new AABB(this.getX() - 0.25, this.getY() - 0.25, this.getZ() - 0.25, this.getX() + 0.25, this.getY() + 0.25, this.getZ() + 0.25));
        startPosition = this.position();
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("Projectile Initialized at: " + this.position());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        hitEntity = result.getEntity();
        System.out.println("Hit Entity: " + hitEntity);
        if (hitEntity instanceof LivingEntity) {
            // Apply lasso effect (simulate lead)
            if (this.getOwner() instanceof Player player) {
                // Custom logic to attach lead
                hitEntity.startRiding(player);
            }
            this.discard(); // Remove the projectile after hitting an entity
            this.remove(RemovalReason.KILLED);
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            this.tick();
        }
        if (this.level().isClientSide) {
            double d0 = this.getX() - this.xo;
            double d1 = this.getY() - this.yo;
            double d2 = this.getZ() - this.zo;
            if (Math.abs(d0) > 0.001D || Math.abs(d1) > 0.001D || Math.abs(d2) > 0.001D) {
                this.level().addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
            System.out.println("Projectile Position: " + this.position());
        }
        double distanceTraveled = startPosition.distanceTo(this.position());
        if (distanceTraveled > 10) {
            this.discard();
            this.remove(RemovalReason.KILLED);
            MinecraftForge.EVENT_BUS.unregister(this);
            System.out.println("Projectile discarded due to exceeding maximum distance");
            return;
        }
        if (--lifespan <= 0) {
            this.discard();
            this.remove(RemovalReason.KILLED);
            MinecraftForge.EVENT_BUS.unregister(this);
            System.out.println("Projectile discarded due to expired lifespan");
            return;
        }
        else {
            System.out.println("Remaining lifespan: " + lifespan);
        }
    }

}

