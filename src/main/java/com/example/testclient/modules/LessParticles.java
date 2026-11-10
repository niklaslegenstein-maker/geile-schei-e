package com.example.testclient.modules;

import net.minecraft.client.option.ParticlesMode;

public class LessParticles extends Module {
    private ParticlesMode previous;

    public LessParticles() { super("LessParticles", "FPS"); }

    @Override protected void onEnable() {
        previous = mc.options.getParticles().getValue();
        mc.options.getParticles().setValue(ParticlesMode.MINIMAL);
    }

    @Override protected void onDisable() {
        if (previous != null) mc.options.getParticles().setValue(previous);
    }
}
