package com.gravity.client.module.combat;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class BowAimbot extends Module {
    private final BooleanSetting smooth = addSetting(new BooleanSetting("Smooth", "Smooth aiming", true));
    private final BooleanSetting predict = addSetting(new BooleanSetting("Predict Movement", "Predict target movement", true));
    public BowAimbot() { super("BowAimbot", "Automatically aims bow at targets", Category.COMBAT, -1); }
}
