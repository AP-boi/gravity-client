package com.gravity.client.mixin;

import com.gravity.client.GravityClient;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (action == 1) { // 1 = GLFW_PRESS
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && client.currentScreen == null) {
                GravityClient.getInstance().getModuleManager().onKeyPress(key);
            }
        }
    }
}
