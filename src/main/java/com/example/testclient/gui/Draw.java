package com.example.testclient.gui;

import net.minecraft.client.gui.DrawContext;

public class Draw {
    /** Abgerundetes Rechteck ueber Scanlines, funktioniert ohne eigene Shader. */
    public static void roundedRect(DrawContext ctx, int x, int y, int w, int h, int r, int color) {
        r = Math.min(r, Math.min(w, h) / 2);
        for (int i = 0; i < h; i++) {
            int d = i < r ? i : (i >= h - r ? h - 1 - i : -1);
            int inset = 0;
            if (d >= 0) {
                double dy = r - d - 0.5;
                inset = r - (int) Math.round(Math.sqrt(r * r - dy * dy));
            }
            ctx.fill(x + inset, y + i, x + w - inset, y + i + 1, color);
        }
    }
}
