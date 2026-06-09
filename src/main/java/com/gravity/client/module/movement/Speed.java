package com.gravity.client.module.movement;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import net.minecraft.util.math.Vec3d;

public class Speed extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Speed method", "BHop", "BHop", "Strafe", "Vanilla"));
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Speed multiplier", 1.5, 1.0, 3.0, 0.1));

    public Speed() { super("Speed", "Increases movement speed beyond normal limits", Category.MOVEMENT, -1); }

    @Override
    public void onTick() {
        if (!isInGame()) return;
        if (mc.player.input.hasForwardMovement()) {
            Vec3d vel = mc.player.getVelocity();
            mc.player.setVelocity(vel.x * multiplier.getValue(), vel.y, vel.z * multiplier.getValue());
            if ("BHop".equals(mode.getValue()) && mc.player.isOnGround()) {
                mc.player.jump();
            }
        }
    }
}
