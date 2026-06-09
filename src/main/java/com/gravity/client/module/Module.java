package com.gravity.client.module;

import com.gravity.client.GravityClient;
import com.gravity.client.setting.Setting;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all AntiGravity modules.
 * Extend this class and register settings in the constructor.
 */
public abstract class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    private final String name;
    private final String description;
    private final Category category;
    private boolean enabled;
    private int keyBind; // GLFW key code, -1 = unbound
    private final boolean signature;
    private final List<Setting<?>> settings = new ArrayList<>();

    public Module(String name, String description, Category category, int keyBind) {
        this(name, description, category, keyBind, false);
    }

    public Module(String name, String description, Category category, int keyBind, boolean signature) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keyBind = keyBind;
        this.signature = signature;
        this.enabled = false;
    }

    // ─── Setting Registration ─────────────────────────────────
    protected <T extends Setting<?>> T addSetting(T setting) {
        settings.add(setting);
        return setting;
    }

    public List<Setting<?>> getSettings() { return settings; }

    // ─── Getters / Setters ────────────────────────────────────
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Category getCategory() { return category; }
    public boolean isEnabled() { return enabled; }
    public int getKeyBind() { return keyBind; }
    public void setKeyBind(int keyBind) { this.keyBind = keyBind; }
    public boolean isSignature() { return signature; }

    public String getKeyBindName() {
        if (keyBind == -1) return "NONE";
        String name = GLFW.glfwGetKeyName(keyBind, 0);
        return name != null ? name.toUpperCase() : "KEY_" + keyBind;
    }

    // ─── Toggle ───────────────────────────────────────────────
    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
        // Save config on toggle
        GravityClient.getInstance().getConfigManager().save();
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            toggle();
        }
    }

    // ─── Lifecycle Hooks ──────────────────────────────────────
    /** Called when the module is enabled. */
    public void onEnable() {}

    /** Called when the module is disabled. */
    public void onDisable() {}

    /** Called every client tick while enabled. */
    public void onTick() {}

    /** Called every render frame while enabled (for visual modules). */
    public void onRender(float tickDelta) {}

    /** Called to render HUD elements while enabled. */
    public void onHudRender(net.minecraft.client.gui.DrawContext context, float tickDelta) {}

    // ─── Utility ──────────────────────────────────────────────
    protected boolean isInGame() {
        return mc.player != null && mc.world != null;
    }
}
