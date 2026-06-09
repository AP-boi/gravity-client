package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import org.lwjgl.glfw.GLFW;
public class Xray extends Module {
    private final NumberSetting opacity = addSetting(new NumberSetting("Opacity", "Non-ore block opacity", 20, 0, 100, 5));
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Xray mode", "Xray", "Xray", "Cave"));
    public Xray() { super("Xray", "See through blocks to find ores", Category.VISUAL, GLFW.GLFW_KEY_X); }
}
