package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import org.lwjgl.glfw.GLFW;
public class Zoom extends Module {
    private final BooleanSetting smoothZoom = addSetting(new BooleanSetting("Smooth Zoom", "Smooth zoom transition", true));
    private final BooleanSetting scrollAdjust = addSetting(new BooleanSetting("Scroll Adjust", "Adjust zoom with scroll", true));
    private final NumberSetting zoomFov = addSetting(new NumberSetting("Zoom FOV", "Zoomed field of view", 20, 5, 60, 1));
    public Zoom() { super("Zoom", "Zoom in like a telescope", Category.VISUAL, GLFW.GLFW_KEY_C); }
    public float getZoomFov() { return zoomFov.getFloatValue(); }
}
