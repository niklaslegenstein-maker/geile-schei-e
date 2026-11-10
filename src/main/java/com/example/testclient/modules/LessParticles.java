package com.example.testclient.modules;

import net.minecraft.client.option.SimpleOption;

public class LessParticles extends Module {
    private Object previous;

    public LessParticles() { super("LessParticles", "FPS"); }

    @Override protected void onEnable() {
        previous = mc.options.getParticles().getValue();
        setLast(mc.options.getParticles());
    }

    @Override protected void onDisable() {
        if (previous != null) restore(mc.options.getParticles());
    }

    // Setzt die letzte Stufe der Option (= MINIMAL), ohne den Klassennamen zu benoetigen
    private static <T extends Enum<T>> void setLast(SimpleOption<T> opt) {
        T[] all = opt.getValue().getDeclaringClass().getEnumConstants();
        opt.setValue(all[all.length - 1]);
    }

    @SuppressWarnings("unchecked")
    private <T> void restore(SimpleOption<T> opt) {
        opt.setValue((T) previous);
    }
}
