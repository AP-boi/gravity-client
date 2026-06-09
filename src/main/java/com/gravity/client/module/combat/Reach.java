package com.gravity.client.module.combat;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;

public class Reach extends Module {
    private final NumberSetting distance = addSetting(new NumberSetting("Distance", "Reach distance", 3.5, 3.0, 5.5, 0.1));
    private final BooleanSetting bothSides = addSetting(new BooleanSetting("Both Sides", "Apply to both attack and interact", false));

    public Reach() {
        super("Reach", "Extends attack and interaction reach distance", Category.COMBAT, -1);
    }

    public double getReachDistance() {
        return isEnabled() ? distance.getValue() : 3.0;
    }
}
