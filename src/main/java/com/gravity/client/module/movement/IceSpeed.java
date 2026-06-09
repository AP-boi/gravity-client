package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class IceSpeed extends Module {
    private final NumberSetting multiplier = addSetting(new NumberSetting("Multiplier", "Speed on ice", 2.0, 1.0, 5.0, 0.1));
    public IceSpeed() { super("IceSpeed", "Increases speed on ice blocks", Category.MOVEMENT, -1); }
}
