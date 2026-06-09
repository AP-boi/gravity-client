package com.gravity.client.module.combat;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;

public class AimAssist extends Module {
    private final NumberSetting smoothness = addSetting(new NumberSetting("Smoothness", "Aim smoothness", 5, 1, 10, 1));
    private final NumberSetting fov = addSetting(new NumberSetting("FOV", "Field of view check", 90, 30, 180, 1));
    private final BooleanSetting vertical = addSetting(new BooleanSetting("Vertical Adjust", "Adjust vertical aim", true));
    private final ModeSetting priority = addSetting(new ModeSetting("Target Priority", "Targeting priority", "Distance", "Health", "Distance", "Angle"));

    public AimAssist() {
        super("AimAssist", "Smoothly adjusts aim toward nearest target", Category.COMBAT, -1);
    }
}
