package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class FastBreak extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Break speed multiplier", 2.0, 1.0, 5.0, 0.1));
    public FastBreak() { super("FastBreak", "Break blocks faster than normal", Category.UTILITY, -1); }
}
