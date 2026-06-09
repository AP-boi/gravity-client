package com.gravity.client.module.movement;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class FastLadder extends Module {
    private final NumberSetting speed = addSetting(new NumberSetting("Speed", "Ladder speed", 3, 1, 10, 1));
    public FastLadder() { super("FastLadder", "Climb ladders at increased speed", Category.MOVEMENT, -1); }
}
