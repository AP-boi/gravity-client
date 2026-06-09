package com.gravity.client.module.combat;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Hitboxes extends Module {
    private final NumberSetting scale = addSetting(new NumberSetting("Scale", "Hitbox scale multiplier", 1.0, 1.0, 2.0, 0.1));
    public Hitboxes() { super("Hitboxes", "Scales entity hitbox size for easier targeting", Category.COMBAT, -1); }
}
