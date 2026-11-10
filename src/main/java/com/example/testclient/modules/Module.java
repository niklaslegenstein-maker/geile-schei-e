package com.example.testclient.modules;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    public final String name;
    public final String category;
    private boolean enabled;
    protected final MinecraftClient mc = MinecraftClient.getInstance();

    protected Module(String name, String category) { this.name = name; this.category = category; }

    public boolean isEnabled() { return enabled; }
    public void toggle() { setEnabled(!enabled); }

    public void setEnabled(boolean value) {
        if (enabled == value) return;
        enabled = value;
        if (value) onEnable(); else onDisable();
    }

    protected void onEnable() {}
    protected void onDisable() {}
    public void onTick() {}
}
