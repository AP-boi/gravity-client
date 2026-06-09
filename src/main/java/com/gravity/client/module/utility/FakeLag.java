package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class FakeLag extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Lag delay in ms", 200, 50, 2000, 50));
    private final NumberSetting chance = addSetting(new NumberSetting("Chance %", "Chance to lag", 100, 10, 100, 10));
    public FakeLag() { super("FakeLag", "Simulates network lag for desync", Category.UTILITY, -1); }
}
