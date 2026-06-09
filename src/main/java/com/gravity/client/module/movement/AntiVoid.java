package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
public class AntiVoid extends Module {
    public AntiVoid() { super("AntiVoid", "Prevents falling into the void", Category.MOVEMENT, -1); }
    @Override public void onTick() {
        if (isInGame() && mc.player.getY() < -10) {
            mc.player.setVelocity(mc.player.getVelocity().x, 0.5, mc.player.getVelocity().z);
        }
    }
}
