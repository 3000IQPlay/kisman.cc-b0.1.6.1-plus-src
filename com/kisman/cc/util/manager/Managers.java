//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.manager;

import com.kisman.cc.util.render.*;

public class Managers
{
    public static Managers instance;
    public FPSManager fpsManager;
    public PulseManager pulseManager;
    
    public Managers() {
        Managers.instance = this;
    }
    
    public String getRainbowCommandMessage() {
        final StringBuilder stringBuilder = new StringBuilder("[kisman.cc+]");
        stringBuilder.insert(0, "§+");
        stringBuilder.append("§r");
        return stringBuilder.toString();
    }
    
    public void init() {
        this.fpsManager = new FPSManager();
        this.pulseManager = new PulseManager();
    }
}
