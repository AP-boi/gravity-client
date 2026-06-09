package com.gravity.client.module.combat;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;

public class Velocity extends Module {
    private final NumberSetting horizontal = addSetting(new NumberSetting("Horizontal %", "Horizontal knockback multiplier", 80, 0, 100, 1));
    private final NumberSetting vertical = addSetting(new NumberSetting("Vertical %", "Vertical knockback multiplier", 100, 0, 100, 1));
    private final NumberSetting chance = addSetting(new NumberSetting("Chance %", "Chance to reduce knockback", 100, 0, 100, 1));

    public Velocity() {
        super("Velocity", "Reduces knockback taken from attacks", Category.COMBAT, GLFW_KEY_V());
    }

    private static int GLFW_KEY_V() { return org.lwjgl.glfw.GLFW.GLFW_KEY_V; }

    public double getHorizontalMultiplier() { return horizontal.getValue() / 100.0; }
    public double getVerticalMultiplier() { return vertical.getValue() / 100.0; }
    public boolean shouldApply() { return Math.random() * 100 < chance.getValue(); }
}
