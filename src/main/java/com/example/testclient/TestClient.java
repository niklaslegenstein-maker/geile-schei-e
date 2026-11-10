package com.example.testclient;

import com.example.testclient.gui.ClickGui;
import com.example.testclient.gui.Draw;
import com.example.testclient.modules.Module;
import com.example.testclient.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class TestClient implements ClientModInitializer {
    private static KeyBinding openGui;

    @Override public void onInitializeClient() {
        ModuleManager.init();

        // Falls die Category-API abweicht: KeyBinding.Category.MISC bzw. KeyBinding.Category.create(Identifier) anpassen
        openGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.testclient.gui", GLFW.GLFW_KEY_RIGHT_SHIFT, KeyBinding.Category.MISC));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGui.wasPressed()) client.setScreen(new ClickGui());
            ModuleManager.tick();
        });

        // Aktive Module als Liste oben rechts
        HudElementRegistry.addLast(Identifier.of("testclient", "hud"), (ctx, tick) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            int y = 4;
            for (Module m : ModuleManager.MODULES) {
                if (!m.isEnabled()) continue;
                int w = mc.textRenderer.getWidth(m.name) + 8;
                int x = ctx.getScaledWindowWidth() - w - 4;
                Draw.roundedRect(ctx, x, y, w, 12, 4, 0xAA000000);
                ctx.drawTextWithShadow(mc.textRenderer, m.name, x + 4, y + 2, 0xFF55FF99);
                y += 14;
            }
        });
    }
}
