package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Jesus extends Module {
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "Water walk method", "Vanilla", "Vanilla", "Packet", "Timer"));
    private final BooleanSetting sneakToSink = addSetting(new BooleanSetting("Sneak to Sink", "Sneak to go underwater", true));
    public Jesus() { super("Jesus", "Walk on water and lava", Category.MOVEMENT, -1); }
}
