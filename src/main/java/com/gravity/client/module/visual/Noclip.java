package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Noclip extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Noclip speed", 2, 1, 5, 0.5));
    public Noclip() { super("Noclip", "Move through solid blocks", Category.VISUAL, -1); }
    @Override public void onTick() { if (isInGame()) mc.player.noClip = true; }
    @Override public void onDisable() { if (isInGame()) mc.player.noClip = false; }
}
