package com.gravity.client.module;

public enum Category {
    COMBAT("Combat", 0xFFf04f4f),
    MOVEMENT("Movement", 0xFF5b8af5),
    VISUAL("Visual", 0xFF3dd68c),
    UTILITY("Utility", 0xFF7c5cfc);

    private final String name;
    private final int color;

    Category(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() { return name; }
    public int getColor() { return color; }
}
