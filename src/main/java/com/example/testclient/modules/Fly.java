package com.example.testclient.modules;

public class Fly extends Module {
    public Fly() { super("Fly", "Movement"); }

    @Override public void onTick() {
        if (mc.player == null) return;
        mc.player.getAbilities().allowFlying = true;
        mc.player.getAbilities().flying = true;
    }

    @Override protected void onDisable() {
        if (mc.player == null) return;
        if (!mc.player.isCreative() && !mc.player.isSpectator()) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
    }
}
