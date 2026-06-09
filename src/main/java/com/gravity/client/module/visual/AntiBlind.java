package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class AntiBlind extends Module {
    private final BooleanSetting nausea = addSetting(new BooleanSetting("Block Nausea", "Block nausea effect", true));
    private final BooleanSetting blindness = addSetting(new BooleanSetting("Block Blindness", "Block blindness effect", true));
    private final BooleanSetting darkness = addSetting(new BooleanSetting("Block Darkness", "Block darkness effect", true));
    public AntiBlind() { super("AntiBlind", "Blocks visual debuffs", Category.VISUAL, -1); }
}
