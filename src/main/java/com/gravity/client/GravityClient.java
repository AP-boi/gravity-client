package com.gravity.client;

import com.gravity.client.config.ConfigManager;
import com.gravity.client.gui.ClickGuiScreen;
import com.gravity.client.hud.HudManager;
import com.gravity.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gravity Client v1.0
 * A premium Minecraft utility client mod for Fabric.
 */
public class GravityClient implements ClientModInitializer {
    public static final String MOD_ID = "gravity";
    public static final String MOD_NAME = "Gravity Client";
    public static final String MOD_VERSION = "1.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    private static GravityClient INSTANCE;

    private ModuleManager moduleManager;
    private ConfigManager configManager;
    private HudManager hudManager;
    private KeyBinding guiKeyBind;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        LOGGER.info("⚡ {} v{} initializing...", MOD_NAME, MOD_VERSION);

        // Initialize managers
        moduleManager = new ModuleManager();
        configManager = new ConfigManager();
        hudManager = new HudManager();

        // Register all modules
        moduleManager.init();

        // Load saved config
        configManager.load();

        // Register ClickGUI keybind (Right Shift)
        guiKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Open Gravity GUI",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "Gravity Client"
        ));

        // Register tick listener
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Check GUI keybind
            while (guiKeyBind.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }

            // Dispatch tick to enabled modules
            moduleManager.onTick();
        });

        LOGGER.info("⚡ {} v{} loaded! Press Right Shift to open GUI.", MOD_NAME, MOD_VERSION);
    }

    public static GravityClient getInstance() {
        return INSTANCE;
    }

    public ModuleManager getModuleManager() { return moduleManager; }
    public ConfigManager getConfigManager() { return configManager; }
    public HudManager getHudManager() { return hudManager; }
}
