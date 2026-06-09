package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class NameTags extends Module {
    private final NumberSetting scale = addSetting(new NumberSetting("Scale", "Nametag scale", 1.5, 1, 3, 0.5));
    private final NumberSetting distance = addSetting(new NumberSetting("Distance", "Render distance", 32, 10, 64, 1));
    private final BooleanSetting healthBar = addSetting(new BooleanSetting("Health Bar", "Show health bar", true));
    private final BooleanSetting ping = addSetting(new BooleanSetting("Ping", "Show ping", true));
    public NameTags() { super("NameTags", "Enhanced name tags with extra info", Category.VISUAL, -1); }
}
