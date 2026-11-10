package com.example.testclient.modules;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static final List<Module> MODULES = new ArrayList<>();

    public static void init() {
        MODULES.add(new KillAura());
        MODULES.add(new Fly());
        MODULES.add(new Sprint());
        MODULES.add(new Fullbright());
        MODULES.add(new LessParticles());
    }

    public static void tick() {
        for (Module m : MODULES) if (m.isEnabled()) m.onTick();
    }
}
