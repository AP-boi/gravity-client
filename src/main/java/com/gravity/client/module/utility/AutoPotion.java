package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class AutoPotion extends Module {
    private final NumberSetting healthThreshold = addSetting(new NumberSetting("Health %", "Health threshold percent", 50, 10, 90, 5));
    private final BooleanSetting instant = addSetting(new BooleanSetting("Instant", "Use instant health", true));
    public AutoPotion() { super("AutoPotion", "Automatically throws health potions", Category.UTILITY, -1); }
}
