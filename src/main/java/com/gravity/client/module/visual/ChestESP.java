package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class ChestESP extends Module {
    private final ColorSetting color = addSetting(new ColorSetting("Color", "Chest highlight color", 245, 166, 35));
    private final NumberSetting fillOpacity = addSetting(new NumberSetting("Fill Opacity", "Fill transparency", 30, 0, 100, 5));
    public ChestESP() { super("ChestESP", "Highlights chests and containers", Category.VISUAL, -1); }
}
