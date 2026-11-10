package com.example.testclient.modules;

public class Sprint extends Module {
    public Sprint() { super("Sprint", "Movement"); }

    @Override public void onTick() {
        if (mc.player == null) return;
        if (mc.options.forwardKey.isPressed() && !mc.player.isSneaking()) mc.player.setSprinting(true);
    }
}
