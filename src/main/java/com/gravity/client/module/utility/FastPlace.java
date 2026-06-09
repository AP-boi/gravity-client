package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class FastPlace extends Module {
    private final NumberSetting delay = addSetting(new NumberSetting("Delay", "Place delay in ticks", 0, 0, 5, 1));
    public FastPlace() { super("FastPlace", "Place blocks with no delay", Category.UTILITY, -1); }
}
