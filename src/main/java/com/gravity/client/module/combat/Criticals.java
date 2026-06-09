package com.gravity.client.module.combat;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Criticals extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Criticals method", "Jump", "Jump", "Packet", "Mini Jump"));
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Jump height", 0.2, 0.1, 0.5, 0.01));
    public Criticals() { super("Criticals", "Forces critical hits on every attack", Category.COMBAT, -1); }
    @Override public void onTick() {
        if (!isInGame()) return;
        if (mc.player.isOnGround() && "Jump".equals(mode.getValue())) {
            // Will be triggered before attacks via attack event
        }
    }
}
