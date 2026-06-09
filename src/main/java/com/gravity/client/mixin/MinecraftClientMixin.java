package com.gravity.client.mixin;

import com.gravity.client.GravityClient;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hooks into MinecraftClient to handle keybind dispatch.
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void onHandleInputEvents(CallbackInfo ci) {
        if (GravityClient.getInstance() == null) return;
        // Keybind handling is done via Fabric's keybind API in the entrypoint
    }
}
