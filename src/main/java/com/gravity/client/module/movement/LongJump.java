package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class LongJump extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Jump mode", "Vanilla", "Vanilla", "Strafe"));
    private final NumberSetting boost = addSetting(new NumberSetting("Boost Amount", "Jump boost", 2.0, 1.0, 5.0, 0.1));
    public LongJump() { super("LongJump", "Launches you forward with enhanced jump distance", Category.MOVEMENT, -1); }
}
