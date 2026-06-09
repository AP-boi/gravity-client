package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Parkour extends Module {
    private final BooleanSetting autoJump = addSetting(new BooleanSetting("Auto Jump at Edge", "Jump at block edges", true));
    public Parkour() { super("Parkour", "Auto-jumps at block edges for parkour", Category.MOVEMENT, -1); }
}
