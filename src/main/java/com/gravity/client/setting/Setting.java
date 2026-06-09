package com.gravity.client.setting;

/**
 * Base class for all module settings.
 * @param <T> The value type of the setting.
 */
public abstract class Setting<T> {
    private final String name;
    private final String description;
    private T value;

    public Setting(String name, String description, T defaultValue) {
        this.name = name;
        this.description = description;
        this.value = defaultValue;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public T getValue() { return value; }

    public void setValue(T value) {
        this.value = value;
    }
}
