package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class ChestStealer extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Steal delay in ms", 50, 0, 500, 10));
    private final ModeSetting items = addSetting(new ModeSetting("Items", "Items to steal", "All", "All", "Whitelist"));
    public ChestStealer() { super("ChestStealer", "Quickly takes all items from chests", Category.UTILITY, -1); }
}
