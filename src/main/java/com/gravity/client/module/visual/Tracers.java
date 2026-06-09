package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Tracers extends Module {
    private final ModeSetting target = addSetting(new ModeSetting("Target", "Tracer target", "Players", "Players", "Mobs", "Items"));
    private final ColorSetting color = addSetting(new ColorSetting("Color", "Tracer color", 124, 92, 252));
    private final NumberSetting width = addSetting(new NumberSetting("Width", "Line width", 1, 1, 5, 0.5));
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Tracer endpoint", "Middle", "Bottom", "Middle", "Top"));
    public Tracers() { super("Tracers", "Draws lines to entities", Category.VISUAL, -1); }
}
