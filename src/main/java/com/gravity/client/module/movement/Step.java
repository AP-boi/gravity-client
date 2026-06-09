package com.gravity.client.module.movement;

import com.gravity.client.module.Category;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;

public class Step extends Module {
    private final NumberSetting height = addSetting(new NumberSetting("Height", "Step height in blocks", 1.0, 1.0, 3.0, 0.5));

    public Step() { 
        super("Step", "Step up blocks instantly without jumping", Category.MOVEMENT, -1); 
    }

    @Override 
    public void onTick() { 
        if (isInGame()) {
            EntityAttributeInstance instance = mc.player.getAttributeInstance(EntityAttributes.STEP_HEIGHT);
            if (instance != null) {
                instance.setBaseValue(height.getValue());
            }
        } 
    }

    @Override 
    public void onDisable() { 
        if (isInGame()) {
            EntityAttributeInstance instance = mc.player.getAttributeInstance(EntityAttributes.STEP_HEIGHT);
            if (instance != null) {
                instance.setBaseValue(0.6);
            }
        } 
    }
}
