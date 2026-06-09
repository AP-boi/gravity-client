package com.gravity.client.module.utility;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
public class AutoSprint extends Module {
    public AutoSprint() { super("AutoSprint", "Always sprinting when moving", Category.UTILITY, -1); }
    @Override public void onTick() { if (isInGame() && mc.player.input.hasForwardMovement()) mc.player.setSprinting(true); }
}
