package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class AutoFish extends Module {
    private final NumberSetting sensitivity = addSetting(new NumberSetting("Sensitivity", "Bite detection sensitivity", 5, 1, 10, 1));
    private final NumberSetting recastDelay = addSetting(new NumberSetting("Re-cast Delay", "Delay before recasting (ms)", 500, 100, 2000, 100));
    public AutoFish() { super("AutoFish", "Automatically reels in and recasts fishing rod", Category.UTILITY, -1); }
}
