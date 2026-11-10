package com.example.testclient.modules;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class KillAura extends Module {
    public double range = 4.0;
    public boolean targetPlayers = false;

    public KillAura() { super("KillAura", "Combat"); }

    @Override public void onTick() {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
        if (mc.player.getAttackCooldownProgress(0.5f) < 1.0f) return;

        LivingEntity best = null;
        double bestDist = range * range;
        for (Entity e : mc.world.getEntities()) {
            if (!(e instanceof LivingEntity le) || e == mc.player || !le.isAlive()) continue;
            if (e instanceof PlayerEntity && !targetPlayers) continue;
            double d = mc.player.squaredDistanceTo(e);
            if (d < bestDist) { bestDist = d; best = le; }
        }
        if (best != null) {
            mc.interactionManager.attackEntity(mc.player, best);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
