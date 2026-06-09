package com.gravity.client.module.combat;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import net.minecraft.util.Hand;

public class AutoClicker extends Module {
    private final NumberSetting minCps = addSetting(new NumberSetting("Min CPS", "Minimum clicks per second", 8, 1, 20, 1));
    private final NumberSetting maxCps = addSetting(new NumberSetting("Max CPS", "Maximum clicks per second", 12, 1, 20, 1));
    private final BooleanSetting leftMouse = addSetting(new BooleanSetting("Left Mouse", "Auto-click left", true));
    private final BooleanSetting rightMouse = addSetting(new BooleanSetting("Right Mouse", "Auto-click right", false));
    private final BooleanSetting breakBlocks = addSetting(new BooleanSetting("Break Blocks", "Allow breaking blocks", false));

    private int tickCounter = 0;

    public AutoClicker() { super("AutoClicker", "Automatically clicks at a set CPS rate", Category.COMBAT, -1); }

    @Override
    public void onTick() {
        if (!isInGame()) return;
        int avgCps = (minCps.getIntValue() + maxCps.getIntValue()) / 2;
        int tickDelay = Math.max(1, 20 / avgCps);
        tickCounter++;
        if (tickCounter >= tickDelay) {
            tickCounter = 0;
            if (leftMouse.isEnabled() && mc.options.attackKey.isPressed()) {
                mc.player.swingHand(Hand.MAIN_HAND);
            }
        }
    }
}
