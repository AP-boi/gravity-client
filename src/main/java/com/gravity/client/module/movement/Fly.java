package com.gravity.client.module.movement;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Fly extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Fly method", "Vanilla", "Vanilla", "Packet", "Glide"));
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Flight speed", 3, 1, 10, 1));

    public Fly() { super("Fly", "Enables creative-style flight in survival", Category.MOVEMENT, GLFW.GLFW_KEY_F); }

    @Override
    public void onEnable() {
        if (isInGame() && "Vanilla".equals(mode.getValue())) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(speed.getFloatValue() / 20f);
            mc.player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void onDisable() {
        if (isInGame()) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().setFlySpeed(0.05f);
            mc.player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void onTick() {
        if (!isInGame()) return;
        if ("Vanilla".equals(mode.getValue())) {
            mc.player.getAbilities().flying = true;
            mc.player.getAbilities().setFlySpeed(speed.getFloatValue() / 20f);
        } else if ("Glide".equals(mode.getValue())) {
            Vec3d vel = mc.player.getVelocity();
            if (!mc.player.isOnGround()) {
                mc.player.setVelocity(vel.x, Math.max(vel.y, -0.04), vel.z);
            }
        }
    }
}
