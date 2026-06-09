package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class HighJump extends Module {
    private final NumberSetting boost = addSetting(new NumberSetting("Boost Amount", "Jump boost", 2.0, 1.0, 5.0, 0.1));
    public HighJump() { super("HighJump", "Jump much higher than normal", Category.MOVEMENT, -1); }
}
