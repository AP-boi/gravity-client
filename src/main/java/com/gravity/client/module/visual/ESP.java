package com.gravity.client.module.visual;
import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
public class ESP extends Module {
    private final ColorSetting playersColor = addSetting(new ColorSetting("Players Color", "Player ESP color", 255, 68, 68));
    private final ColorSetting mobsColor = addSetting(new ColorSetting("Mobs Color", "Mob ESP color", 68, 255, 68));
    private final ColorSetting itemsColor = addSetting(new ColorSetting("Items Color", "Item ESP color", 68, 68, 255));
    private final ModeSetting mode = addSetting(new ModeSetting("Mode", "ESP render mode", "Box", "Box", "Corner", "Glow"));
    private final BooleanSetting fill = addSetting(new BooleanSetting("Fill", "Fill ESP boxes", false));
    public ESP() { super("ESP", "Highlights entities through walls", Category.VISUAL, -1); }
}
