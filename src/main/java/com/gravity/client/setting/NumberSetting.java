package com.gravity.client.setting;

public class NumberSetting extends Setting<Double> {
    private final double min;
    private final double max;
    private final double step;

    public NumberSetting(String name, String description, double defaultValue, double min, double max, double step) {
        super(name, description, defaultValue);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getStep() { return step; }

    @Override
    public void setValue(Double value) {
        // Clamp and snap to step
        value = Math.max(min, Math.min(max, value));
        value = Math.round(value / step) * step;
        super.setValue(value);
    }

    public float getFloatValue() {
        return getValue().floatValue();
    }

    public int getIntValue() {
        return getValue().intValue();
    }
}
