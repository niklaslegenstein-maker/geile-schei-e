package com.example.testclient.gui;

import com.example.testclient.modules.Module;
import com.example.testclient.modules.ModuleManager;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClickGui extends Screen {
    private static final int W = 120, H = 22, GAP = 4;

    public ClickGui() { super(Text.literal("TestClient")); }

    private int startX() { return width / 2 - W / 2; }
    private int startY() { return height / 2 - (ModuleManager.MODULES.size() * (H + GAP)) / 2; }

    @Override public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        int y = startY();
        for (Module m : ModuleManager.MODULES) {
            boolean hover = mouseX >= startX() && mouseX <= startX() + W && mouseY >= y && mouseY <= y + H;
            int bg = m.isEnabled() ? (hover ? 0xFF3FA66B : 0xFF2E8B57) : (hover ? 0xFF3A3A3A : 0xFF262626);
            Draw.roundedRect(ctx, startX(), y, W, H, 6, bg);
            ctx.drawCenteredTextWithShadow(textRenderer, m.name + " [" + m.category + "]", startX() + W / 2, y + 7, 0xFFFFFFFF);
            y += H + GAP;
        }
    }

    @Override public boolean mouseClicked(Click click, boolean doubled) {
        if (click.button() == 0) {
            int y = startY();
            for (Module m : ModuleManager.MODULES) {
                if (click.x() >= startX() && click.x() <= startX() + W && click.y() >= y && click.y() <= y + H) {
                    m.toggle();
                    return true;
                }
                y += H + GAP;
            }
        }
        return super.mouseClicked(click, doubled);
    }

    @Override public boolean shouldPause() { return false; }
}
