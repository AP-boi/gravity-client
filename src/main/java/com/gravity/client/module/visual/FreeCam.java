package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class FreeCam extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Camera speed", 3, 1, 10, 1));
    private final ModeSetting rotateMode = addSetting(new ModeSetting("Rotate Mode", "Camera rotation", "Free", "Free", "Lock"));
    public FreeCam() { super("FreeCam", "Detach camera and fly freely", Category.VISUAL, -1); }
}
