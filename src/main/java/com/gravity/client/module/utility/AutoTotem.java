package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class AutoTotem extends Module {
    private final NumberSetting healthThreshold = addSetting(new NumberSetting("Health Threshold", "Auto-equip below health", 6, 1, 20, 1));
    private final BooleanSetting offhandOnly = addSetting(new BooleanSetting("Off-hand Only", "Only equip to off-hand", true));
    public AutoTotem() { super("AutoTotem", "Automatically equips totem of undying", Category.UTILITY, -1); }
}
