package com.gravity.client.module.combat;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;

public class AntiKnockback extends Module {
    private final NumberSetting horizontal = addSetting(new NumberSetting("Horizontal %", "Horizontal KB reduction", 0, 0, 100, 1));
    private final NumberSetting vertical = addSetting(new NumberSetting("Vertical %", "Vertical KB reduction", 0, 0, 100, 1));

    public AntiKnockback() { super("AntiKnockback", "Prevents knockback from all sources", Category.COMBAT, -1); }
}
