package io.github.ultrusbot.merryminecraft.mixin;

import io.github.ultrusbot.merryminecraft.MerryMinecraft;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;
import java.util.function.Consumer;

@Mixin(LivingEntity.class)
public abstract class LivingEntityIceMixin extends Entity {
    public final UUID SKIING_SPEED_BOOST = UUID.fromString("17fc32bc-1b1c-4b2b-bbdf-707080ab65f0");
    public LivingEntityIceMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    protected boolean method_29500(BlockState blockState) { return true;}

    @Inject(method= "applyMovementEffects(Lnet/minecraft/util/math/BlockPos;)V", at = @At("TAIL"), cancellable = true)
    public void applyMovementEffectsIce(CallbackInfo cir) {
        if (this.method_29500(this.getLandingBlockState())) {
            this.removeSkiingSpeedBoost();
        }

        this.addSkiiingBoostIfNeeded();
    }
    @Inject(method = "getVelocityMultiplier()F", at = @At("HEAD"), cancellable = true)
    public void getVelocityMult(CallbackInfoReturnable<Float> cir) {
        if (this.onIce() && EnchantmentHelper.getEquipmentLevel(MerryMinecraft.ICE_SKATING_ENCHANTMENT, (LivingEntity)(Object)this) > 0) {
            cir.setReturnValue(1.0001F);
        }
    }
    public boolean onIce() {
        return this.world.getBlockState(this.getVelocityAffectingPos()).isIn(BlockTags.ICE);
    }
    public void addSkiiingBoostIfNeeded() {

        if (!this.getLandingBlockState().isAir()) {
            int i = EnchantmentHelper.getEquipmentLevel(MerryMinecraft.ICE_SKATING_ENCHANTMENT, (LivingEntity)(Object)this);
            if (i > 0 && onIce()) {
                EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                if (entityAttributeInstance == null) {
                    return;
                }

                entityAttributeInstance.addTemporaryModifier(new EntityAttributeModifier(SKIING_SPEED_BOOST, "Skiing speed boost", (double)(0.03F * (1.0F + (float)i * 0.50F)), EntityAttributeModifier.Operation.ADDITION));
            }
        }
    }
    public void removeSkiingSpeedBoost() {
        EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (entityAttributeInstance != null) {
            if (entityAttributeInstance.getModifier(SKIING_SPEED_BOOST) != null) {
                entityAttributeInstance.removeModifier(SKIING_SPEED_BOOST);
            }

        }
    }

    @Shadow
    protected abstract EntityAttributeInstance getAttributeInstance(EntityAttribute genericMovementSpeed);
}
