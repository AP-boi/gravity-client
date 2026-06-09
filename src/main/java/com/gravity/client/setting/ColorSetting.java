package com.gravity.client.setting;

import java.awt.Color;

public class ColorSetting extends Setting<Integer> {

    public ColorSetting(String name, String description, int defaultColor) {
        super(name, description, defaultColor);
    }

    public ColorSetting(String name, String description, int r, int g, int b) {
        super(name, description, new Color(r, g, b).getRGB());
    }

    public int getRed() { return (getValue() >> 16) & 0xFF; }
    public int getGreen() { return (getValue() >> 8) & 0xFF; }
    public int getBlue() { return getValue() & 0xFF; }
    public int getAlpha() { return (getValue() >> 24) & 0xFF; }

    public float[] getFloatComponents() {
        return new float[]{ getRed() / 255f, getGreen() / 255f, getBlue() / 255f, getAlpha() / 255f };
    }
}
