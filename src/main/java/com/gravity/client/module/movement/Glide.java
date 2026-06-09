package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Glide extends Module {
    private final NumberSetting fallSpeed = addSetting(new NumberSetting("Fall Speed", "Fall speed multiplier", 0.3, 0.1, 1.0, 0.05));
    public Glide() { super("Glide", "Glide through the air with reduced fall speed", Category.MOVEMENT, -1); }
    @Override public void onTick() {
        if (isInGame() && !mc.player.isOnGround()) {
            var vel = mc.player.getVelocity();
            if (vel.y < 0) mc.player.setVelocity(vel.x, vel.y * fallSpeed.getValue(), vel.z);
        }
    }
}
