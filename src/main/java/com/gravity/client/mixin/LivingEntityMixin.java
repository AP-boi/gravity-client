package com.gravity.client.mixin;

import com.gravity.client.GravityClient;
import com.gravity.client.module.combat.Velocity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * Hooks into LivingEntity for Velocity/AntiKnockback modifications.
 */
@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    // Velocity modification is handled via the Velocity module's
    // direct velocity manipulation in onTick rather than mixin
    // for maximum compatibility across versions.
}
