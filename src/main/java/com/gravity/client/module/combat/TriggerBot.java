package com.gravity.client.module.combat;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class TriggerBot extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay (ms)", "Attack delay", 50, 0, 500, 10));
    private final NumberSetting fov = addSetting(new NumberSetting("FOV %", "FOV percentage", 100, 10, 100, 5));
    private final BooleanSetting onlySprint = addSetting(new BooleanSetting("Only Sprint", "Only when sprinting", false));
    public TriggerBot() { super("TriggerBot", "Attacks when crosshair is on a target", Category.COMBAT, -1); }
}
