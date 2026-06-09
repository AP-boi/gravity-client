package com.gravity.client.module.combat;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class Surround extends Module {
    private final BooleanSetting obsidian = addSetting(new BooleanSetting("Obsidian", "Use obsidian", true));
    private final BooleanSetting bedrock = addSetting(new BooleanSetting("Bedrock", "Use bedrock", false));
    public Surround() { super("Surround", "Automatically places blocks around you", Category.COMBAT, -1); }
}
