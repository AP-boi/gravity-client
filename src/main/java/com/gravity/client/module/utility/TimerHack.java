package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class TimerHack extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Game speed multiplier", 1.0, 0.5, 2.0, 0.1));
    public TimerHack() { super("TimerHack", "Speeds up or slows down game tick rate", Category.UTILITY, -1); }
}
