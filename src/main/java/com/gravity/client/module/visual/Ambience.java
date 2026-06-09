package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Ambience extends Module {
    private final NumberSetting fogDensity = addSetting(new NumberSetting("Fog Density", "Fog density", 50, 0, 100, 5));
    private final ColorSetting skyColor = addSetting(new ColorSetting("Sky Color", "Sky color override", 26, 26, 46));
    public Ambience() { super("Ambience", "Custom fog and sky color overrides", Category.VISUAL, -1); }
}
