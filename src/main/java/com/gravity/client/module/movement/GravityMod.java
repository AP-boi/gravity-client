package com.gravity.client.module.movement;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

/**
 * ⭐ SIGNATURE MOD — AntiGravity
 * Alters gravity physics for advanced movement.
 */
public class GravityMod extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Gravity mode", "Float", "Float", "Zero-G", "LowGrav", "Reverse"));
    private final NumberSetting gravityScale = addSetting(new NumberSetting("Gravity Scale", "Gravity multiplier", 0.3, 0, 2.0, 0.1));
    private final NumberSetting driftSpeed = addSetting(new NumberSetting("Drift Speed", "Drift movement speed", 1.0, 0.1, 5.0, 0.1));
    private final ModeSetting boostKey = addSetting(new ModeSetting("Boost Key", "Key for boost", "Space", "Space", "Shift", "Ctrl"));
    private final BooleanSetting particles = addSetting(new BooleanSetting("Particles", "Show gravity particles", true));

    public GravityMod() {
        super("Gravity", "Signature mod — alter gravity physics for advanced movement",
                Category.MOVEMENT, GLFW.GLFW_KEY_G, true);
    }

    @Override
    public void onTick() {
        if (!isInGame()) return;

        Vec3d velocity = mc.player.getVelocity();
        double motionX = velocity.x;
        double motionY = velocity.y;
        double motionZ = velocity.z;

        switch (mode.getValue()) {
            case "Float" -> {
                // Slow descent, float in air
                if (!mc.player.isOnGround()) {
                    motionY = Math.max(motionY, -0.04 * gravityScale.getValue());
                }
                if (mc.options.jumpKey.isPressed()) {
                    motionY = 0.06 * driftSpeed.getValue();
                }
                if (mc.options.sneakKey.isPressed()) {
                    motionY = -0.06 * driftSpeed.getValue();
                }
            }
            case "Zero-G" -> {
                // No gravity at all
                motionY = 0;
                if (mc.options.jumpKey.isPressed()) {
                    motionY = 0.1 * driftSpeed.getValue();
                }
                if (mc.options.sneakKey.isPressed()) {
                    motionY = -0.1 * driftSpeed.getValue();
                }
            }
            case "LowGrav" -> {
                // Reduced gravity
                if (!mc.player.isOnGround()) {
                    motionY += 0.08 * (1.0 - gravityScale.getValue());
                }
            }
            case "Reverse" -> {
                // Reverse gravity — float upward
                if (!mc.player.isOnGround()) {
                    motionY += 0.08 + (0.04 * gravityScale.getValue());
                }
                if (mc.options.sneakKey.isPressed()) {
                    motionY = -0.2;
                }
            }
        }

        mc.player.setVelocity(motionX, motionY, motionZ);
    }
}
