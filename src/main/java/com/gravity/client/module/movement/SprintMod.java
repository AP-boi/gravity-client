package com.gravity.client.module.movement;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;

public class SprintMod extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Sprint mode", "Legit", "Omni", "Legit", "Toggle"));
    private final BooleanSetting noSlowdown = addSetting(new BooleanSetting("No Slowdown", "Sprint through items", false));

    public SprintMod() { super("Sprint", "Toggles sprint mode for constant running", Category.MOVEMENT, -1); }

    @Override
    public void onTick() {
        if (!isInGame()) return;
        if (mc.player.input.hasForwardMovement() || "Omni".equals(mode.getValue())) {
            mc.player.setSprinting(true);
        }
    }
}
