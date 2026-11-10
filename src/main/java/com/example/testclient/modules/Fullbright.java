package com.example.testclient.modules;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Fullbright extends Module {
    public Fullbright() { super("Fullbright", "Visuals"); }

    @Override public void onTick() {
        if (mc.player == null) return;
        mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1000, 0, false, false, false));
    }

    @Override protected void onDisable() {
        if (mc.player != null) mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
}
