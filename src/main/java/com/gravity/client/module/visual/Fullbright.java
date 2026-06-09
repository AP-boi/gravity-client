package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Fullbright extends Module {
    private final NumberSetting gamma = addSetting(new NumberSetting("Gamma", "Brightness level", 16, 1, 16, 1));
    public Fullbright() { super("Fullbright", "Maximum brightness everywhere", Category.VISUAL, -1); }
    public double getGammaValue() { return gamma.getValue(); }
}
