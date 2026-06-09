package com.gravity.client.config;

import com.gravity.client.GravityClient;
import com.gravity.client.module.Module;
import com.gravity.client.setting.*;
import com.google.gson.*;

import java.io.*;
import java.nio.file.*;

/**
 * Saves and loads module configuration to/from JSON.
 * File location: .minecraft/antigravity/config.json
 */
public class ConfigManager {
    private final Path configDir;
    private final Path configFile;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager() {
        configDir = Path.of("gravity");
        configFile = configDir.resolve("config.json");
    }

    public void save() {
        try {
            Files.createDirectories(configDir);

            JsonObject root = new JsonObject();
            JsonObject modulesObj = new JsonObject();

            for (Module module : GravityClient.getInstance().getModuleManager().getModules()) {
                JsonObject modObj = new JsonObject();
                modObj.addProperty("enabled", module.isEnabled());
                modObj.addProperty("keyBind", module.getKeyBind());

                JsonObject settingsObj = new JsonObject();
                for (Setting<?> setting : module.getSettings()) {
                    if (setting instanceof BooleanSetting bs) {
                        settingsObj.addProperty(setting.getName(), bs.getValue());
                    } else if (setting instanceof NumberSetting ns) {
                        settingsObj.addProperty(setting.getName(), ns.getValue());
                    } else if (setting instanceof ModeSetting ms) {
                        settingsObj.addProperty(setting.getName(), ms.getValue());
                    } else if (setting instanceof ColorSetting cs) {
                        settingsObj.addProperty(setting.getName(), cs.getValue());
                    }
                }
                modObj.add("settings", settingsObj);
                modulesObj.add(module.getName(), modObj);
            }

            root.add("modules", modulesObj);

            Files.writeString(configFile, gson.toJson(root));
        } catch (IOException e) {
            System.err.println("[AntiGravity] Failed to save config: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void load() {
        if (!Files.exists(configFile)) return;

        try {
            String json = Files.readString(configFile);
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();

            if (!root.has("modules")) return;
            JsonObject modulesObj = root.getAsJsonObject("modules");

            for (Module module : GravityClient.getInstance().getModuleManager().getModules()) {
                if (!modulesObj.has(module.getName())) continue;
                JsonObject modObj = modulesObj.getAsJsonObject(module.getName());

                if (modObj.has("keyBind")) {
                    module.setKeyBind(modObj.get("keyBind").getAsInt());
                }

                if (modObj.has("settings")) {
                    JsonObject settingsObj = modObj.getAsJsonObject("settings");
                    for (Setting<?> setting : module.getSettings()) {
                        if (!settingsObj.has(setting.getName())) continue;
                        JsonElement elem = settingsObj.get(setting.getName());

                        if (setting instanceof BooleanSetting bs) {
                            bs.setValue(elem.getAsBoolean());
                        } else if (setting instanceof NumberSetting ns) {
                            ns.setValue(elem.getAsDouble());
                        } else if (setting instanceof ModeSetting ms) {
                            ms.setValue(elem.getAsString());
                        } else if (setting instanceof ColorSetting cs) {
                            cs.setValue(elem.getAsInt());
                        }
                    }
                }

                // Enable after loading settings
                if (modObj.has("enabled") && modObj.get("enabled").getAsBoolean()) {
                    module.setEnabled(true);
                }
            }
        } catch (Exception e) {
            System.err.println("[AntiGravity] Failed to load config: " + e.getMessage());
        }
    }
}
