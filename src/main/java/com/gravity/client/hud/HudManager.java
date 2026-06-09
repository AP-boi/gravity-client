package com.gravity.client.hud;

import com.gravity.client.GravityClient;
import com.gravity.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Renders the HUD overlay showing enabled modules (ArrayList HUD)
 * and dispatches onHudRender to all enabled modules.
 */
public class HudManager {
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public void render(DrawContext context, float tickDelta) {
        if (mc.player == null || mc.options.hudHidden) return;

        // Render arraylist (enabled modules list) on top-right
        renderArrayList(context);

        // Dispatch HUD render to enabled modules
        for (Module module : GravityClient.getInstance().getModuleManager().getModules()) {
            if (module.isEnabled()) {
                module.onHudRender(context, tickDelta);
            }
        }
    }

    private void renderArrayList(DrawContext context) {
        List<Module> enabled = GravityClient.getInstance().getModuleManager().getModules().stream()
                .filter(Module::isEnabled)
                .sorted((a, b) -> mc.textRenderer.getWidth(b.getName()) - mc.textRenderer.getWidth(a.getName()))
                .collect(Collectors.toList());

        int y = 4;
        int screenWidth = mc.getWindow().getScaledWidth();

        for (Module module : enabled) {
            String text = module.getName();
            int textWidth = mc.textRenderer.getWidth(text);
            int x = screenWidth - textWidth - 4;

            // Background
            context.fill(x - 2, y - 1, screenWidth, y + 10, 0x80000000);
            // Accent bar on right
            context.fill(screenWidth - 1, y - 1, screenWidth, y + 10, module.getCategory().getColor());
            // Text
            context.drawText(mc.textRenderer, text, x, y, module.getCategory().getColor(), true);

            y += 11;
        }
    }
}
