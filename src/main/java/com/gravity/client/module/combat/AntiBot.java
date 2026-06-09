package com.gravity.client.module.combat;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class AntiBot extends Module {
    private final ModeSetting method = addSetting(new ModeSetting("Filter Method", "Bot detection method", "Ping", "Name", "Ping", "Model"));
    private final NumberSetting pingThreshold = addSetting(new NumberSetting("Ping Threshold", "Max ping for bot detection", 100, 0, 500, 10));
    public AntiBot() { super("AntiBot", "Filters out bot entities from target list", Category.COMBAT, -1); }
}
