package com.gravity.client.module.combat;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class AutoArmor extends Module {
    private final ModeSetting priority = addSetting(new ModeSetting("Priority", "Armor priority", "Best", "Protection", "Thorns", "Best"));
    public AutoArmor() { super("AutoArmor", "Equips best available armor automatically", Category.COMBAT, -1); }
}
