package com.gravity.client.mixin;

import com.gravity.client.GravityClient;
import com.gravity.client.module.visual.Fullbright;
import com.gravity.client.module.visual.Zoom;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Hooks into GameRenderer for Fullbright (gamma) and Zoom (FOV).
 */
@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void onGetFov(CallbackInfoReturnable<Float> cir) {
        if (GravityClient.getInstance() == null) return;

        Zoom zoom = GravityClient.getInstance().getModuleManager().getModule(Zoom.class);
        if (zoom != null && zoom.isEnabled()) {
            cir.setReturnValue(zoom.getZoomFov());
        }
    }
}
