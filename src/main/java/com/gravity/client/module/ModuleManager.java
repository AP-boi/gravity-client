package com.gravity.client.module;

import com.gravity.client.module.combat.*;
import com.gravity.client.module.movement.*;
import com.gravity.client.module.visual.*;
import com.gravity.client.module.utility.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages all modules — registration, lookup, tick dispatching.
 */
public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void init() {
        // ═══ COMBAT ═══
        register(new KillAura());
        register(new AimAssist());
        register(new Reach());
        register(new Velocity());
        register(new AntiKnockback());
        register(new AutoClicker());
        register(new Criticals());
        register(new Hitboxes());
        register(new AntiBot());
        register(new Surround());
        register(new AutoArmor());
        register(new BowAimbot());
        register(new TriggerBot());

        // ═══ MOVEMENT ═══
        register(new GravityMod());
        register(new SprintMod());
        register(new Speed());
        register(new Fly());
        register(new NoFall());
        register(new Step());
        register(new Jesus());
        register(new Scaffold());
        register(new LongJump());
        register(new FastLadder());
        register(new AntiVoid());
        register(new IceSpeed());
        register(new Parkour());
        register(new Glide());
        register(new HighJump());

        // ═══ VISUAL ═══
        register(new Fullbright());
        register(new Xray());
        register(new ESP());
        register(new Tracers());
        register(new ChestESP());
        register(new NameTags());
        register(new Noclip());
        register(new Ambience());
        register(new AntiBlind());
        register(new FreeCam());
        register(new Zoom());

        // ═══ UTILITY ═══
        register(new AutoTotem());
        register(new AutoPotion());
        register(new AntiAFK());
        register(new FastPlace());
        register(new FastBreak());
        register(new ChestStealer());
        register(new AutoFish());
        register(new TimerHack());
        register(new InventoryMove());
        register(new NoRotate());
        register(new AntiHunger());
        register(new FakeLag());
        register(new AutoRespawn());
        register(new NoSwing());
        register(new AutoSprint());
        register(new NoSlowdown());
    }

    private void register(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getModulesByCategory(Category category) {
        return modules.stream()
                .filter(m -> m.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Module getModuleByName(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) modules.stream()
                .filter(m -> m.getClass() == clazz)
                .findFirst()
                .orElse(null);
    }

    /** Call every client tick to dispatch onTick to enabled modules. */
    public void onTick() {
        for (Module module : modules) {
            if (module.isEnabled()) {
                try {
                    module.onTick();
                } catch (Exception e) {
                    System.err.println("[Gravity] Error in " + module.getName() + ".onTick(): " + e.getMessage());
                }
            }
        }
    }

    /** Call every render frame to dispatch onRender to enabled modules. */
    public void onRender(float tickDelta) {
        for (Module module : modules) {
            if (module.isEnabled()) {
                try {
                    module.onRender(tickDelta);
                } catch (Exception e) {
                    // Silent fail to avoid render crashes
                }
            }
        }
    }

    /** Handle keybind press — toggle matching module. */
    public void onKeyPress(int keyCode) {
        for (Module module : modules) {
            if (module.getKeyBind() == keyCode) {
                module.toggle();
            }
        }
    }
}
