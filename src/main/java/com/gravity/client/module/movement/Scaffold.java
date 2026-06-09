package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Scaffold extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Scaffold mode", "Normal", "Normal", "Tower", "Telly", "Eagle"));
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Place delay in ms", 50, 0, 200, 10));
    private final BooleanSetting rotate = addSetting(new BooleanSetting("Rotate", "Rotate to place", true));
    private final BooleanSetting safeWalk = addSetting(new BooleanSetting("SafeWalk", "Prevent walking off edges", true));
    public Scaffold() { super("Scaffold", "Automatically places blocks beneath you", Category.MOVEMENT, -1); }
}
