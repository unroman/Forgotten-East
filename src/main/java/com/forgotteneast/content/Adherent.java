package com.forgotteneast.content;

import com.forgotteneast.init.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class Adherent extends Monster {
    protected int leapTickCount;
    protected static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(Adherent.class, EntityDataSerializers.BOOLEAN);
    public Adherent(EntityType<? extends Monster> p_32133_, Level p_32134_) {
        super(p_32133_, p_32134_);
    }

    protected void populateDefaultEquipmentSlots(RandomSource p_218949_, DifficultyInstance p_218950_) {
        super.populateDefaultEquipmentSlots(p_218949_, p_218950_);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.STAFF.get()));
    }
    public boolean isFlying() {
        return this.entityData.get(FLYING);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
    }

    protected void defineSynchedData(SynchedEntityData.Builder p_335149_) {
        super.defineSynchedData(p_335149_);
        p_335149_.define(FLYING, false);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_32146_, DifficultyInstance p_32147_, MobSpawnType p_32148_, @Nullable SpawnGroupData p_32149_) {
        p_32149_ = super.finalizeSpawn(p_32146_, p_32147_, p_32148_, p_32149_);
        RandomSource randomsource = p_32146_.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, p_32147_);
        return p_32149_;
    }

    public void tick() {
        super.tick();
        if (this.leapTickCount > 0) {
            leapTickCount -= 1;
        }
        if (this.isFlying()) {
            this.level().addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0f, 0f, 0f);
        }
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new FarLeapGoal(this, 0.6f));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public boolean hurt(DamageSource source, float p_32495_) {
        if (source.is(DamageTypeTags.IS_PROJECTILE)) {
            this.swing(InteractionHand.MAIN_HAND);
            this.sweepAttack();
        } else if (!source.is(DamageTypeTags.IS_FALL)) {
            boolean flag2 = super.hurt(source, p_32495_);
            return flag2;
        }
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource p_33579_) {
        return SoundEvents.ILLUSIONER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ILLUSIONER_DEATH;
    }
    public void sweepAttack() {
        double d0 = -Mth.sin(this.getYRot() * ((float)Math.PI / 180F));
        double d1 = Mth.cos(this.getYRot() * ((float)Math.PI / 180F));
        if (this.level() instanceof ServerLevel) {
            ((ServerLevel)this.level()).sendParticles(ParticleTypes.SWEEP_ATTACK, this.getX() + d0, this.getY(0.5D), this.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
        }
    }

    @Override
    protected float getEquipmentDropChance(EquipmentSlot p_21520_) {
        return 0.1f;
    }

    public class FarLeapGoal extends Goal {
        private final Mob mob;
        private LivingEntity target;
        private final float yd;

        public FarLeapGoal(Mob p_25492_, float p_25493_) {
            this.mob = p_25492_;
            this.yd = p_25493_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (leapTickCount > 0) {
                return false;
            } else {
                this.target = this.mob.getTarget();
                if (this.target == null) {
                    return false;
                } else {
                    double d0 = this.mob.distanceTo(this.target);
                    if (d0 < 6.0D) {
                        if (!this.mob.onGround()) {
                            return false;
                        } else {
                            return this.mob.getRandom().nextInt(reducedTickDelay(5)) == 0;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }

        public boolean canContinueToUse() {
            return !this.mob.onGround();
        }

        public void start() {
            Vec3 vec31 = new Vec3(this.target.getX() - this.mob.getX(), 0.0D, this.target.getZ() - this.mob.getZ());
            setFlying(true);
            leapTickCount = 200;
            this.mob.setDeltaMovement(vec31.x/2, this.yd, vec31.z/2);
        }

        public void stop() {
            super.stop();
            this.mob.level().playLocalSound(this.mob.blockPosition(), SoundEvents.ARMOR_STAND_FALL, SoundSource.HOSTILE, 1.0f, 1.0f, true);
            setFlying(false);
        }
    }
}
