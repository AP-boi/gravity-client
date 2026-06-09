package com.gravity.client.setting;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting<String> {
    private final List<String> modes;

    public ModeSetting(String name, String description, String defaultMode, String... modes) {
        super(name, description, defaultMode);
        this.modes = Arrays.asList(modes);
    }

    public List<String> getModes() { return modes; }

    public void cycle() {
        int index = modes.indexOf(getValue());
        index = (index + 1) % modes.size();
        setValue(modes.get(index));
    }

    public int getIndex() {
        return modes.indexOf(getValue());
    }

    @Override
    public void setValue(String value) {
        if (modes.contains(value)) {
            super.setValue(value);
        }
    }
}
