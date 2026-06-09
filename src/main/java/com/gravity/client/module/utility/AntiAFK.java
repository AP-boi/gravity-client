package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class AntiAFK extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "AFK prevention method", "Random", "Rotate", "Jump", "Walk", "Random"));
    private final NumberSetting interval = addSetting(new NumberSetting("Interval", "Action interval in seconds", 15, 1, 60, 1));
    private int tickCounter = 0;
    public AntiAFK() { super("AntiAFK", "Prevents AFK kick with random movement", Category.UTILITY, -1); }
    @Override public void onTick() {
        if (!isInGame()) return;
        tickCounter++;
        if (tickCounter >= interval.getIntValue() * 20) {
            tickCounter = 0;
            mc.player.setYaw(mc.player.getYaw() + (float)(Math.random() * 20 - 10));
        }
    }
}
